<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<common:head title="Cart" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <common:header />
    <div class="container">

        <c:choose>
            <c:when test="${not empty order.orderEntries}">
                <order:cart order="${order}" />
            </c:when>
            <c:otherwise>
              <div class="card text-center">
              <div class="card-body"><fmt:message bundle="${loc}" key="local.cart.empty" /></div>
              </div>
            </c:otherwise>
        </c:choose>

    </div>
    <common:footer />
</body>

</html>