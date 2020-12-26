package classes;

import interfaces.IStringExpressionCleaner;

/**
 * Класс StringExpressionCleaner удаляет из
 * строкового арифметического выражения нежелательные элементы
 */
public class StringExpressionCleaner implements IStringExpressionCleaner {

    @Override
    public String clean(String expression) {
        if (expression != null && expression.length() > 0) {
            String expressionCleaned = expression;

            expressionCleaned = expressionCleaned
                    .replace(" ", "")
                    .replace("PI", "" + Math.PI)
                    .replace("E", "" + Math.E);

            do {
                expression = expressionCleaned;

                expressionCleaned = expressionCleaned
                        .replace("*+", "*")
                        .replace("/+", "/")
                        .replace("(+", "(")
                        .replace("^+", "^")
                        .replace("++", "+")
                        .replace("--", "+")
                        .replace("+-", "-")
                        .replace("-+", "-")
                        .replaceFirst("^\\+", "");
            } while (!expression.equals(expressionCleaned));
        }

        return expression;
    }
}
