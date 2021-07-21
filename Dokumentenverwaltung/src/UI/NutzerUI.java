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
    boolean angemeldet;
	
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
				while (angemeldet != true) {
					System.out.println("Sie müssen angemelddet sein");
					System.out.println("\nDie Liste der Nutzer:");
					nc.printNutzerList();
					ausfuereBefehle(user);
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
			boolean erfolg = false;
			while (erfolg != true) {
				System.out.println("Geben Sie einen Name ein : ");
				String userName = sca.next();  //SichereEingabe.liestChar
				if(userName.compareTo("end") == 0) break;
				if(anmeldeNutzer(userName) == true) {
					System.out.println("Sie sind angemeldet");
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
		}else if(command.compareTo("end") == 0) {Runtime.getRuntime().exit(1);
			System.out.println("Sie verlassen das Programm");
			/////////////  
			
		}else {
			System.out.println("Der falsche Befehl\n");
			ausfuereBefehle(user);
		}
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
	}
	
	private void bearbeiteNutzer() {
		System.out.println("Geben Sie einen Name ein : ");
		String userName = sc.next();
		String input = "";
		if(userName == "end") return;
		Nutzer n = nc.sucheNutzer(userName);		
		if(n == null) {
			System.out.println("Der Name ist ungültig");
			bearbeiteNutzer();
		}else n.printNutzer();
		boolean erfolg = false;
		while(erfolg != true) {		
			printBefehleEditNutzer();
			input = sc.next();   //SichereEingabe.liestChar
			if(input.compareTo("username") == 0) {
				String name = SichereEingabe.checkName(nc);   //SichereEingabe.liestChar
				n.setName(name);
				if(n.getName().compareTo(name) != 0) erfolg = true;				
			}else if(input.compareTo("recht") == 0) {
				Rechte r = SichereEingabe.checkRechte();   //SichereEingabe.liestChar
				n.setRechte(r);
				if(n.getRechte().compareTo(r) == 0) System.out.println("Das gleiche Recht");
				else erfolg = true;
			}else if(input.compareTo("vollname") == 0) {
				String nameVoll = SichereEingabe.checkVollstaendigenName();   //SichereEingabe.liestChar
				n.setNameVollstaendig(nameVoll);
				if(n.getNameVollstaendig().compareTo(nameVoll) == 0) System.out.println("Der gleiche Name");
				else erfolg = true;
			}else if(input.compareTo("end") == 0) {
				//// zurück
				break;
			}else {
				System.out.println("Der falsche Befehl");
				bearbeiteNutzer();
			}
		}
		if(erfolg == true) {
			System.out.println("Der Nutzer wurde erfolgreich bearbeitet");
			bearbeiteNutzer();
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
		System.out.println("\n-----Arbeit mit Nutzer-----\n");
		System.out.println("login \t\t meldet sich an");
		System.out.println("create \t erzeugt neuen Nutzer");
		System.out.println("edit \t\t bearbeitet den Nutzer");
		System.out.println("del \t\t löscht den Nutzer");
		System.out.println("end \t\t beendet das Programm\n");
	}
}