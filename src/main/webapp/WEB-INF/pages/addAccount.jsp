<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Добавление счета</title>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:url value="/addAccount" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="clientsid" id="clientsid" value="${client.id}">
    <input type="hidden" name="balance" id="balance" value="0.0">
    <p>
        <label for="full_name">Клиент:</label>
        <input readonly type="text" name="full_name" id="full_name" value="${fn:trim(client.surname)} ${fn:trim(client.name)} ${fn:trim(client.patronymic)}">
    </p>

    <p>
        <label for="number">Номер счета:</label>
        <input type="text" name="number" id="number">
    </p>
    <p>
        <input type="submit" value="Добавить">
    </p>
</form>
</body>
</html>