<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ attribute name="order" required="true" type="com.epam.training.epharmacy.entity.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.delete" var="remove_button" />

<table>
    <tr>
        <td><fmt:message bundle="${loc}" key="local.prescribed.medicine" /></td>
        <td><fmt:message bundle="${loc}" key="local.prescribed.amount" /></td>
        <td><fmt:message bundle="${loc}" key="local.packagePrice" /></td>
    </tr>
<c:forEach var ="orderEntry" items="${order.orderEntries}">
    <tr>
        <td>${orderEntry.medicine.commercialName}</td>
        <td>${orderEntry.packageAmount}</td>
        <td>${orderEntry.medicine.packagePrice}руб</td>
        <td>
        <form action = "/pharmacy/controller" method = "post">
         <input type="hidden" name="command" value="DELETE_ENTRY">
         <input type="hidden" name="entryId" value="${orderEntry.id}"/>
         <input type="submit" value="${remove_button}"/>
        </form>
        </td>
    </tr>
</c:forEach>
    <tr>
        <td></td><td></td><td><fmt:message bundle="${loc}" key="local.totalPrice" />: ${order.totalPrice}</td>
    </tr>
</table>

<form action = "/pharmacy/controller" method = "post">
   <input type="hidden" name="command" value="PAY_ORDER">
   <p><fmt:message bundle="${loc}" key="local.choose.deliveryTime" /></p>
   <input type="datetime-local" name="deliveryTime">
   <p><fmt:message bundle="${loc}" key="local.add.orderNumber" /></p>
      <input type="text" name="orderNumber">
   <p><input type="submit" value="Pay"></p>
</form>

