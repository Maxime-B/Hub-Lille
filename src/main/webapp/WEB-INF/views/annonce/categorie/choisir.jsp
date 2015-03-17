<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title"><spring:message code="annonce.choix.titre" /></tiles:putAttribute>
	<tiles:putAttribute name="main">
		<div class="row">
			<div class="large-12 columns">
				<br/>
				<b><spring:message code="annonce.choix.categorie" /></b>
				<br/><br/>
			

				<form method="get"
					action="${pageContext.request.contextPath}/annonce/creer">
					<select name="categorieChoisie">
						<c:forEach items="${categories}" var="item">
						<c:set var="tmp" value="${item.nom}"/>
						<c:set var="libelle" value="${fn:split(tmp, '-')}" />
						<c:set var="langue">
							<spring:message code="template.langue" />
						</c:set>
						<c:if test="${langue=='fr'}">
							<option value="${item.nom}">${libelle[0]}</option>
							</c:if>
						<c:if test="${langue=='en'}" >
							<option value="${item.nom}">${libelle[1]}</option>
						</c:if>
						</c:forEach>
					</select>
					
					<b><spring:message code="annonce.choix.type" /></b>
					<br/><br/>
		     		
					<select name="typeChoisit">
						<c:forEach items="${types}" var="type">
							<option value="${type}"><spring:message code="annonce.${type}" /></option>
						</c:forEach>
					</select>
					<div style="text-align:right">
					 <input type="submit"  style="height:40px" value="<spring:message code="annonce.choix.suivant" />" class="radius button">
					 </div>
				</form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>