package classes;

import interfaces.IStringOperation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс StringPower вычисляет значение
 * строкового арифметического выражения по заданному паттерну
 * (вычисляет значение возведения числа в степень)
 */
public class StringPower implements IStringOperation {
    private static final int PRIORITY = 4;

    private static final Pattern PATTERN =
            Pattern.compile("((^-)?[0-9]+\\.?([0-9]+)?)\\^(-?[0-9]+\\.?([0-9]+)?)");

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
                return Math.pow(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(4)));
            }
        }

        return null;
    }
}
