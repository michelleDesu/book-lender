package se.lexicon.michelle.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.michelle.booklender.dto.LoanDto;
import se.lexicon.michelle.booklender.entity.Loan;
import se.lexicon.michelle.booklender.repository.BookRepository;
import se.lexicon.michelle.booklender.repository.LibraryUserRepository;
import se.lexicon.michelle.booklender.repository.LoanRepository;

import java.util.ArrayList;
import java.util.List;

public class LoanServiceImpl implements LoanService{
    LoanRepository loanRepository;
    LibraryUserRepository userRepository;
    BookRepository bookRepository;

    /**
     * constructor
     * @param loanRepository LoanRepository
     * @param userRepository LibraryUserRepository
     * @param bookRepository BookRepository
     */
    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, LibraryUserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * converts a loan to a ToLoanDto
     * @param loan Loan
     * @return LoanDto
     */
    protected LoanDto convertToLoanDto(Loan loan){
        if(loan == null){
            return null;
        }
        return new LoanDto(
                loan.getLoanID(),
                loan.getLoanTaker(),
                loan.getBook(),
                loan.getLoanDate(),
                loan.isTerminated()
        );

    }

    /**
     * convert a list of loans To List Of LoanDto
     * @param loanList List<Loan>
     * @return List<LoanDto>
     */
    protected List<LoanDto> convertToListOfLoanDto(List<Loan> loanList){
        if(loanList == null){
            return null;
        }
        List<LoanDto> loanDtoList = new ArrayList<>();

        for (Loan loan : loanList){
            LoanDto toAdd = convertToLoanDto(loan);
            loanDtoList.add(toAdd);
        }

        return loanDtoList;
    }

    /**
     * find By Id
     * @param loanId long
     * @return LoanDto
     */
    @Override
    public LoanDto findById(long loanId) {
        Loan loan = loanRepository.findByLoanID(loanId);

        return convertToLoanDto(loan);
    }

    /**
     * find By Book Id
     * @param bookId int
     * @return List<LoanDto>
     */
    @Override
    public List<LoanDto> findByBookId(int bookId) {
        List<Loan> loans = loanRepository.findAllByBook_bookId(bookId);
        return convertToListOfLoanDto(loans);
    }

    /**
     * find By User Id
     * @param userId int
     * @return  List<LoanDto>
     */
    @Override
    public List<LoanDto> findByUserId(int userId) {
        List<Loan> loans = loanRepository.findAllByLoanTaker_userId(userId);
        return convertToListOfLoanDto(loans);
    }

    /**
     * find By Terminated
     * @return List<LoanDto>
     */
    @Override
    public List<LoanDto> findByTerminated() {
        List<Loan> loans = loanRepository.findAllByTerminated(true);
        return convertToListOfLoanDto(loans);
    }

    /**
     * find All
     * @return List<LoanDto>
     */
    @Override
    public List<LoanDto> findAll() {
        List<Loan> loans = loanRepository.findAll();
        return convertToListOfLoanDto(loans);
    }

    /**
     * create a loan
     * @param loanDto LoanDto
     * @return LoanDto
     */
    @Override
    @Transactional
    public LoanDto create(LoanDto loanDto) {
        //TODO insert exception here
        if(loanDto == null){

        }
        Loan loan = new Loan(
                loanDto.getLoanID(),
                loanDto.getLoanTaker(),
                loanDto.getBook(),
                loanDto.getLoanDate(),
                loanDto.isTerminated()
        );
        return convertToLoanDto(loanRepository.save(loan));
    }

    /**
     * update an existing loan
     * @param loanDto LoanDto
     * @return LoanDto
     */
    @Override
    @Transactional
    public LoanDto update(LoanDto loanDto) {
        //TODO insert exception here
        if(loanDto == null){

        }
        Loan updatedLoan = loanRepository.findByLoanID(loanDto.getLoanID());

        updatedLoan.setLoanTaker(loanDto.getLoanTaker());
        updatedLoan.setBook(loanDto.getBook());
        updatedLoan.setLoanDate(loanDto.getLoanDate());
        updatedLoan.setTerminated(loanDto.isTerminated());

        updatedLoan = loanRepository.save(updatedLoan);
        return convertToLoanDto(updatedLoan);
    }

    /**
     * delete an existing loan
     * @param loanId long
     * @return boolean
     */
    @Override
    @Transactional
    public boolean delete(long loanId) {
        //TODO insert exception here

        boolean isDeleted = false;
        if (loanRepository.existsById(loanId)){
            loanRepository.delete(loanRepository.findByLoanID(loanId));
            isDeleted = true;
        }
        return isDeleted;
    }
}
