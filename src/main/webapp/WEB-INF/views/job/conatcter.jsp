<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title">
		<spring:message code="job.postuler.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">

			<div style="text-align: left">
				<h3>
					<spring:message code="job.contacter.titre" /> "${a.titre }"
				</h3>
				
			</div>

			<form:form action="contacter" method="post" enctype="multipart/form-data"
				modelAttribute="formcontact">
				<c:if test='${estUnSucces}'>
					<div class="alert-box success radius">
						<spring:message code="job.contacter.succes" />
					</div>
				</c:if>
				<c:if test='${!estUnSucces}'>
					<span>Â </span>
				</c:if>

									<input type="hidden" name="ref" value="${a.id}"/>
				<spring:message code="annonce.contacter.emetteur" />
				<form:input type="text" path="emeteur"  cssErrorClass="error"/>
				<form:errors path="emeteur" cssClass="error" />
				
				
				<spring:message code="annonce.contacter.objet" />
				<form:input type="text" path="objet"  cssErrorClass="error"/>
				<form:errors path="emeteur" cssClass="error" />

				<spring:message code="annonce.contacter.message" />
				<form:textarea path="message" rows="5" cols="30"
					 cssErrorClass="error"></form:textarea>
				<form:errors path="message" cssClass="error" />
				
			
					<spring:message code="job.creer.submit" var="submit" />

					<input type="submit" value="${submit}" class="radius button" />
				</div>
			</form:form>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>