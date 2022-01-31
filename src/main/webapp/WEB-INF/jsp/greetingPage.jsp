<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<style>
   h1 {
       margin: 1em 0 0.5em 0;
       color: #343434;
       font-weight: normal;
       font-family: 'Ultra', sans-serif;
       font-size: 36px;
       line-height: 42px;
       text-transform: uppercase;
       text-shadow: 0 2px white, 0 3px #777;
   }
   h3 {
       margin: 1em 0 0.5em 0;
       color: #343434;
       font-size: 22px;
       line-height: 40px;
       font-weight: normal;
       text-transform: uppercase;
       font-family: 'Orienta', sans-serif;
       letter-spacing: 1px;
       font-style: normal;
   }

     body { background: url(img/shopping-cart.jpg);
      background-size: contain;
      margin: 0;
          height: 100%;

      }
  </style>
<common:head title="Welcome" />

<body>
    <div class="container">
        <common:header />

        <c:if test="${accessDenied}">
            <fmt:message bundle="${loc}" key="local.access.denied" />
        </c:if>

        <br />
        <h3>
           <fmt:message bundle="${loc}" key="local.greeting.add" />
        </h3>

        <h1>
            <fmt:message bundle="${loc}" key="local.greeting.main.page" />
        </h1>

        <p>
        <font size="3" color="black"> <em>We care about your health</em>
        </p>


    <common:footer />
    </div>
</body>

</html>