<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">
		<spring:message code="annonce.choix.titre" />
	</tiles:putAttribute>
	<h5>Créer annonce de type offre</h5>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<br /> <b><spring:message code="annonce.choix.categorie" /></b> <br />
				<br /> <br />

				<form method="get"
					action="${pageContext.request.contextPath}/annonce/creerOffre">
					<select name="categorieChoisie">
						<c:forEach items="${categories}" var="item">
							<option value="${item.nom}">${item.nom}</option>
						</c:forEach>
					</select>					
						<div style="text-align: right">
							<input type="submit" style="height: 40px" value="suivant "
								class="radius button">
						</div>
					</form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>