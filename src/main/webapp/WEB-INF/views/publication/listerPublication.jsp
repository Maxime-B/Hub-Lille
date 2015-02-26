<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">Les annonces</tiles:putAttribute>
	<tiles:putAttribute name="main">
		<session> 
		
		<c:if test="${type == 'mix'}">
			<c:set var="estMix" value="${true}"/>
		</c:if>
		
		<table width="100%" id="datatable">
			<thead>
				<tr>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${publications}" var="a" varStatus="status">
					<c:if test="${estMix}">
						<c:set var="type" value="${types[status.index]}" />
					</c:if>
					
					<tr>
						<td>
							<table
								style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px; width: 100%;">
								<tr>
									<td><p class="titre upper">${a.titre}<span
												class="type right upper">${type}</span>
										</p>
										<p class="description upper">${a.description}</p>
								</tr>
								<tr>
									<c:if test="${type == 'Annonce'}">
										<td width=25% bgcolor="#FFFFFF"><a class="radius button"
											href="${pageContext.request.contextPath}/annonce/consulter?ref=${a.id}">Details</a></td>
									</c:if>
									<c:if test="${type == 'Job'}">
										<td width=25% bgcolor="#FFFFFF"><a class="radius button"
											href="${pageContext.request.contextPath}/job/consulter?ref=${a.id}">Details</a></td>
									</c:if>
								</tr>
							</table>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</session>
	</tiles:putAttribute>

	<tiles:putAttribute name="js">
		<script src="<c:url value="/ressources/js/datatablesLister.js"/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>