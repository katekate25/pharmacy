<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<common:head title="Personal cabinet" />

<body>
    <div class="container">
        <common:header />

        <br />
        <h1>
            <fmt:message bundle="${loc}" key="local.personal.cabinet.title" />
        </h1>

        <c:if test="${user.userRole.name() eq 'PHARMACIST'}">
            <a href="/pharmacy/controller?command=GO_TO_MEDICINE_FORM">
                <fmt:message bundle="${loc}" key="local.medicine.form" />
            </a>
            <a href="/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE">
                <fmt:message bundle="${loc}" key="local.register.user" />
            </a>
            <br />
            Orders
            <table>
                <tr>
                    <td>orderNumber</td>
                    <td>client</td>
                    <td>deliveryTime</td>
                    <td>pharmacist</td>
                    <td>amount</td>
                    <td>orderStatus</td>
                    <td>paymentStatus</td>
                    <td>orderEntries</td>
                </tr>

                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>
                            <c:out value="${order.orderNumber}" />
                        </td>
                        <td>
                            <c:out value="${order.client}" />
                        </td>
                        <c:out value="${medicine.medicineExpirationDate}" />
                        <td>
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${order.deliveryTime.getTime()}" />
                        </td>
                        <td>
                            <c:out value="${order.pharmacist}" />
                        </td>
                        <td>
                            <c:out value="${order.amount}" />
                        </td>
                        <td>
                            <c:out value="${order.orderStatus}" />
                        </td>
                        <td>
                            <c:out value="${order.paymentStatus}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <br />
            Clients
            <table>
                <tr>
                    <td>id</td>
                    <td>login</td>
                    <td>name</td>
                    <td>age</td>
                    <td>email</td>
                    <td>tel</td>
                </tr>

                <c:forEach var="user" items="${customers}">
                    <tr>
                        <td>
                            <c:out value="${user.id}" />
                        </td>
                        <td>
                            <c:out value="${user.login}" />
                        </td>
                        <td>
                            <c:out value="${user.fullName}" />
                        </td>
                        <td>
                            <c:out value="${user.age}" />
                        </td>
                        <td>
                            <c:out value="${user.email}" />
                        </td>
                        <td>
                            <c:out value="${user.telNumber}" />
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <br />
        </c:if>

        <c:if test="${user.userRole.name() eq 'DOCTOR'}">
            <fmt:message bundle="${loc}" key="local.message.message" />
            <table>
                <tr>
                    <td>
                        <fmt:message bundle="${loc}" key="local.message.message" />
                    </td>
                    <td>
                        <fmt:message bundle="${loc}" key="local.date" />
                    </td>
                    <td>
                        <fmt:message bundle="${loc}" key="local.sender" />
                    </td>
                    <td>
                        <fmt:message bundle="${loc}" key="local.seen" />
                    </td>
                </tr>
                <c:forEach var="messages" items="${messages}">
                    <tr>
                        <td>
                            <c:out value="${messages.message}" />
                        </td>
                        <td>
                            <c:out value="${messages.messageDate}" />
                        </td>
                        <td>
                            <c:out value="${messages.sender}" />
                        </td>
                        <td><select name="seen">
                                <option value="Seen">
                                    <fmt:message bundle="${loc}" key="local.seen" />
                                </option>
                            </select></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
            <user:customerPersonalCabinet customer="${user}" />
        </c:if>

        <common:footer />
    </div>
</body>

</html>