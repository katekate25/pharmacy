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
    <common:header />
    <div class="container">

        <br><br><br>

        <c:if test="${not empty orderList}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th>
                        Order number
                    </th>
                    <th>
                        Customer
                    </th>
                    <th>
                        Order status
                    </th>
                    <th>
                        Payment status
                    </th>

                    <th>
                       Delivery time
                    </th>
                    <th>
                       Medicines
                    </th>
                     <th>
                       Total price
                     </th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="order" items="${orderList}">
                                    <tr>
                                        <td>
                                            <c:out value="${order.orderNumber}" />
                                        </td>
                                        <td>
                                            <c:out value="${order.client.fullName}" /><br>
                                            <c:out value="${order.client.login}" />
                                        </td>

                                        <td>
                                            <form class="form-inline" action = "/pharmacy/controller" method = "post">
                                                 <input type="hidden" name="command" value="UPDATE_STATUS">
                                                 <input type="hidden" name="orderNumber" value="${order.orderNumber}"/>
                                                 <select class="form-control btn-light" name="orderStatus" onchange="this.form.submit()">
                                                    <option class="dropdown-item" value="READY_FOR_PAYMENT" ${order.orderStatus.name() eq 'READY_FOR_PAYMENT' ? 'selected' : ''}>Ready for payment</option>
                                                    <option class="dropdown-item" value="PAID" ${order.orderStatus.name() eq 'PAID' ? 'selected' : ''}>Paid</option>
                                                    <option class="dropdown-item" value="COMPLETED" ${order.orderStatus.name() eq 'COMPLETED' ? 'selected' : ''}>Completed</option>
                                                    <option class="dropdown-item" value="CANCELLED" ${order.orderStatus.name() eq 'CANCELLED' ? 'selected' : ''}>Canceled</option>
                                                 </select>
                                            </form>
                                        </td>
                                        <td>
                                            <c:out value="${order.paymentStatus}" />
                                        </td>
                                        <td>
                                            <c:out value="${order.deliveryTime}" />
                                        </td>
                                        <td>
                                        <c:forEach var="orderEntry" items="${order.orderEntries}">
                                            ${orderEntry.medicine.commercialName} - ${orderEntry.medicine.packagePrice} $ x ${orderEntry.packageAmount} <br>
                                        </c:forEach>
                                        </td>
                                        <td>
                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${order.totalPrice}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
            <tbody>

        </table>
        </c:if>
        <c:if test="${empty orderList}">
            You do not have orders yet
        </c:if>
    </div>
    <common:footer />
</body>
</html>