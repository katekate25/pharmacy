<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html >
<head>
<title>Welcome</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
</head>
<body>

<table>
<tr>
<td>medicine</td>
<td>packagePrice</td>
<td>packageAmount</td>
</tr>

<c:forEach var = "orderEntry" items="${orderEntries}">
    <tr>
        <td><a href="/pharmacy/controller?command=SHOW_MEDICINE_DETAILS&serialNumber=${medicine.serialNumber}" >${medicine.commercialName}</a></td>
        <td> <c:out value="${orderEntry.medicine.commercialName}"/> , <c:out value="${orderEntry.medicine.medicineDose}"/> mg </td>
        <td> <c:out value="${orderEntry.medicine.packagePrice}" /> </td>
        <td> <c:out value="${orderEntry.packageAmount}" /> </td>
    </tr>
</c:forEach>
</table>
SHOW_TOTAL_PRICE

<form action = "/pharmacy/controller" method = "post">
        <input type="hidden" name="command" value="SHOW_TOTAL_PRICE">
        Итого:
        <input type="text" name="totalPrice" max="&{totalPrice}"/>

        <br/>

        <input type="submit" value="Оформить" />
        </form>

<form action = "/pharmacy/controller" method = "post">
        <input type="hidden" name="command" value="ADD_ORDER">
        Клиент, время доставки
        статус оплаты
        <input type="number" name="packageAmount" max="&{medicineBySeries.productBalance}"/>
        <input type="hidden" name="serialNumber" value="${medicineBySeries.serialNumber}">
        <br/>

        <input type="submit" value="Оформить" />
        </form>


</body>
</html>