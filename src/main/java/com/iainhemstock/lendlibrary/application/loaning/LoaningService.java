package com.iainhemstock.lendlibrary.application.loaning;

import com.iainhemstock.lendlibrary.application.loaning.dto.LoanDTO;

import java.util.Collections;
import java.util.List;

public interface LoaningService {

    public static final List<LoanDTO> NO_OUTSTANDING_LOANS = Collections.emptyList();

    String loanBookToMember(String bookId, String memberId);

    void finaliseBookLoan(String loanId);

    List<LoanDTO> fetchOutstandingLoansForMember(String memberId);

    List<LoanDTO> fetchLoanHistoryForMember(String memberId);

}
