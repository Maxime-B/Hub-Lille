function test(choix){
	var nom = $(choix).attr("value");
	var check = $(choix).is(":checked");
	if(check){
	$('#sortable').append('<li id="' + nom + '" class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"><input type="hidden" name ="champs" value="' + nom + '"/></span>' + nom + '</li>');
		//$('#champsChoisi').append('<div id="' + nom + '"><input type="hidden" name ="champs" value="' + nom + '"/>'+ nom +'</div>');
	}else{
		$('#' + nom).remove();
	}
	
}