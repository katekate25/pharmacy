<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html >
<head>
<title>Personal Cabinet</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

<h1><fmt:message bundle="${loc}" key="local.personal.cabinet.title" /></h1>



<c:if test="${role='PHARMACIST'}">
<table>
<tr>
<td>orderNumber</td>
<td>client</td>
<td>deliveryTime</td>
<td>pharmacist</td>
<td>amount</td>
<td>orderStatus</td>
<td>paymentStatus</td>


</tr>
<c:forEach var = "order" items="${ordersAdmin}">
    <tr>
        <td><c:out value = "${order.orderNumber}"/> </td>
        <td> <c:out value="${order.client}"/> </td>
        <td> <fmt:formatDate pattern="yyyy-MM-dd" value="${order.deliveryTime.getTime()}" /> </td>
        <td> <c:out value="${order.pharmacist}"/> </td>
        <td> <c:out value="${order.amount}"/> </td>
        <td> <c:out value="${order.orderStatus}"/> </td>
        <td> <c:out value="${order.paymentStatus}" /> </td>
    </tr>
</c:forEach>

</table>
</c:if>

<a href="/pharmacy/controller?command=GO_TO_MEDICINE_FORM">Med form</a>
<a href="/pharmacy/controller?command=GO_TO_PRESCRIPTION_PAGE">Prescription form</a>



</body>
</html>