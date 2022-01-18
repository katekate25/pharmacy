<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Welcome</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.message" var="message"/>


</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
<br />
<h1><fmt:message bundle="${loc}" key="local.invitation" /></h1>






</body>
</html>