package com.testjee1;
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
