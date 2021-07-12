package Nutzer;

public class Nutzer {
	private String name;
	private String rechte;
	private String nameVollstaendig;
	
	public Nutzer(String name, String rechte, String nameVollstaendig) {
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

	public String getRechte() {
		return rechte;
	}

	public void setRechte(String rechte) {
		this.rechte = rechte;
	}

	public String getNameVollstaendig() {
		return nameVollstaendig;
	}

	public void setNameVollstaendig(String nameVollstaendig) {
		this.nameVollstaendig = nameVollstaendig;
	}
	
	
//Test
}
