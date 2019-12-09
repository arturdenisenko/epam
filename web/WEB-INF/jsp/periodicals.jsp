<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<link rel="icon" href="${pageContext.request.contextPath}/periodicals.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/periodicals.ico">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>periodicals</title>
</head>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Издатель</th>
        <th>Категория</th>
        <th>Периодичность в шесть месяцев</th>
        <th>Минимальный период подписки</th>
        <th>Стоимость в месяц</th>
    </tr>
    <c:forEach var="periodical" items="${requestScope.periodicals}">

        <tr>
            <td><c:out value="${periodical.id}"/></td>
            <td><c:out value="${periodical.name}"/></td>
            <td><c:out value="${periodical.about}"/></td>
            <td><c:out value="${periodical.publisher.name}"/></td>
            <td><c:out value="${periodical.periodicalCategory.name}"/></td>
            <td><c:out value="${periodical.periodicityInSixMonth}"/></td>
            <td><c:out value="${periodical.minSubscriptionPeriod}"/></td>
            <td><c:out value="${periodical.costPerMonth}"/></td>
        </tr>
    </c:forEach>
</table>

<h3>Добавление нового издания в каталог</h3>

<form method="post" action="">
    <label><input type="text" name="name"></label>Название<br>
    <label><input type="text" name="about"></label>Описание<br>
    <label><input type="text" name="publisher"></label>Издатель<br>
    <label><input type="text" name="category"></label>Категория<br>
    <label><input type="number" min="0" name="periodicityInSixMonth"></label>Периодичность в шесть месяцев<br>
    <label><input type="number" min="0" name="minSubscriptionPeriod"></label>Минимальный период подписки<br>
    <label><input type="number" step="0.01" min="0" name="costPerMonth"></label>Стоимость в месяц<br>
    <br>
    <input type="submit" value="Добавить" name="OK"><br>
</form>

<body>

</body>
</html>
