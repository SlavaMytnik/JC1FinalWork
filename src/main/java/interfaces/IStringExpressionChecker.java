package interfaces;

/**
 * Интерфейс IStringExpressionChecker определяет
 * корректность строкового арифметического выражения
 */
public interface IStringExpressionChecker {

    /**
     * Метод check определяет корректность строкового арифметического выражения
     * @param expression - строковое арифметическое выражение
     * @return возвращает true, если выражение корректное,
     * или false, если выражение некорректное
     */
    boolean check(String expression);
}
