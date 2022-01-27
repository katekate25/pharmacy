<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.add.button" var="add_button" />

<html>
<common:head title="Doctors" />

<body>
    <div class="container">
        <common:header />


        <table class="table table-hover table-bordered">
            <tr>
                <td>
                    <fmt:message bundle="${loc}" key="local.fullName" />
                </td>
                <td>
                    <fmt:message bundle="${loc}" key="local.specialization" />
                </td>
                <td>
                    <fmt:message bundle="${loc}" key="local.workPlace" />
                </td>
                <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
                    <td></td>
                </c:if>
            </tr>
            <c:forEach var="doctor" items="${doctors}">
                <tr>
                    <td>
                        <c:out value="${doctor.fullName}" />
                    </td>
                    <td>
                        <c:out value="${doctor.specialization}" />
                    </td>
                    <td>
                        <c:out value="${doctor.workPlace}" />
                    </td>

                    <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
                        <td><a href="/pharmacy/controller?command=GO_TO_DOCTOR_PERSONAL_PAGE&recipient=${doctor.login}">
                                <fmt:message bundle="${loc}" key="local.doctor.details" />
                            </a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

    </div>
</body>

</html>