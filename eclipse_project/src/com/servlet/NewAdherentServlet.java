package com.servlet;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import com.objects.Adherent;

public class NewAdherentServlet extends HttpServlet {
    // Enregistrement de la classe persistable auprès d'Objectify
    static {
        ObjectifyService.register(Adherent.class);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.getServletContext().getRequestDispatcher("/WEB-INF/newAdherent.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {  
        // Création de l'objet
		Adherent adherent = new Adherent();
		
		adherent.setFirstName(req.getParameter("first_name"));
		adherent.setLastName(req.getParameter("last_name"));
		adherent.setOption(req.getParameter("option"));
		adherent.setYear(Integer.parseInt(req.getParameter("year")));
		adherent.setMail(req.getParameter("mail"));
		
		// Enregistrement de l'objet dans le Datastore avec Objectify
		ofy().save().entity(adherent).now();
		
		req.setAttribute("actionSucceed", "yes");
        try {
            this.getServletContext().getRequestDispatcher("/WEB-INF/newAdherent.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
