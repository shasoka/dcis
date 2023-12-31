# Практическая работа №2

## Spring, внедрение зависимостей. Способ 1

**Цель**: ознакомиться с механизмом внедрения зависимостей в Spring.

**Общая постановка задачи**: Необходимо создать приложение, в котором будут объявлены Spring-конфигурации при помощи xml-конструкций

В каждом варианте есть сущность (класс), необходимо создать интерфейс (самостоятельно на усмотрение студента) и классы, его имплементирующие. Объекты классов, имплементирующих данный интерфейс, будут передаваться в качестве зависимостей. Выполнить связывание и получить объекты из контекста. Продемонстрировать результаты в простейшем консольном приложении.

Необходимо:

- Реализовать внедрение простых значений через конструктор
- Реализовать внедрение зависимости по ссылке через конструктор
- Интерфейс должен содержать как минимум один метод
- Классы, имплементирующие интерфейс, должны содержать как минимум одно поле (у разных классов - разные)
- Зависимый класс должен содержать метод, который на основе вызова метода у зависимости выводил бы некоторое сообщение в консоль (Например для класса Автомобиля, в который внедряются Двигатели. Они могут выдавать свою мощность, а автомобиль может выводить сообщение, с какой скоростью он может двигаться).
- Реализовать внедрение простых значений из внешнего файла через setter

## Результат работы

**Вариант 6**. Зависимая сущность: кинотеатр, внедряемые зависимости: зритель, фильм, кинозал.

**Инструкция по сборке и запуску проекта**:
1. [Загрузите содержимое папки prac_2](https://minhaskamal.github.io/DownGit/#/home?url=https://github.com/shasoka/dcis/tree/master/practice/prac_2/prac_2);
2. Проверьте наличие Maven на вашей машине:
```
mvn -v
```
3. Находясь в корне папки prac_2 скомпилируйте проект:
```
mvn compile
```
4. Запустите скомпилированный проект:
```
mvn exec:java
```
5. Победа! ✌️
