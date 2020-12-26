package classes;

import interfaces.IStringExpressionChecker;
import interfaces.IStringExpressionGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Класс StringExpressionGenerator генерирует строковое арифметическое выражение
 */
public class StringExpressionGenerator implements IStringExpressionGenerator {

    @Override
    public String generate(int minLength) {
        if (minLength > 0) {
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "password")) {
                IStringExpressionChecker checker = new StringExpressionChecker();

                Random random = new Random();

                boolean isExpressionCorrect;

                String expression;

                List<String> expressionItems = new ArrayList<>();

                long millis = System.currentTimeMillis();

                do {
                    if (System.currentTimeMillis() - millis > TimeUnit.SECONDS.toMillis(1)) {
                        millis = System.currentTimeMillis();
                        expressionItems = new ArrayList<>();
                    }

                    isExpressionCorrect = false;

                    List<String> expressionItemsModified = new ArrayList<>(expressionItems);

                    for (int j = 0; j < random.nextInt(5) + 1; j++) {
                        expressionItemsModified.add(expressionItemsModified.size() > 0
                                ? random.nextInt(expressionItemsModified.size()) : 0, getExpressionItem());
                    }

                    expression = new StringExpressionCleaner().clean(expressionItemsModified.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining()));

                    if (checker.check(expression)) {
                        try (ResultSet expressionResultSet = connection.createStatement().executeQuery(
                                "SELECT " + expression + ";")) {

                            if (expressionResultSet.next()) {
                                expressionItems = new ArrayList<>(expressionItemsModified);
                                isExpressionCorrect = true;
                            }
                        } catch (SQLException ignored) {
                        }
                    }
                } while (!isExpressionCorrect || expression.length() < minLength);

                return expression;
            } catch (SQLException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Функциональный интерфейс для формирования элемента строкового
     * арифметического выражения с помощью лямбда-выражения
     */
    @FunctionalInterface
    private interface IGetter {

        /**
         * Метод get формирует элемент строкового
         * арифметического выражения с помощью лямбда-выражения
         * @return возвращает элемент строкового арифметического выражения
         */
        String get();
    }

    /**
     * Перечисление ExpressionItems содержит элементы строкового арифметического выражения
     */
    private enum ExpressionItems {
        MULTIPLYING (20, () -> "*"),
        DIVISION (20, () -> "/"),
        ADDITION (8, () -> "+"),
        SUBTRACTION (4, () -> "-"),
        BRACKET_LEFT (30, () -> "("),
        BRACKET_RIGHT (40, () -> ")"),
        POWER (20, () -> "^"),
        PI (1, () -> "pi()"),
        EXP (1, () -> "exp(1.0)"),
        ABS (5, () -> "abs("),
        RANDOM_INTEGER (8, () -> "" + (new Random().nextInt(200) - 100) + ".0"),
        RANDOM_DOUBLE (8, () -> "" + (new Random().nextDouble() * 200 - 100)),
        RANDOM_INTEGER_POWER (1, () -> "^" + new Random().nextInt(5) + ".0"),
        RANDOM_INTEGER_POWER_NEGATIVE (1, () -> "^(" + (new Random().nextInt(5) - 5) + ".0)"),
        RANDOM_DOUBLE_POWER (1, () -> "^(" + new Random().nextDouble() * 5 + ")"),
        RANDOM_DOUBLE_POWER_NEGATIVE (1, () -> "^(" + (new Random().nextDouble() * 5 - 5) + ")");

        int weight;

        IGetter getter;

        /**
         * Конструктор перечисления ExpressionItems
         * @param weight - частота использования элемента
         *               в строковом арифметическом выражении
         * @param getter - лямбда-выражение формирования элемента
         *               строкового арифметического выражения
         */
        ExpressionItems(int weight, IGetter getter) {
            this.weight = weight;
            this.getter = getter;
        }
    }

    /**
     * Метод getExpressionItem генерирует новый
     * элемент строкового арифметического выражения
     * @return возвращает новый элемент строкового арифметического выражения
     */
    private static String getExpressionItem() {
        Map<Integer, IGetter> items = new HashMap<>();

        int i = 0;

        for (ExpressionItems value : ExpressionItems.values()) {
            for (int j = 0; j < value.weight; j++) {
                items.put(i++, value.getter);
            }
        }

        return items.get(new Random().nextInt(i)).get();
    }
}
