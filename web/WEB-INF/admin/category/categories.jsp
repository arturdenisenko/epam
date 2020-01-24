<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
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
    <meta name="author" content="Denisenko Artur">

    <%--Localization--%>
    <c:if test="${sessionScope.locale == null}">
        <fmt:setLocale value="ru"/>
    </c:if>
    <c:if test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="localization" var="bundle"/>
    <%----%>

    <title>Periodicals - <fmt:message key="admin" bundle="${bundle}"/> - <fmt:message key="categories"
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

        <c:if test="${updateSuccess != null && updateSuccess == true}">
            <div class="alert alert-success" role="alert"><fmt:message key="categoryUpdated" bundle="${bundle}"/></div>
        </c:if>
        <c:if test="${deletionSuccess != null && deletionSuccess == true}">
            <div class="alert alert-success" role="alert"><fmt:message key="categoryDeleted" bundle="${bundle}"/></div>
        </c:if>
        <c:if test="${deletionSuccess != null && deletionSuccess == false}">
            <div class="alert alert-danger" role="alert"><fmt:message key="categoryDeleteFail"
                                                                      bundle="${bundle}"/></div>
        </c:if>

        <h1><fmt:message key="categories" bundle="${bundle}"/></h1>



        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="category" bundle="${bundle}"/></th>
                <th><fmt:message key="actions" bundle="${bundle}"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td class="col-md-3">${category.name}</td>
                    <td class="col-md-1">
                        <a class='btn btn-info btn-xs'
                           href="${pageContext.request.contextPath}/admin/categories/edit?id=${category.id}">
                            <span class="glyphicon glyphicon-edit"></span> <fmt:message key="edit" bundle="${bundle}"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pull-left">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/categories/add" role="button">
                <fmt:message key="add" bundle="${bundle}"/>
            </a>
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
