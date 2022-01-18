<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html >
<head>
<title>header</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

</head>
<body>
<form action="/pharmacy/controller" method="post">
 <input type="hidden" name="command" value="localization" />
  <input type="hidden" name="local" value="ru" />
 <input type="submit" value="${ru_button}" /> <br>
</form>

<form action="/pharmacy/controller" method="post">
 <input type="hidden" name="command" value="localization" />
  <input type="hidden" name="local" value="en" />
 <input type="submit" value="${en_button}" /> <br>
</form>



<c:if test="${not empty user}">
    Hello, <c:out value="${user.fullName}" />
    <a href="/pharmacy/controller?command=Logout">Logout</a>

    <a href="/pharmacy/controller?command=GO_TO_CART">Cart</a>

    <a href="/pharmacy/controller?command=GO_TO_PERSONAL_CABINET">To Personal Cabinet</a> <br>
</c:if>

<c:if test="${empty user}">
<a href="/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE"><fmt:message bundle="${loc}" key="local.registration.link" /></a>
<a href="/pharmacy/controller?command=GO_TO_LOGIN_PAGE"><fmt:message bundle="${loc}" key="local.logination.link" /></a>
</c:if>

<a href="/pharmacy/controller?command=GO_TO_MAIN_PAGE">To Main Page</a>
<a href="/pharmacy/controller?command=GO_TO_CATALOG"><fmt:message bundle="${loc}" key="local.catalog" /></a>
<a href="/pharmacy/controller?command=SHOW_DOCTORS">Список врачей</a>


</body>
</html>