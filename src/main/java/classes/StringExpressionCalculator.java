package classes;

import interfaces.IStringExpressionCalculator;
import interfaces.IStringOperation;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;

import static java.lang.Double.NaN;

/**
 * Класс StringExpressionCalculator вычисляет
 * значение строкового арифметического выражения
 */
public class StringExpressionCalculator implements IStringExpressionCalculator {
    private IStringOperation[] stringOperations;

    /**
     * Конструктор класса StringExpressionCalculator
     * @param stringOperations - экземпляры классов IStringOperation
     *                         для выполнения арифметических операций
     */
    public StringExpressionCalculator(IStringOperation... stringOperations) {
        if (stringOperations != null && stringOperations.length > 0) {

            Map<Integer, List<IStringOperation>> operationsPriority =
                    new TreeMap<>(Collections.reverseOrder());

            for (IStringOperation operation : stringOperations) {
                int priority = operation.getPriority();

                List<IStringOperation> operations;

                if (operationsPriority.containsKey(priority)) {
                    operations = operationsPriority.get(priority);
                } else {
                    operations = new ArrayList<>();
                }

                operations.add(operation);
                operationsPriority.put(priority, operations);
            }

            this.stringOperations = new IStringOperation[stringOperations.length];

            int position = 0;

            for (Map.Entry<Integer, List<IStringOperation>> entry
                    : operationsPriority.entrySet()) {
                for (IStringOperation operation : entry.getValue()) {
                    this.stringOperations[position] = operation;
                    position++;
                }
            }
        }
    }

    @Override
    public Double calculate(String expression) {
        if (stringOperations != null && expression != null && expression.length() > 0) {
            boolean isFind;

            StringExpressionCleaner stringExpressionCleaner =
                    new StringExpressionCleaner();

            do {
                isFind = false;

                expression = stringExpressionCleaner.clean(expression);

                for (IStringOperation stringOperation : stringOperations) {
                    Matcher matcher = stringOperation.getPattern().matcher(expression);

                    if (matcher.find()) {
                        try {
                            expression = expression.replace(matcher.group(),
                                    BigDecimal.valueOf(stringOperation
                                            .calculate(matcher.group()))
                                            .toPlainString());
                            isFind = true;
                            break;
                        } catch (Exception ignored) {}
                    }
                }
            } while (isFind);

            try {
                return Double.parseDouble(expression);
            } catch (Exception e) {
                return NaN;
            }
        }

        return NaN;
    }
}
