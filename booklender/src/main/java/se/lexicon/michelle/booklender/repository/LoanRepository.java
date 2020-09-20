package se.lexicon.michelle.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.michelle.booklender.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findAll();

    List<Loan> findAllByLoanTaker_userId(int userId);

    List<Loan> findAllByBook_bookId(int bookId);

    List<Loan> findAllByTerminated(boolean terminated);

}
