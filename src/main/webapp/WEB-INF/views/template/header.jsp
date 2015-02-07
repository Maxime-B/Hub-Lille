<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@ page import="java.util.*"%>
<%@ page
	import="org.springframework.security.cas.authentication.CasAuthenticationToken"%>

<div class="contain-to-grid sticky">
	<nav class="top-bar" data-topbar role="navigation"
		data-options="sticky_on: large">
		<ul class="title-area">
			<li class="name"><a href="${pageContext.request.contextPath}"
				title="accueil"><img width="100px"
					src="${pageContext.request.contextPath}/ressources/img/UL1-BLANC-2014.png" /></a>
				<h1>
					<a href="#"></a>
				</h1></li>
		</ul>

		<section class="top-bar-section">
			<!-- Top Bar Left Nav Elements -->
			<!-- Top Bar Right Nav Elements -->
			<ul class="right">

				<c:if test="${!empty pageContext.request.remoteUser}">
					<%--  liens de l'utilisateur  --%>
					<!-- Dropdown -->
					<li class="has-dropdown not-click"><a href="#"><i
							class="step fi-male size-36"> </i>${pageContext.request.remoteUser}</a>
						<ul class="dropdown">
							<li class="title back js-generated"><h5>
									<a href="javascript:void(0)">Back</a>
								</h5></li>
							<li class="parent-link show-for-small-only"><a
								class="parent-link js-generated" href="#">Item 1</a></li>
							<li><a href="${pageContext.request.contextPath}/utilisateur"><i
									class="step fi-widget size-36"> </i> <spring:message
										code="template.header.monProfil" /></a></li>
							<li><a
								href="${pageContext.request.contextPath}/utilisateur/lister/annonce"><i
									class="step fi-results size-36"> </i> <spring:message
										code="template.header.mesAnnonces" /></a></li>
							<li><a
								href="${pageContext.request.contextPath}/utilisateur/lister/job"><i
									class="step fi-dollar-bill size-36"> </i> <spring:message
										code="template.header.mesJobs" /></a></li>
							<li><a
								href="${pageContext.request.contextPath}/utilisateur/lister/evenement"><i
									class="step fi-universal-access size-36"> </i> <spring:message
										code="template.header.mesEvenements" /></a></li>
						</ul></li>
					<%--  fin liens de l'utilisateur  --%>
				</c:if>

				<!-- bouton de déconnexion -->
				<li class="has-form show-for-medium-up"><c:if
						test="${empty pageContext.request.remoteUser}" var="estDeconnecte">
						<a href="${pageContext.request.contextPath}/utilisateur/connecter"><i
							class="step fi-power size-8"> </i> <spring:message
								code="template.header.connecter" /> </a>
					</c:if> <c:if test="${!estDeconnecte}">
						<a
							href="${pageContext.request.contextPath}/utilisateur/deconnecter"><i
							class="step fi-power size-8"> </i> <spring:message
								code="template.header.deconnecter" /> </a>

					</c:if></li>
			</ul>

			<%-- choix de la langue --%>
			<c:set var="URLCourante"
				value="${requestScope['javax.servlet.forward.servlet_path']}" />

			<c:url value="${URLCourante}" var="en_EN">
				<c:if test="${pageContext.request.method=='GET'}">
					<c:forEach items="${param}" var="entry">
						<c:if test="${'locale'!=entry.key}">
							<c:param name="${entry.key}" value="${entry.value}" />
						</c:if>
					</c:forEach>
				</c:if>
				<c:param name="locale" value="en_EN" />
			</c:url>

			<c:url value="${URLCourante}" var="fr_FR">
				<c:if test="${pageContext.request.method=='GET'}">
					<c:forEach items="${param}" var="entry">
						<c:if test="${'locale'!=entry.key}">
							<c:param name="${entry.key}" value="${entry.value}" />
						</c:if>
					</c:forEach>
				</c:if>
				<c:param name="locale" value="fr_FR" />
			</c:url>

			<ul class="right">
				<!-- Dropdown -->
				<li class="has-dropdown not-click"><a href="#"><i
						class="fi-flag"> </i> <spring:message code="template.langue" /></a>
					<ul class="dropdown">
						<li class="title back js-generated"><h5>
								<a href="javascript:void(0)">Back</a>
							</h5></li>
						<li><a href="${en_EN}"><i class="fi-flag"> </i>en</a></li>
						<li><a href="${fr_FR}"><i class="fi-flag"> </i>fr</a></li>
					</ul></li>
			</ul>
		</section>
	</nav>
</div>
<%-- fin choix de la langue --%>