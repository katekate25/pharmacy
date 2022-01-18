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
<br />
<h1><fmt:message bundle="${loc}" key="local.greeting.main.page" /></h1>
<br />

<c:if test="${not empty user}">
    Hello, <c:out value="${user.fullName}" />
</c:if>

<c:if test="${accessDenied}">
   Access Denied />
</c:if>




</body>
</html>
