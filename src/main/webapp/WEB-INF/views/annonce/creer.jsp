<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title"> <spring:message code="annonce.creer.titre" /></tiles:putAttribute>
	<tiles:putAttribute name="main">
		<section class="section">
				<h1><spring:message code="annonce.creer.titre" /></h1>
				<form method="post">
					<c:forEach items="${lesChamps}" var="item">
					 <spring:message code="annonce.creer.label.${item.libelle}" /> 
					 <c:if test="${item.obligatoire}"><small><spring:message code="annonce.creer.requis" /></small></c:if>
						<c:choose>
							<c:when test="${item.typeChamp=='TEXTE'}">
  							    
  							  : <input type="text" name="${item.libelle}">
							</c:when>
							<c:when test="${item.typeChamp=='DATE'}">
  							  
  							  : <input type="date" type="text" name="${item.libelle}">
							</c:when>
							<c:when test="${item.typeChamp=='TEXTEAREA'}">
  							   
  							  : <textarea name="${item.libelle}"></textarea>
							</c:when>
							<c:when test="${item.typeChamp=='IMAGE'}">
  							   
  							  : <input type="file" type="text" name="${item.libelle}">
							</c:when>
							
							<c:otherwise>
 						 </c:otherwise>
						</c:choose>

						<br />
					</c:forEach>
					<input type="submit" value=" <spring:message code="annonce.creer.submit" />" />
				</form>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>