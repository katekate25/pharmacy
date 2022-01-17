<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Welcome</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.message" var="message"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

</head>
<body>
<h1><fmt:message bundle="${loc}" key="local.invitation" /></h1>
<a href="/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE"><fmt:message bundle="${loc}" key="local.registration.link" /></a>

<a href="/pharmacy/controller?command=GO_TO_LOGIN_PAGE"><fmt:message bundle="${loc}" key="local.logination.link" /></a>


<c:set var="role" scope="session" value="pharmacist" />
<c:if test="${not empty role and role eq 'pharmacist'}" var="testif">
<p>

</p>
</c:if>
<c:out value="${testif}" />


<form action="/pharmacy/controller" method="post">
 <input type="hidden" name="command" value="localization" />
  <input type="hidden" name="local" value="ru" />
 <input type="submit" value="${ru_button}" /> <br />
</form>

<form action="/pharmacy/controller" method="post">
 <input type="hidden" name="command" value="localization" />
  <input type="hidden" name="local" value="en" />
 <input type="submit" value="${en_button}" /> <br />
</form>


</body>
</html>