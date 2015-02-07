<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- jquery -->

<!-- Jquery avec support des vieux navigateurs -->
<!--[if lt IE 9]>
<script
	src="${pageContext.request.contextPath}/ressources/js/jquery-1.11.2.min.js"></script>
<![endif]-->

<!-- Jquery optimisé pour les navigateurs modernes (v2) -->
<!--[if gte IE 9]>
<script
	src="${pageContext.request.contextPath}/ressources/js/vendor/jquery.js"></script>
<![endif]-->
<!--[if !IE]><!-->
<script
	src="${pageContext.request.contextPath}/ressources/js/vendor/jquery.js"></script>
<!--<![endif]-->

<!-- fin jquery -->

<script
	src="${pageContext.request.contextPath}/ressources/js/foundation.min.js"></script>

<script
	src="${pageContext.request.contextPath}/ressources/js/jquery-ui/jquery-ui.min.js"></script>

<script
	src="${pageContext.request.contextPath}/ressources/js/jquery-ui/jquery-ui-i18n.js"></script>

<spring:message code="template.langue" var="langue" />
<script>
	$(document).foundation();
	$(".spinner").spinner()
	//traductions à télécharger : https://github.com/jquery/jquery-ui/tree/master/ui/i18n
	$(".datepicker").datepicker($.datepicker.regional["${langue}"]);
	$(".datepicker").datepicker("option", {
		changeMonth : true,
		changeYear : true,
		showWeek : true,
		firstDay : 1,
		showButtonPanel : true
	});
</script>