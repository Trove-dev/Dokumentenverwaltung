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
    Scanner sc;
    Nutzer user;
	
	public NutzerUI(NutzerContainerInterface nc, ArrayList<Nutzer> nutzerListe) {
		this.nc = nc;
		this.nutzerListe = nutzerListe;
		sc = new Scanner(System.in);
	}

	public Nutzer startAnmelden() {
		
		if(nutzerListe.isEmpty()) {			
			System.out.println("Es gibt noch keine Nutzer. Registrieren Sie sich bitte.");
			erzeugeNutzer();
			}else {
				System.out.println("Die Liste der Nutzer:\n");
				nc.printNutzerList();
				ausfuereBefehle(user);
				int antwort = 0;
				for(int i = 0; i < nutzerListe.size(); i++) {
					System.out.println(++antwort + " - " + nutzerListe.get(i).getName());
					}
			}
		return user;
	}
	
	private void erzeugeNutzer() {
		String name = SichereEingabe.checkName(nc);
		Rechte recht = SichereEingabe.checkRechte();
		String vollName = SichereEingabe.checkVollstaendigenName();
		user = nc.addNeuenNutzer(name, recht, vollName);
		if(user != null) {
		    	System.out.println("Sie sind nun registriert:" );
		    	user.printNutzer();
		}else System.out.println("Sie sind nicht registriert");
	}
	
	public void ausfuereBefehle(Nutzer user) {
		printBefehle();
		Scanner sca = new Scanner(System.in);
		String command = "";
		command = sca.next(); //SichereEingabe.liestChar
		if(command.compareTo("login") == 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Geben Sie einen Name ein : ");
			String userName = sc.next();  //SichereEingabe.liestChar
			System.out.println("_"+userName+"_");
			if(anmeldeNutzer(userName) == true) {
				System.out.println("Sie sind angemeldet");
			}else {
				System.out.println("Dieser Name ist ungültig");
				ausfuereBefehle(user);
			}			
		}else if(command.compareTo("create") == 0) {
			erzeugeNutzer();
		}else if(command.compareTo("edit") == 0) {
			bearbeiteNutzer();
		}else if(command.compareTo("del") == 0) {
			loescheNutzer();
		}else if(command.compareTo("end") == 0) {
			System.out.println("Sie verlassen das Programm");
		}else {
			System.out.println("Der falsche Befehl\n");
			ausfuereBefehle(user);
		}
		sca.close();
		sc.close();
	}
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
		input.close();
	}
	
	private void bearbeiteNutzer() {
		String userName = sc.nextLine();
		if(userName == "end") return;
		Nutzer n = nc.sucheNutzer(userName);
		boolean erfolg = false;
		while(erfolg != true) {
			if(n == null) {
				System.out.println("Der Name ist ungültig");
				bearbeiteNutzer();
			}else {
				printBefehleEditNutzer();
				String input = sc.nextLine();  //SichereEingabe.liestChar
				if(input.compareTo(userName) == 0) {
					String name = SichereEingabe.checkName(n);   //SichereEingabe.liestChar
					n.setName(name);
					erfolg = true;
				}else if(input.compareTo("recht") == 0) {
					Rechte r = SichereEingabe.checkRechte();   //SichereEingabe.liestChar
					n.setRechte(r);
					erfolg = true;
				}else if(input.compareTo("vollname") == 0) {
					String nameVoll = SichereEingabe.checkVollstaendigenName();   //SichereEingabe.liestChar
					n.setNameVollstaendig(nameVoll);
					erfolg = true;
				}else if(input.compareTo("end") == 0) {
					//// zurück
					break;
				}else {
					System.out.println("Der falsche Befehl");
					bearbeiteNutzer();
				}
		}
		if(erfolg == true) System.out.println("Der Nutzer wurde erfolgreich bearbeitet");
		}
	}
	
	public boolean anmeldeNutzer(String name) {
		user = nc.sucheNutzer(name);
		return user != null;
	}
	private void printBefehleEditNutzer() {
		System.out.println("username \t ändert den Nutzername");
		System.out.println("recht \t\t ändert das Racht");
		System.out.println("vollname \t ändert den vollständigen Name");
		System.out.println("end \t\t beendet die Bearbeitung");
	}
	
	public void printBefehle() {
		System.out.println("-----Arbeit mit Nutzer-----\n");
		System.out.println("login \t\t meldet sich an");
		System.out.println("create \t erzeugt neuen Nutzer");
		System.out.println("edit \t\t bearbeitet den Nutzer");
		System.out.println("del \t\t löscht den Nutzer");
		System.out.println("end \t\t beendet das Programm\n");
	}
}