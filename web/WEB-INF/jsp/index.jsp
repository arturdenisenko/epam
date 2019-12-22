<jsp:include page="fragments/header.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="mainContainer">
    <p class="login"><a href="user/login.html">Вход</a></p>
    <hr>
    <a href="index.jsp"> <img alt="Belpost logo" src="${pageContext.request.contextPath}/images/belpost-logo.png"/></a>
    <h2>Каталог печатных изданий</h2>
    <hr>
    <form action="/search/${id}">
        <label for="id"></label>
        <input size="70" type="text" id="id" name="id" placeholder="Поиск по всем каталогам, разделам и индексам"/>
        <br/>
        <br/>
        <input size="100" type="submit" value="Поиск в каталоге печатных изданий"/>
    </form>
    <p><a href="${pageContext.request.contextPath}/periodicals">Список периодических изданий</a></p>
    Категории
    <hr>
    <br>
    А Б В Г Д Е Ё Ж З И К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ
    <br>
    Политика
    <br>
    Новости
    <br>
    Спорт
</div>
<jsp:include page="fragments/footer.jsp">
