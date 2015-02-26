<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title">
		<spring:message code="job.postuler.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
			</br>
			<div style="text-align: left">
				<h2>
					<spring:message code="job.contacter.titre" />
					"${a.titre }"
				</h2>

			</div>
			<table style="border: solid 1px #FFFFFF">
				<tr>
					<td></td>
				</tr>
			</table>

			<form:form action="contacter" method="post"
				enctype="multipart/form-data" modelAttribute="formcontact">
				<c:if test='${estUnSucces}'>
					<div class="alert-box success radius">
						<spring:message code="job.contacter.succes" />
					</div>
				</c:if>

				<input type="hidden" name="ref" value="${a.id}" />
				<B><spring:message code="job.contacter.emeteur" /></B>
				<form:input type="text" path="emeteur" cssErrorClass="error" />
				<form:errors path="emeteur" cssClass="error" />


				<B><spring:message code="job.contacter.objet" /></B>
				<form:input type="text" path="objet" cssErrorClass="error" />
				<form:errors path="emeteur" cssClass="error" />

				<B><spring:message code="annonce.contacter.message" /></B>
				<form:textarea path="message" rows="5" cols="30"
					cssErrorClass="error"></form:textarea>
				<form:errors path="message" cssClass="error" />
				<table style="border: solid 1px #FFFFFF">
					<tr>
						<td></td>
					</tr>
				</table>

				<table
					style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #FFFFFF; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; width: 100%;">
					<tr>


						<td style="text-align: right"><input type="submit"
							value="<spring:message code="job.postuler.titre"/>" class="radius button" /></td>
					</tr>
				</table>
				</div>
			</form:form>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>
