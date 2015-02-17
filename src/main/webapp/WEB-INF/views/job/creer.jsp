<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="job">
<tiles:putAttribute name="title">
		<spring:message code="job.creer.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
		<div class="row">
			<div class="large-12 columns">
				<br />
				<h3><spring:message code="job.creer.titre" /></h3>
				<br />
				<form:form method="post" action="creer" modelAttribute="job">
				
				<c:if test='${estUnSucces}'>
					<div class="alert-box success radius">
						<spring:message code="annonce.creer.succes" />
					</div>
				</c:if>
				<c:if test='${!estUnSucces}'>
					<span> </span>
				</c:if>
				
				
				
					<form:label path="titre" cssErrorClass="error" for="input-titre">
					<spring:message code="job.creer.label.titre" />
					<small><spring:message code="evenement.creer.requis" /></small>
					</form:label>
					
						<form:input cssErrorClass="error" path="titre"
						id="input-titre" />

					<form:errors path="titre" cssClass="error" />
					
					<!--<label>Description:</label> <input type="text" name="description" />  -->
					
					<form:label  path="description" cssErrorClass="error" for="input-description"><spring:message code="job.creer.label.description" />
					<small><spring:message code="evenement.creer.requis" /></small>
					</form:label>
					<form:textarea cssErrorClass="error" path="description"
						id="input-description" rows="5" maxlength="400"/>

					<form:errors path="description" cssClass="error" />
					
					
					<form:label  path="modalite" cssErrorClass="error" for="input-description"><spring:message code="job.creer.label.postuler" />
					<small><spring:message code="evenement.creer.requis" /></small>
					</form:label>
					<form:textarea cssErrorClass="error" path="modalite"
						id="input-description" rows="5" maxlength="400"/>
						<form:errors path="modalite" cssClass="error" />

					<label>remuneration</label>
					<input type="text" name="remuneration" placeholder="50 char max"/>
					<!--  	 <input type="submit" value="Publier"/>
		-->
		
				<spring:message code="job.creer.submit" var="submit" />
				<input type="submit" value="${submit}" class="radius button" />
					
				</form:form>
			</div>
		</div>
		</section>
		</tiles:putAttribute>
</tiles:insertDefinition>
