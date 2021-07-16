package Nutzer;

import java.io.Serializable;

public class Nutzer implements NutzerContainerInterface, Serializable{
	private String name;
	private Rechte rechte;
	private String nameVollstaendig;
	
	public Nutzer(String name, Rechte rechte, String nameVollstaendig) {
		this.name = name;
		this.rechte = rechte;
		this.nameVollstaendig = nameVollstaendig;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rechte getRechte() {
		return rechte;
	}

	public void setRechte(Rechte rechte) {
		this.rechte = rechte;
	}

	public String getNameVollstaendig() {
		return nameVollstaendig;
	}

	public void setNameVollstaendig(String nameVollstaendig) {
		this.nameVollstaendig = nameVollstaendig;
	}

}
