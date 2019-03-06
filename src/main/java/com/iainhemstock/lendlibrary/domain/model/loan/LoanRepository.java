package com.iainhemstock.lendlibrary.domain.model.loan;

import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.shared.Repository;

import java.util.List;

public abstract class LoanRepository extends Repository<Loan> {

    public LoanId nextId() {
        return new LoanId(super.generateUniqueId());
    }

    public abstract List<Loan> findLoansByMember(MemberId memberId);

    public abstract Loan getById(final LoanId loanId);
}
