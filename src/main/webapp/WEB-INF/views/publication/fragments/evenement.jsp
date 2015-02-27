<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="evenement" value="${publication}" />

<div id="evenement-champs">
	<fmt:formatDate value="${evenement.dateDebut}" type="date"
		dateStyle="long" />
	<c:if test="${not empty evenement.heureDebut}"> - </c:if>
	<fmt:formatDate value="${evenement.heureDebut}" type="time"
		timeStyle="short" />
	<c:if test="${not empty evenement.lieu}">
 - ${evenement.lieu}
</c:if>
</div>