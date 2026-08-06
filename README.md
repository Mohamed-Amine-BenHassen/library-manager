markdown
# Library Manager

A command-line library system written in Java with an SQLite database.
Student project — built to practise JDBC, SQL, and layered architecture.

## What It Does

- Add books and members
- Borrow a book (checks the book exists, the member exists, and it isn't already out)
- Return a book
- List books, members, or active loans
- Loans run 14 days; due dates are worked out automatically

## Built With

Java 17, Maven, SQLite (JDBC)

## Project Structure

com/library/
├── Main.java menu and user input
├── db/ database connection and table setup
├── model/ Book, Member, Loan
├── repository/ all SQL, one class per table
└── service/ borrow and return logic


Each layer only talks to the one below it, so SQL stays in the repositories
and the menu stays free of logic.

## Running It

Clone the repo, open it in IntelliJ, and run `Main`. The database file is
created on the first run.

## Notes

- Queries with user input use `PreparedStatement`, so SQL injection isn't possible
- Active loans use a JOIN across three tables to show titles and names, not IDs

## To Do

- Search by title
- Show overdue loans
- Stop the program crashing when you type a letter at the menu
- Tests