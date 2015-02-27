<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="job" value="${publication}" />

<div id="job-champs" class="row">
	<div class="small-4 columns">
		<strong><spring:message code="job.creer.label.remuneration" /></strong>
		<fmt:formatNumber value="${job.remuneration}" type="currency" />
	</div>
	
	<div class="small-8 columns">
		<strong><spring:message code="job.consulter.modalite" />
			: </strong>
		${job.modalite}
	</div>
</div>