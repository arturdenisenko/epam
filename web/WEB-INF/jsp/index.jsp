<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%--
  ~ @Denisenko Artur
  --%>

<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
<link rel="icon" href="${pageContext.request.contextPath}/periodicals.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/periodicals.ico">

<head>
    <title>Каталог печатных изданий РБ</title>
</head>
<body>
<p class="login"><a href="${pageContext.request.contextPath}/jsp/login.jsp">Вход</a></p>
<hr>
<div align="center">
    <jsp:include page="fragments/header.jsp"></jsp:include>
    <h2>Каталог печатных изданий</h2>
    <hr>
    <form action="/search/${periodical.name}">
        <label for="id"></label>
        <input size="70" type="text" id="id" name="id" placeholder="Поиск по всем каталогам, разделам и индексам"/>
        <br/>
        <br/>
        <input size="100" type="submit" value="Поиск в каталоге печатных изданий"/>
    </form>

    <p><a href="${pageContext.request.contextPath}/periodicals">Список периодических изданий</a></p>
    Категории

    <hr>
    <%-- А Б В Г Д Е Ё Ж З И К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Э Ю Я--%>
    <br>
    <table width="100%">
        <tbody>
        <tr>
            <c:forEach var="periodicalcategory" items="${requestScope.periodicalCategories}">
                <td><a href=""><c:out value="${periodicalcategory.name}"></c:out></a></td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
    <hr>
    <br>
    <table>
        <tbody>
        <tr>
            <c:forEach var="periodical" items="${requestScope.periodicals}">
                <td>
                    <table>
                        <tbody>
                        <tr>
                            <td><a href=""><c:out value="${periodical.name}"></c:out></a></td>
                        </tr>
                        <tr>
                            <td><c:out value="${periodical.about}"></c:out></td>
                        </tr>
                        <tr>
                            <td>Цена</td>
                        </tr>
                        <tr>
                            <td><c:out value="${periodical.costPerMonth}"></c:out></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Подписаться"/></td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
