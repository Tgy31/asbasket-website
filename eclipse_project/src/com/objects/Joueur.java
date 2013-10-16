package com.objects;

import com.googlecode.objectify.annotation.*;

@EntitySubclass
@Embed
public class Joueur extends Adherent {
	
	public Joueur() {};
	
	public Joueur(Adherent adherent) {
		this.id = adherent.id;
		this.firstName = adherent.firstName;
		this.lastName = adherent.lastName;
		this.year = adherent.year;
		this.option = adherent.option;
		this.mail = adherent.mail;
	}
}
