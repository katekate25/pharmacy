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

              .leftimg {
               float:left; /* Выравнивание по левому краю */
               margin: 7px 7px 7px 0; /* Отступы вокруг картинки */
              }
              .rightimg  {
               float: right; /* Выравнивание по правому краю  */
               margin: 7px 0 7px 7px; /* Отступы вокруг картинки */
              }

  </style>

<body>
    <common:header />
    <div class="container">

        <br>
        <p><img src="img/snake.png" width="100" height="120" alt="Snake"><br><br><a href="http://minzdrav.gov.by/ru/">Ministry of Health</a></p>

        <br>
    </div>
    <common:footer />
</body>
</html>