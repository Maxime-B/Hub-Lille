<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="evenement">
	<tiles:putAttribute name="title">
		<spring:message code="evenement.creer.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
			<h1>
				<spring:message code="evenement.creer.titre" />
			</h1>

			<form:form method="get" modelAttribute="evenement">

				<div>
					<form:label path="titre" cssErrorClass="error" for="input-titre">
						<spring:message code="evenement.creer.label.titre" />
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

					<form:input cssErrorClass="error" path="description"
						id="input-description" />

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
						<form:label path="dateDebut" cssErrorClass="error"
							for="input-dateDebut">
							<spring:message code="evenement.creer.label.dateDebut" />
						</form:label>

						<form:input required="required" type="date" cssErrorClass="error"
							path="dateDebut" id="input-dateDebut" />

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