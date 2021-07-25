
package UI;
import Verarbeitung.ServiceLocator;
import hilf.SichereEingabe;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
import Nutzer.Nutzer;
import java.io.Serializable;
import Nutzer.NutzerContainerInterface;
import Nutzer.Rechte;
import Tag.Tag;
import Tag.TagsContainerInterface;
import Datei.Datei;

public class ControllerUI implements Serializable{
	
	private ServiceLocator serviceLocator;
	Nutzer user;
	TagsContainerInterface tci;

	public static void main(String[] args) throws IOException {
		ControllerUI c = new ControllerUI();
	}
	
	public ControllerUI() throws IOException {
		serviceLocator = ServiceLocator.getInstance();
		start();
	}
		
	private void start() throws IOException {	
		loadall();
		NutzerContainerInterface nc = serviceLocator.getNutzerContainer();
		ArrayList<Nutzer> nutzerListe = nc.getListeNutzer();	
		NutzerUI nui = new NutzerUI(nc, nutzerListe);
		user = nui.startAnmelden();
		saveall();
		tci = serviceLocator.getTagsContainer();
	
		HilfUI.printBefehleControllerUI();
	    String input = "";
	    Scanner sc = new Scanner(System.in);
	    
	    while(true) {
	    	System.out.print("Bitte einen Befehl eingeben: ");
	    	input = sc.nextLine();
	        input = input.trim();
	        if (input.startsWith("upload")){
	            uploadDatei();
	        }
	        else if (input.startsWith("view")){                
				dateiAnzeige();
	        }
	        else if (input.startsWith("end")) {
	        	System.out.println("Programm wird beendet. \n\nAuf Wiedersehen!");
	        	sc.close();
	        	break;
	        }
	        else if (input.startsWith("saveall")) {
	        	saveall();
	        }
	        else if (input.startsWith("loadall")) {
	        	loadall();
	        }
	        else if (input.startsWith("help")) {
	    		HilfUI.printBefehleControllerUIClear();
	        }
	        else{ 
	            System.out.println("Unbekannter Befehl\n");
	        } 
	    }
    }
	
	private void uploadDatei() {
		if (neueDatei() == false) {
        	System.out.println("Es wurde keine Datei gespeichert!"); 
        	HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
        }
        else {
        	System.out.println("\nDatei wurde erfolgreich gespeichert!");
        	saveall();
        	HilfUI.promtEnterKey();
        	HilfUI.printBefehleControllerUIClear();
        }
	}
	
	private void saveall() {		
		String dateiName = "containers.dat";
		serviceLocator.speicherAlleContainer(dateiName, serviceLocator);
		System.out.println("Die Information wurde in der Datei " + dateiName + " gespeichert!\n");
	}

	private void loadall() {
		String dateiName = "containers.dat";
		if(serviceLocator.ladeAlleContainer(dateiName) != null)
			serviceLocator = serviceLocator.ladeAlleContainer(dateiName);
			System.out.println("Die Dokumente wurden aus der Datei " + dateiName + " ausgelesen!\n");
	}
	
