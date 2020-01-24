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

    <title>Periodicals - <fmt:message key="admin" bundle="${bundle}"/> - <fmt:message key="editPeriodicals"
                                                                                      bundle="${bundle}"/></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">

    <div class="col-md-2"></div>

    <div class="col-md-7">

        <form class="form-horizontal" method="post"
              action="${pageContext.request.contextPath}/admin/periodicals/update?id=${periodical.id}"
              enctype="multipart/form-data">

            <legend><fmt:message key="editPeriodicals" bundle="${bundle}"/></legend>

            <div class="form-group">
                <label class="col-md-3 control-label" for="title">
                    <fmt:message key="title" bundle="${bundle}"/>
                </label>
                <div class="col-md-8">
                    <input id="title" name="title" placeholder="title" class="form-control input-md" type="text"
                           value="${periodical.name}" required>

                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label" for="price">
                    <fmt:message key="price" bundle="${bundle}"/>
                </label>
                <div class="col-md-8">
                    <input id="price" name="price" placeholder="" class="form-control input-md" type="number"
                           value="${periodical.price}" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label" for="publisher">
                    <fmt:message key="publisher" bundle="${bundle}"/>
                </label>
                <div class="col-md-8">
                    <select id="publisher" name="publisher" class="form-control">
                        <c:forEach items="${publishers}" var="publisher">
                            <option value="${publisher.id}"
                                ${periodical.publisher.id == publisher.id ? 'selected' : ''}>${publisher.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label" for="category">
                    <fmt:message key="category" bundle="${bundle}"/>
                </label>
                <div class="col-md-8">
                    <select id="category" name="category" class="form-control">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}"
                                ${periodical.category.id == category.id ? 'selected' : ''}>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label" for="description">
                    <fmt:message key="description" bundle="${bundle}"/>
                </label>
                <div class="col-md-8">
                    <textarea class="form-control" id="description" name="description" maxlength="300"
                              cols="5">${periodical.name}</textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label" for="enabled">
                    <fmt:message key="enabled" bundle="${bundle}"/>
                </label>
                <div class="col-md-8 checkbox">
                    <input id="enabled" name="enabled" placeholder="" class="form-control input-md"
                           type="checkbox" value="true" <c:if test="${periodical.active}"><c:out
                            value="checked"/></c:if>/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">
                    <fmt:message key="image" bundle="${bundle}"/>
                </label>
                <div class="col-md-8">
                    <input type="file" id="image" name="image"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-2">
                    <button id="submit" name="submit" type="submit" class="btn btn-primary">
                        <fmt:message key="update" bundle="${bundle}"/>
                    </button>
                </div>
                <div class="col-md-2">
                    <a class='btn btn-danger'
                       href="${pageContext.request.contextPath}/admin/periodicals/delete?id=${magazine.id}">
                        <span class="glyphicon glyphicon-remove"></span> <fmt:message key="delete" bundle="${bundle}"/></a>
                </div>
            </div>
        </form>

        <a class='btn btn-default' href="${pageContext.request.contextPath}/admin/periodicals?p=1&s=10">
            <span class="glyphicon glyphicon-chevron-left"></span> <fmt:message key="back" bundle="${bundle}"/></a>

    </div>

    <div class="col-md-3">
        <a href="#" class="thumbnail">
            <c:if test="${periodical.imageLink == ''}">
                <img src="${pageContext.request.contextPath}/images/noImagePeriodical.png"/>

            </c:if>
            <c:if test="${periodical.imageLink != ''}">
                <img src="${pageContext.request.contextPath}/images/${periodical.imageLink}"
                     alt="${periodical.name}">
            </c:if>
        </a>
    </div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

</body>
</html>
