package com.example.demo;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void addUser() {


        try {
            UserDAO userDAO = new UserDAO(DatabaseConnection.getConnection());

            User user = new User();
            user.setUsername("John Doe");
            user.setPassword(" 1312323131 ");
            user.setRole("admin");

            int id = userDAO.addUser(user);
            user.setUserId(id);
            System.out.println(user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void getAllUsers() {

        try {
            UserDAO userDAO = new UserDAO(DatabaseConnection.getConnection());

            List<User> users = userDAO.getAllUsers();

            for(User u:users){

                System.out.println(u);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}