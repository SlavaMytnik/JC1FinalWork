package interfaces;

/**
 * Интерфейс IStringExpressionGenerator генерирует строковое арифметическое выражение
 */
public interface IStringExpressionGenerator {

    /**
     * Метод generate генерирует строковое арифметическое выражение
     * @param minLength - минимальная длина строкового арифметического выражения
     * @return возвращает сгенерированное арифметическое выражение
     */
    String generate(int minLength);
}
