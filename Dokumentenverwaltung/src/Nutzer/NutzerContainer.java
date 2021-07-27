package Nutzer;


import java.util.ArrayList;	
import java.io.Serializable;

public class NutzerContainer implements NutzerContainerInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5149599819425251462L;
	private static NutzerContainer uniqueInstance = null;
	private ArrayList <Nutzer> listeNutzer = new ArrayList<Nutzer>();
	
	private NutzerContainer() {
	}
	
	public static NutzerContainer getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NutzerContainer();
		}
		return uniqueInstance;
	}
	
	@Override
/*
	public boolean addNeuenNutzer(String name, Rechte rechte, String nameVollstaendig) {
		if(sucheNutzer(name) != null || rechte == null) return false;
		Nutzer newNutzer = new Nutzer(name, rechte, nameVollstaendig);
		listeNutzer.add(newNutzer);
		return true;*/
	public Nutzer addNeuenNutzer(String name, Rechte rechte, String nameVollstaendig) {
		Nutzer n = null;
		if(sucheNutzer(name) == null && rechte != null) {
			n = new Nutzer(name, rechte, nameVollstaendig);
			listeNutzer.add(n);
		}
		return n;
	}

	public void loescheNutzer(String name) {
		Nutzer delNutzer = sucheNutzer(name);
		listeNutzer.remove(delNutzer);
	}
	
	public void bearbeiteNutzerName(Nutzer user, String nameNew) {
		user.setName(nameNew);
	}
		
	public void bearbeiteNutzerRechte(Nutzer user, Rechte rechteNew) {
		user.setRechte(rechteNew);
	}
		
	public void bearbeiteNutzerNameVollstandig(Nutzer user, String nameVollstaendigNew) {
		user.setNameVollstaendig(nameVollstaendigNew);
	}
	
	public Nutzer sucheNutzer(String name) {
		for(Nutzer user:listeNutzer) 
			if(user.getName().compareTo(name) == 0)
				return user;
		return null;		
	}
	
	@Override
	public ArrayList<Nutzer> getListeNutzer() {
		
		return listeNutzer;
	}

	public void printNutzerList() {
		for(Nutzer user:listeNutzer) 
			System.out.println("Name: " + user.getName() + "; Rechte : " + user.getRechte()+ "; vollständiger Name : " + user.getNameVollstaendig() + "\n");
		
	}
}
