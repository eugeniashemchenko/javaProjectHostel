<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Вахтер: ${sessionScope.currentWatchman.fullName}</h1>
<c:url value="/watchman/pass" var="passUrl" />
<c:url value="/watchman/pass/new" var="newPassUrl" />
<ul>

    <li><a href="${newPrescriptionUrl}">Новый пропуск</a></li>
    <li><a href="${prescriptionsUrl}">Пропуски</a></li>
</ul>