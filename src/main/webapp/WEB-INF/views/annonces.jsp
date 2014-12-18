<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/css/foundation.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HubLille1 | Les annonces</title>
</head>
<body>
	<jsp:include page="bar.jsp" />
	
	
	<div class="row">
		<div class="large-12 columns">
			<br/><h3>Les annonces</h3><br/>
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
</body>
</html>