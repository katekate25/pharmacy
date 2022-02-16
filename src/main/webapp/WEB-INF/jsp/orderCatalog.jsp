<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.type.customer.name" var="customer" />
<html >
    <common:head title="Orders" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;
      }
      .table thead th {
          vertical-align: middle;
          }
       </style>
<body>
    <common:header />
    <br><br>
    <div class="container">
         <div class="row">
            <div class="col-md-8 offset-md-1">
                <form class="form-inline" action="/pharmacy/controller" method="post">
                    <input type="hidden" name="command" value="GO_TO_ORDER_LIST">
                    <input class="form-control col-md-4 mr-sm-3 mb-2" type="text" name="customerName" value="" placeholder="${customer}"/>
                    <button class="btn btn-dark mb-2" type="submit"><fmt:message bundle="${loc}" key="local.search.button" /></button>
                </form>
            </div>
        </div>

        <br><br>
        <c:if test="${not empty orderList}">
        <div class="row justify-content-center">
            <div class="col-md-10">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th>
                        <fmt:message bundle="${loc}" key="local.order.number" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.customer" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.order.status" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.payment.status" />
                    </th>

                    <th>
                       <fmt:message bundle="${loc}" key="local.delivery.time" />
                    </th>
                    <th>
                       <fmt:message bundle="${loc}" key="local.ordered" />
                    </th>
                     <th>
                       <fmt:message bundle="${loc}" key="local.total.price" />
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
                                        <c:if test="${order.orderStatus ne 'CANCELLED'}">
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
                                        </c:if>
                                        <c:if test="${order.orderStatus eq 'CANCELLED'}">
                                        <c:out value="${order.orderStatus}" />
                                        </c:if>
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

    </div>
</div>
        </c:if>
        <c:if test="${empty orderList}">
            <fmt:message bundle="${loc}" key="local.not.found" />
        </c:if>
    </div>
    <common:footer />
</body>
</html>