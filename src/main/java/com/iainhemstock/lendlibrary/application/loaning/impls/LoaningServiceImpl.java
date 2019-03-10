package com.iainhemstock.lendlibrary.application.loaning.impls;

import com.iainhemstock.lendlibrary.application.loaning.LoaningService;
import com.iainhemstock.lendlibrary.application.loaning.dto.LoanDTO;
import com.iainhemstock.lendlibrary.application.loaning.impls.assembler.LoanDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.loan.Loan;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanId;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanRepository;
import com.iainhemstock.lendlibrary.domain.model.loan.RentalPeriod;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.service.Clock;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public final class LoaningServiceImpl implements LoaningService {

    private LoanRepository loanRepository;
    private Clock clock;

    public LoaningServiceImpl(LoanRepository loanRepository, Clock clock, LoanDTOAssembler loanDTOAssembler) {
        this(loanRepository, clock);
    }

    public LoaningServiceImpl(LoanRepository loanRepository, Clock clock) {
        this.loanRepository = loanRepository;
        this.clock = clock;
    }

    @Override
    public String loanBookToMember(String bookId, String memberId) {
        requireNonNull(bookId, "Book id is required");
        requireNonNull(memberId, "Member id is required");

        Loan loan = new Loan(
                loanRepository.nextId(),
                new BookId(bookId),
                new MemberId(memberId),
                new RentalPeriod(clock.now()));

        loanRepository.add(loan);

        return loan.getId().toString();
    }

    @Override
    public void finaliseBookLoan(String loanId) {
        requireNonNull(loanId, "Loan id is required");
        Loan loan = loanRepository.getById(new LoanId(loanId));
        loan.finalise(clock.now());
        loanRepository.update(loan);
    }

    @Override
    public List<LoanDTO> fetchOutstandingLoansForMember(String memberId) {
        requireNonNull(memberId, "Member id is required");
        List<Loan> outstandingLoans = loanRepository.findLoansByMember(new MemberId(memberId)).stream()
                .filter(loan -> loan.getActualReturnDate() == null)
                .collect(Collectors.toList());

        LoanDTOAssembler assembler = new LoanDTOAssembler();
        return assembler.toDTOList(outstandingLoans);
    }

    @Override
    public List<LoanDTO> fetchLoanHistoryForMember(String memberId) {
        requireNonNull(memberId, "Member id is required");
        List<Loan> finalizedLoans = loanRepository.findLoansByMember(new MemberId(memberId)).stream()
                .filter(loan -> loan.getActualReturnDate() != null)
                .collect(Collectors.toList());

        LoanDTOAssembler assembler = new LoanDTOAssembler();
        return assembler.toDTOList(finalizedLoans);
    }
}
