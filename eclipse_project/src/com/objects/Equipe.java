package com.objects;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipe {

	@Id Long id; // Sera généré automatiquement
    String nom;
    List<Joueur> joueurs = new ArrayList<Joueur>();
    int capitaine;
    String coachName;
	String photoUrl;

	public boolean containsAdherent(Adherent adherent) {
    	for (Joueur joueur : this.joueurs)
    	{
    		if (joueur.id.longValue() == adherent.id.longValue()) {
    			return true;    		
    		}
    	}
    	return false;
    }
    
    public boolean addJoueurFromAdherent(Adherent adherent) {
    	if (adherent != null) {
    		if (!this.containsAdherent(adherent))
    		{
    			Joueur joueur = new Joueur(adherent);
    			this.joueurs.add(joueur);
;    		}
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean removeJoueurAtIndex(int index) {
    	if (index >= 0 && index < this.joueurs.size()) {
        	this.joueurs.remove(index);    	
        	return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean removeJoueurWithId(long joueurId) {
    	for (Joueur joueur : this.joueurs)
    	{
    		if (joueur.id.longValue() == joueurId) {
    			this.joueurs.remove(joueur);
    			return true;    		
    		}
    	}
    	return false;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public int getCapitaine() {
		return capitaine;
	}

	public void setCapitaine(int capitaine) {
		this.capitaine = capitaine;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


    

    

}
