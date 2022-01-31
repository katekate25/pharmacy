<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<html >
    <common:head title="Orders" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <div class="container">
        <common:header />

        <br><br><br>

        <c:if test="${not empty orders}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th>
                        Order number
                    </th>
                    <th>
                        Order status
                    </th>
                    <th>
                        Payment status
                    </th>
                    <th>
                        Total price
                    </th>
                    <th>
                       Delivery time
                    </th>
                    <th>
                       Medicines
                    </th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="order" items="${orders}">
                    <order:orderItem order="${order}"/>
                </c:forEach>
            <tbody>

        </table>
        </c:if>
        <c:if test="${empty orders}">
            You do not have orders yet
        </c:if>
        <common:footer />
    </div>
</body>
</html>