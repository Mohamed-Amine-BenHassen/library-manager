package com.library.repository;
import com.library.db.DatabaseManager;
import com.library.model.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {
    public void addLoan(int bookId, int memberId, String borrowedOn, String dueDate){
    String sql ="INSERT INTO loans (book_id, member_id, borrowed_on, due_date) VALUES (?, ?, ?, ?)";
        try(Connection connection = DatabaseManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {


            stmt.setInt(1, bookId);
            stmt.setInt(2, memberId);
            stmt.setString(3, borrowedOn);
            stmt.setString(4, dueDate);
            stmt.executeUpdate();
            System.out.println("Book loaned at : " + borrowedOn + " and due on : " + dueDate);

        }
        catch (SQLException e) {
            System.out.println("Error loaning book: " + e.getMessage());
        }}
    public void markReturned(int bookId, String returnedOn){
        String sql = "UPDATE loans SET returned_on = ? WHERE book_id = ? AND returned_on IS NULL";
            try(Connection connection = DatabaseManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, returnedOn);
                stmt.setInt(2, bookId);
                stmt.executeUpdate();
                System.out.println("Book marked as returned on : " + returnedOn);

            } catch (SQLException e) {
                System.out.println("Error marking book as returned: " + e.getMessage());
            }
        }
    public List<Loan> getActiveLoans() {
        ArrayList<Loan> activeLoans = new ArrayList<>();
        String sql = """
    SELECT loans.id AS loan_id, books.title, members.name,
           loans.borrowed_on, loans.due_date
    FROM loans
    JOIN books   ON loans.book_id   = books.id
    JOIN members ON loans.member_id = members.id
    WHERE loans.returned_on IS NULL
    """;
        try (Connection connection = DatabaseManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int loanId = rs.getInt("loan_id");
                String bookTitle = rs.getString("title");
                String memberName = rs.getString("name");
                String borrowedOn = rs.getString("borrowed_on");
                String dueDate = rs.getString("due_date");
                activeLoans.add(new Loan(loanId, bookTitle, memberName, borrowedOn, dueDate));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving active loans: " + e.getMessage());
        }
        return activeLoans;
    }
}











