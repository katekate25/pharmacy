<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<html>
<head>
<title>Medicine Page</title>
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
<td>Create Prescription</td>
</tr>


    <tr>
        <td>${medicineBySeries.commercialName}</td>
        <td> <c:out value="${medicineBySeries.internationalName}"/> </td>
        <td> <c:out value="${medicineBySeries.medicineForm}"/> </td>
        <td> <c:out value="${medicineBySeries.medicineDose}"/> </td>
        <td> <c:out value="${medicineBySeries.serialNumber}"/> </td>
        <td> <c:out value="${medicineBySeries.packagePrice}" /> </td>
        <td> <c:out value="${medicineBySeries.medicineExpirationDate}" />  </td>
        <td> <c:out value="${medicineBySeries.invoiceNumber}"/> </td>
        <td> <c:out value="${medicineBySeries.arrivalDate}" /> </td>
        <td> <c:out value="${medicineBySeries.productArrival}"/> </td>
        <td><c:out value = "${medicineBySeries.productBalance}"/> </td>
        <td> <c:out value="${medicineBySeries.prescriptionRequired}"/> </td>
        <td> <c:out value="${medicineBySeries.producer.producerFactoryName}"/> </td>
        <td> <c:out value="${medicineBySeries.producer.producerCountry}"/> </td>
        <td> <c:out value="${medicineBySeries.diseaseGroup}"/> </td>
        <td><a href="/pharmacy/controller?command=GO_TO_PRESCRIPTION_PAGE&serialNumber=${medicineBySeries.serialNumber}">Prescribe</a></td>

    </tr>

</table>
<form action = "/pharmacy/controller" method = "post">
        <input type="hidden" name="command" value="ADD_ENTRY_TO_CART">
        <fmt:message bundle="${loc}" key="local.amount.medicine" />:
        <input type="number" name="packageAmount" max="&{medicineBySeries.productBalance}"/>
        <input type="hidden" name="serialNumber" value="${medicineBySeries.serialNumber}">
        <br/>

        <input type="submit" value="enter" />
        </form>
        <br/>
</body>
</html>