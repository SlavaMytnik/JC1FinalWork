package classes;

import interfaces.IStringExpressionCalculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Double.NaN;

/**
 * Класс StringExpressionTester вычисляет
 * значение строкового арифметического выражения
 */
public class StringExpressionTester implements IStringExpressionCalculator {

    @Override
    public Double calculate(String expression) {
        if (expression != null && expression.length() > 0) {
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "password");
                 ResultSet expressionResult = connection.createStatement().executeQuery(
                         "SELECT " + expression + ";")) {

                expressionResult.next();

                return expressionResult.getDouble(1);
            } catch (SQLException e) {
                return NaN;
            }
        }

        return NaN;
    }
}
