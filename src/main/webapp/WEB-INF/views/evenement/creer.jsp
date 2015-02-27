<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<tiles:insertDefinition name="evenement">
	<%-- <tiles:putAttribute name="search" value=""/> --%>

	<tiles:putAttribute name="title">
		<spring:message code="evenement.creer.titre" />
	</tiles:putAttribute>
	
	<tiles:putAttribute name="main">
		<c:if test="${evenement.id eq 0}">
			<c:set var="page" value="creer"/>
		</c:if>
		<c:if test="${not(evenement.id eq 0)}">
			<c:set var="page" value="modifier"/>
		</c:if>
		
		<section class="section">
			<h1>
				<spring:message code="evenement.${page}.titre" />
			</h1>

			<form:form action="${pageContext.request.contextPath}/evenement/${page}" method="post" modelAttribute="evenement" >
				<div>
					<form:input path="id" type="hidden"/>
					<form:errors path="id" cssClass="error" />

					<fmt:formatDate type="date" dateStyle="long" value="${evenementCree.dateDebut}" var="dateDebut"/>
					<c:if test="${estUnSucces}">
						<div class="alert-box success radius">
							<spring:message code="evenement.${page}.succes" arguments="${evenementCree.titre},${dateDebut}"/>
						</div>
					</c:if>
					<c:if test="${!estUnSucces}">
						<span>&nbsp;</span>
					</c:if>
				</div>
				
				<div>
					<form:label path="titre" cssErrorClass="error" for="input-titre">
						<spring:message code="evenement.creer.label.titre" />
						<small><spring:message code="evenement.creer.requis" /></small>
					</form:label>

					<form:input required="required" cssErrorClass="error" path="titre"
						id="input-titre" />

					<form:errors path="titre" cssClass="error" />
				</div>

				<div>
					<form:label path="description" cssErrorClass="error"
						for="input-description">
						<spring:message code="evenement.creer.label.description" />
					</form:label>

					<form:textarea cssErrorClass="error" path="description"
						id="input-description" rows="5" maxlength="254"/>

					<form:errors path="description" cssClass="error" />
				</div>

				<div>
					<form:label path="lieu" cssErrorClass="error" for="input-lieu">
						<spring:message code="evenement.creer.label.lieu" />
					</form:label>

					<form:input cssErrorClass="error" path="lieu" id="input-lieu" />

					<form:errors path="lieu" cssClass="error" />
				</div>

				<div class="row">
					<div class="small-6 columns">
						<form:label path="dateDebut" cssErrorClass="error">
							<spring:message code="evenement.creer.label.dateDebut" />
							<small><spring:message code="evenement.creer.requis" /></small>
						</form:label>

						<form:hidden class="datepicker" required="required"
							path="dateDebut"/>
						<noscript><form:input class="datepicker" required="required"
							path="dateDebut" type="date"/></noscript>

						<form:errors path="dateDebut" cssClass="error" />
					</div>

					<div class="small-6 columns">
						<form:label path="heureDebut" cssErrorClass="error"
							for="input-heureDebut">
							<spring:message code="evenement.creer.label.heureDebut" />
						</form:label>

						<form:input type="time" cssErrorClass="error" path="heureDebut"
							id="input-heureDebut" />

						<form:errors path="heureDebut" cssClass="error" />
					</div>
				</div>
				
				<spring:message code="evenement.creer.submit" var="submit" />
				<input type="submit" value="${submit}" class="radius button" />
			</form:form>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>