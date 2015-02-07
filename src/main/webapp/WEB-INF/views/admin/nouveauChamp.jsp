<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="global">
	<tiles:putAttribute name="title">Nouveau champ
	</tiles:putAttribute>
	<tiles:putAttribute name="main">
<form:form modelAttribute="champ" method="POST" action="creationChamp">
                       <table width="550px" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="63%">libelle</td>
                                <td width="37%"><form:input path="libelle" /></td>
                              </tr>
                              <tr>     
                                <td>limite</td>
                                <td><form:input path="limite" /></td>
                              </tr>
                             <tr>	
                             		<td>type de champ</td>
                             		<td><form:select path="typeChamp">
   										 <form:options items="${typeChamps}" />
											</form:select></td>
                             </tr>
                                                           <tr>     
                                <td>Obligatoire</td>
                                <td><form:checkbox path="obligatoire" /></td>
                              </tr>
                             
                              <tr>    
                                <td colspan="2"><input type="submit" value="Validez" /></td>
                              </tr>
                       </table>
                     </form:form>
<script src="<c:url value="/ressources/js/creerCategorie.js"/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>
