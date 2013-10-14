package com.servlet;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.objects.Adherent;
import com.objects.Equipe;
import com.objects.Joueur;
import com.sun.tools.javac.util.List;

public class RemoveJoueurFromEquipeServlet extends HttpServlet { 
	// Enregistrement de la classe persistable auprès d'Objectify
	static {
		ObjectifyService.register(Equipe.class);
		ObjectifyService.register(Joueur.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {		        
		// Requête Objectify
		String sId = (String)request.getParameter("equipe");
		Long id = Long.parseLong(sId);

		Equipe equipe = ofy().load().type(Equipe.class).id(id).now();
		System.out.println("equipe = " + equipe);

		Enumeration<String> paramNames = (Enumeration<String>)request.getParameterNames();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html");

		String[] sIndexCheckJoueurs = request.getParameterValues("check");
		boolean success = true;
		int oldSize = equipe.getJoueurs().size();
		System.out.println("old size = " + oldSize);
		for (int i = sIndexCheckJoueurs.length - 1; i >= 0 ; i--)
		{
			long lJoueurId =Long.parseLong(sIndexCheckJoueurs[i]);
			success = equipe.removeJoueurWithId(lJoueurId);
			System.out.println("Remove joueur at index : " + lJoueurId);
		}
		int newSize = equipe.getJoueurs().size();
		System.out.println("new size = " + newSize);
		if (success) {
			out.print("success");
			if (oldSize > newSize) {
				System.out.println("persist " + equipe);
				ofy().save().entity(equipe);
			}
		} else {
			System.out.println("transaction failed");
			out.print("Votre demande a échouée");
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {  

	}


}
