package com.gcit.lms;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "index";
	}
	/*
	 * Librarian Navigation Mapping
	 */
	@RequestMapping(value = "/librarian_login_modal", method = RequestMethod.GET)
	public String goToLibrarianLoginModal(Locale locale, Model model) {
		return "librarian_login_modal";
	}
	/*
	 * Borrower Navigation Mapping
	 */
	@RequestMapping(value = "/borrower_checkOut_modal", method = RequestMethod.GET)
	public String goToBorrowerCheckOutModal(Locale locale, Model model) {
		return "borrower_checkOut_modal";
	}
	
	@RequestMapping(value = "/borrower_checkIn_modal", method = RequestMethod.GET)
	public String goToBorrowerCheckInModal(Locale locale, Model model) {
		return "borrower_checkIn_modal";
	}
	/*
	 * Administrator Navigation Mapping
	 */
	@RequestMapping(value = "/admin_book", method = RequestMethod.GET)
	public String goToAdminBook(Locale locale, Model model) {
		return "admin_book";
	}
	
	@RequestMapping(value = "/admin_author", method = RequestMethod.GET)
	public String goToAdminAuthor(Locale locale, Model model) {
		return "admin_author";
	}
	
	@RequestMapping(value = "/admin_genre", method = RequestMethod.GET)
	public String goToAdminGenre(Locale locale, Model model) {
		return "admin_genre";
	}
	
	@RequestMapping(value = "/admin_publisher", method = RequestMethod.GET)
	public String goToAdminPublisher(Locale locale, Model model) {
		return "admin_publisher";
	}
	
	@RequestMapping(value = "/admin_library_branch", method = RequestMethod.GET)
	public String goToAdminLibraryBranch(Locale locale, Model model) {
		return "admin_library_branch";
	}
	
	@RequestMapping(value = "/admin_borrower", method = RequestMethod.GET)
	public String goToAdminBorrower(Locale locale, Model model) {
		return "admin_borrower";
	}
	
	@RequestMapping(value = "/admin_book_loans", method = RequestMethod.GET)
	public String goToAdminBookLoans(Locale locale, Model model) {
		return "admin_book_loans";
	}
	
}
