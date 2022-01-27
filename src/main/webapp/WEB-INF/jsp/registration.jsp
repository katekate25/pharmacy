<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.register" var="register" />

<html>
<common:head title="Registration" />

<body>
    <div class="container">
        <common:header />

        <c:if test='${param["userExists"]}'>
            <p style="color:red">
                <fmt:message bundle="${loc}" key="local.user.already.exist" />
            </p>
        </c:if>
        <h2>
            <fmt:message bundle="${loc}" key="local.registration" />
        </h2>

        <form action="/pharmacy/controller" method="get">
            <input type="hidden" name="command" value="Registration" />
            <fmt:message bundle="${loc}" key="local.name.surname" />:
            <input type="text" name="name" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.login" />:
            <input type="text" name="login" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.password" />:
            <input type="password" name="password" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.email" />:
            <input type="text" name="email" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.telephone" />:
            <input type="text" name="telNumber" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.age" />:
            <input type="text" name="age" value="" />
            <br />

            <c:if test="${user.userRole.name() eq 'PHARMACIST'}">
                <fmt:message bundle="${loc}" key="local.workPlace" />:
                <input type="text" name="workPlace" value="" />
                <br />
                <fmt:message bundle="${loc}" key="local.specialization" />:
                <select name="specialization">
                    <option value="Гастроэнтеролог">Гастроэнтеролог</option>
                    <option value="Гинеколог">Гинеколог</option>
                    <option value="Дерматолог">Дерматолог</option>
                    <option value="Кардиолог">Кардиолог</option>
                    <option value="ЛОР">ЛОР</option>
                    <option value="Невролог">Невролог</option>
                    <option value="Офтальмолог">Офтальмолог</option>
                    <option value="Педиатр">Педиатр</option>
                    <option value="Психотерапевт">Психотерапевт</option>
                    <option value="Терапевт">Терапевт</option>
                    <option value="Хирург">Хирург</option>
                    <option value="Эндокринолог">Эндокринолог</option>
                </select>
                <br />
            </c:if>
            <input type="submit" value="${register}" />
        </form>

        <common:footer />
    </div>
</body>

</html>