<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<ul class="side-nav">
<li><a href="${pageContext.request.contextPath}/job/creer"><spring:message
				code="job.nav.creer" /></a></li>
<li><a href="${pageContext.request.contextPath}/job/listerJob"><spring:message
				code="job.nav.lister" /></a></li>
				<li><a href="${pageContext.request.contextPath}/"><spring:message
				code="job.nav.home" /></a></li>
<!--  <li><a href="${pageContext.request.contextPath}/job/chercherJob">chercher un job</a></li>-->
</ul>