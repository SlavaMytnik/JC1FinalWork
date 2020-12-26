import classes.*;
import interfaces.IStringExpressionCalculator;
import interfaces.IStringExpressionGenerator;
import interfaces.IStringExpressionModifier;
import interfaces.IStringOperation;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс StringExpressionCalculatorTest тестирует
 * классы IStringExpressionCalculator и IStringOperation
 */
class StringExpressionCalculatorTest {
    private static final double PRECISION = 1E-9;

    private static final IStringExpressionCalculator CALCULATOR_INNER =
            new StringExpressionCalculator(
            new StringPower(),
            new StringDivision(),
            new StringMultiplication(),
            new StringSubtraction(),
            new StringAddition());

    /**
     * Метод bracketTest тестирует класс StringBracket
     * @param expression строковое арифметическое выражение
     * @param expected ожидаемый результат вычисления
     *                 строкового арифметического выражения
     */
    @ParameterizedTest
    @CsvFileSource(resources = "expressions/bracket.csv", delimiter = '=')
    void bracketTest(String expression, Double expected) {
        IStringOperation stringOperation = new StringBracket(CALCULATOR_INNER);

        double actual = stringOperation.calculate(expression);
        double delta = Math.min(Math.abs(expected), Math.abs(actual)) * PRECISION;

        assertEquals(expected, actual, delta, expression);
    }

    /**
     * Метод moduleTest тестирует класс StringModule
     * @param expression строковое арифметическое выражение
     * @param expected ожидаемый результат вычисления
     *                 строкового арифметического выражения
     */
    @ParameterizedTest
    @CsvFileSource(resources = "expressions/module.csv", delimiter = '=')
    void moduleTest(String expression, Double expected) {
        IStringOperation stringOperation = new StringModule(CALCULATOR_INNER);

        double actual = stringOperation.calculate(expression);
        double delta = Math.min(Math.abs(expected), Math.abs(actual)) * PRECISION;

        assertEquals(expected, actual, delta, expression);
    }

    /**
     * Метод powerTest тестирует класс StringPower
     * @param expression строковое арифметическое выражение
     * @param expected ожидаемый результат вычисления
     *                 строкового арифметического выражения
     */
    @ParameterizedTest
    @CsvFileSource(resources = "expressions/power.csv", delimiter = '=')
    void powerTest(String expression, Double expected) {
        IStringOperation stringOperation = new StringPower();

        double actual = stringOperation.calculate(expression);
        double delta = Math.min(Math.abs(expected), Math.abs(actual)) * PRECISION;

        assertEquals(expected, actual, delta, expression);
    }

    /**
     * Метод divisionTest тестирует класс StringDivision
     * @param expression строковое арифметическое выражение
     * @param expected ожидаемый результат вычисления
     *                 строкового арифметического выражения
     */
    @ParameterizedTest
    @CsvFileSource(resources = "expressions/division.csv", delimiter = '=')
    void divisionTest(String expression, Double expected) {
        IStringOperation stringOperation = new StringDivision();

        double actual = stringOperation.calculate(expression);
        double delta = Math.min(Math.abs(expected), Math.abs(actual)) * PRECISION;

        assertEquals(expected, actual, delta, expression);
    }

    /**
     * Метод multiplicationTest тестирует класс StringMultiplication
     * @param expression строковое арифметическое выражение
     * @param expected ожидаемый результат вычисления
     *                 строкового арифметического выражения
     */
    @ParameterizedTest
    @CsvFileSource(resources = "expressions/multiplication.csv", delimiter = '=')
    void multiplicationTest(String expression, Double expected) {
        IStringOperation stringOperation = new StringMultiplication();

        double actual = stringOperation.calculate(expression);
        double delta = Math.min(Math.abs(expected), Math.abs(actual)) * PRECISION;

        assertEquals(expected, actual, delta, expression);
    }

    /**
     * Метод additionTest тестирует класс StringAddition
     * @param expression строковое арифметическое выражение
     * @param expected ожидаемый результат вычисления
     *                 строкового арифметического выражения
     */
    @ParameterizedTest
    @CsvFileSource(resources = "expressions/addition.csv", delimiter = '=')
    void additionTest(String expression, Double expected) {
        IStringOperation stringOperation = new StringAddition();

        double actual = stringOperation.calculate(expression);
        double delta = Math.min(Math.abs(expected), Math.abs(actual)) * PRECISION;

        assertEquals(expected, actual, delta, expression);
    }

    /**
     * Метод subtractionTest тестирует класс StringSubtraction
     * @param expression строковое арифметическое выражение
     * @param expected ожидаемый результат вычисления
     *                 строкового арифметического выражения
     */
    @ParameterizedTest
    @CsvFileSource(resources = "expressions/subtraction.csv", delimiter = '=')
    void subtractionTest(String expression, Double expected) {
        IStringOperation stringOperation = new StringSubtraction();

        double actual = stringOperation.calculate(expression);
        double delta = Math.min(Math.abs(expected), Math.abs(actual)) * PRECISION;

        assertEquals(expected, actual, delta, expression);
    }

    /**
     * Метод expressionTest тестирует класс StringExpressionCalculator
     */
    @RepeatedTest(100)
    void expressionTest() {
        IStringExpressionGenerator generator = new StringExpressionGenerator();

        String expression = generator.generate(20 + new Random().nextInt(80));

        IStringExpressionCalculator calculator = new StringExpressionCalculator(
                new StringBracket(CALCULATOR_INNER),
                new StringModule(CALCULATOR_INNER),
                new StringPower(),
                new StringDivision(),
                new StringMultiplication(),
                new StringSubtraction(),
                new StringAddition());

        IStringExpressionModifier modifier = new StringExpressionModifier();

        double expected = new StringExpressionTester().calculate(expression);
        double actual = calculator.calculate(modifier.modify(expression));
        double delta = 0;

        if (!Double.isNaN(expected) && !Double.isNaN(actual))
            delta = Math.min(Math.abs(expected), Math.abs(actual)) * PRECISION;

        assertEquals(expected, actual, delta, modifier.modify(expression));
    }
}