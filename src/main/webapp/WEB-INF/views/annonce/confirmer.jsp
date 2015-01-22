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
			<h3><b>
				Votre annonce est créee avec succés
			</h3></b>
			
			
				<div>Votre annonce est de la catégorie :
				${annonce.categorie.nom }</div>
			
			
			<div style="text-align:left">
			Avec les informations suivantes:
				<c:forEach items='${annonce.lesChamps}' var="item">
					
						<div><u>${item.key} :</u>
						${item.value}</div>
					
				
					
				</c:forEach>
		
			</div>
			
				
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>