package com.library;

import com.library.db.DatabaseManager;
import com.library.model.Book;
import com.library.model.Member;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       DatabaseManager.initializeDatabase();
       BookRepository bookRepository = new BookRepository();
       LibraryService libraryService = new LibraryService();
       MemberRepository memberRepository = new MemberRepository();
       while (true) {
        System.out.println("Library Management System");
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. List All Members");
        System.out.println("6. Add Member");
        System.out.println("7. get Book by ID");
        System.out.println("8. get Member by ID");
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

          bookRepository.getAllBooks().forEach(System.out::println);
       break;
      case 0:
         System.out.println("Exiting...");

       return;
      case 3:
          System.out.print("Enter Book ID to borrow: ");
          int bookId = scanner.nextInt();
          System.out.print("Enter Member ID: ");
          int memberId = scanner.nextInt();
          libraryService.borrowBook(bookId, memberId);
          break;
      case  4:
          System.out.print("Enter Book ID to return: ");
          int returnBookId = scanner.nextInt();
          libraryService.returnBook(returnBookId);
          break;
      case 5:
          memberRepository.getAllMembers().forEach(System.out::println);
          break;


      case 6:
          System.out.print("Enter Member Name: ");
          String memberName = scanner.nextLine();
          System.out.print("Enter Member Email: ");
          String memberEmail = scanner.nextLine();
          memberRepository.addMember(memberName, memberEmail);
          break;
      case  7:
          System.out.print("Enter Book ID to get details: ");
          int getBookId = scanner.nextInt();
          Book book = bookRepository.getBookById(getBookId);
          if (book != null) {
              System.out.println(book);
          } else {
              System.out.println("Book not found.");
          }

          break;
      case 8:
          System.out.print("Enter Member ID to get details: ");
          int getMemberId = scanner.nextInt();

          Member member = memberRepository.getMemberById(getMemberId);
           if (member != null) {
               System.out.println(member);
           } else {
               System.out.println("Member not found.");
           }
          break;
      default:
       System.out.println("Invalid choice");
     }



      }
    }


    }
