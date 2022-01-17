<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<html>
<head>
<title>Log in</title>
<style>
   table {
    width: 100%;
    border: 4px double black;
    border-collapse: collapse;
   }
   th {
    text-align: left;
    background: #ccc;
    padding: 5px;
    border: 1px solid black;
   }
   td {
    padding: 5px;
    border: 1px solid black;
   }
  </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>


<h2><fmt:message bundle="${loc}" key="local.catalog.name" /></h2>
<form action = "/pharmacy/controller" method = "post">
 <input type="hidden" name="command" value="SEARCH_MEDICINES">
 <fmt:message bundle="${loc}" key="local.search.medicine.name" />:
 <input type="text" name="commercialName" value=""/>
 <input type="submit" value="Enter"/>
</form>
<br/>
<table>
<tr>
<td>commercialName</td>
<td>internationalName</td>
<td>medicineForm</td>
<td>medicineDose</td>
<td>serialNumber</td>
<td>packagePrice</td>
<td>ExpirationDate</td>
<td>invoiceNumber</td>
<td>arrivalDate</td>
<td>productArrival</td>
<td>productBalance</td>
<td>prescriptionRequired</td>
<td>producer</td>
<td>producer Country</td>
<td>diseaseGroup</td>
</tr>

<c:forEach var = "medicine" items="${medicines}">
    <tr>
        <td><a href="/pharmacy/controller?command=SHOW_MEDICINE_DETAILS&serialNumber=${medicine.serialNumber}" >${medicine.commercialName}</a></td>
        <td> <c:out value="${medicine.internationalName}"/> </td>
        <td> <c:out value="${medicine.medicineForm}"/> </td>
        <td> <c:out value="${medicine.medicineDose}"/> </td>
        <td> <c:out value="${medicine.serialNumber}"/> </td>
        <td> <c:out value="${medicine.packagePrice}" /> </td>
        <td> <c:out value="${medicine.medicineExpirationDate}" /> </td>
        <td> <c:out value="${medicine.invoiceNumber}"/> </td>
        <td> <c:out value="${medicine.arrivalDate}" /> </td>
        <td> <c:out value="${medicine.productArrival}"/> </td>
        <td><c:out value = "${medicine.productBalance}"/> </td>
        <td> <c:out value="${medicine.prescriptionRequired}"/> </td>
        <td> <c:out value="${medicine.producer.producerFactoryName}"/> </td>
        <td> <c:out value="${medicine.producer.producerCountry}"/> </td>
        <td> <c:out value="${medicine.diseaseGroup}"/> </td>
    </tr>
</c:forEach>
</table>

<c:if test="${not empty analogueFind}">
<br><br><br> Analogs <br>
<table>
<tr>
<td>commercialName</td>
<td>internationalName</td>
<td>medicineForm</td>
<td>medicineDose</td>
<td>serialNumber</td>
<td>packagePrice</td>
<td>ExpirationDate</td>
<td>invoiceNumber</td>
<td>arrivalDate</td>
<td>productArrival</td>
<td>productBalance</td>
<td>prescriptionRequired</td>
<td>producer</td>
<td>producer Country</td>
<td>diseaseGroup</td>
</tr>

<c:forEach var = "medicine" items="${analogueFind}">
    <tr>
        <td><a href="/pharmacy/controller?command=GO_TO_MEDICINE&serialNumber=${medicine.serialNumber}" >${medicine.commercialName}</a></td>
        <td> <c:out value="${medicine.internationalName}"/> </td>
        <td> <c:out value="${medicine.medicineForm}"/> </td>
        <td> <c:out value="${medicine.medicineDose}"/> </td>
        <td> <c:out value="${medicine.serialNumber}"/> </td>
        <td> <c:out value="${medicine.packagePrice}" /> </td>
        <td> <fmt:formatDate pattern="yyyy-MM-dd" value="${medicine.medicineExpirationDate.getTime()}" /> </td>
        <td> <c:out value="${medicine.invoiceNumber}"/> </td>
        <td> <fmt:formatDate pattern="yyyy-MM-dd" value="${medicine.arrivalDate.getTime()}" /> </td>
        <td> <c:out value="${medicine.productArrival}"/> </td>
        <td><c:out value = "${medicine.productBalance}"/> </td>
        <td> <c:out value="${medicine.prescriptionRequired}"/> </td>
        <td> <c:out value="${medicine.producer.producerFactoryName}"/> </td>
        <td> <c:out value="${medicine.producer.producerCountry}"/> </td>
        <td> <c:out value="${medicine.diseaseGroup}"/> </td>
    </tr>
</c:forEach>
</table>
</c:if>


</body>
</html>