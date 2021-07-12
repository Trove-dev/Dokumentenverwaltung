package Nutzer;

public class NutzerContainer implements NutzerContainerInterface{
	private Nutzer listeNutzer[];
	
	private static NutzerContainer uniqueInstance;
	
	
	
	
	public void addiereNutzer(String name, String rechte, String nameVollstaendig) {
		
	}
	
	public void loescheNutzer(String name) {
		
	}
	
	public void bearbeiteNutzer(String name, String rechte, String nameVollstaendig) {
		
	}
	
	public NutzerContainer getInstanceNutzer() {
		if (uniqueInstance == null) {
			uniqueInstance = new NutzerContainer(listeNutzer);
		}
		return uniqueInstance;
	}
	
	public NutzerContainer(Nutzer[] listeNutzer) {
		super();
		this.listeNutzer = listeNutzer;
	}
	

}
