/**
 * @Author Shuo Zhang <shuo_zhang@gcitsolutions.com>
 * @Date Sep 28, 2017
 */
package com.gcit.libmgmtsys.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.libmgmtsys.dao.*;
import com.gcit.libmgmtsys.entity.*;

public class LibrarianService {
	// =================================================================================================================
	// SPRING DAOs
	// =================================================================================================================
	
	@Autowired
	LibraryBranchDAO libraryBranchDao;

	@Autowired
	BookDAO bookDao;

	@Autowired
	BookCopiesDAO bookCopiesDao;
	
	
	// =================================================================================================================
	// TRANSACTIONS
	// =================================================================================================================
	
	@Transactional
	//update one library branch's information
	public void updateBranchInfo(LibraryBranch libraryBranch) throws SQLException {
		libraryBranchDao.updateLibraryBranch(libraryBranch);
	}
	
	@Transactional
	//insert a book copy record into database
	public void insertBookCopies(BookCopies bookCopies) throws SQLException {
		bookCopiesDao.addBookCopies(bookCopies);
	}
	
	@Transactional
	//update an existing book copy's record
	public void updateBookCopies(BookCopies bookCopies) throws SQLException {
		bookCopiesDao.updateBookCopies(bookCopies);
	}
	

	// =================================================================================================================
	// EXTRACTIONS
	// =================================================================================================================
	
	//read one book information given book id
	public Book readOneBook(Integer bookId) throws SQLException {
		return bookDao.readOneBook(bookId);
	}

	//read all books by page number
	public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException {
		return bookDao.readBooks(searchString, pageNo);
	}
	
	//read one library branch information given branch id
	public LibraryBranch readOneBranch(Integer branchId) throws SQLException {
		return libraryBranchDao.readOneBranch(branchId);
	}
	
	//read all library branches by page number
	public List<LibraryBranch> readLibraryBranches(String searchString, Integer pageNo) throws SQLException {
		return libraryBranchDao.readLibraryBranches(searchString, pageNo);
	}
	
	//check if a branch name already exists in the database
	public Boolean checkBranchName(String branchName) throws SQLException {
		return libraryBranchDao.checkBranchByName(branchName) != null;
	}

	//check if a book copy pair (bookId, branchId) exist in the databse
	public Boolean checkBookCopy(BookCopies bookCopy) throws SQLException {
		List<BookCopies> bookCopies = bookCopiesDao.checkBookCopies(bookCopy);
		if (bookCopies == null || bookCopies.size() == 0) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
}
