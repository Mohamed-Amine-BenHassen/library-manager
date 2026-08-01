package com.library.repository;
import com.library.db.DatabaseManager;
import com.library.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MemberRepository {
    public void addMember(String name, String email){
        String sql = "INSERT INTO Members (name, email) VALUES (?, ?)";
     try(Connection connection = DatabaseManager.getConnection();
    PreparedStatement stmt = connection.prepareStatement(sql)) {


        stmt.setString(1, name);
        stmt.setString(2, email);

        stmt.executeUpdate();
        System.out.println("member added successfully: " + name);

    }
        catch (SQLException e) {
        System.out.println("Error adding member: " + e.getMessage());
    }}
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT id, name, email FROM members";

        try (Connection connection = DatabaseManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                members.add(new Member(id, name, email));


            }}catch (SQLException e) {
                System.out.println("Error retrieving member: " + e.getMessage());

            }
            return members;

        }
        public Member getMemberById(int id) {
            String sql = "SELECT id, name, email FROM members WHERE id = ?";
            try (Connection connection = DatabaseManager.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    return new Member(id, name, email);
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving member by ID: " + e.getMessage());
            }
            return null;
        }


    }
