package UI;

import java.io.IOException;
import java.util.Scanner;
import Verarbeitung.ServiceLocator;

public class ControllerUI {
	private ServiceLocator serviceLocator;

	public static void main(String[] args) throws IOException {
		ControllerUI c = new ControllerUI();
	}
	
	public ControllerUI() throws IOException {
		serviceLocator = ServiceLocator.getInstance();
		start();
	}
		
	private void start() throws IOException {	
		System.out.println("Willkommen bei dem Dokumentenmanager!");
		System.out.println("upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzuf�gen");
		System.out.println("view \t\t- wechselt in die Ansicht, um Dokumente anzusehen");
		System.out.println("saveall \t- speichert alle Dokumente ab (not working!)");
		System.out.println("loadall \t- ruft gespeicherte Dokumente ab (not working!)");
	    System.out.println("help \t\t- zeigt alle verf�gbaren Befehle an");
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
	        	System.out.println("upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzuf�gen");
	    		System.out.println("view \t\t- wechselt in die Ansicht, um Dokumente anzusehen");
	    		System.out.println("saveall \t- speichert alle Dokumente ab (not working!)");
	    		System.out.println("loadall \t- ruft gespeicherte Dokumente ab (not working!)");
	    	    System.out.println("help \t\t- zeigt alle verf�gbaren Befehle an");
	    		System.out.println("end\t\t- beendet das Programm");
	    	    System.out.println("----------------\n");
	        }
	        else{
	            System.out.println("Unbekannter Befehl\n");
	        } 
	    }
    }

	
	private void saveall() {
		String dateiName = "";
		Scanner s = new Scanner(System.in);
		System.out.print("Bitte Dateinamen eingeben: ");
		dateiName = s.next();
		serviceLocator.speicherAlleContainer(dateiName, serviceLocator);
		System.out.println("Die Dokumente wurden in der Datei " + dateiName + " gespeichert!\n");
	}
	
	private void loadall() {
		String dateiName = "";
		Scanner s = new Scanner(System.in);
		System.out.print("Bitte Dateinamen eingeben: ");
		dateiName = s.next();
		serviceLocator.ladeAlleContainer(dateiName);
		System.out.println("Die Dokumente wurden aus der Datei " + dateiName + " ausgelesen!\n");
	}
}
