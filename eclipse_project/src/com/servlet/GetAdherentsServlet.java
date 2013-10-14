package com.servlet;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.objects.Adherent;
import com.objects.Equipe;
import com.testjee1.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAdherentsServlet extends HttpServlet {
	// Enregistrement de la classe persistable auprès d'Objectify
	static {
		ObjectifyService.register(Adherent.class);
	}

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Requête Objectify
            List<Adherent> adherents = ofy().load().type(Adherent.class).list();
            req.setAttribute("adherents", adherents);
            
            String[] sEquipeId = req.getParameterValues("equipe");
            if (sEquipeId != null) {
                System.out.println(sEquipeId.length);
                for (int i = 0; i < sEquipeId.length; i++) {
                	Long lEquipeId = Long.parseLong(sEquipeId[i]);
                	Equipe equipe = ofy().load().type(Equipe.class).id(lEquipeId).now();
                	List<Adherent> adherentsToRemove = new ArrayList<Adherent>();
                	for (Adherent adherent : adherents) {
                		if (equipe.containsAdherent(adherent)) {
                			adherentsToRemove.add(adherent);
                		}
                	}
                	adherents.removeAll(adherentsToRemove);
                }
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/ajax/getAdherents.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
