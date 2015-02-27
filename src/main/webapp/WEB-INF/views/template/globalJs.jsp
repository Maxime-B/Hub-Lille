<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jquery -->

<!-- Jquery avec support des vieux navigateurs -->
<!--[if lt IE 9]>
<script
	src="${pageContext.request.contextPath}/ressources/js/jquery-1.11.2.min.js"></script>
<script>
	(function giveWindowAttr(html, body) {
		if (!window.innerWidth) Object.defineProperty(window, 'innerWidth', {
			get: function () { return html.clientWidth; }
		});
		if (!window.innerHeight) Object.defineProperty(window, 'innerHeight', {
			get: function () { return html.clientHeight; }
		});
	}(document.documentElement, document.body));
</script>
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
<script src="<c:url value="/ressources/js/jquery.dataTables.min.js"/>"></script>
		
		<script src="<c:url value="/ressources/js/dataTables.foundation.min.js"/>"></script>
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
	$(".datepicker").each(function(i, datePicker){
		var id = "fake-" + $(datePicker).attr("id")
		,   date = $(datePicker).attr("value")
		,   fakeInput = $('<input type="text"/>')
		fakeInput
			.attr("id", id)
			.datepicker($.datepicker.regional["${langue}"])
			.attr("placeholder", "dd/mm/yyyy")
			.datepicker("option", {
				changeMonth : true,
				changeYear : true,
				showWeek : true,
				firstDay : 1,
				showButtonPanel : true,
				altField: datePicker,
				altFormat: $.datepicker.W3C,//equivaut à yyyy-MM-dd
			})
		$(fakeInput).datepicker( "setDate", $.datepicker.parseDate("yy-mm-dd", date));
		$(datePicker).before(fakeInput)
		$('[for="' + $(datePicker).attr("id") + '"]').attr("for", id)
	})
</script>