## Введение
Настоящий проект осуществляет тестирование калькулятора строковых арифметических выражений класса `StringExpressionCalculator`.
> Примечание: кроме того, осуществляется тестирование классов `StringBracket`, `StringModule`, `StringPower`, `StringDivision`, `StringMultiplication`, `StringSubtraction`, `StringAddition`, вычисляющих значения простых арифметических операций раскрытия скобок, раскрытия модуля, возведения в степень, деления, умножения, вычитания и сложения соответственно. Далее рассматривается вопрос тестирования только основного класса `StringExpressionCalculator`.
## Принцип тестирования
Тестирование осуществляется в следующей последовательности:
- с помощью класса `StringExpressionGenerator` генерируется строковое арифметическое выражение; 
- сгенерированное строковое арифметическое выражение передается тестируемому калькулятору класса `StringExpressionCalculator` и тестирующему калькулятору класса `StringExpressionTester`;
- осуществляется сравнения результатов вычисления тестируемого и тестирующего классов с заданной степенью точности. 
> Примечание: сгенерированное с помощью класса `StringExpressionGenerator` строковое арифметическое выражение до передачи его тестируемому калькулятору `StringExpressionCalculator` модифицируется до требуемого формата с помощью класса `StringExpressionModifier`.
## Обязательные настройки
До сборки и запуска проекта необходимо внесение следующих изменений в файлы конфигурации в папке `config`:
- в файле `postgresql.csv` должны быть заданы параметры соединения с базой данных **PostgreSQL**;
- в файле `gmail.csv` должны быть заданы параметры электронной почты **Gmail**.
> Примечание: база данных **PostgreSQL** необходима для работы классов `StringExpressionGenerator` и `StringExpressionTester`.
## Необязательные настройки
До сборки и запуска проекта может быть задана точность сравнения результатов вычисления строкового арифметического выражения в классе `StringExpressionCalculatorTest` (переменная **PRECISION**).
## Сборка и запуск тестирования
Сборка и запуск тестирования осуществляется **Maven** с помощью выполнения команды `mvn clean site`.
## Результат тестирования
Результатом тестирования является формирование в папке `output` ZIP-архива отчета `report.zip` и отправка его на электронную почту, указанную в файле конфигурации `gmail.csv`.