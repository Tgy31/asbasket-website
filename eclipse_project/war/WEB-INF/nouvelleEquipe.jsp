<%@ page import="com.objects.Equipe"%>
<%@ page import="com.objects.Adherent"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.google.appengine.api.blobstore.*"%>

<div class="container">

	<h3>
		Nouvelle Equipe
	</h3>


	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Informations générales</div>
		<div class="panel-body">
			<div class="col-lg-7">
				<form class="form-horizontal" action="/equipes/masculines/"
					method="POST">

					<div class="form-group">
						<label for="inputNomEquipe" class="col-lg-4 control-label">
							Nom d'équipe </label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="inputNomEquipe"
								placeholder="Nom d'équipe" name="equipeName">
						</div>
					</div>

					<div class="form-group">
						<label for="inputCoach" class="col-lg-4 control-label">Coach</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="inputCoach"
								placeholder="Prénom Nom" name="coach">
						</div>
					</div>

					<input type="hidden" name="formType" value="new" />

					<div class="form-group">
						<div class="col-lg-offset-4 col-lg-8">
							<button type="submit" class="btn btn-primary">
								Créer l'équipe
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>






