package com.iainhemstock.lendlibrary.application.loaning.impls.assembler;

import com.iainhemstock.lendlibrary.application.loaning.dto.LoanDTO;
import com.iainhemstock.lendlibrary.domain.model.loan.Loan;

import java.util.ArrayList;
import java.util.List;

public final class LoanDTOAssembler {
    public List<LoanDTO> toDTOList(List<Loan> loans) {
        List<LoanDTO> loanDTOs = new ArrayList<>();

        for (Loan loan : loans) {
            loanDTOs.add(new LoanDTO(
                    loan.getId().toString(),
                    loan.getBookId().toString(),
                    loan.getMemberId().toString(),
                    loan.getStartDate(),
                    loan.getExpectedReturnDate(),
                    loan.getActualReturnDate()));
        }
        return loanDTOs;
    }
}
