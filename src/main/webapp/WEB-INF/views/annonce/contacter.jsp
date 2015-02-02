//JSP

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">
		<spring:message code="annonce.creer.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">

			<div style="text-align: left">
				<h3>
					<spring:message code="annonce.creer.titre" />
				</h3>
				
			</div>

			<form:form action="contacter" method="post" enctype="multipart/form-data"
				modelAttribute="formcontact">
				<c:if test='${estUnSucces}'>
					<div class="alert-box success radius">
						<spring:message code="annonce.creer.succes" />
					</div>
				</c:if>
				<c:if test='${!estUnSucces}'>
					<span>Â </span>
				</c:if>

									<input type="hidden" name="ref" value="${a.id}"/>
				Emetteur
				<form:input type="text" path="emeteur" placeholder="50 char max" cssErrorClass="error"/>
				<form:errors path="emeteur" cssClass="error" />
				
				
				Objet
				<form:input type="text" path="objet" placeholder="50 char max" cssErrorClass="error"/>
				<form:errors path="emeteur" cssClass="error" />

				Message
				<form:textarea path="message" rows="5" cols="30"
					placeholder="200 caractÃ¨re max" cssErrorClass="error"></form:textarea>
				<form:errors path="message" cssClass="error" />
				
			
					<spring:message code="job.creer.submit" var="submit" />

					<input type="submit" value="${submit}" class="radius button" />
				</div>
			</form:form>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>l>