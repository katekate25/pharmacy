<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Registration</title>
</head>
<body>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.message" var="message"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.register" var="register" />

<form action="/pharmacy/controller" method="get">
 <input type="hidden" name="command" value="localization" />
  <input type="hidden" name="local" value="ru" />
 <input type="submit" value="${ru_button}" /> <br />
</form>

<form action="/pharmacy/controller" method="get">
 <input type="hidden" name="command" value="localization" />
  <input type="hidden" name="local" value="en" />
 <input type="submit" value="${en_button}" /> <br />
</form>

<c:if test='${param["userExists"]}'>
    <p style="color:red">User with such login already exists</p>
</c:if>
<h2><fmt:message bundle="${loc}" key="local.registration" /></h2>

<form action="/pharmacy/controller" method="get">
<input type="hidden" name="command" value="Registration"/>
<fmt:message bundle="${loc}" key="local.name.surname" />:
<input type="text" name="name" value=""/>
<br/>
<fmt:message bundle="${loc}" key="local.login" />:
<input type="text" name="login" value=""/>
<br/>
<fmt:message bundle="${loc}" key="local.password" />:
<input type="password" name="password" value=""/>
<br/>
<fmt:message bundle="${loc}" key="local.email" />:
<input type="text" name="email" value=""/>
<br/>
<fmt:message bundle="${loc}" key="local.telephone" />:
<input type="text" name="telNumber" value=""/>
<br/>

<input type="submit" value="${register}"/>
</form>


</body>
</html>