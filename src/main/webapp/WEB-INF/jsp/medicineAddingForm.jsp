<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<html>
<head>
<title>Medicine Form</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

<h2><fmt:message bundle="${loc}" key="local.medicine.form" /></h2>
<br/>

<form action = "/pharmacy/controller" method = "post">
<input type="hidden" name="command" value="ADD_MEDICINE_TO_DB">
commercialName:
<input type="text" name="commercialName" value=""/>
<br/>
internationalName:
<input type="text" name="internationalName" value=""/>
<br/>
medicineForm:
<input type="text" name="medicineForm" value=""/>
<br/>
medicineDose:
<input type="text" name="medicineDose" value=""/>
<br/>
productArrival:
<input type="text" name="productArrival" value=""/>
<br/>
diseaseGroup:
<input type="text" name="diseaseGroup" value=""/>
<br/>
arrivalDate:
<input type="text" name="arrivalDate" value=""/>
<br/>
invoiceNumber:
<input type="text" name="invoiceNumber" value=""/>
<br/>
medicineExpirationDate:
<input type="text" name="medicineExpirationDate" value=""/>
<br/>
isPrescriptionRequired:
<input type="text" name="prescriptionRequired" value=""/>
<br/>
packagePrice:
<input type="text" name="packagePrice" value=""/>
<br/>
producer:
<input type="text" name="producerName" value=""/>
<br/>
serialNumber:
<input type="text" name="serialNumber" value=""/>
<br/>

<input type="submit" value="enter" />
</form>

<c:if test="${param['successfulAdding']}">
   Успешно добавлено
</c:if>

</body>
</html>