$.fn.dataTableExt.oApi.fnReloadAjax = function ( oSettings, sNewSource ) {
	if ( typeof sNewSource != 'undefined' )
		oSettings.sAjaxSource = sNewSource;

	this.fnClearTable( this );
	this.oApi._fnProcessingDisplay( oSettings, true );
	var that = this;

	$.getJSON( oSettings.sAjaxSource, null, function(json) {
		/* Got the data - add it to the table */
		for ( var i=0 ; i<json.aaData.length ; i++ ) {
			that.oApi._fnAddData( oSettings, json.aaData[i] );
		}

		oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
		that.fnDraw( that );
		that.oApi._fnProcessingDisplay( oSettings, false );
	});
}

/*
 * I don't actually use this here, but it is provided as it might be useful and demonstrates
 * getting the TR nodes from DataTables
 */
function fnGetSelected(oTableLocal) {
	return oTable.$('tr.row_selected');
}

function refreshDataTables() {
	$('.table_joueur_equipe').each(function( index ) {
		var url = '/ajax/getjoueurfromequipe/?equipe=' + $(this).prop('id');
		$(this).dataTable().fnReloadAjax(url);
	});
}

function openModalAddjoueur() {
	var sSelectedTeam = $('.tab_equipe.active').prop('id');
	var url = '/ajax/getadherents/?equipe=' + sSelectedTeam;
	oTableAddAdherent.fnReloadAjax(url);
}

function checkRow(row)
{
	$(row).toggleClass('row_selected');
	var checkBoxes = $(row).children(4).children('input');
	checkBoxes.prop("checked", $(row).hasClass('row_selected'));
}

/*
 * I don't actually use this here, but it is provided as it might be useful and demonstrates
 * getting the TR nodes from DataTables
 */
function fnGetSelected( oTableLocal )
{
	return oTable.$('tr.row_selected');
}


$(".openModalAddjoueur").click(function() {
	openModalAddjoueur();
});

function submitFormRemoveJoueur() { 
    var sCheckData = oTableJoueurEquipe.$('input').serialize();
	var sSelectedTeam = $("#table_joueur_equipe").attr("equipeid");
    
    var xhr; 
    try {  xhr = new ActiveXObject('Msxml2.XMLHTTP');   }
    catch (e) 
    {
        try {   xhr = new ActiveXObject('Microsoft.XMLHTTP'); }
        catch (e2) 
        {
           try {  xhr = new XMLHttpRequest();  }
           catch (e3) {  xhr = false;   }
         }
    }
  
    xhr.onreadystatechange  = function() { 
        if(xhr.readyState  == 4)
        {
 	        if(xhr.status  == 200) {
 	        	if (xhr.responseText != "success") {
 	        		alert("La requte a renvoyŽ : " + xhr.responseText);
 	        	} 
 	        	setTimeout(validateRemoveJoueurAction(),1000)
 	        } else {
 	            alert("Error code " + xhr.status);
 	        }
        }
    }; 
    
   if (sCheckData.length > 0) {
	   var sData = "equipe=" + sSelectedTeam + "&" + sCheckData;
	   var url = "/ajax/removejoueurfromequipe/?" + sData;
	   console.log("ajax request url = " + url);
	   xhr.open( "GET", url,  true); 
	   xhr.send(null);
   } 
}

function validateRemoveJoueurAction() {
	oTableAddAdherent.fnReloadAjax();
	oTableJoueurEquipe.fnReloadAjax();
}




$(document).ready(function() {
	var lEquipeId = $("#table_joueur_equipe").attr("equipeid");
	
	$('.tab_equipe').click(function (e) {
		refreshDataTables();
	});

	/* Init the table */
	oTableAddAdherent = $('#addAdherentTable').dataTable( {
		"bProcessing": true,
		"bRetrieve": true,
		"sAjaxSource": '/ajax/getadherents/?equipe=' + lEquipeId,
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			/* Append the click callback */
			$(nRow).click(function(){checkRow(this)});
		}
	});


	/* Init the tabls */
	console.log(lEquipeId);
	var url = '/ajax/getjoueurfromequipe/?equipe=' + lEquipeId;
	oTableJoueurEquipe = $('#table_joueur_equipe').dataTable({
			"bProcessing" : true,
			"bRetrieve" : true,
			"bDestroy" : true,
			"sAjaxSource" : url,
			"oLanguage" : {
				"sLengthMenu" : "Afficher _MENU_ joueurs par page",
				"sZeroRecords" : "Aucun joueur",
				"sInfo" : "Joueurs _START_ &agrave; _END_ sur _TOTAL_",
				"sInfoEmpty" : "Aucun joueur &agrave; afficher",
				"sInfoFiltered" : "(filtered from _MAX_ total records)"
			},
			"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
				/* Append the click callback */
				$(nRow).click(function(){checkRow(this)});
			}
		});

});