<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags" %>

<html >
<head>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
</head>
<body>

<c:choose>
    <c:when test="${not empty order.orderEntries}">
       <order:order order="${order}"/>
    </c:when>
    <c:otherwise>
        Cart is empty
    </c:otherwise>
</c:choose>

</body>
</html>