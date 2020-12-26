package interfaces;

/**
 * Интерфейс IStringExpressionCleaner удаляет из
 * строкового арифметического выражения нежелательные элементы
 */
public interface IStringExpressionCleaner {

    /**
     * Метод clean удаляет из строкового арифметического выражения нежелательные элементы
     * @param expression - исходное строковое арифметическое выражение
     * @return возвращает строковое арифметическое выражение без нежелательных элементов
     */
    String clean(String expression);
}
