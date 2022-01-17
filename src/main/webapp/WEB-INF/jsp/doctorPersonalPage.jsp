<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<html>
<head>
<title>Welcome</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
<br/>
<h2>Запрос на рецепт</h2>
<br/>
<form action = "/pharmacy/controller" method = "post">
<input type="hidden" name="command" value="CREATE_REQUEST_FOR_PRESCRIPTION">
Сообщение:
<input type="text" name="message" value=""/>
<br/>
<input type="hidden" name="recipient" value="${recipient}"/>

<input type="submit" value="send" />
</form>
<br/>

<c:if test="${param['sendSuccessful']}">
    Заявка отправлена
</c:if>

</body>
</html>