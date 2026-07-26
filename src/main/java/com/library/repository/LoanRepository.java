package com.library.repository;
import com.library.db.DatabaseManager;

import java.sql.*;

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


        }









