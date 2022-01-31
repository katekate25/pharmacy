<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<html >
    <common:head title="Messages" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <div class="container">
        <common:header />

        <br><br><br>

        <c:if test="${not empty messages}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                                    <th>
                                        <fmt:message bundle="${loc}" key="local.message.message" />
                                    </th>
                                    <th>
                                        <fmt:message bundle="${loc}" key="local.date" />
                                    </th>
                                    <th>
                                        <fmt:message bundle="${loc}" key="local.sender" />
                                    </th>

                                </tr>
            </thead>

            <tbody>
                <c:forEach var="messages" items="${messages}">
                                    <tr>
                                        <td>
                                            <c:out value="${messages.message}" />
                                        </td>
                                        <td>
                                            <c:out value="${messages.messageDate}" />
                                        </td>
                                        <td>
                                            <c:out value="${messages.sender.fullName}" /><br>
                                            <c:out value="${messages.sender.login}" />
                                        </td>

                                    </tr>
                                </c:forEach>
            </tbody>

        </table>
        </c:if>
        <c:if test="${empty messages}">
            You do not have messages yet
        </c:if>
        <common:footer />
    </div>
</body>
</html>