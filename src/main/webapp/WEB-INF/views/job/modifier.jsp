<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="job">
<tiles:putAttribute name="title">
		<spring:message code="job.modifier" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
		<div class="row">
			<div class="large-12 columns">
				<br />
				<h2><spring:message code="job.modifier" /></h2>
			
				<form:form method="post" action="${pageContext.request.contextPath}/job/modifier" modelAttribute="job">
				
				<c:if test='${estUnSucces}'>
					<div class="alert-box success radius">
						<spring:message code="annonce.creer.succes" />
					</div>
				</c:if>
				<c:if test='${!estUnSucces}'>
					<span> </span>
				</c:if>
				
				
				<form:hidden path="id"></form:hidden>
					<form:label path="titre" cssErrorClass="error" for="input-titre">
					<B><spring:message code="job.creer.label.titre" /></B>
					<small><spring:message code="evenement.creer.requis" /></small>
					</form:label>
					
						<form:input cssErrorClass="error" path="titre"
						id="input-titre" />

					<form:errors path="titre" cssClass="error" />
					
					<!--<label>Description:</label> <input type="text" name="description" />  -->
					
					<form:label  path="description" cssErrorClass="error" for="input-description">
					<B><spring:message code="job.creer.label.description" /></B>
					<small><spring:message code="evenement.creer.requis" /></small>
					</form:label>
					<form:textarea cssErrorClass="error" path="description"
						id="input-description" rows="5" maxlength="400"/>

					<form:errors path="description" cssClass="error" />
					
					
					<form:label  path="modalite" cssErrorClass="error" for="input-description">
					<B><spring:message code="job.creer.label.postuler" /></B>
					<small><spring:message code="evenement.creer.requis" /></small>
					</form:label>
					<form:textarea cssErrorClass="error" path="modalite"
						id="input-description" rows="5" maxlength="400"/>
						<form:errors path="modalite" cssClass="error" />

					<label><B><spring:message code="job.creer.label.remuneration" /></B></label>
					<input type="text" name="remuneration" placeholder="50 char max"/>
					<!--  	 <input type="submit" value="Publier"/>
		-->
				<table style="border: solid 1px #FFFFFF">
				<tr>
					<td></td>
				</tr>
			</table>
			<table
					style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #FFFFFF; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; width: 100%;">
					<tr>

			
				<td style="text-align: right">
				<spring:message code="job.creer.submit" var="submit" />
				<input type="submit" value="${submit}" class="radius button" />
					</td>
					</tr>
					</table>
				</form:form>
			</div>
		</div>
		</section>
		</tiles:putAttribute>
</tiles:insertDefinition>