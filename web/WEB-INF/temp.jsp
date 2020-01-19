<%--
  ~ @Denisenko Artur
  --%>

<%--
  ~ @Denisenko Artur
  --%>

<%--
  Created by IntelliJ IDEA.
  User: XXX
  Date: 20.01.2020
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/periodical?id=${periodical.id}"
   class="thumbnail">
    <img src="img/${periodical.imageLink}" class="img-fluid" alt="">

</a>
</body>
</html>
