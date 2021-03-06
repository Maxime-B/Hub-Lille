		
var fr = {
		"sProcessing":     "Traitement en cours...",
		"sSearch":         "Rechercher&nbsp;:",
	    "sLengthMenu":     "Afficher _MENU_ &eacute;l&eacute;ments",
		"sInfo":           "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
		"sInfoEmpty":      "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
		"sInfoFiltered":   "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
		"sInfoPostFix":    "",
		"sLoadingRecords": "Chargement en cours...",
	    "sZeroRecords":    "Aucun &eacute;l&eacute;ment &agrave; afficher",
		"sEmptyTable":     "Aucune donn&eacute;e disponible dans le tableau",
		"oPaginate": {
			"sFirst":      "Premier",
			"sPrevious":   "Pr&eacute;c&eacute;dent",
			"sNext":       "Suivant",
			"sLast":       "Dernier"
		},
		"oAria": {
			"sSortAscending":  ": activer pour trier la colonne par ordre croissant",
			"sSortDescending": ": activer pour trier la colonne par ordre d&eacute;croissant"
		}
	};

var en = 
{
		"sEmptyTable":     "No data available in table",
		"sInfo":           "Showing _START_ to _END_ of _TOTAL_ entries",
		"sInfoEmpty":      "Showing 0 to 0 of 0 entries",
		"sInfoFiltered":   "(filtered from _MAX_ total entries)",
		"sInfoPostFix":    "",
		"sInfoThousands":  ",",
		"sLengthMenu":     "Show _MENU_ entries",
		"sLoadingRecords": "Loading...",
		"sProcessing":     "Processing...",
		"sSearch":         "Search:",
		"sZeroRecords":    "No matching records found",
		"oPaginate": {
			"sFirst":    "First",
			"sLast":     "Last",
			"sNext":     "Next",
			"sPrevious": "Previous"
		},
		"oAria": {
			"sSortAscending":  ": activate to sort column ascending",
			"sSortDescending": ": activate to sort column descending"
		}
	};

$( document ).ready(function() {
	var current = extractUrlParams()["locale"];
	
	
	var langage = (current == "fr_FR" || current == null ? fr : en)
		$("#datatable").DataTable({
		"searching": false,
		"pagingType": "simple_numbers",
			"oLanguage": langage
		});
		
		
});


function extractUrlParams () {
	var t = location.search.substring(1).split('&');
	
	var f = [];
	for (var i=0; i<t.length; i++) {
	var x = t[ i ].split('=');
	f[x[0]]=x[1];
	}
	
	return f;
	}