	private boolean neueDatei() {
		
		DateiEinlesenlUI einleseFenster = new DateiEinlesenlUI();
		try {
			einleseFenster.DateiEinlesenUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (einleseFenster.getPath() != null && einleseFenster.getName() != null) {
			if (serviceLocator.getDateienContainer().hochladeDatei(einleseFenster.getPath(), einleseFenster.getName()) == true) {
				return true;
			}
		}
		return false;
	}
	
	private void dateiAnzeige() {
		DateiAnzeigeUI anzeigeFenster = new DateiAnzeigeUI();
		anzeigeFenster.DateiAnzeigeUIAnzeige();
		if (anzeigeFenster.getBefehl() == "listall") {       
			if (serviceLocator.getDateienContainer().getAlleDateien() != null) {   
				System.out.println("Liste aller Dateien im Detail:\n");
				serviceLocator.getDateienContainer().zeigeAlleDateienDetails();
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();				
			}
			else {
				System.out.println("Es wurde bisher keine Dateien gespeichert!\n");
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();
			}
		}
		else if (anzeigeFenster.getBefehl() == "delete") {               
			boolean erfolg = false;
			if(user != null && user.getRechte() != Rechte.admin) {
				System.out.println("Nur Nutzer mit dem Recht admin darf Dateien löschen");
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();
			}else {
				Scanner s = new Scanner(System.in);
				serviceLocator.getDateienContainer().zeigeAlleDateienDetails();
				System.out.print("Welche Datei möchten Sie löschen? (Bitte Dateinamen eingeben): ");
				String dateiName = s.next();
				Iterator<Datei> it = serviceLocator.getDateienContainer().getAlleDateien().iterator();
				while (it.hasNext()) {
					Datei datei = it.next();
					if (datei.getName().equals(dateiName)) {
						it.remove();
						erfolg = true;
						System.out.println("\nDatei mit dem Namen: " + dateiName + " wurde erfolgreich entfernt!");
						saveall();
						HilfUI.promtEnterKey();
						HilfUI.printBefehleControllerUIClear();
						break;
					}
				}
				if (erfolg == false) {
					System.out.println("\nEs konnte keine Datei mit den Namen "+ dateiName + " gefunden werden!");
					HilfUI.promtEnterKey();
					HilfUI.printBefehleControllerUIClear();
				}
			}
		}
		else if (anzeigeFenster.getBefehl().compareTo("search") == 0) {
			String tmpSuche = "";
			Scanner ss = new Scanner(System.in);
			System.out.print("Bitte einen Dateinamen, Tag oder Dateiformat eingeben: ");    /// ????? tag unten
			tmpSuche = ss.nextLine();
			serviceLocator.getDateienContainer().sucheDatei(tmpSuche);
			HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
		}
		else if (anzeigeFenster.getBefehl().compareTo("searchtag") == 0) {                 
			ArrayList<String> tagsNames = new ArrayList<>();
			boolean added = false;
			while(added == false) {
				String tmpSuche = ""; 
				Scanner sc = new Scanner(System.in);
				serviceLocator.getTagsContainer().printTagsListe();
				System.out.println("\nGeben Sie bitte ein Tag ein oder exit, wenn Sie die Suche beenden wollen:");
				tmpSuche = sc.next();
				if(tmpSuche.compareTo("exit") == 0) {
					tagsNames = null;
					HilfUI.promtEnterKey();
					HilfUI.printBefehleControllerUIClear();
					break; 
				}
				if(tci.sucheTag(tmpSuche) != null) {
					tagsNames.add(tmpSuche);
					HilfUI.printBefehleSucheNachTags();
					String command = SichereEingabe.liesCharacters();
					if(command.compareTo("start") == 0) {
						if(tagsNames != null && !tagsNames.isEmpty()) { 							
							serviceLocator.getDateienContainer().sucheDateiTags(tagsNames);
							HilfUI.promtEnterKey();
							HilfUI.printBefehleControllerUIClear();
							added = true;
							break; 
						}else {
							System.out.println("Es gibt keine eingegebenen Tags");
							continue;
						}
					}else if(command.compareTo("add") == 0) {
						continue;
					}else {
						System.out.println("Unbekannter Befehl");
					}
					continue;
				}else {
					System.out.println("Dieses Tag gibt es nicht in der Tag Kollektion\n");
					continue;
				}			
			}			
		}
		else if(anzeigeFenster.getBefehl() == "open") {
			Scanner sc = new Scanner(System.in);
			serviceLocator.getDateienContainer().zeigeAlleDateienDetails();
			System.out.println("Geben Sie bitte den Name der Datei");
			String dateiName = sc.next();
			Iterator<Datei> it = serviceLocator.getDateienContainer().getAlleDateien().iterator();
			Datei datei = it.next();
			//check,ob die im Ordner ist , wenn ja -> save
			//									nein -> Fehler
			if (datei.getName().equals(dateiName)) {	
				try {
		    		if (Desktop.isDesktopSupported()) {
		    			Desktop.getDesktop().open(new File(datei.getDateiPfad()));
		    			HilfUI.promtEnterKey();
						HilfUI.printBefehleControllerUIClear();
		    		}
		    	}catch (IOException ioe) {
		    		ioe.printStackTrace();
		    	}
			}else {  
				
			}
			
		}
		else if(anzeigeFenster.getBefehl() == "worktags") {
			Scanner sc = new Scanner(System.in);
			serviceLocator.getDateienContainer().zeigeAlleDateienDetails();
			System.out.print("Welche Datei möchten Sie für die Arbeit mit Tags ? (Bitte Dateinamen eingeben): ");
			String dateiName = sc.next();
			Iterator<Datei> it = serviceLocator.getDateienContainer().getAlleDateien().iterator();
			boolean erfolg = false;
			while (it.hasNext()) {
				Datei datei = it.next();
				if (datei.getName().equals(dateiName)) {					
					erfolg = true;
					System.out.println("\nSie haben die Datei mit dem Namen: " + dateiName + " ausgewählt");
					TagUI tui = new TagUI(tci, datei);
					tui.ausfuerungBefehl(datei);
					saveall(); 
					HilfUI.promtEnterKey();
					HilfUI.printBefehleControllerUIClear();
					break; 
				}
			}
			if (erfolg == false) {
				System.out.println("\nEs konnte keine Datei mit den Namen "+ dateiName + " gefunden werden!");
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();
		}
	}
		else if(anzeigeFenster.getBefehl() == "workkomm") {
			Scanner sc = new Scanner(System.in);
			serviceLocator.getDateienContainer().zeigeAlleDateienDetails();
			System.out.print("Welche Datei möchten Sie für die Arbeit mit Kommentaren ? (Bitte Dateinamen eingeben): ");
			String dateiName = sc.next();
			Iterator<Datei> it = serviceLocator.getDateienContainer().getAlleDateien().iterator();
			boolean erfolg = false;
			while (it.hasNext()) {
				Datei datei = it.next();
				if (datei.getName().equals(dateiName)) {					
					erfolg = true;
					System.out.print("\nSie haben die Datei mit dem Namen: " + dateiName + " ausgewählt. ");
					if(datei.getKommentar() == "") System.out.println("Den Kommentar gibt es nicht");
					else System.out.println("Der Kommentar: " + datei.getKommentar());
					System.out.println("Geben Sie einen Kommentar bitte ein :");
					sc = new Scanner(System.in);
					datei.setKommentar(sc.nextLine());
					saveall(); 
					HilfUI.promtEnterKey();
					HilfUI.printBefehleControllerUIClear();
					break; 
				}
			}
			if (erfolg == false) {
				System.out.println("\nEs konnte keine Datei mit den Namen "+ dateiName + " gefunden werden!");
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();
		}
	}
		else if(anzeigeFenster.getBefehl() == "workbind") {
			Scanner sc = new Scanner(System.in);
			serviceLocator.getDateienContainer().zeigeAlleDateienDetails();
			System.out.print("Welche Datei möchten Sie für die Arbeit mit Verlinkung ? (Bitte Dateinamen eingeben): ");
			String dateiName = sc.next();
			Iterator<Datei> it = serviceLocator.getDateienContainer().getAlleDateien().iterator();
			boolean erfolg = false;
			while (it.hasNext()) {
				Datei datei = it.next();
				if (datei.getName().equals(dateiName)) {					
					erfolg = true;
					System.out.print("\nSie haben die Datei mit dem Namen: " + dateiName + " ausgewählt. ");
					if(datei.getVerknuepfung() == null) System.out.println("Die Verlinkungen gibt es nicht");
					else System.out.println("Die verlinkte Dateien: " + datei.getVerknuepfung());
					System.out.println("Geben Sie bittee inen Name der Datei zur Verlinkung ein :");
					String verlink = SichereEingabe.liesCharacters();
					
					saveall(); 
					HilfUI.promtEnterKey();
					HilfUI.printBefehleControllerUIClear();
					break; 
				}
			}
			if (erfolg == false) {
				System.out.println("\nEs konnte keine Datei mit den Namen "+ dateiName + " gefunden werden!");
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();
		}
	}
}
}

