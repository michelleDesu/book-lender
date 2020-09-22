package se.lexicon.michelle.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.michelle.booklender.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    /**
     *
     * @return  List<Loan>
     */
    List<Loan> findAll();

    /**
     *
     * @param loanId long
     * @return Loan
     */
    Loan findByLoanID(long loanId);

    /**
     *
     * @param userId int
     * @return  List<Loan>
     */
    List<Loan> findAllByLoanTaker_userId(int userId);

    /**
     *
     * @param bookId int
     * @return  List<Loan>
     */
    List<Loan> findAllByBook_bookId(int bookId);

    /**
     *
     * @param terminated boolean
     * @return  List<Loan>
     */
    List<Loan> findAllByTerminated(boolean terminated);

}
