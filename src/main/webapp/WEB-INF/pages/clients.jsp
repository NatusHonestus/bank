<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список клиентов</title>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<table>
    <tr>
        <th width="3%">№</th>
        <th width="30%">Ф.И.О.</th>
        <th width="42%">Адрес</th>
        <th style="width:15%; align:center">Возраст</th>
        <th width="5%"></th>
        <th width="5%"></th>
    </tr>
    <c:forEach var="client" items="${clients}" varStatus="numb">
        <tr>
            <td>${numb.index+1}</td>
            <td><a href="/accounts/${client.id}">${client.getSurname()} ${client.getName()} ${client.getPatronymic()}</a></td>
            <td>${client.getAddress()}</td>
            <td>${client.getAge()}</td>
            <td><a href="/editClient/${client.getId()}">Редактировать</a></td>
            <td><a href="/deleteClient/${client.getId()}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<p>
    Страниц:
    <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
        <c:url value="/clients" var="url">
            <c:param name="page" value="${i.index}"/>
        </c:url>
        <a href="${url}">${i.index}</a>
    </c:forEach>
</p>
<p>
    <a href="/addClient">Добавить нового клиента</a>
</p>
</body>
</html>