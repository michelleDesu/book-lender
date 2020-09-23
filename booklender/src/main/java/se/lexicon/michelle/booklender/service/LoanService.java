package se.lexicon.michelle.booklender.service;

import se.lexicon.michelle.booklender.dto.LoanDto;

import java.util.List;

public interface LoanService {
    /**
     * findById
     * @param loanId long
     * @return LoanDto
     */
    LoanDto findById(long loanId);

    /**
     *
     * @param bookId int
     * @return List<LoanDto>
     */
    List<LoanDto> findByBookId(int bookId);

    /**
     * findByUserId
     * @param userId int
     * @return List<LoanDto>
     */
    List<LoanDto> findByUserId(int userId);

    /**
     * findByTerminated
     * @return List<LoanDto>
     */
    List<LoanDto> findByTerminated(boolean terminated);

    /**
     * find All
     * @return List<LoanDto>
     */
    List<LoanDto> findAll();

    /**
     * create a loan
     * @param loanDto LoanDto
     * @return LoanDto
     */
    LoanDto create(LoanDto loanDto);

    /**
     * update an existing loan
     * @param loanDto LoanDto
     * @return LoanDto
     */
    LoanDto update(LoanDto loanDto);

    /**
     * delete an existing loan
     * @param loanId long
     * @return boolean
     */
    boolean delete(long loanId);

}
