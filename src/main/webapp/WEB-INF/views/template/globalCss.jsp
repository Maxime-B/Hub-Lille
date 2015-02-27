<!-- foundation -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/css/normalize.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/css/foundation.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/css/icons/foundation-icons.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/css/dataTables.foundation.css" />

<style>
.row, .contain-to-grid .top-bar {
	max-width: 85rem
}
</style>
<!-- fin foundation -->

<!-- jquery-ui -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/js/jquery-ui/jquery-ui.structure.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/js/jquery-ui/jquery-ui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ressources/js/jquery-ui/jquery-ui.theme.min.css" />

<!-- patchs de compabilité pour foundation zt jquery-ui -->
<style>
.datepicker {
	position: inherit
}

.ui-spinner {
	width: 100%;
	margin-bottom: 16px;
	border-style: none
}

.ui-spinner-input {
	margin-bottom: 0 !important;
}

.ui-icon {
	display: inline-block
}
</style>
<!-- fin jquery-ui -->

<!-- application -->
<style>
#aside {padding-top:1em}
/* #search-publication {float:'right';width:20em;} */
#search-publication .row>*:first-child input {border-radius:1000px 0 0 1000px !important}
#search-publication .row>*:last-child select {border-radius:0 1000px 1000px 0 !important}
</style>
<!-- fin application -->
