package UI;
import Verarbeitung.ServiceLocator;
import java.util.ArrayList;

import Nutzer.Nutzer;
import Nutzer.NutzerContainerInterface;
public class ControllerUI {
	
	private ServiceLocator serviceLocator;
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")     // ??
		ControllerUI c = new ControllerUI();
	}

	public ControllerUI() {
		serviceLocator = ServiceLocator.getInstance();
		start();
	}
	
	private void start() {
		NutzerContainerInterface nc = serviceLocator.getNutzerContainer();
		ArrayList<Nutzer> nutzerListe = nc.getListeNutzer();	
		NutzerUI nui = new NutzerUI(nc, nutzerListe);
		// Auswahl Nutzer
		nui.startAnmelden();
	}
}