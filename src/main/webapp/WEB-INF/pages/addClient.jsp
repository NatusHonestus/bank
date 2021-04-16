<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <c:if test="${empty client.name}">
        <title>Добавление клиента</title>
        <c:url value="/addClient" var="var"/>
    </c:if>
    <c:if test="${!empty client.name}">
        <title>Редактирование клиента</title>
        <c:url value="/editClient" var="var"/>
    </c:if>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<form action="${var}" method="POST">
    <c:if test="${!empty client.id}">
        <input type="hidden" name="id" value="${client.id}">
    </c:if>
    <p>
        <label for="surname">Фамилия:</label>
        <c:if test="${!empty client.surname}">
        <input type="text" name="surname" id="surname" value="${fn:trim(client.surname)}">
        </c:if>
        <c:if test="${empty client.surname}">
        <input stype="text" name="surname" id="surname">
        </c:if>
    <p>
        <label for="name">Имя:</label>
        <c:if test="${!empty client.name}">
            <input type="text" name="name" id="name" value="${fn:trim(client.name)}">
        </c:if>
        <c:if test="${empty client.name}">
            <input type="text" name="name" id="name">
        </c:if>
    </p>
    <p>
        <label for="patronymic">Отчество:</label>
        <c:if test="${!empty client.patronymic}">
            <input type="text" name="patronymic" id="patronymic" value="${fn:trim(client.patronymic)}">
        </c:if>
        <c:if test="${empty client.patronymic}">
            <input type="text" name="patronymic" id="patronymic">
        </c:if>
    </p>

    <p>
        <label for="address">Адрес:</label>
        <c:if test="${!empty client.address}">
            <input type="text" name="address" id="address" value="${fn:trim(client.address)}">
        </c:if>
        <c:if test="${empty client.address}">
            <input type="text" name="address" id="address">
        </c:if>
    </p>

    <p>
        <label for="age">Возраст:</label>
        <c:if test="${!empty client.age}">
            <input type="number" name="age" id="age" value="${client.age}">
        </c:if>
        <c:if test="${empty client.age}">
            <input type="number" name="age" id="age">
        </c:if>
    </p>
    <p>
        <c:if test="${empty client.name}">
            <input type="submit" value="Добавить">
        </c:if>
        <c:if test="${!empty client.name}">
            <input type="submit" value="Сохранить">
        </c:if>
    </p>
</form>
</body>
</html>