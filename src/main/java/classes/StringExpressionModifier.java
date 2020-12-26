package classes;

import interfaces.IStringExpressionModifier;

/**
 * Класс StringExpressionModifier преобразует
 * строковое арифметическое выражение в требуемый формат
 */
public class StringExpressionModifier implements IStringExpressionModifier {

    @Override
    public String modify(String expression) {
        if (expression != null && expression.length() > 0) {
            expression = expression
                    .replace("pi()", "PI")
                    .replace("exp(1.0)", "E");

            while (expression.contains("abs(")) {
                int findIndex = expression.indexOf("abs(") + 4;
                int count = 1;

                do {
                    if (expression.indexOf("(", findIndex) > -1
                            && (expression.indexOf("(", findIndex) < expression.indexOf(")", findIndex))) {
                        count++;
                        findIndex = expression.indexOf("(", findIndex) + 1;
                    } else {
                        count--;
                        findIndex = expression.indexOf(")", findIndex) + 1;
                    }
                } while (count != 0);

                expression = expression.substring(0, findIndex - 1) + "|" + expression.substring(findIndex);
                expression = expression.replaceFirst("abs\\(", "|");
            }
        }

        return expression;
    }
}
