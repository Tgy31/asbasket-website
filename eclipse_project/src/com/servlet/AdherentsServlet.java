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

public class AdherentsServlet extends HttpServlet {
    // Enregistrement de la classe persistable auprès d'Objectify
    static {
        ObjectifyService.register(Adherent.class);
    }
	
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Requête Objectify
            List<Adherent> adherents = ofy().load().type(Adherent.class).list();
            req.setAttribute("adherents", adherents);
            this.getServletContext().getRequestDispatcher("/WEB-INF/adherents.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {  

    }

}
