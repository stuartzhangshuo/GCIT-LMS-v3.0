/**
 * @Author Shuo Zhang <shuo_zhang@gcitsolutions.com>
 * @Date Sep 28, 2017
 */
package com.gcit.libmgmtsys.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.libmgmtsys.dao.*;
import com.gcit.libmgmtsys.entity.*;

public class BorrowerService {
	// =================================================================================================================
	// SPRING DAOs
	// =================================================================================================================

	@Autowired
	BookDAO bookDao;

	@Autowired
	BookCopiesDAO bookCopiesDao;

	@Autowired
	BookLoansDAO bookLoansDao;

	@Autowired
	LibraryBranchDAO libraryBranchDao;

	@Autowired
	BorrowerDAO borrowerDao;
	
	
	// =================================================================================================================
	// TRANSACTIONS
	// =================================================================================================================
	
	@Transactional
	//insert or update (check-out or check-in) a book loan based on if dateOut attribute is NULL
	public void updateBookLoan(BookLoans bookLoan) throws SQLException {
		Book 	   book 	   = bookDao.readOneBook(bookLoan.getBook().getBookId());
		BookCopies bookCopy    = new BookCopies();
		Integer    noOfCopies  = book.getBranchCopies().get(bookLoan.getLibraryBranch().getBranchId());
		bookCopy.setBook(book);
		bookCopy.setLibraryBranch(bookLoan.getLibraryBranch());
		if (bookLoan.getDateOut() == null) {
			bookCopy.setNoOfCopies(noOfCopies - 1);
			bookLoansDao.addBookLoan(bookLoan);
			bookCopiesDao.updateBookCopies(bookCopy);
		} else {
			bookCopy.setNoOfCopies(noOfCopies + 1);
			bookLoansDao.updateBookLoan(bookLoan);
			bookCopiesDao.updateBookCopies(bookCopy);
		}
	}
	
	
	// =================================================================================================================
	// EXTRACTIONS
	// =================================================================================================================
	
	//read ALL books information
	public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException {
		return bookDao.readBooks(searchString, pageNo);
	}

	//read ALL library branch information
	public List<LibraryBranch> readLibraryBranches(String searchString, Integer pageNo) throws SQLException {
		return libraryBranchDao.readLibraryBranches(searchString, pageNo);
	}
	
	//read ONE borrower information given cardNo
	public Borrower readOneBorrower(Integer cardNo) throws SQLException {
		return borrowerDao.readOneBorrower(cardNo);
	}
	
	//read ONE book loan information given cardNo and branchId
	public List<BookLoans> readOneBookLoan(Integer cardNo, Integer branchId) throws SQLException {
		return bookLoansDao.readOneBookLoan(cardNo, branchId);
	}
	
}
