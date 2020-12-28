package classes;

import interfaces.IStringExpressionCalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            try {
                File filePostgresql = new File(System.getProperty("user.dir")
                        + File.separator + "config" + File.separator + "postgresql.csv");

                BufferedReader bufferedReader =
                        new BufferedReader(new FileReader(filePostgresql));

                String line;
                String[] values;

                if ((line = bufferedReader.readLine()) != null) {
                    values = line
                            .replaceAll("\\s+|'", "")
                            .replaceAll("[\\w]+:", "").split(",");

                    try (Connection connection = DriverManager.getConnection(
                            "jdbc:postgresql://" + values[0] + ":" + values[1]
                                    + "/" + values[2], values[3], values[4]);
                         ResultSet expressionResult = connection.createStatement().executeQuery(
                                 "SELECT " + expression + ";")) {

                        expressionResult.next();

                        return expressionResult.getDouble(1);
                    } catch (SQLException e) {
                        return NaN;
                    }
                }
            } catch (IOException e) {
                return NaN;
            }
        }

        return NaN;
    }
}
