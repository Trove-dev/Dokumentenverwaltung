
package UI;
import Verarbeitung.ServiceLocator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import Datei.Datei;
import Nutzer.Nutzer;
import Nutzer.NutzerContainerInterface;

public class ControllerUI implements Serializable{
	
	private ServiceLocator serviceLocator;

	public static void main(String[] args) throws IOException {
		ControllerUI c = new ControllerUI();
	}
	
	public ControllerUI() throws IOException {
		serviceLocator = ServiceLocator.getInstance();
		start();
	}
		
	private void start() throws IOException {	
		NutzerContainerInterface nc = serviceLocator.getNutzerContainer();
		ArrayList<Nutzer> nutzerListe = nc.getListeNutzer();	
		NutzerUI nui = new NutzerUI(nc, nutzerListe);
		// Auswahl Nutzer
		nui.startAnmelden();
		
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
        	HilfUI.printBefehleControllerUIClear();
        }
        else {
        	HilfUI.clearScreen();
        	System.out.println("Datei wurde erfolgreich gespeichert!");
        	HilfUI.printBefehleControllerUI();
        }
	}

	private void saveall() {		
		String dateiName = "containers.dat";
		serviceLocator.speicherAlleContainer(dateiName, serviceLocator);
		System.out.println("Die Dokumente wurden in der Datei " + dateiName + " gespeichert!\n");
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
			serviceLocator.getDateienContainer().hochladeDatei(einleseFenster.getPath(), einleseFenster.getName());
			return true;
		}
		else {
			return false;
		}
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
				HilfUI.printBefehleControllerUI();
			}
		}
		else if (anzeigeFenster.getBefehl() == "delete") {
			boolean erfolg = false;
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

