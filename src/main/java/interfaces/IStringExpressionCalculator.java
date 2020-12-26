package interfaces;

/**
 * Интерфейс IStringExpressionCalculator вычисляет
 * значение строкового арифметического выражения
 */
public interface IStringExpressionCalculator {

    /**
     * Метод calculate вычисляет значение строкового арифметического выражения
     * @param expression - строковое арифметическое выражение
     * @return возвращает результат вычисления строкового арифметического выражения
     */
    Double calculate(String expression);
}
