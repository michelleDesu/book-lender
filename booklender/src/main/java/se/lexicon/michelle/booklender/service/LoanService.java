package se.lexicon.michelle.booklender.service;

import se.lexicon.michelle.booklender.dto.LoanDto;

import java.util.List;

public interface LoanService {
    LoanDto findById(long loanId);
    List<LoanDto> findByBookId(int bookId);
    List<LoanDto> findByUserId(int userId);
    List<LoanDto> findByTerminated();
    List<LoanDto> findAll();
    LoanDto create(LoanDto loanDto);
    LoanDto update(LoanDto loanDto);
    boolean delete(long loanId);

}
