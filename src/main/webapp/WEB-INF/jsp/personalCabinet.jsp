<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>
<%@ taglib prefix="doc" tagdir="/WEB-INF/tags/doc" %>
<%@ taglib prefix="pharmacist" tagdir="/WEB-INF/tags/pharmacist" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<common:head title="Personal cabinet" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <div class="container">
        <common:header />

        <br />
        <h1>
            <fmt:message bundle="${loc}" key="local.personal.cabinet.title" />
        </h1>

        <c:if test="${user.userRole.name() eq 'PHARMACIST'}">
         <pharmacist:pharmacistPersonalCabinet pharmacist="${user}" /><br />
            <a href="/pharmacy/controller?command=GO_TO_MEDICINE_FORM">
                <fmt:message bundle="${loc}" key="local.medicine.form" />
            </a>
            <br>
            <a href="/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE">
                <fmt:message bundle="${loc}" key="local.register.user" />
            </a>
            <br />
        </c:if>

        <c:if test="${user.userRole.name() eq 'DOCTOR'}">
            <doc:doctorPersonalCabinet doctor="${user}" />
        </c:if>

        <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
            <user:customerPersonalCabinet customer="${user}" />
        </c:if>


        <common:footer />
    </div>
</body>

</html>