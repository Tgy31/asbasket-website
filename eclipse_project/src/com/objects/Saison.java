package com.objects;
import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;

@Entity
public class Saison {
    @Id Long id; // Sera généré automatiquement
    @Index int year;
    List<Ref<Equipe>> equipesMasculines = new ArrayList<Ref<Equipe>>();
    List<Ref<Equipe>> equipesFeminines = new ArrayList<Ref<Equipe>>();
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Ref<Equipe>> getEquipesMasculines() {
		return equipesMasculines;
	}

	public void setEquipesMasculines(List<Ref<Equipe>> equipesMasculines) {
		this.equipesMasculines = equipesMasculines;
	}

	public List<Ref<Equipe>> getEquipesFeminines() {
		return equipesFeminines;
	}

	public void setEquipesFeminines(List<Ref<Equipe>> equipesFeminines) {
		this.equipesFeminines = equipesFeminines;
	}

	public Saison() {};
    
    public String title()
    {
    	return "Saison "+ this.year + " - " + (this.year+1);
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
    


}
