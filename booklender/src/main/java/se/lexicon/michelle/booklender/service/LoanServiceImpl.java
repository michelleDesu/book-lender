package se.lexicon.michelle.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, LibraryUserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    protected LoanDto convertToLoanDto(Loan loan){
        return new LoanDto(
                loan.getLoanID(),
                loan.getLoanTaker(),
                loan.getBook(),
                loan.getLoanDate(),
                loan.isTerminated()
        );

    }

    protected List<LoanDto> convertToListOfLoanDto(List<Loan> loanList){
        List<LoanDto> loanDtoList = new ArrayList<>();

        for (Loan loan : loanList){
            LoanDto toAdd = convertToLoanDto(loan);
            loanDtoList.add(toAdd);
        }

        return loanDtoList;
    }

    @Override
    public LoanDto findById(long loanId) {
        Loan loan = loanRepository.findByLoanID(loanId);

        return convertToLoanDto(loan);
    }

    @Override
    public List<LoanDto> findByBookId(int bookId) {
        List<Loan> loans = loanRepository.findAllByBook_bookId(bookId);
        return convertToListOfLoanDto(loans);
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        List<Loan> loans = loanRepository.findAllByLoanTaker_userId(userId);
        return convertToListOfLoanDto(loans);
    }

    @Override
    public List<LoanDto> findByTerminated() {
        List<Loan> loans = loanRepository.findAllByTerminated(true);
        return convertToListOfLoanDto(loans);
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> loans = loanRepository.findAll();
        return convertToListOfLoanDto(loans);
    }

    @Override
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

    @Override
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

    @Override
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
