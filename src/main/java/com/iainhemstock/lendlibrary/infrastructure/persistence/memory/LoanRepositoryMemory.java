package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.domain.model.loan.*;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoanRepositoryMemory extends LoanRepository {
    private List<Loan> loans = new ArrayList<>();

    @Override
    public boolean contains(Loan loan) {
        return loans.contains(loan);
    }

    @Override
    public void add(Loan loan) {
        Loan copy = new Loan(loan);
        loans.add(copy);
    }

    @Override
    public void remove(Loan loan) {
        loans.remove(loan);
    }

    @Override
    public List<Loan> getAll() {
        return loans;
    }

    @Override
    public void update(Loan loan) {
        int index = IntStream.range(0, loans.size())
                .filter(i -> loans.get(i).getId().equals(loan.getId()))
                .findFirst()
                .orElseThrow(loanNotFoundException(loan.getId()));

        loans.set(index, loan);

    }

    @Override
    public List<Loan> findLoansByMember(MemberId memberId) {
        return loans.stream()
                .filter(loan -> loan.getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    @Override
    public Loan getById(final LoanId loanId) {
        return loans.stream()
                .filter(loan -> loan.getId().equals(loanId))
                .findFirst()
                .orElseThrow(loanNotFoundException(loanId));
    }

    private Supplier<LoanNotFoundException> loanNotFoundException(LoanId loanId) {
        return () -> new LoanNotFoundException(
                "Loan with id <" + loanId.toString() + "> could not be found");
    }
}
