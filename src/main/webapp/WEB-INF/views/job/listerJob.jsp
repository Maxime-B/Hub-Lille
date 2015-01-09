<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="job">
	<tiles:putAttribute name="title">Les jobs</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<br />
				<h3>Les jobs</h3>
				<br />
				<table>
				<tr>
						<th>Titre</th>
						<th>Description</th>
						
					</tr>
				<c:forEach items="${jobs}" var="a">
					
							<tr>
								<td>${a.titre}
								</td>
								<td>${a.description}
								</td>
								
							</tr>
					</c:forEach>
					</table>
					<br />
				
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>