package classes;

import interfaces.IStringExpressionCalculator;
import interfaces.IStringOperation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс StringModule вычисляет значение
 * строкового арифметического выражения по заданному паттерну
 * (вычисляет значение выражения в модуле)
 */
public class StringModule implements IStringOperation {
    private static final int PRIORITY = 5;

    private static final Pattern PATTERN =
            Pattern.compile("\\|[-?0-9.+*/^]+\\|");

    private final IStringExpressionCalculator calculator;

    /**
     * Конструктор класса StringModule
     * @param calculator - экземпляр класса IStringExpressionCalculator для вычисления
     *                   значения строкового арифметического выражения внутри модуля
     */
    public StringModule(IStringExpressionCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public boolean isSupport(String expression) {
        if (expression != null && expression.length() > 0) {
            return PATTERN.matcher(expression.replace(" ", "")).matches();
        }

        return false;
    }

    @Override
    public Double calculate(String expression) {
        if (expression != null && expression.length() > 0) {
            Matcher matcher = PATTERN.matcher(expression.replace(" ", ""));

            if (matcher.find()) {
                return Math.abs(calculator.calculate(matcher.group().substring(1, matcher.group().length() - 1)));
            }
        }

        return null;
    }
}
