<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  ~ @Denisenko Artur
  --%>

<html>
<head>
    <title>Publishers Management</title>
</head>
<body>
<center>
    <h1>Publishers Management</h1>
    <h2>
        <a href="new">Add New Publisher</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Publishers</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Publishers</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="publisher" items="${publishersList}">
            <tr>
                <td><c:out value="${publisher.id}"/></td>
                <td><c:out value="${publisher.name}"/></td>
                <td>
                    <a href="edit?id=<c:out value='${publisher.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${publisher.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>