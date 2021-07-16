package UI;

import java.util.ArrayList;
import java.util.Scanner;

import Nutzer.Nutzer;
import Nutzer.NutzerContainerInterface;
import Nutzer.Rechte;
import hilf.SichereEingabe;
public class NutzerUI {
	
	NutzerContainerInterface nc;
	ArrayList<Nutzer> nutzerListe;	
	
	public NutzerUI(NutzerContainerInterface nc, ArrayList<Nutzer> nutzerListe) {
		this.nc = nc;
		this.nutzerListe = nutzerListe;
	}

	public void startAnmelden() {
		
		if(nutzerListe.isEmpty()) {			
			System.out.println("Es gibt keine Nutzer. Registrieren Sie sich bitte.");
			String name = SichereEingabe.checkName(nc);
			Rechte recht = SichereEingabe.checkRechte();
			String vollName = SichereEingabe.checkVollstaendigenName();
			if(nc.addNeuenNutzer(name, recht, vollName)) {
			    	System.out.println("Sie sind nun registriert: \nUsername: " + name 
			    			+ "; das Recht : " + recht + "; Ihr Name: " + vollName);
				}else {
					System.out.println("Sie sind nicht registriert");
				}
			}else {
				
				int antwort = 0;
				for(int i = 0; i <= nutzerListe.size(); i++) {
					System.out.println(++antwort + " - " + nutzerListe.get(i));
					}
			}
	}
}