<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">
		modifier le droit d'un utilisateur
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section>
			<form:form method="post" modelAttribute="utilisateur">
				<fieldset>
					<legend>modifier le droit d'un utilisateur</legend>
					
					<form:label path="login">login</form:label>
					<form:input type="text" path="login" /> 
					
					<form:label path="droit">droit</form:label>
					<form:input type="text" path="droit" />
						
					<input type="submit" value="valider" class="radius button"/>
				</fieldset>
			</form:form>

		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>