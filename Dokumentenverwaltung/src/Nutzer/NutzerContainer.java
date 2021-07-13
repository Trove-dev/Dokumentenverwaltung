package Nutzer;

public class NutzerContainer implements NutzerContainerInterface{
	private static NutzerContainer uniqueInstance = null;
	private Nutzer listeNutzer[];
	
	private NutzerContainer() {
	}
	public static NutzerContainer getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NutzerContainer();
		}
		return uniqueInstance;
	}
	
	public void addiereNutzer(String name, String rechte, String nameVollstaendig) {
		Nutzer tmp = new Nutzer(name, rechte, nameVollstaendig);
		
	}
	
	public void loescheNutzer(String name) {
		
	}
	
	public void bearbeiteNutzer(String name, String rechte, String nameVollstaendig) {
		
	}

}
