package classes;

import interfaces.IStringExpressionChecker;

/**
 * Класс StringExpressionChecker определяет
 * корректность строкового арифметического выражения
 */
public class StringExpressionChecker implements IStringExpressionChecker {

    @Override
    public boolean check(String expression) {
        if (expression != null && expression.length() > 0) {
            String[] str = new String[]{"*", "/", "+", "-"};
            for (String s1 : str) {
                for (String s2 : str) {
                    if (expression.contains(s1 + s2)) return false;
                }

                if (expression.contains(s1 + ")")
                        || (!s1.equals("-") && expression.contains("(" + s1))) return false;
            }

            return true;
        }

        return false;
    }
}
