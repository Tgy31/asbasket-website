package com.servlet;
import com.testjee1.*;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.googlecode.objectify.Work;

public class AddJoueurToEquipeServlet extends HttpServlet { 
	// Enregistrement de la classe persistable auprès d'Objectify
	static {
		ObjectifyService.register(Equipe.class);
		ObjectifyService.register(Joueur.class);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		final HttpServletRequest request = req;
		final HttpServletResponse response = resp;
		ofy().transact(new VoidWork() {
		    public void vrun() { // Tout ce qui est exécuté dans cette méthode appartient à la même transaction
		        
				// Requête Objectify
				String sId = (String)request.getParameter("equipe");
				Long id = Long.parseLong(sId);
				
				Equipe equipe = ofy().load().type(Equipe.class).id(id).now();
				System.out.println(equipe);

				Enumeration<String> paramNames = (Enumeration<String>)request.getParameterNames();
				PrintWriter out = null;
				try {
					out = response.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setContentType("text/html");

				String[] sIdCheckJoueurs = request.getParameterValues("check");
				boolean success = true;
				int oldSize = equipe.getJoueurs().size();
				for (int i = 0; i < sIdCheckJoueurs.length; i++)
				{
					Long adherentId = Long.parseLong(sIdCheckJoueurs[i]);
					Adherent adherent = ofy().load().type(Adherent.class).id(adherentId).now();

					success = success & equipe.addJoueurFromAdherent(adherent);
				}
				int newSize = equipe.getJoueurs().size();
				System.out.println(newSize);
				if (success) {
					out.print("success");
					if (oldSize < newSize) {
						System.out.println("persist " + equipe);
						ofy().save().entity(equipe);
					}
				} else {
					out.print("Votre demande a échouée");
				}

		    }
		});



	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {  

	}

}
