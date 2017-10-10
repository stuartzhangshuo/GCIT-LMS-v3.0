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

public class AdminService {
	//private Utilities util = new Utilities();
	
	@Autowired
	AuthorDAO authorDao;
	
	@Autowired
	BookDAO bookDao;
	
	@Transactional
	public void addAuthor(Author author) throws SQLException {
		if (author.getAuthorId() == null) {
			Integer id = authorDao.addAuthorWithID(author);
			if (author.getBooks() != null && author.getBooks().size() != 0) {
				author.setAuthorId(id);
				authorDao.addBookAuthor(author);
			}
		} else {
			authorDao.updateAuthorName(author);
		}
	}
	
	public void addBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			if (book.getBookId() == null) {
				Integer id = bookDao.addBookWithID(book);
				book.setBookId(id);
				if (book.getPublisher() != null) {
					bookDao.addPublisher(book);
				}
				if (book.getAuthors() != null && book.getAuthors().size() != 0) {
					bookDao.addBookAuthor(book);
				}
				if (book.getGenres() != null && book.getGenres().size() != 0) {
					bookDao.addBookGenre(book);
				}
			} else {
				bookDao.updateBook(book);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public Integer addAuthorWithId(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDao = new AuthorDAO(conn);
			Integer id = authorDao.addAuthorWithID(author);
			conn.commit();
			return id;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public void deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDao = new AuthorDAO(conn);
			authorDao.deleteAuthor(author);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public Author readOneAuthor(Integer authorId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDao = new AuthorDAO(conn);
			return authorDao.readOneAuthor(authorId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Borrower readOneBorrower(Integer cardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO borrowerDao = new BorrowerDAO(conn);
			return borrowerDao.readOneBorrower(cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Book readOneBook(Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			return bookDao.readOneBook(bookId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Publisher readOnePublisher(Integer publisherId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			return publisherDao.readOnePublisher(publisherId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public BookLoans readOneBookLoan(BookLoans bookLoan) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			return bookLoansDao.readOneBookLoan(bookLoan);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public LibraryBranch readOneBranch(Integer branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO branchDao = new LibraryBranchDAO(conn);
			return branchDao.readOneBranch(branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Genre readOneGenre(Integer genreId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDao = new GenreDAO(conn);
			return genreDao.readOneGenre(genreId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	//Check if an author name already exist in the database
	public Boolean checkAuthorName(String authorName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDao = new AuthorDAO(conn);
			List<Author> authorWithSameName = authorDao.checkAuthorByName(authorName);
			return authorWithSameName != null;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return Boolean.FALSE;
	}
	
	public Boolean checkBookName(String bookName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			List<Book> bookWithSameName = bookDao.checkBookByName(bookName);
			return bookWithSameName != null;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return Boolean.FALSE;
	}
	
	public List<Author> readAuthors(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDao = new AuthorDAO(conn);
			return authorDao.readAuthors(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public List<BookLoans> readBookLoans(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			return bookLoansDao.readBookLoans(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
//	public List<Genre> readGenres(String searchString) throws SQLException {
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			GenreDAO genreDao = new GenreDAO(conn);
//			return genreDao.getGenres(searchString);
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}
//		return null;
//	}
	
	public List<Genre> readGenres(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDao = new GenreDAO(conn);
			return genreDao.readGenres(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Integer getAuthorsCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDao = new AuthorDAO(conn);
			return authorDao.getAuthorsCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Integer getBookLoansCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			return bookLoansDao.getBookLoansCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Integer getGenresCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDao = new GenreDAO(conn);
			return genreDao.getGenresCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Integer getPublishersCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			return publisherDao.getPublishersCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Integer getLibraryBranchCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO libraryBranchDao = new LibraryBranchDAO(conn);
			return libraryBranchDao.getLibraryBranchesCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Integer getBooksCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			return bookDao.getBooksCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	
	
	public List<Publisher> readPublishers(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			return publisherDao.readPublishers(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public List<LibraryBranch> readLibraryBranches(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO libraryBranchrDao = new LibraryBranchDAO(conn);
			return libraryBranchrDao.readLibraryBranches(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
//	public List<BookLoans> readBookLoans(String cardNo, String branchId) throws SQLException {
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
//			return bookLoansDao.readBookLoans(cardNo, branchId);
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}
//		return null;
//	}
	
	public List<Borrower> readBorrowers(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO borrowerDao = new BorrowerDAO(conn);
			return borrowerDao.readBorrowers(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			return bookDao.readBooks(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void addGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDao = new GenreDAO(conn);
			if (genre.getGenreId() == null) {
				Integer id = genreDao.addGenreWithID(genre);
				if (genre.getBooks() != null && genre.getBooks().size() != 0) {
					genre.setGenreId(id);
					genreDao.addBookGenre(genre);
				}
			} else {
				genreDao.updateGenreName(genre);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			if (publisher.getPublisherId() == null) {
				Integer id = publisherDao.addPublisherWithId(publisher);
				if (publisher.getBooks() != null && publisher.getBooks().size() != 0) {
					publisher.setPublisherId(id);
					publisherDao.updateBookPublisher(publisher);
				}
			} else {
				publisherDao.updatePublisherInfo(publisher);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void addLibraryBranch(LibraryBranch branch, Boolean addBooks) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO libraryBranchDao = new LibraryBranchDAO(conn);
			BookCopiesDAO bookCopiesDao 	  = new BookCopiesDAO(conn);
			
			if (branch.getBranchId() == null) {
				Integer branchId = libraryBranchDao.addLibraryBranchWithID(branch);
				if (addBooks) {
					for (BookCopies bookCopy : branch.getBookCopies()) {
						bookCopy.getLibraryBranch().setBranchId(branchId);
						bookCopiesDao.addBookCopies(bookCopy);
					}
				}
			} else {
				libraryBranchDao.updateLibraryBranch(branch);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void addBookAuthor(int bookId, Integer authorId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookAuthorDAO bookAuthorDao = new BookAuthorDAO(conn);
			bookAuthorDao.addBookAuthor(bookId, authorId);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDao = new GenreDAO(conn);
			genreDao.deleteGenre(genre);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			publisherDao.deletePublisher(publisher);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Integer addGenreWithId(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDao = new GenreDAO(conn);
			Integer id = genreDao.addGenreWithID(genre);
			conn.commit();
			return id;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public void addBookGenres(Integer bookId, Integer genreId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookGenreDAO bookGenreDao = new BookGenreDAO(conn);
			bookGenreDao.addBookGenre(bookId, genreId);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateLibraryBranch(LibraryBranch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO branchDao = new LibraryBranchDAO(conn);
			branchDao.updateLibraryBranch(branch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	

	
	
//	public Integer addPublisherWithId(Publisher publisher) throws SQLException {
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			PublisherDAO publisherDao = new PublisherDAO(conn);
//			Integer id = publisherDao.addPublisherWithID(publisher);
//			conn.commit();
//			return id;
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			conn.rollback();
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}
//		return null;
//	}

//	public void addBookPublisher(Integer bookId, Integer publisherId) throws SQLException {
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			BookDAO bookDao = new BookDAO(conn);
//			bookDao.updateBookPublisherWithPubId(bookId, publisherId);
//			conn.commit();
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			conn.rollback();
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}
//	}

//	public void updateAuthor(Author author) throws SQLException {
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			AuthorDAO authorDao = new AuthorDAO(conn);
//			authorDao.updateAuthorName(author);
//			if (author.getBooks() == null || author.getBooks().size() == 0) {
//				authorDao.deleteBookAuthor(author);
//			} else {
//				authorDao.deleteBookAuthor(author);
//				authorDao.updateBookAuthor(author);
//			}
//			conn.commit();
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			conn.rollback();
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}
//	}
	
	public void updateBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			bookDao.updateBook(book);//change name
			if (book.getAuthors() == null || book.getAuthors().size() == 0) {
				bookDao.deleteBookAuthor(book);
			} else {
				bookDao.deleteBookAuthor(book);
				bookDao.updateBookAuthor(book);
			}
			if (book.getGenres() == null || book.getGenres().size() == 0) {
				bookDao.deleteBookGenre(book);
			} else {
				bookDao.deleteBookGenre(book);
				bookDao.updateBookGenre(book);
			}
			if (book.getPublisher() != null) {
				bookDao.updateBookPublisher(book);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Boolean checkGenreName(String genreName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDao = new GenreDAO(conn);
			List<Genre> genreWithSameName = genreDao.checkGenreByName(genreName);
			return genreWithSameName != null;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return Boolean.FALSE;
	}
	
	public Boolean checkPublisherName(String publisherName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			List<Publisher> publisherWithSameName = publisherDao.checkPublisherByName(publisherName);
			return publisherWithSameName != null;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return Boolean.FALSE;
	}

//	public void updateGenre(Genre genre) throws SQLException {
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			GenreDAO genreDao = new GenreDAO(conn);
//			genreDao.updateGenreName(genre);
//			if (genre.getBooks() == null || genre.getBooks().size() == 0) {
//				genreDao.deleteBookGenre(genre);
//			} else {
//				genreDao.deleteBookGenre(genre);
//				genreDao.updateBookGenre(genre);
//			}
//			conn.commit();
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			conn.rollback();
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}
//	}
	
	public void updatePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDao = new PublisherDAO(conn);
			publisherDao.updatePublisherInfo(publisher);
			if (publisher.getBooks() == null || publisher.getBooks().size() == 0) {
				publisherDao.deleteBookPublisher(publisher);
			} else {
				publisherDao.deleteBookPublisher(publisher);
				publisherDao.updateBookPublisher(publisher);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDao = new BookDAO(conn);
			bookDao.deleteBook(book);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void deleteBranch(LibraryBranch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO branchDao = new LibraryBranchDAO(conn);
			branchDao.deleteLibraryBranch(branch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	

	public boolean checkBranchName(String branchName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO libraryBranchDao = new LibraryBranchDAO(conn);
			List<LibraryBranch> branchWithSameName = libraryBranchDao.checkBranchByName(branchName);
			return branchWithSameName != null;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return Boolean.FALSE;
	}

	public Boolean checkBorrowerName(String borrowerName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO borrowerDao = new BorrowerDAO(conn);
			List<Borrower> borrowerWithSameName = borrowerDao.checkBorrowerByName(borrowerName);
			return borrowerWithSameName != null;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return Boolean.FALSE;
	}

	public void addBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO borrowerDao = new BorrowerDAO(conn);
			if (borrower.getCardNo() == null) {
				borrowerDao.addBorrower(borrower);
			} else {
				//borrowerDao.updateBorrower(borrower);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO borrowerDao = new BorrowerDAO(conn);
			borrowerDao.deleteBorrower(borrower);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO borrowerDao = new BorrowerDAO(conn);
			borrowerDao.updateBorrower(borrower);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteBookLoan(BookLoans bookLoan) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			bookLoansDao.deleteBookLoan(bookLoan);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateBookLoan(BookLoans bookLoan) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bookLoansDao = new BookLoansDAO(conn);
			bookLoansDao.updateBookLoan(bookLoan);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
