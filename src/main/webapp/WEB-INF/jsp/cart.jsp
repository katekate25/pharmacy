<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<html>
<common:head title="Cart" />

<body>
    <div class="container">
        <common:header />

        <c:choose>
            <c:when test="${not empty order.orderEntries}">
                <order:order order="${order}" />
            </c:when>
            <c:otherwise>
                <fmt:message bundle="${loc}" key="local.cart.empty" />
            </c:otherwise>
        </c:choose>

    </div>
</body>

</html>