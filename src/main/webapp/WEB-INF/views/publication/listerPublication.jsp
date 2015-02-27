<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">Les annonces</tiles:putAttribute>

	<tiles:putAttribute name="main">
		<session id="publications">
		<ul class="no-bullet list">
			<c:forEach items="${publications}" var="publication"
				varStatus="status">
				<li><c:if test="${publicationsMixtes}">
						<c:set var="type" value="${types[status.index]}" />
					</c:if>
					<div class="publication ${type}">
						<div>
							<div>
								<span class="titre upper">${publication.titre} </span>

								<c:if test="${publicationsMixtes}">
									<span class="type right upper">${type}</span>
								</c:if>
							</div>

							<div class="description upper">${publication.description}</div>
						</div>

						<div class="publication-fragments">
							<c:set var="publication" value="${publication}" scope="request" />
							<c:import
								url="/WEB-INF/views/publication/fragments/${fn:toLowerCase(type)}.jsp" />
						</div>

						<div class="publication-bouttons">
							<c:if test="${type == 'Annonce'}">
								<a class="radius button small"
									href="${pageContext.request.contextPath}/annonce/consulter?ref=${publication.id}">Details</a>
							</c:if>
							<c:if test="${type == 'Job'}">
								<a class="radius button small"
									href="${pageContext.request.contextPath}/job/consulter?ref=${publication.id}">Details</a>
							</c:if>
						</div>
					</div></li>
			</c:forEach>
		</ul>

		<ul class="pagination"></ul>
		</session>
	</tiles:putAttribute>

	<tiles:putAttribute name="css">
		<style>
.publication {
	padding: 1em;
	margin-bottom: 1em;
	border: solid 1px #EAEAEA;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}

.publication-bouttons {
	margin-top: 1em;
}

#annonce-photos {
	margin-top: 1em
}
</style>
	</tiles:putAttribute>

	<tiles:putAttribute name="js">
		<script src="${pageContext.request.contextPath}/ressources/js/list.js"></script>
		<script
			src="${pageContext.request.contextPath}/ressources/js/list.pagination.min.js"></script>
		<script>
		$(function(){
			var paginationOptions = {
				outerWindow : 1,
				innerWindow : 8
			}
			var options = {
				valueNames : [ 'titre', 'description' ],
				plugins : [ ListPagination(paginationOptions) ],
				item : '<li><span class="titre"></span><span class="description"></span></li>'
			}
			,	publicationList = new List('publications', options)
			,	limiterHauteur = function(selecteur) {
	        	$("#container, body").css({
	                margin: 0,
	                padding: 0
	            }) //normalizer
	            var sauvegarde = $(selecteur).css('max-height')
	            $(selecteur).css('max-height', "inherit")
	            var heightNonVisible = ($('#container').height() - innerHeight)
	            if (heightNonVisible <= 0) {
	                return
	            }
	            $(selecteur).css({
	                "max-height": $(selecteur).height() - heightNonVisible - 2,
	                "overflow-y": "auto"
	            }).children().css({
	                "margin-right": "8px" //scroll-bar, margin de row
	            })
	            heightNonVisible = ($('#container').height() - innerHeight)
	            if (heightNonVisible > 0) { //rollback si scroll bar toujours visible
	            	$(selecteur).css('max-height', "20em")
	            	//$(selecteur).css('max-height', sauvegarde)
	            }
	        }
			limiterHauteur("#publications .list:first")
		})
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>