package com.library;

import com.library.db.DatabaseManager;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.service.LibraryService;

public class Main {
    public static void main(String[] args) {
       DatabaseManager.initializeDatabase();
        BookRepository bookRepo = new BookRepository();
        bookRepo.addBook("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        bookRepo.addBook("To Kill a Mockingbird", "Harper Lee", 1960);
        bookRepo.addBook("1984", "George Orwell", 1949);
        System.out.println("Books in the library:");
        bookRepo.getAllBooks().forEach(System.out::println);

        MemberRepository memberRepo = new MemberRepository();
        memberRepo.addMember("Alice Smith", "alice@gmail.com");
        memberRepo.addMember("Bob Johnson", "bob@gmail.com");
        System.out.println("Library members:");
        memberRepo.getAllMemebers().forEach(System.out::println);

        LibraryService libraryService = new LibraryService();
        libraryService.borrowBook(1, 1);
        libraryService.borrowBook(1, 1);


        libraryService.borrowBook(1, 1);   // borrow it
        libraryService.returnBook(1);      // return it
        libraryService.borrowBook(1, 1);
    }
}