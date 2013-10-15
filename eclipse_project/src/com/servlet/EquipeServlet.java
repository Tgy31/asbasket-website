package com.servlet;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.images.*;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.objects.Adherent;
import com.objects.Equipe;
import com.objects.Saison;

public class EquipeServlet extends HttpServlet {
	// Enregistrement de la classe persistable auprès d'Objectify
	static {
		ObjectifyService.register(Saison.class);
		ObjectifyService.register(Equipe.class);
		ObjectifyService.register(Adherent.class);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("GET:" + req.getRequestURL().toString());
		//this.createSaison();
		try {
			String[] path = req.getPathInfo().split("/");
			System.out.print("Path split = ");
			for (int i = 0; i < path.length; i++) {
				System.out.print(path[i] + " - ");
			}
			System.out.println("");
			String type = "";
			List<Ref<Equipe>> rEquipes = new ArrayList<Ref<Equipe>>();
			if (path.length <= 1) {
				resp.sendRedirect("/equipes/masculines/");
			} else {

				String saisonIndex = "2013";
				if (path.length > 2) { 
					saisonIndex = path[2];
				} else {
					resp.sendRedirect("/equipes/" + path[1] + "/" + saisonIndex + "/");
				}
				int nSaisonIndex = Integer.parseInt(saisonIndex);
				System.out.println("saisonIndex = " + saisonIndex);
				req.setAttribute("saisonIndex", saisonIndex);

				if (path[1].contains("feminine")) {
					type = "féminines";
					Saison saison = ofy().load().type(Saison.class).filter("year", nSaisonIndex).first().now();
					rEquipes = saison.getEquipesFeminines();
					req.setAttribute("equipeType", "feminines");
				} else if (path[1].contains("masculine")) {
					type = "masculines";
					Saison saison = ofy().load().type(Saison.class).filter("year", nSaisonIndex).first().now();
					rEquipes = saison.getEquipesMasculines();
					req.setAttribute("equipeType", "masculines");
				} else {
					resp.sendRedirect("/equipes/masculines/");
				}

				List<Equipe> listEquipes = new ArrayList<Equipe>();
				for (Ref<Equipe> rEquipe : rEquipes) {
					listEquipes.add(rEquipe.get());
				}

				req.setAttribute("equipes", listEquipes);
				req.setAttribute("type", type);

				Equipe equipe = null;
				if (path.length > 3) {
					String sEquipeId = path[3];
					if (!sEquipeId.equalsIgnoreCase("nouvelle-equipe")) {
						long lEquipeId = Long.parseLong(sEquipeId);
						equipe = ofy().load().type(Equipe.class).id(lEquipeId).now();
					}
				} else {
					if (listEquipes.size() > 0) {
						equipe = listEquipes.get(0);
					}
				}
				req.setAttribute("equipe", equipe);
				if (equipe != null) {
					System.out.println("Equipe id = " + equipe.getId());
				} else {
					System.out.println("Equipe id = nul");
				}

				// Requête Objectify
				List<Saison> saisons = ofy().load().type(Saison.class).order("year").list();
				req.setAttribute("saisons", saisons);
				List<Adherent> adherents = ofy().load().type(Adherent.class).list();
				req.setAttribute("adherents", adherents);

				String requestURL = req.getRequestURL().toString(); 
				req.setAttribute("requestURL", requestURL);

				System.out.println("Dispatcher");
				this.getServletContext().getRequestDispatcher("/WEB-INF/equipes.jsp").forward(req, resp);

			}



		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("POST");

		String formType = req.getParameter("formType");
		if (formType != null && formType.equalsIgnoreCase("info")) {
			this.updateEquipeInfo(req, resp);
		} else if (formType != null && formType.equalsIgnoreCase("delete")) {

		} else if (formType != null && formType.equalsIgnoreCase("new")) {
			this.createNewEquipe(req, resp);			
		} else {
			this.updateEquipePhoto(req, resp);
		}

		String requestURL = req.getRequestURL().toString();

		resp.sendRedirect(requestURL);
	}


	private void createSaison()
	{

		Saison saison1 = new Saison();
		saison1.setYear(2013);

		Equipe equipe1 = new Equipe();
		equipe1.setNom("Equipe 1");
		Equipe equipe2 = new Equipe();
		equipe2.setNom("Equipe 2");

		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy().save().entities(equipe1).now();
		ofy().save().entities(equipe2).now();

		Key<Equipe> kEquipe1 = com.googlecode.objectify.Key.create(Equipe.class, equipe1.getId());
		Key<Equipe> kEquipe2 = com.googlecode.objectify.Key.create(Equipe.class, equipe2.getId());

		saison1.getEquipesMasculines().add(Ref.create(kEquipe1));
		saison1.getEquipesMasculines().add(Ref.create(kEquipe2));

		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy().save().entity(saison1).now();
		System.out.println(equipe1.getId());

		System.out.println("saison créée");
	}

	private void updateEquipeInfo(HttpServletRequest req, HttpServletResponse resp) {

		String sEquipeId = (String) req.getParameter("equipeid");
		Long lEquipeId = Long.parseLong(sEquipeId);
		Equipe equipe = ofy().load().type(Equipe.class).id(lEquipeId).now();

		// On peut récupérer les autres champs du formulaire comme d'habitude
		String equipeName = req.getParameter("equipeName");
		String coachName = req.getParameter("coach");
		int capitaineIndex = Integer.parseInt((String) req.getParameter("capitaine"));

		equipe.setNom(equipeName);
		equipe.setCoachName(coachName);
		equipe.setCapitaine(capitaineIndex);

		ofy().save().entity(equipe).now();
	}

	private void updateEquipePhoto(HttpServletRequest req, HttpServletResponse resp) {
		String sEquipeId = "";

		BufferedReader reader;
		String line = null;
		try {
			reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
			while ((line = reader.readLine()) != null) {
				if (line.contains("equipeid=")) {
					System.out.println(line);
					sEquipeId = line.substring(9);
					System.out.println(sEquipeId);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Long lEquipeId = Long.parseLong(sEquipeId);
		Equipe equipe = ofy().load().type(Equipe.class).id(lEquipeId).now();

		// Récupération du service d'images	
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		ImagesService imagesService = (ImagesService) ImagesServiceFactory.getImagesService();

		// Récupère une Map de tous les champs d'upload de fichiers
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

		// Récupère la liste des fichiers uploadés dans le champ "uploadedFile"
		// (il peut y en avoir plusieurs si c'est un champ d'upload multiple, d'où la List)
		List<BlobKey> blobKeys = blobs.get("uploadedPhoto");

		// Récupère la clé identifiant du fichier uploadé dans le Blobstore (à sauvegarder)
		//String cleFichierUploade = blobKeys.get(0).getKeyString();


		// Récupération de l'URL de l'image
		String urlImage = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)));


		equipe.setPhotoUrl(urlImage);

		ofy().save().entity(equipe).now();
	}

	public void createNewEquipe(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String[] path = req.getPathInfo().split("/");
			System.out.print("Path split = ");
			for (int i = 0; i < path.length; i++) {
				System.out.print(path[i] + " - ");
			}
			System.out.println("");
			String type = "";
			List<Ref<Equipe>> rEquipes = new ArrayList<Ref<Equipe>>();
			if (path.length <= 1) {
				resp.sendRedirect("/equipes/masculines/");
			} else {

				String saisonIndex = "2013";
				String gender = path[1];
				if (path.length > 2) { 
					saisonIndex = path[2];
				} else {
					resp.sendRedirect("/equipes/" + gender + "/" + saisonIndex + "/");
				}
				int nSaisonIndex = Integer.parseInt(saisonIndex);
				

				String coachName = req.getParameter("coach");
				String equipeName = req.getParameter("equipeName");

				Equipe equipe = new Equipe();
				equipe.setNom(equipeName);
				equipe.setCoachName(coachName);

				// Enregistrement de l'objet dans le Datastore avec Objectify
				ofy().save().entities(equipe).now();

				Key<Equipe> kEquipe1 = com.googlecode.objectify.Key.create(Equipe.class, equipe.getId());
				Saison saison = null;

				if (path[1].contains("feminine")) {
					type = "féminines";
					saison = ofy().load().type(Saison.class).filter("year", nSaisonIndex).first().now();
					saison.getEquipesFeminines().add(Ref.create(kEquipe1));
				} else if (path[1].contains("masculine")) {
					type = "masculines";
					saison = ofy().load().type(Saison.class).filter("year", nSaisonIndex).first().now();
					saison.getEquipesMasculines().add(Ref.create(kEquipe1));
				} else {
					resp.sendRedirect("/equipes/" + gender + "/" + saisonIndex + "/");
				}
				



				// Enregistrement de l'objet dans le Datastore avec Objectify
				ofy().save().entity(saison).now();
				resp.sendRedirect("/equipes/" + gender + "/" + saisonIndex + "/" + equipe.getId() + "/");
				
				

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
