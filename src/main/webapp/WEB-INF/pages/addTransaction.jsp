<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Создание транзакции</title>
    <c:url value="/addTransaction" var="var"/>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<form action="${var}" method="POST">
    <input type="hidden" name="clientsid" value="${client.id}">

    <p>
        <label for="accounts_snd">Счет отправителя:</label>
        <select name="accounts_snd" id="accounts_snd">
            <option value=""></option>
            <c:forEach var="owner_account" items="${owner_accounts}">
                <option value="${owner_account.id}">${owner_account.number}</option>
            </c:forEach>
        </select>
        <c:out value="${snd_mess}">${snd_mess}</c:out>
    </p>
    <p>
        <label for="accounts_rcv">Счет получателя:</label>
        <select name="accounts_rcv" id="accounts_rcv">
            <option value=""></option>
            <c:forEach var="account" items="${accounts}">
                <option value="${account.id}">${account.number}</option>
            </c:forEach>
        </select>
        <c:out value="${rcv_cl}">${rcv_cl}</c:out>

    </p>

    <p>
        <label for="operdate">Дата и время:</label>
        <input readonly type="text" name="operdate" id="operdate" value="${oper_date}">
    </p>
    <p>
        <label for="amount">Сумма:</label>
        <input type="number" step="0.01" name="amount" id="amount">

        <label for="comment">Комментарий:</label>
        <input type="text" name="comment" id="comment">
    </p>
    <p>
        <input type="submit" value="Добавить">
    </p>
</form>
</body>
</html>