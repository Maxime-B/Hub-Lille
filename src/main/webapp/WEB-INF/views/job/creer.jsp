<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title"><spring:message code="job.creer.titre" /></tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<br />
				<h3><spring:message code="job.creer.titre" /></h3>
				<br />
				<form:form method="post" action="creer">
					<label>
					<spring:message code="job.creer.label.titre" /></label>
					<input type="text" name="titre" placeholder="50 char max"/>
					<!--<label>Description:</label> <input type="text" name="description" />  -->
					<label><spring:message code="job.creer.label.description" /></label></label>
					<textarea id="description" name="description" rows="5" cols="30"placeholder="50 caractère max"> 

</textarea>

					<label><spring:message code="job.creer.label.remuneration" /></label></label>
					<input type="text" name="remuneration" placeholder="50 char max"/>
					<!--  	 <input type="submit" value="Publier"/>
		-->
		
				<spring:message code="job.creer.submit" var="submit" />
				<input type="submit" value="${submit}" class="radius button" />
					
				</form:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>