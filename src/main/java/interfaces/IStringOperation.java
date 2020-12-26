package interfaces;

import java.util.regex.Pattern;

/**
 * Интерфейс IStringOperation вычисляет значение
 * строкового арифметического выражения по заданному паттерну
 */
public interface IStringOperation {

    /**
     * Метод getPriority возвращает значение приоритета арифметической операции
     * @return возвращает значение приоритета арифметической операции
     */
    int getPriority();

    /**
     * Метод getPattern возвращает паттерн арифметической операции
     * @return возвращает паттерн арифметической операции
     */
    Pattern getPattern();

    /**
     * Метод isSupport определяет возможность вычисления значения
     * строкового арифметического выражения по заданному паттерну
     * @param expression - стороковое арифметическое выражение
     * @return возвражает true, если имеется возможность вычисления,
     * или false, если возможность вычисления отсутствует
     */
    boolean isSupport(String expression);

    /**
     * Метод calculate вычисляет значение строкового арифметического выражения
     * @param expression - стороковое арифметическое выражение
     * @return возвращает результат вычисления строкового арифметического выражения
     */
    Double calculate(String expression);
}