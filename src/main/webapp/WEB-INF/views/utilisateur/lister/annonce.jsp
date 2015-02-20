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
				<table>
				<tr>
					<th>titre</th>
					
					<th>Date de publication</th>
					<th>Date de  fin de publication</th>
					
					<th></th>
					<th></th>
					<th></th>
				</tr>
				 <c:forEach items="${annonces}" var="annonce" >
					<tr>
						<td>${annonce.titre}</td>
						
						<td>${annonce.datepublication}</td>
						<td>${annonce.finpublication}</td> 
						
						<td><a href="${pageContext.request.contextPath}/annonce/consulter?ref=${annonce.id}"><button>Consulter</button> </a></td>
						<td>
							<form method="post" action="">
								<input type="hidden" name="ref" value="${annonce.id}"/>
								<input type="hidden" name="typeAction" value="republier"/>
								<button onclick="this.form.submit();">Republier</button>
							</form>
							
							</td>
						<td>
							<form method="post" action="">
								<input type="hidden" name="ref" value="${annonce.id}"/>
								<input type="hidden" name="typeAction" value="supprimer"/>
								<button onclick="this.form.submit();">Supprimer</button>
							</form>
						</td>
					</tr>
					</c:forEach>
					</table>
				</c:if>
				<c:if test="${empty annonces}">
					<tr>
						<td colspan="2">aucune annonce créée</td>
					</tr>
				</c:if>
			
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>