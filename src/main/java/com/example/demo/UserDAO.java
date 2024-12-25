package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("Connection cannot be null");
        }
        this.connection = connection;
    }


    public int addUser(User user) throws SQLException {
        int newid=0;

        String sql = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            int affectedRows = stmt.executeUpdate();

            if( affectedRows == 0){
                throw new SQLException("Creating task failed.");
            }

            try( ResultSet generatedKeys = stmt.getGeneratedKeys()){

                if(generatedKeys.next()){
                    newid = generatedKeys.getInt(1);

                }
            }

        }
        return newid;
    }



    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
            }
        }
        return users;
    }

    private String hashPassword(String password) {
        // Implement password hashing, e.g., using bcrypt
        return password; // Placeholder - replace with actual hash function
    }
}

