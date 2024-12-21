# Описание

Данный проект сравнивает производительность 4 способов вызова метода `Student#name()`:

1.  **Прямой доступ**
2.  `java.lang.reflect.Method`
3.  `java.lang.invoke.MethodHandles`
4.  `java.lang.invoke.LambdaMetafactory`

Я оставил компьютер без фоновой нагрузки и установил 2 минуты на выполнения бенчмарка. 
Ниже представлена получившаяся таблица

| Benchmark                              | Mode | Cnt | Score | Error | Units |
|----------------------------------------|------|-----|-------|-------|-------|
| ReflectionBenchmark.directAccess       | avgt |     | 0,550 |       | ns/op |
| ReflectionBenchmark.lambdaMetafactory  | avgt |     | 0,769 |       | ns/op |
| ReflectionBenchmark.methodHandle       | avgt |     | 3,304 |       | ns/op |
| ReflectionBenchmark.reflection         | avgt |     | 5,897 |       | ns/op |
