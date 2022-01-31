<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<html>
<common:head title="News" />
<style type="text/css">
   A {
    text-decoration: none;
   }
   A:hover {
    text-decoration: underline;
    color: blue;
   }
   body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
           background-size:cover;
           margin: 0;

           }
  </style>

<body>
    <div class="container">
<common:header />

<br>
<p><img src="img/snake.png" width="100" height="120" alt="Snake"></p>
<a href="http://minzdrav.gov.by/ru/">Ministry of Health</a>
<br>
<common:footer />
    </div>
</body>
</html>