<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		liste de mes annonces
	</tiles:putAttribute>
	
	<tiles:putAttribute name="main">
		<section class="section">
			<h1>liste de mes annonces</h1>
		
			<c:if test="${!empty republierSucces}">
				<div class="alert-box success radius">L'annonce ${annonce.titre} est  a été republier jusqu'au ${annonce.finpublication} <br/>
				</div>
				
			</c:if>
			
				
				<c:if test="${not empty annonces}">
				<table id="datatable">
				<thead>
				<tr>
					<th>titre</th>
					
					<th>Date de publication</th>
					<th>Date de  fin de publication</th>
					
					<th></th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				 <c:forEach items="${annonces}" var="annonce" >
					<tr class="ligne">
						<td>${annonce.titre}</td>
						
						<td>${annonce.datepublication}</td>
						<td>${annonce.finpublication}</td> 
						
						<td><a href="${pageContext.request.contextPath}/annonce/consulter?ref=${annonce.id}"><button>Consulter</button> </a></td>
						<td><a href="${pageContext.request.contextPath}/annonce/modifier?ref=${annonce.id}"><button>Modifier</button> </a></td>
						<td>
							<button type="button" data-reveal-id="publier_${annonce.id}">Republier</button>
							<div id="publier_${annonce.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
							<h3>Voulez vous republier l'annonce "${annonce.titre}"</h3>
								<input type="hidden" name="ref" value="${annonce.id}"/>
								<input type="hidden" name="typeAction" value="republier"/>
								<button onclick="this.form.submit();">Oui</button>
								<button class="republier" id="rep_annuler_${annonce.id}" type="button">Non</button>
							</form>
							</div>
							
						</td>
						<td>
							<button type="button" data-reveal-id="supprimer_${annonce.id}">Supprimer</button>
							<div id="supprimer_${annonce.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
								<h3>Voulez vous supprimer l'annonce "${annonce.titre}"</h3>
								<input type="hidden" name="ref" value="${annonce.id}"/>
								<input type="hidden" name="typeAction" value="supprimer"/>
								<button onclick="this.form.submit();">Oui</button>
								<button class="supprimer"id="rep_annuler_${annonce.id}"type="button" data-reveal>Non</button>
							</form>
							</div>
						</td>
					</tr>
					</c:forEach>
					</tbody>
					</table>
				</c:if>
				<c:if test="${empty annonces}">
					<tr>
						<td colspan="2">aucune annonce créée</td>
					</tr>
				</c:if>
			
		</section>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
	<script type="text/javascript">
	$( document ).ready(function() {
	    var length = $(".ligne").length;
	    for(i = 1 ; i < length+1;i++)
	    	{
	    	$(document).on('click tap touchstart', '.reveal-modal-bg, .supprimer,.republier', function() {
	    	    return $('[data-reveal]').foundation('reveal', 'close');
	    	});
	    	}
	});
	
	
	</script>
	
<script src="<c:url value="/ressources/js/datatablesLister.js"/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>