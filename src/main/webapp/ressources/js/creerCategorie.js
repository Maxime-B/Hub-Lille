function test(choix){
	var nom = $(choix).attr("value");
	var check = $(choix).is(":checked");
	if(check){
	$('#sortable').append('<li id="' + nom + '" class="ui-state-default ui-sortable-handle"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span><input type="hidden" name ="champs" value="' + nom + '"/>' + nom + '</li>');
		//$('#champsChoisi').append('<div id="' + nom + '"><input type="hidden" name ="champs" value="' + nom + '"/>'+ nom +'</div>');
	}else{
		$('#' + nom).remove();
	}
	
}