<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/"><spring:message code="nav.accueil" /></a></li>
		<li><a href="${pageContext.request.contextPath}/init"><spring:message code="nav.initialiser" /></a></li>
</ul>

<h6><spring:message code="nav.annonce" /></h6>
<ul class="four side-nav">
	<li><a
				href="${pageContext.request.contextPath}/annonce/categorie/choisir"><spring:message code="nav.deposeAnn" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/annonce/listerOffre"><spring:message code="nav.offre" /></a></li>

			<li><a href="${pageContext.request.contextPath}/annonce/listerDemande"><spring:message code="nav.demande" /></a></li>
</ul>
<sec:authorize url="/evenement/**">
<h6><spring:message code="nav.evenement" /></h6>
<!-- evenement -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/evenement/creer"><spring:message code="nav.creerEve" /></a></li>
	<li><a href="${pageContext.request.contextPath}/evenement"><spring:message code="nav.lesEve" /> </a></li>
</ul>
</sec:authorize>
<sec:authorize url="/job/**">
<h6><spring:message code="nav.job" /></h6>
<!-- job -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/job/creer"><spring:message code="nav.creerJob" /></a></li>
	<li><a href="${pageContext.request.contextPath}/job/listerJob"><spring:message code="nav.lesJob" /></a></li>
</ul>
</sec:authorize>
<sec:authorize url="/moderateur/**">
<h6><spring:message code="nav.moderation" /></h6>
<!-- Moderation -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/moderateur"><spring:message code="nav.moderer" /></a></li>
</ul>
</sec:authorize>
<sec:authorize url="/admin/**">
<h6><spring:message code="nav.admin" /></h6>
<!-- Administration -->
<ul class="four side-nav">
	<li><a href="${pageContext.request.contextPath}/admin/droit"><spring:message code="nav.gerer" /></a></li>
	<li><a href="${pageContext.request.contextPath}/admin/categorieAdmin"><spring:message code="nav.administrer" /></a></li>
</ul>
</sec:authorize>

