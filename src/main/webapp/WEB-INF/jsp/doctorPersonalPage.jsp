<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.send.button" var="send_button" />

<html>
<common:head title="Personal page" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <div class="container">
        <common:header />

        Name - ${recipient.fullName} <br>
        Email - ${recipient.email} <br>
        Tel. Number - ${recipient.telNumber} <br>
        Work Place - ${recipient.workPlace} <br>
        Specialization - ${recipient.specialization} <br>
        Age - ${recipient.age} <br>


        <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
            <h2>
                <fmt:message bundle="${loc}" key="local.ask.prescription" />
            </h2>
            <br />
            <form action="/pharmacy/controller" method="post">
                <input type="hidden" name="command" value="CREATE_REQUEST_FOR_PRESCRIPTION">
                <fmt:message bundle="${loc}" key="local.message.message" />:
                <input type="text" name="message" value="" />
                <br />
                <input type="hidden" name="recipient" value="${recipient.login}" />

                <input type="submit" value="${send_button}" />
            </form>
            <br />

            <c:if test="${param['sendSuccessful']}">
                <fmt:message bundle="${loc}" key="local.send" />
            </c:if>

        </c:if>
        <common:footer />
    </div>
</body>

</html>