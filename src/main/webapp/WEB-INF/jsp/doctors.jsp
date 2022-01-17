<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.add.button" var="add_button" />
<html>
<head>
<title>Welcome</title>
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
<td>FullName</td>
<td>Specialization</td>
<td>WorkPlace</td>
<td>Email</td>
<td>TelNumber</td>
<td>Подробнее о враче,
Оставить запрос на рецепт</td>
</tr>

<c:forEach var = "doctor" items="${doctors}">
    <tr>
        <td> <c:out value="${doctor.fullName}"/> </td>
        <td> <c:out value="${doctor.specialization}"/> </td>
        <td> <c:out value="${doctor.workPlace}"/> </td>
        <td> <c:out value="${doctor.email}"/> </td>
        <td> <c:out value="${doctor.telNumber}" /> </td>
        <td><a href="/pharmacy/controller?command=GO_TO_DOCTOR_PERSONAL_PAGE&recipient=${doctor.login}">Подробнее</a></td>
    </tr>
</c:forEach>
</table>

</body>
</html>