package com.servlet;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import com.objects.Equipe;
import com.objects.Joueur;
import com.objects.Saison;

public class GetJoueurFromEquipeServlet extends HttpServlet {
	// Enregistrement de la classe persistable auprès d'Objectify
	static {
		ObjectifyService.register(Saison.class);
		ObjectifyService.register(Equipe.class);
		ObjectifyService.register(Joueur.class);
	}
	
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Requête Objectify
        	String sEquipeId = req.getParameter("equipe");
        	if (sEquipeId != null) {
            	Long lEquipeId = Long.parseLong(sEquipeId);
            	Equipe equipe = ofy().load().type(Equipe.class).id(lEquipeId).now();
                req.setAttribute("joueurs", equipe.joueurs);
                this.getServletContext().getRequestDispatcher("/WEB-INF/ajax/getJoueurFromEquipe.jsp").forward(req, resp);
        	}
        } catch (ServletException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {  

    }

}
