<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ attribute name="order" required="true" type="com.epam.training.epharmacy.entity.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<table>
    <tr>
        <td>medicine</td>
        <td>packageAmount</td>
        <td>packagePrice</td>
    </tr>
<c:forEach var ="orderEntry" items="${order.orderEntries}">
    <tr>
        <td>${orderEntry.medicine.commercialName}</td>
        <td>${orderEntry.packageAmount}</td>
        <td>${orderEntry.medicine.packagePrice}</td>
    </tr>
</c:forEach>
    <tr>
        <td></td><td></td><td>Total price: ${order.totalPrice}</td>
    </tr>
</table>
