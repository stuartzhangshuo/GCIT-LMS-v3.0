/**
 * @Author Shuo Zhang <shuo_zhang@gcitsolutions.com>
 * @Date Sep 27, 2017
 */
package com.gcit.libmgmtsys.entity;

import java.io.Serializable;
import java.util.*;

public class Book implements Serializable{
	
	private static final long serialVersionUID = 8947870066099291812L;
	
	private Integer	  bookId;
	private String 	  title;
	private Publisher 	   publisher;
	private List<Author>   authors;
	private List<Genre>    genres;
	private List<Borrower> borrowers;
	private HashMap<Integer, Integer> branchCopies;	//branchId, noOfCopies
	private Integer totalNumOfCopies;
	
	public HashMap<Integer, Integer> getBranchCopies() {
		return branchCopies;
	}

	public void setBranchCopies(HashMap<Integer, Integer> branchCopies) {
		this.branchCopies = branchCopies;
	}

	public void setTotalNumOfCopies(Integer totalNumOfCopies) {
		this.totalNumOfCopies = totalNumOfCopies;
	}

	public Integer getTotalNumOfCopies() {
		return totalNumOfCopies;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Borrower> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
