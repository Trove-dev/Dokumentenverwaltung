package UI;

import java.util.ArrayList;
import java.util.Scanner;
import Nutzer.Nutzer;
import Nutzer.NutzerContainerInterface;
import Nutzer.Rechte;
import hilf.SichereEingabe;

/**
 * Klasse, welche die Oberfläche von Nutzern verwaltet
 */
public class NutzerUI {
	
	NutzerContainerInterface nc;
	ArrayList<Nutzer> nutzerListe;
    Scanner sc;
    Nutzer user;
    boolean angemeldet;
    
    /**
     * Konstrukor
     * @param nc
     * @param nutzerListe
     */
	public NutzerUI(NutzerContainerInterface nc, ArrayList<Nutzer> nutzerListe) {
		this.nc = nc;
		this.nutzerListe = nutzerListe;
		sc = new Scanner(System.in);
	}

	/**
	 * Start des Anmeldens
	 * Wenn keine Nutzer: neuen Nutzer anlegen
	 * Anonsten: Verwaltung von Nutzern
	 * 
	 * @return Nutzer, welcher nun angemeldet ist
	 */
	public Nutzer startAnmelden() {
		if(nutzerListe.isEmpty()) {			
			System.out.println("Es gibt noch keine Nutzer. Registrieren Sie sich bitte. "
					+ "Oder exit für das Ende des Programms. ");
			erzeugeNutzer();
			}else {
				while (angemeldet != true) {
					System.out.println("Sie müssen angemeldet sein");
					nc.printNutzerList();
					ausfuereBefehle(user);
				}
			}
		return user;
	}
	
	/**
	 * Neuen Nutzer erzeugen mittels sicherer Eingabe
	 */
	private void erzeugeNutzer(){
		String name = SichereEingabe.checkName(nc);
		Rechte recht = SichereEingabe.checkRechte();
		String vollName = SichereEingabe.checkVollstaendigenName();
		user = nc.addNeuenNutzer(name, recht, vollName);
		if(user != null) {
		    	System.out.println("Sie sind nun registriert:" );
		    	user.printNutzer();
		}else System.out.println("Sie sind nicht registriert");
	}
	
	/**
	 * Menü für die Verwaltung von Nutzern
	 */
	public void ausfuereBefehle(Nutzer user) {
		HilfUI.printBefehleNutzerUI();
		Scanner sca = new Scanner(System.in);
		String command = "";
		System.out.print("Bitte einen Befehl eingeben: ");
		command = SichereEingabe.liesCharacters();
		if(command.compareTo("login") == 0) {
			boolean erfolg = false;
			while (erfolg != true) {
				System.out.print("Geben Sie einen Namen ein: ");
				String userName = SichereEingabe.liesCharacters();
				if(userName.compareTo("end") == 0) break;
				if(anmeldeNutzer(userName) == true) {
					System.out.println("\n" + userName + ", Sie sind angemeldet\n");
					angemeldet = true;
					erfolg = true;
				}else {
					System.out.println("Dieser Name ist ungültig");
					System.out.println("Die Liste der Nutzer:\n");
					nc.printNutzerList();
				}
			}
		}else if(command.compareTo("create") == 0) {
			erzeugeNutzer();
		}else if(command.compareTo("edit") == 0) {
			bearbeiteNutzer();
		}else if(command.compareTo("del") == 0) {
			loescheNutzer();
		}else if(command.compareTo("end") == 0) {
			System.out.println("Sie verlassen das Programm");			
			sca.close();
			System.exit(0);
		}else {
			System.out.println("Der falsche Befehl\n");
			ausfuereBefehle(user);
		}
	}
	
	/**
	 * Löschen von Nutzer
	 * wird anhand von Username Eingabe erledigt
	 */
	private void loescheNutzer() {
		nc.printNutzerList();
		System.out.println("Name den zu löschenden Nutzer: ");
		String name = "";	
		Scanner input = new Scanner(System.in);
		name = input.nextLine();
		Nutzer n = nc.sucheNutzer(name);
		if(n == null) System.out.println("Es gibt keinen Nutzer zu löschen");
		else {
			nc.loescheNutzer(name);
			n.printNutzer();
			System.out.println("wurde erfolgreich gelöscht");
		}
	}
	
	/**
	 * Bearbeiten von Nutzer
	 * wird anhand von Username Eingabe erledigt
	 * Es kann Username, Recht, und Vollname geändert werden
	 * Schleife wird durch "end" beendet
	 */
	private void bearbeiteNutzer() {
		System.out.print("Geben Sie einen Namen ein: ");
		String userName = sc.next();
		String input = "";
		if(userName == "end") return;
		Nutzer n = nc.sucheNutzer(userName);		
		if(n == null) {
			System.err.println("Der Name ist ungültig!");
			bearbeiteNutzer();
		}else n.printNutzer();
		boolean erfolg = false;
		while(true) {		
			HilfUI.printBefehleEditNutzer();
			input = SichereEingabe.liesCharacters();
			if(input.compareTo("username") == 0) {
				String name = SichereEingabe.checkName(nc);   
				if(n.getName().compareTo(name) != 0) {
					n.setName(name);
					erfolg = true;				
				}
			}else if(input.compareTo("recht") == 0) {
				Rechte r = SichereEingabe.checkRechte();  
				if(n.getRechte().compareTo(r) == 0) {
					System.err.println("Das gleiche Recht");
					continue;
				}
				else {
					n.setRechte(r);
					erfolg = true;
				}
			}else if(input.compareTo("vollname") == 0) {
				String nameVoll = SichereEingabe.checkVollstaendigenName();   
				if(n.getNameVollstaendig().compareTo(nameVoll) == 0) {
					System.err.println("Der gleiche Name");
					continue;
				}
				else {
					n.setNameVollstaendig(nameVoll);
					erfolg = true;
				}				
			}else if(input.compareTo("end") == 0) {
				return;
			}else {
				System.err.println("Der falsche Befehl");
				continue;
			}
			if(erfolg == true) {
				System.out.println("Der Nutzer wurde erfolgreich bearbeitet\n");
				continue;
			}
		}
	}
	
	/**
	 * Anmeldung von Nutzer
	 * 
	 * @param name Welcher Nutzer soll angemeldet werden
	 * @return wenn erfolgreich, wird true zurückgeliefert
	 */
	public boolean anmeldeNutzer(String name) {
		user = nc.sucheNutzer(name);
		return user != null;
	}

}