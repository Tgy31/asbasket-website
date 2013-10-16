<!-- Modal -->
<div class="modal fade" id="addJoueurModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Ajouter des joueurs</h4>
			</div>
			<div class="modal-body">
				<!-- Table -->
				<table class="table" id="addAdherentTable">
					<thead>
						<tr>
							<th></th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Année</th>
							<th>Option</th>
							<th>Mail</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" id="save">Sauvegarder</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">
var oTableAddAdherent;

$(document).ready(function() {  

    $('#save').click( function() {
    	submitFormAddJoueur();
    } );
    
} );

function submitFormAddJoueur() { 
    var sCheckData = oTableAddAdherent.$('input').serialize();
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
 	        		alert("La requête a renvoyé : " + xhr.responseText);
 	        	} 
 	        	setTimeout(validateAddJoueurAction(),1000)
 	        } else {
 	            alert("Error code " + xhr.status);
 	        }
        }
    }; 
    
   if (sCheckData.length > 0) {
	   var sData = "equipe=" + sSelectedTeam + "&" + sCheckData;
	   var url = "/ajax/addjoueurtoequipe/?" + sData;
	   console.log(url);
	   xhr.open( "GET", url,  true); 
	   xhr.send(null);
   } 
}

function validateAddJoueurAction() {
	oTableAddAdherent.fnReloadAjax();
	oTableJoueurEquipe.fnReloadAjax();
}
 
</script>