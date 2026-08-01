package com.library;

import com.library.db.DatabaseManager;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       DatabaseManager.initializeDatabase();
       BookRepository bookRepository = new BookRepository();
       while (true) {
        System.out.println("Library Management System");
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
           scanner.nextLine();
           switch (choice) {
      case 1:
          System.out.print("Title: ");
          String name = scanner.nextLine();
          System.out.print("Author: ");
          String author = scanner.nextLine();
          System.out.print("Year: ");
          int year = scanner.nextInt();
          scanner.nextLine();  // ← consume again, since the loop repeats
          bookRepository.addBook(name, author, year);
          break;
      case 2:

       bookRepository.getAllBooks().forEach(book -> {
           System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Year: " + book.getYear() + ", Available: " + book.isAvailable());
       });
       break;
      case 0:
         System.out.println("Exiting...");

       return;
      default:
       System.out.println("Invalid choice");
     }



      }
    }


    }
