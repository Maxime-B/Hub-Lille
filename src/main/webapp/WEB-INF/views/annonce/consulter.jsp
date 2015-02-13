<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title" value="${annonce.titre}" />

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>${annonce.titre}</h1>

			<!-- message si redirection -->
			<c:if test="${!empty param['estUnSucces']}">
				<div class="alert-box success radius">L'annonce ${annonce.titre} est maintenant consultable. <br/>
				Tout les message seront dressés à l'adresse suivante : ${annonce.utilisateur.email}</div>
			</c:if>
			<div class="row">
				<div class="row">
				  <div class="small-8 columns">
				  	<div class="row">
					<b>Nature de l'annonce</b>
					<p>${annonce.type }</p>
				</div>
				<div class="row">
					<b>Description</b>
					<p>${annonce.description}</p>
				</div>
				
				<div class="row">
					<b>Categorie</b>
					<p>${annonce.categorie.nom }</p>
				</div>
				<c:forEach items='${annonce.lesChamps}' var="item">
					<div class="row">
						<b>${item.key}<b>
						<p>${item.value}<p>
					</div>
				</c:forEach>
				  </div>
				  <div class="small-4 columns">
				  <div class="row">
				  <img src="/hublille1/ressources/img/pas-dimage.png" width="200" height="450" style="border: solid 1px;"/>
				  </div>
				  <br/><br/>
				  <div class="row">
				  Prix : ${annonce.lesChamps['prix']}
				  </div>
				  <br/><br/>
				 
				  	<form action="contacter" method="get">
					<input type="hidden" name="ref" value="${annonce.id}"/>
					<input type="submit" name="Contacter" value="Contacter"class="radius button"  style="padding: 10px 50px" >
					</form>
				  </div>
				</div>
				
			</div>
			
		
			
		

			<br /> <a href="/">Retour à la page d'accueil</a>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>
