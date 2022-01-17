<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.enter" var="enter" />

<html>
<head>
<title>Log in</title>
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

<h2><fmt:message bundle="${loc}" key="local.logination.link" /></h2>
<br/>
<form action = "/pharmacy/controller" method = "post">
<input type="hidden" name="command" value="Login">
<fmt:message bundle="${loc}" key="local.login" />:
<input type="text" name="login" value=""/>
<br/>
<fmt:message bundle="${loc}" key="local.password" />:
<input type="password" name="password" value=""/>

<input type="submit" value="${enter}" />
</form>
<br/>
<c:if test="${param['wrongUser']}">
    Incorrect login or password
</c:if>
</body>
</html>