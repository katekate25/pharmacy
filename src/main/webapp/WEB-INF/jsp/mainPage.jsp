<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<html>
<head>
<title>Welcome</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

<h1><fmt:message bundle="${loc}" key="local.greeting.main.page" /></h1>
<br />

<c:if test="${not empty user}">
    Hello, <c:out value="${user.fullName}" />
</c:if>

<br />
<a href="/pharmacy/controller?command=GO_TO_CATALOG"><fmt:message bundle="${loc}" key="local.catalog" /></a>
<br />
<a href="/pharmacy/controller?command=GO_TO_PERSONAL_CABINET"><fmt:message bundle="${loc}" key="local.personal.cabinet" /></a>
<br/>
<a href="/pharmacy/controller?command=SHOW_DOCTORS">Список врачей</a>

</body>
</html>
