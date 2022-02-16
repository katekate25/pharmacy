<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

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
    <common:header />
    <div class="container">

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
                                    <th> </th>
                                </tr>
            </thead>

            <tbody>
                <c:forEach var="message" items="${messages}">
                                    <tr>
                                        <td>
                                            <c:out value="${message.message}" />
                                        </td>
                                        <td>
                                            <c:out value="${message.messageDate}" />
                                        </td>
                                        <td>
                                            <c:out value="${message.sender.fullName}" /><br>
                                            <c:out value="${message.sender.login}" /><br>
                                            <c:out value="${message.sender.telNumber}" />
                                        </td>
                                        <td>
                      <form action = "/pharmacy/controller" method = "post">
                 <input type="hidden" name="command" value="UPDATE_MESSAGE_APPROVAL">
                 <input type="hidden" name="messageId" value="${message.id}">
                 <select class="form-control btn-light" name="approvalStatus" onchange="this.form.submit()">
                     <option class="dropdown-item" value="true" ${message.approved ? 'selected' : ''}><fmt:message bundle="${loc}" key="local.message.approved" /></option>
                     <option class="dropdown-item" value="false" ${!message.approved ? 'selected' : ''}><fmt:message bundle="${loc}" key="local.message.not.seen" /></option>
                 </select>
              </form>
              </td>
                                    </tr>
                                </c:forEach>
            </tbody>

        </table>
        </c:if>
        <c:if test="${empty messages}">
            You do not have messages yet
        </c:if>

    </div>
    <common:footer />
</body>
</html>