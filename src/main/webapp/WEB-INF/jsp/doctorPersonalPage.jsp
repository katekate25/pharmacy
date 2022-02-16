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
    <common:header />
    <div class="container">

      <br>


<div class="card" style="width:400px; margin:auto" >
  <img class="card-img-top" src="img_avatar1.png" alt="Card image">
  <div class="card-body">
    <h4 class="card-title">${recipient.fullName}</h4>
    <p class="card-text"><fmt:message bundle="${loc}" key="local.specialization" />: ${recipient.specialization} <br>
                       <fmt:message bundle="${loc}" key="local.email" />: ${recipient.email} <br>
                       <fmt:message bundle="${loc}" key="local.telephone" />: ${recipient.telNumber} <br>
                       <fmt:message bundle="${loc}" key="local.workPlace" />: ${recipient.workPlace} <br>
                       <fmt:message bundle="${loc}" key="local.age" />: ${recipient.age} <br></p>

        <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
            <h5>
                <fmt:message bundle="${loc}" key="local.ask.prescription" />
            </h5>
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

        </c:if>  </div>
               </div>
    </div>
    <common:footer />
</body>

</html>