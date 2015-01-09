<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title">Proposer un job</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">

				<form:form method="post" action="creer">
					<label>Titre:</label>
					<input type="text" name="titre" placeholder="50 char max"/>
					<!--<label>Description:</label> <input type="text" name="description" />  -->
					<label>Description:</label>
					<textarea id="description" name="description" rows="5" cols="30"placeholder="50 caractère max"> 

</textarea>

					<label>Remuneration:</label>
					<input type="text" name="remuneration" placeholder="50 char max"/>
					<!--  	 <input type="submit" value="Publier"/>
		-->
					<input type="submit" value="Publier" class="radius button" />
				</form:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>