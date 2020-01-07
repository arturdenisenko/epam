<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  ~ @Denisenko Artur
  --%>

<html>
<head>
    <title>Управление издателями</title>
</head>
<body>
<center>
    <h1>Управление издателями</h1>
    <h2>
        <a href="new">Добавить нового издателя</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">Все издатели</a>

    </h2>
</center>
<div align="center">
    <c:if test="${publisher != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${publisher == null}">
        <form action="insert" method="post" accept-charset="UTF-8">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${publisher != null}">
                            Редактировать
                        </c:if>
                        <c:if test="${publisher == null}">
                            Удалить
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${publisher != null}">
                    <input type="hidden" name="id" value="<c:out value='${publisher.id}' />"/>
                </c:if>
                <tr>
                    <th>Имя издателя:</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${publisher.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Сохранить"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>