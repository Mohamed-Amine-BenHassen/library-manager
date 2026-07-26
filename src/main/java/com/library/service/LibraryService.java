package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.MemberRepository;
import java.time.LocalDate;

public class LibraryService {
    private final BookRepository bookRepository = new BookRepository();
    private final MemberRepository memberRepository = new MemberRepository();
    private final LoanRepository loanRepository = new LoanRepository();
    public void borrowBook(int bookId, int memberId) {
        // Code to borrow a book
        Book book = bookRepository.getBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
        return;}



        if (!book.isAvailable()) {
            System.out.println("Book is not available for borrowing.");
            return;}

        String borrowedOn = LocalDate.now().toString();
        String dueDate = LocalDate.now().plusWeeks(2).toString();
        loanRepository.addLoan(bookId, memberId, borrowedOn, dueDate);
        bookRepository.setAvailability(bookId, false);
        System.out.println("Book borrowed successfully. Due date: " + dueDate);



        // Check if the book is available, then mark it as borrowed and associate it with the member

    }
    public void returnBook(int bookId) {
        Book book = bookRepository.getBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isAvailable()) {
            System.out.println("Book is already marked as available.");
            return;
        }
        String returnedOn = LocalDate.now().toString();
        loanRepository.markReturned(bookId, returnedOn);
        bookRepository.setAvailability(bookId, true);
        System.out.println("Book returned successfully.");

    }


}







