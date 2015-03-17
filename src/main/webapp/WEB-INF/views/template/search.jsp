<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="search-publication">
	<c:if test="${empty search}">
		<jsp:useBean id="search" class="ipint.glp.controlleurs.forms.FormRecherche" scope="request"/>
	</c:if>
	<form:form modelAttribute="search" action="${pageContext.servletContext.contextPath }/publication" method="get">
		<div class="row">
		<div class="row collapse postfix-round">
			<div class="small-6 columns">
				<form:input path="motCle" type="text" />
			</div>
			
			<div class="small-4 columns">
				<form:select path="where">
					<form:option value=""><spring:message code="accueil.site" /></form:option>
					<form:option value="annonces-offres"><spring:message code="accueil.annOffre" /></form:option>
					<form:option value="annonces-demandes"><spring:message code="accueil.annDemande" /></form:option>
					<form:option value="jobs"><spring:message code="accueil.job" /></form:option>
					<form:option value="evenements"><spring:message code="accueil.evenement" /></form:option>
				</form:select>
			</div>
			
			<div class="small-2 columns">
				<spring:message code="template.rechercher" var="submit"/>
				<button type="submit" id="publication-submit" class="postfix button small">
					<i class="fi-magnifying-glass small"></i>
				</button>
			</div>
		</div>
		</div>
	</form:form>
</div>