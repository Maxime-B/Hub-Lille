<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<ul class="side-nav">
	<li><a href="${pageContext.request.contextPath}/evenement/creer"><spring:message
				code="evenement.nav.creer" /></a></li>
	<li><a href="${pageContext.request.contextPath}/evenement"><spring:message
				code="evenement.nav.lister" /></a></li>
</ul>