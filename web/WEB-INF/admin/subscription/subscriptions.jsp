<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="navbar" %>
<%@ taglib uri="/WEB-INF/price.tld" prefix="p" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  ~ @Denisenko Artur
  --%>

<%--
  ~ @Denisenko Artur
  --%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%--Localization--%>
    <c:if test="${sessionScope.locale == null}">
        <fmt:setLocale value="ru"/>
    </c:if>
    <c:if test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="localization" var="bundle"/>
    <%----%>

    <title>Periodical - <fmt:message key="admin" bundle="${bundle}"/> - <fmt:message key="subscriptions"
                                                                                     bundle="${bundle}"/></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">

    <div class="col-md-2"></div>

    <div class="col-md-8">

        <h1><fmt:message key="subscriptions" bundle="${bundle}"/></h1>

        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="periodical" bundle="${bundle}"/></th>
                <th><fmt:message key="subscriptionType" bundle="${bundle}"/></th>
                <th><fmt:message key="from" bundle="${bundle}"/></th>
                <th><fmt:message key="to" bundle="${bundle}"/></th>
                <th><fmt:message key="price" bundle="${bundle}"/></th>
                <th><fmt:message key="user" bundle="${bundle}"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${page.items}" var="subscription">
                <tr>
                    <td>${subscription.periodical.name}</td>
                    <td>${subscription.type.name}</td>
                    <td>${subscription.startDate}</td>
                    <td>${subscription.endDate}</td>
                    <td><p:price price="${subscription.cost}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/user?id=${subscription.user.id}"> ${subscription.user.firstName} ${subscription.user.lastName}</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="row">
            <ul class="pager">
                <c:if test="${!page.first}">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/subscriptions?p=${page.number-1}&s=${page.size}">
                            <span aria-hidden="true">&larr;</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${!page.last}">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/subscriptions?p=${page.number+1}&s=${page.size}">
                            <span aria-hidden="true">&rarr;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>

    <div class="col-md-2"></div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

</body>
</html>
