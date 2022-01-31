<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ attribute name="order" required="true" type="com.epam.training.epharmacy.entity.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<tr>
    <td>
        ${order.orderNumber}
    </td>
    <td>
        ${order.orderStatus.name()}
    </td>
    <td>
       ${order.paymentStatus.name()}
    </td>
    <td>
        <fmt:formatNumber type="number" maxFractionDigits="2" value="${order.totalPrice}"/>
    </td>
    <td>
       ${order.deliveryTime}
    </td>
    <td>
        <c:forEach var="orderEntry" items="${order.orderEntries}">

             ${orderEntry.medicine.commercialName} - ${orderEntry.medicine.packagePrice} $ x ${orderEntry.packageAmount} <br>

        </c:forEach>
    </td>
</tr>


