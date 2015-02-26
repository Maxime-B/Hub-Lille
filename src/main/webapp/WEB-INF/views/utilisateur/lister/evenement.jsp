<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="utilisateur">
	<tiles:putAttribute name="title">
		liste de mes evenements
	</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<section class="section">
			<h1>liste de mes evenements</h1>


			<c:if test="${not empty evenements}">
				<table id="datatable">
					<thead>
						<tr>
							<th>Titre</th>
							<th>Date</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${evenements}" var="evenement">
						<tr>
							<td>${evenement.titre}</td>
							<td>${evenement.dateDebut}</td>
							<td><a
								href="${pageContext.request.contextPath}/evenement/modifier/${evenement.id}"
								class="button small">Modifier</a></td>
							<td>
							
								<button type="button" data-reveal-id="supprimer_${evenement.id}">Supprimer</button>
							<div id="supprimer_${evenement.id}" class="reveal-modal" data-reveal>
							<form method="post" action="">
								<h3>Voulez vous supprimer l'evenement "${evenement.titre}"</h3>
								<input type="hidden" name="ref" value="${evenement.id}"/>
								<input type="hidden" name="typeAction" value="supprimer"/>
								<button onclick="this.form.submit();">Oui</button>
								<button class="supprimer" type="button" data-reveal>Non</button>
							</form>
							</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				

			
			</c:if>
			<c:if test="${empty evenements}">
				<div>aucun evenement créé</div>
			</c:if>
		</section>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
	<script type="text/javascript">
	$( document ).ready(function() {
	    var length = $(".ligne").length;
	 
	    	$(document).on('click tap touchstart', '.reveal-modal-bg, .supprimer,.republier', function() {
	    	    return $('[data-reveal]').foundation('reveal', 'close');
	    	});
	    	
	});
	
	
	</script>
	
<script src="<c:url value="/ressources/js/datatablesLister.js"/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>
