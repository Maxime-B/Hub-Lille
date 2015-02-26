<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		<spring:message code="utilisateur.profil.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
			<h1>Profil</h1>

			<div>login : ${ pageContext.request.remoteUser }</div>
			<div>roles : ${ pageContext.request.userPrincipal.authorities }</div>

			<h2><spring:message code="utilisateur.profil.attributs" /></h2>

			<c:forEach
				items="${pageContext.request.userPrincipal.assertion.principal.attributes}"
				var="attribute">
				<div>${attribute.key } : ${attribute.value }</div>
			</c:forEach>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>