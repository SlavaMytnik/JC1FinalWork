package interfaces;

/**
 * Интерфейс IStringExpressionModifier преобразует
 * строковое арифметическое выражение в требуемый формат
 */
public interface IStringExpressionModifier {

    /**
     * Метод modify преобразует строковое арифметическое выражение в требуемый формат
     * @param expression - исходное строковое арифметическое выражение
     * @return возвращает преобразованное строковое арифметическое выражение
     */
    String modify(String expression);
}
