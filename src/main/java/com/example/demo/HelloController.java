package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private TextField usernameField; // Поле для ввода имени пользователя
    @FXML
    private TextField passwordField; // Поле для ввода пароля
    @FXML
    private TextField camkdrtField; // Поле для ввода налогооблагаемого дохода
    @FXML
    private TextField salesDoolsField; // Поле для ввода суммы дохода
    @FXML
    private TextField resultingsField; // Поле для вывода рассчитанного налога
    @FXML
    private Button calculateButton; // Кнопка для расчёта налога
    @FXML
    private TextField roleField; // Новый инпут для роли


    @FXML
    private void calculateTax() {
        try {
            // Получаем введённые данные
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String role = roleField.getText().trim();  // Получаем значение роли

            // Проверяем, что логин и пароль не пустые
            if (username.isEmpty() || password.isEmpty()) {
                resultingsField.setText("Enter username and password");
                return;
            }

            // Парсим входные значения для налогообложения
            double camkdrt = parseCurrency(camkdrtField.getText());
            double salesDools = parseCurrency(salesDoolsField.getText());

            // Выполняем расчёт налога
            double result = camkdrt * 0.07 + salesDools * 0.05;

            // Сохраняем данные в базу данных
            saveUserToDatabase(username, password, role); // Сохраняем пользователя с ролью
            saveTaxDataToDatabase(camkdrt, salesDools, result);

            // Отображаем результат в поле
            resultingsField.setText(String.format("$%.2f", result));
        } catch (NumberFormatException e) {
            resultingsField.setText("Invalid input");
        }
    }

    // Метод для парсинга введённых значений (удаление символов $, , и пробелов)
    private double parseCurrency(String value) {
        value = value.replace(",", "").replace("$", "").trim();
        return Double.parseDouble(value);
    }

    // Метод для сохранения пользователя в таблицу users
    private void saveUserToDatabase(String username, String password, String role) {
        // Сначала проверим, существует ли уже пользователь с таким username
        String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            // Устанавливаем параметры для запроса
            checkStatement.setString(1, username);

            // Выполняем запрос
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Если пользователь уже существует, выводим сообщение
                resultingsField.setText("Username already exists");
                return;
            }

            // Если пользователь не существует, выполняем вставку
            String insertQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, username);
                insertStatement.setString(2, password);
                insertStatement.setString(3, role); // Роль

                // Выполняем запрос вставки
                insertStatement.executeUpdate();
                resultingsField.setText("User added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultingsField.setText("Database error (users)");
        }
    }

    // Метод для сохранения данных расчета налога в таблицу taxpaymants
    private void saveTaxDataToDatabase(double taxableIncome, double amountIncome, double calculatedTax) {
        String query = "INSERT INTO taxpaymants (taxable_income, amount_income, calculated_tax) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Устанавливаем параметры запроса
            statement.setDouble(1, taxableIncome);
            statement.setDouble(2, amountIncome);
            statement.setDouble(3, calculatedTax);

            // Выполняем запрос
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultingsField.setText("Database error (taxpaymants)");
        }
    }

    @FXML
    private void initialize() {
        // Убедитесь, что все поля правильно привязаны
        if (roleField == null) {
            System.out.println("Role field is not initialized properly");
        }

        // Привязываем обработчик событий к кнопке расчёта
        calculateButton.setOnAction(event -> calculateTax());
    }
}
