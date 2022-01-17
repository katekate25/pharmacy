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
 <input type="submit" value="${ru_button}" />
</form>

<form action="/pharmacy/controller" method="post">
 <input type="hidden" name="command" value="localization" />
  <input type="hidden" name="local" value="en" />
 <input type="submit" value="${en_button}" /> <br />
</form>



<c:if test="${not empty user}">
    Hello, <c:out value="${user.fullName}" />
    <form action = "/pharmacy/controller" method = "post">
    <input type="hidden" name="command" value="Logout">
    <input type="submit" value="Logout" />
    </form>

    <form action = "/pharmacy/controller" method = "post">
        <input type="hidden" name="command" value="GO_TO_CART">
        <input type="submit" value="Cart" />
        </form>
</c:if>

<c:if test="${not empty user}">
  <form action = "/pharmacy/controller" method = "post">
        <input type="hidden" name="command" value="GO_TO_LOGIN_PAGE">
        <input type="submit" value="Sign in" />
  </form>
</c:if>


</body>
</html>