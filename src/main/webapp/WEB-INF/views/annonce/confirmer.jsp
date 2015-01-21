<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">
		<spring:message code="annonce.creer.titre" />
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
			<h1>
				Votre annonce à été créee avec succès
			</h1>
			
			<div class="row">
				<div>Categorie</div>
				<div>${annonce.categorie.nom }</div>
			</div>
			
			
			
				<c:forEach items='${annonce.lesChamps}' var="item">
					<div class="row">
						<div>${item.key}</div>
						<div>${item.value}</div>
					</div>
				
					
				</c:forEach>
		
			
			<a href="/">Retour à la page d'accueil</a>
				
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>