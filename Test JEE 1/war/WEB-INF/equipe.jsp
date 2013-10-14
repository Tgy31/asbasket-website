<%@ page import="com.testjee1.Equipe"%>
<%@ page import="com.testjee1.Adherent"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.google.appengine.api.blobstore.*"%>

<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
	String formPostUrl = (String) request.getAttribute("requestURL");
%>
<div class="container">

	<h3>
		<c:out value="${equipe.nom}" />
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
								placeholder="Nom d'équipe" name="equipeName"
								value='<c:out value="${equipe.nom}"/>'>

						</div>
					</div>

					<div class="form-group">
						<label for="inputCoach" class="col-lg-4 control-label">Coach</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" id="inputCoach"
								placeholder="Prénom Nom" name="coach"
								value='<c:out value="${equipe.coachName}"/>'>
						</div>
					</div>

					<div class="form-group">
						<label for="selectCapitaine" class="col-lg-4 control-label">Capitaine</label>
						<div class="col-lg-8">
							<select class="form-control" name="capitaine"
								id="selectCapitaine">
								<option value="-1">Sélectionnez un joueur</option>
								<c:set var="i" value="0" scope="request" />
								<c:forEach var="joueur" items="${equipe.joueurs}">
									<option value='<c:out value="${i}"/>'
										<c:if test="${i == equipe.capitaine}">selected</c:if>>
										<c:out value="${joueur.firstName}" />
										<c:out value="${joueur.lastName}" />
									</option>
									<c:set var="i" value="${i + 1}" />
								</c:forEach>
							</select>
						</div>
					</div>

					<input type="hidden" name="formType" value="info" />
					<input type="hidden" name="equipeid"
						value="<c:out value="${equipe.id}" />" />

					<div class="form-group">
						<div class="col-lg-offset-4 col-lg-8">
							<button type="submit" class="btn btn-primary">Enregistrer
								les modifications</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-lg-5">
				<form class="form-horizontal" action="/equipes/masculines/"
					method="POST">

					<input type="hidden" name="formType" value="delete" />
					<input type="hidden" name="equipeid"
						value="<c:out value="${equipe.id}" />" />

					<div class="form-group">
						<div class="pull-right">
							<button type="submit" class="btn btn-danger">Supprimer
								l'équipe</button>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Liste des joueurs</div>

		<div class="panel-body">
			<!-- Table -->
			<table class="table" id="table_joueur_equipe" equipeid=<c:out value="${equipe.id}" />>
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

				<div class="btn-group pull-right">
					<!-- Button trigger modal -->
					<a data-toggle="modal" href="#addJoueurModal"
						class="btn btn-primary">Ajouter des joueurs</a> <a
						data-toggle="modal" href="#deleteJoueurModal"
						class="btn btn-primary" onclick="submitFormRemoveJoueur()">Retirer les joueurs</a>
				</div>
			</table>
		</div>
	</div>


	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Photo d'équipe</div>


		<div class="panel-body">
			<div class="col-lg-7">
				<form class="form-horizontal"
					action="<%= blobstoreService.createUploadUrl("/equipes/masculines/") %>"
					method="POST" enctype="multipart/form-data">

					<input type="hidden" name="formType" value="photo" /> <input
						type="hidden" name="equipeid"
						value="equipeid=<c:out value="${equipe.id}" />" />

					<div class="form-group">
						<label for="selectCapitaine" class="col-lg-4 control-label">Nouvelle
							image</label>
						<div class="col-lg-8">
							<input class="form-control" type="file" name="uploadedPhoto" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-offset-4 col-lg-8">
							<button type="submit" class="btn btn-primary">Envoyer</button>
						</div>
					</div>
				</form>
			</div>

			<div class="col-lg-5 text-center">
				<img src='<c:out value="${equipe.photoUrl}" />' alt="Photo d'équipe"
					class="img-thumbnail fill-container-img"> <br /> 
					<a href='<c:out value="${equipe.photoUrl}" />'>Taille réelle</a>
			</div>
		</div>
	</div>
</div>






