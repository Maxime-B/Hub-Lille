<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tiles:insertDefinition name="annonce">
	<tiles:putAttribute name="title">Les annonces</tiles:putAttribute>
	<tiles:putAttribute name="main">
	
		<div class="row">
			<div class="large-12 columns">
				<br />
				<h4>
					<spring:message code="annonce.lister.titre" />
				</h4>
				<br />
				<div style="text-align: right">
					<div align="right">
							<td><select name="publication"
								style="width: 200px; height: 35px">
									<option>publications</option>
									<option>annonces</option>
									<option>jobs</option>
									<option>evenements</option>
							</select></td>
							&nbsp
							<td><input type="reset" value="chercher" 
								 /></td>
						
					</div>
				</div>
				<!--  <div style="width: 450px; height: 250px; border: 1px solid black;-webkit-border-radius: 10px;-moz-border-radius: 10px; border-radius: 10px;">-->

				<div
					style="padding-top: 10px; padding-right: 40px; padding-bottom: 10px; padding-left: 40px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px;margin: 0 2% 5% 0;">
					<form method="get" action="">
						<!--<form method="get" action= "annonce/listerCategorie">-->
						<div style="text-align: right">
							<table>
								<tr>
									<td><input type="text" name="motCle"
										style="width: 170px; height: 30px"></td>

									<td><select name="categorie"
										style="width: 200px; height: 35px">
											<option></option>
											<c:forEach items="${categories}" var="item">
												<option value="${item.nom}" selected>${item.nom}</option>
											</c:forEach>
									</select></td>
									<td><select name="type"
												style="width: 200px; height: 35px">
													<option></option>
													<c:forEach items='${types}' var="t">
													<option  value="${t} ">${t} </option>
													</c:forEach>
											</select></td>
									<td><input type="submit" value="chercher"
										class="radius button" style="padding: 10px 50px" /></td>
								</tr>
							</table>
						</div>

					</form>
					${motCle} ${categorie} <br /> <br /> &nbsp
					<c:if test="${not empty annonces}">
						<c:forEach items="${annonces}" var="a">
							<table
								style="padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px; border: solid 1px #EAEAEA; -webkit-border-radius: 10px; -moz-border-radius: 10px; border-radius: 10px;width:100%;">
								<tr>
									<td rowspan="2">
									
											<c:if test="${fn:length(a.images) gt 0}">
				  		
  							
								 <img class="th"src="${pageContext.request.contextPath}/ressources/photos/${a.images[0] }">
	
				</c:if>
				<c:if test="${fn:length(a.images) lt 1}">
							  <img class="th" src="/hublille1/ressources/img/pas-dimage.png"/>

				</c:if>
									</td>

									<td><h5>
												${a.titre} </h5><span>${a.description}</span></td>
												
									<td align=right><B><FONT color="#008CBA">${a.lesChamps['prix']}&#x20AC</FONT></B></td>

								</tr>
								<tr>
									<td width=50% bgcolor="#FFFFFF"></td>
									<td bgcolor="#FFFFFF">
										<a href="annonce/consulter?ref=${a.id }" />

											 <button class="radius button" >Details</button>

										</a>
									</td>
								</tr>
							</table>
						</c:forEach>
						<!--  <p>
							
							<div align=center>
								<h6>
									<u><span
										style="font-family: Comic Sans MS; font-weight: bold; color: #000000;">
											${a.titre} </span></u>
											${a.lesChamps['prix']} 
								</h6>
							</div>
							<div align=left>
								<img
									src="${pageContext.request.contextPath}/ressources/img/pas-dimage.png"
									width="200" height="450" style="border: solid 1px;" />
								<div class="orbit-caption">
									<!--<c:forEach items="${a.lesChamps}" var="entry">
										<%--${entry.key}--%>${entry.value}
									</c:forEach>-->
						<!--  </div>
							</div>
							<form method="get" action="annonce/consulter">
								<div align=right>
									<input type="hidden" name="ref" value="${a.id}"> <input
										type="submit" value="details" style="padding: 8px 40px"
										class="radius button" />
								</div>
							</form>
					
							</p>-->


					</c:if>
					<c:if test="${empty annonces}">
						<tr>
							<td colspan="2">aucune annonce créée</td>
						</tr>
					</c:if>

				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>