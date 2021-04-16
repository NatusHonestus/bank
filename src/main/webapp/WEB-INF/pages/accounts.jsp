<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список счетов</title>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<p>
    <a href="/clients">Клиенты</a>
    <a href="/transactions/${clients_id}">Транзакции</a>
</p>
<table>
    <tr>
        <th>№</th>
        <th>Клиент</th>
        <th>Счет</th>
        <th>Баланс</th>
        <th></th>
    </tr>
    <c:forEach var="account" items="${accounts}" varStatus="numb">
        <tr>
            <td>${numb.index+1}</td>
            <td>${account.clients_id.getSurname()} ${account.clients_id.getName()} ${account.clients_id.getPatronymic()}</td>
            <td>${account.number}</td>
            <td>${account.balance}</td>
            <td><a href="/deleteAccount/${account.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<p>
    Страниц:
    <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
        <c:url value="/accounts/${clients_id}/" var="url">
            <c:param name="page" value="${i.index}"/>
        </c:url>
        <a href="${url}">${i.index}</a>
    </c:forEach>
</p>
<p>
    <a href="/addAccount/${clients_id}">Добавить счет</a>
</p>
</body>
</html>