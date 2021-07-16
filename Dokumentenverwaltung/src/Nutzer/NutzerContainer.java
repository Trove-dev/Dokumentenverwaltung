package Nutzer;

import java.util.ArrayList;

public class NutzerContainer implements NutzerContainerInterface{
	
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
	public boolean addNeuenNutzer(String name, Rechte rechte, String nameVollstaendig) {
		if(sucheNutzer(name) != null || rechte == null) return false;
		listeNutzer.add(new Nutzer(name, rechte, nameVollstaendig));
		return true;
	}

	public boolean loescheNutzer(String name) {
		Nutzer delNutzer = sucheNutzer(name);
		if(delNutzer == null) return false;
		listeNutzer.remove(delNutzer);
		return true;
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
			if(user.getName() == name)
				return user;
		return null;		
	}
	
	@Override
	public ArrayList<Nutzer> getListeNutzer() {
		
		return listeNutzer;
	}

	public void printNutzer() {
	//	listeNutzer.sort(null);
		for(Nutzer user:listeNutzer) 
			System.out.println(user.getName());
	}
	

}
