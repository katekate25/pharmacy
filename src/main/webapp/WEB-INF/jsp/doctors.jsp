<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.add.button" var="add_button" />

<html>
<common:head title="Doctors" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <common:header />
    <div class="container">
        <br> <br>
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
            <tr>
                <th>
                    <fmt:message bundle="${loc}" key="local.fullName" />
                </th>
                <th>
                    <fmt:message bundle="${loc}" key="local.specialization" />
                </th>
                <th>
                    <fmt:message bundle="${loc}" key="local.workPlace" />
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="doctor" items="${doctors}">
                <tr>
                    <td>
                        <a href="/pharmacy/controller?command=GO_TO_DOCTOR_PERSONAL_PAGE&recipient=${doctor.login}">
                            ${doctor.fullName}
                        </a>
                    </td>
                    <td>
                        <c:out value="${doctor.specialization}" />
                    </td>
                    <td>
                        <c:out value="${doctor.workPlace}" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <common:footer />
</body>

</html>