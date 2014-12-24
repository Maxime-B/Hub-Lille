<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">Les annonces</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<br />
				<h3>Les annonces</h3>
				<br />
				<c:forEach items="${annonces}" var="a">
					<table>
						<tr>
							<th>#${a.id}</th>
						</tr>
						<c:forEach items="${a.lesChamps}" var="entry">
							<tr>
								<td>${entry.key}
								<td />
								<td>${entry.value}
								<td />
							</tr>
						</c:forEach>
					</table>
					<br />
				</c:forEach>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>