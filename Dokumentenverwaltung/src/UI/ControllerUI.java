
package UI;
import Verarbeitung.ServiceLocator;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
import Nutzer.Nutzer;
import java.io.Serializable;
import Nutzer.NutzerContainerInterface;
import Tag.Tag;
import Tag.TagsContainerInterface;

public class ControllerUI implements Serializable{
	
	private ServiceLocator serviceLocator;

	public static void main(String[] args) throws IOException {
		ControllerUI c = new ControllerUI();
	}
	
	public ControllerUI() throws IOException {
		serviceLocator = ServiceLocator.getInstance();
		start();
	}
		
	
	/*	TagsContainerInterface tc = serviceLocator.getTagsContainer();
		TreeSet <Tag> tst = tc.getTagsListe();
		TagUI tui = new TagUI(tc);
		tui.anzeigeTagsCloud();  */
		
		
	private void start() throws IOException {	
		NutzerContainerInterface nc = serviceLocator.getNutzerContainer();
		ArrayList<Nutzer> nutzerListe = nc.getListeNutzer();	
		NutzerUI nui = new NutzerUI(nc, nutzerListe);
		// Auswahl Nutzer
		nui.startAnmelden();
		
		
		System.out.println("Willkommen bei dem Dokumentenmanager!");
		System.out.println("upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzufügen");
		System.out.println("view \t\t- wechselt in die Ansicht, um Dokumente anzusehen");
		System.out.println("saveall \t- speichert alle Dokumente ab (not working!)");
		System.out.println("loadall \t- ruft gespeicherte Dokumente ab (not working!)");
	    System.out.println("help \t\t- zeigt alle verfügbaren Befehle an");
		System.out.println("end\t\t- beendet das Programm");
	    System.out.println("----------------");
	    
	    String input = "";
	    Scanner sc = new Scanner(System.in);
	    
	    while(true) {
	    	System.out.print("Bitte einen Befehl eingeben: ");
	    	input = sc.nextLine();
	        input = input.trim();
	        if (input.startsWith("upload")){
	            DateiEinlesenlUI.DateiAuswahlUIAuswahl();
	        }
	        else if (input.startsWith("view")){                
				DateiAnzeigeUI.DateiAnzeigeUIAnzeige();
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
	    		HilfUI.clearScreen();
	    		System.out.println("Willkommen bei dem Dokumentenmanager!");
	        	System.out.println("upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzufügen");
	    		System.out.println("view \t\t- wechselt in die Ansicht, um Dokumente anzusehen");
	    		System.out.println("saveall \t- speichert alle Dokumente ab (not working!)");
	    		System.out.println("loadall \t- ruft gespeicherte Dokumente ab (not working!)");
	    	    System.out.println("help \t\t- zeigt alle verfügbaren Befehle an");
	    		System.out.println("end\t\t- beendet das Programm");
	    	    System.out.println("----------------\n");
	        }
	        else{
	            System.out.println("Unbekannter Befehl\n");
	        } 
	    }
    }
	
	/*private void saveall() {
		String dateiName = "";
		Scanner s = new Scanner(System.in);
		System.out.print("Bitte Dateinamen eingeben: ");
		dateiName = s.next();
		serviceLocator.speicherAlleContainer(dateiName, serviceLocator);
		System.out.println("Die Dokumente wurden in der Datei " + dateiName + " gespeichert!\n");
	}
	
	private void loadall() {
		String dateiName = "containers.dat";
		Scanner s = new Scanner(System.in);
		System.out.print("Bitte Dateinamen eingeben: ");
		dateiName = s.next();
		serviceLocator.ladeAlleContainer(dateiName);
		System.out.println("Die Dokumente wurden aus der Datei " + dateiName + " ausgelesen!\n");
	}*/
	
	private void saveall() {		
		String dateiName = "containers.dat";
		serviceLocator.speicherAlleContainer(dateiName, serviceLocator);
		System.out.println("Die Dokumente wurden in der Datei " + dateiName + " gespeichert!\n");
	}

	private void loadall() {
		String dateiName = "containers.dat";
		if(serviceLocator.ladeAlleContainer(dateiName) != null)		
			System.out.println("Die Dokumente wurden aus der Datei " + dateiName + " ausgelesen!\n");
	}
}

