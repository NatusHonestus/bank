<script type="text/javascript">
    function filter(client, beg_date, end_date){
        location.href = 'http://localhost:8080/transactions/'+client+'?beg_date='+beg_date+'&end_date='+end_date;
    }
</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список транзакций</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jQuery UI Datepicker - Default functionality</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#beg_date" ).datepicker({dateFormat: 'yy-mm-dd'});
            $( "#end_date" ).datepicker({dateFormat: 'yy-mm-dd'});
        } );
    </script>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<p>
    <a href="/clients">Клиенты</a>
</p>

<p>

    <label for="client_filter">Клиент:</label>
    <select name="client_filter" id="client_filter" onchange="filter(document.getElementById('client_filter').value, document.getElementById('beg_date').value, document.getElementById('end_date').value)">
        <c:out value="temp ${client.id}">is ${client.id}</c:out>
        <c:forEach var="client" items="${clients}">
            <option <c:if test="${client.id==clients_id}">selected</c:if> value="${client.id}">${client.surname} ${client.name} ${client.patronymic}</option>
        </c:forEach>
    </select>
</p>
<p>
    Начало периода: <input type="text" id="beg_date" onchange="filter(document.getElementById('client_filter').value, document.getElementById('beg_date').value, document.getElementById('end_date').value)">
    Окончание периода: <input type="text" id="end_date" onchange="filter(document.getElementById('client_filter').value, document.getElementById('beg_date').value, document.getElementById('end_date').value)">
</p>

<table>
    <tr>
        <th>№</th>
        <th>Тип транзакции</th>
        <th>Дата транзакции</th>
        <th>Счет отправителя</th>
        <th>Счет получателя</th>
        <th>Сумма</th>
        <th>Комментарий</th>
    </tr>
    <c:forEach var="transaction" items="${transactions}" varStatus="numb">
        <tr>
            <td>${numb.index+1}</td>
            <td><c:choose>
                <c:when test="${transaction.getType() eq 'r'}">Пополнение</c:when>
                <c:otherwise>Списание</c:otherwise>
            </c:choose>
            </td>
            <td>${transaction.oper_date.toString().substring(0,19)}</td>
            <td>${transaction.account_snd.getNumber()}</td>
            <td>${transaction.account_rcv.getNumber()}</td>
            <td>${transaction.getAmount()}</td>
            <td>${transaction.getComment()}</td>
        </tr>
    </c:forEach>
</table>
<p>
    Страниц:
    <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
        <c:url value="/transactions/${clients_id}/" var="url">
            <c:param name="page" value="${i.index}"/>
        </c:url>
        <a href="${url}">${i.index}</a>
    </c:forEach>
</p>
<p>
    <a href="/addTransaction/${clients_id}">Добавить транзакцию</a>
</p>
</body>
</html>