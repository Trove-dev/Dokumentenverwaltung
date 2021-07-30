
package UI;
import Verarbeitung.ServiceLocator;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import Nutzer.Nutzer;
import java.io.Serializable;
import Nutzer.NutzerContainerInterface;
import Papierkorb.PapierkorbUI;
import Tag.TagUISearchTag;
import Datei.DateiUIDeleteDatei;
import Datei.DateiUIOpenDatei;
import Datei.DateiUIWorkBind;
import Datei.DateiUIWorkkomm;
import Datei.DateiUIWorktags;

public class ControllerUI implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ServiceLocator serviceLocator;
	Nutzer user;

	public static void main(String[] args) throws IOException {
		new ControllerUI();
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
	
		HilfUI.printBefehleControllerUI();
	    String input = "";
	    Scanner sc = new Scanner(System.in);
	    
	    while(true) {
	    	System.out.print("Bitte einen Befehl eingeben: ");
	    	input = sc.nextLine();
	        input = input.trim();
	        if (input.startsWith("upload")){
	            neueDatei();
	            HilfUI.printBefehleControllerUIClear();
	        }
	        else if (input.startsWith("view")){                
				dateiAnzeige();
				HilfUI.printBefehleControllerUIClear();
	        }
	        else if (input.startsWith("end")) {
	        	saveall();
	        	System.out.println("Programm wird beendet. \n\nAuf Wiedersehen!");
	        	sc.close();
	        	break;
	        }
	        else if (input.startsWith("saveall")) {
	        	saveall();
	        	HilfUI.printBefehleControllerUIClear();
	        }
	        else if (input.startsWith("loadall")) {
	        	loadall();
	        	HilfUI.printBefehleControllerUIClear();
	        }
	        else if (input.startsWith("help")) {
	    		HilfUI.printBefehleControllerUIClear();
	        }
	        else{ 
	            System.err.println("Unbekannter Befehl\n");
	            HilfUI.printBefehleControllerUIClear();
	        } 
	    }
    }
	
	private void saveall() {		
		String dateiName = "containers.dat";
		serviceLocator.speicherAlleContainer(dateiName, serviceLocator);
		System.out.println("Alle Daten wurden in der Datei " + dateiName + " gespeichert!\n");
	}

	private void loadall() {
		String dateiName = "containers.dat";
		if(serviceLocator.ladeAlleContainer(dateiName) != null)
			serviceLocator = serviceLocator.ladeAlleContainer(dateiName);
			System.out.println("Alle Daten wurden aus der Datei " + dateiName + " ausgelesen!\n");
	}
	
	private void neueDatei() {
		DateiEinlesenlUI einleseFenster = new DateiEinlesenlUI();
		try {
			einleseFenster.DateiEinlesenUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (einleseFenster.getPath() != null && einleseFenster.getName() != null) {
			if (serviceLocator.getDateienContainer().hochladeDatei(einleseFenster.getPath(), einleseFenster.getName()) == true) {
				String tmpEingabe = "";
				Scanner s = new Scanner(System.in);
				System.out.print("Möchten Sie einen Tag hinzufügen? (y or n) ");
				tmpEingabe = s.nextLine();
				if (tmpEingabe.equals("y")) {
					DateiUIWorktags duiWorktag = new DateiUIWorktags(serviceLocator.getDateienContainer(), serviceLocator.getTagsContainer());
					duiWorktag.worktagsName(einleseFenster.getName());
				}
				System.out.print("Möchten Sie einen Kommentar hinzufügen? (y or n) ");
				tmpEingabe = s.nextLine();
				if (tmpEingabe.equals("y")) {
					DateiUIWorkkomm duiWorkkomm = new DateiUIWorkkomm(serviceLocator.getDateienContainer());
					duiWorkkomm.workkommName(einleseFenster.getName());
				}
				System.out.print("Möchten Sie eine Verlinkung hinzufügen? (y or n) ");
				tmpEingabe = s.nextLine();
				if (tmpEingabe.equals("y")) {
					DateiUIWorkBind duiWorkbind = new DateiUIWorkBind(serviceLocator.getDateienContainer());
					duiWorkbind.workbindName(einleseFenster.getName());
				}
				saveall();
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();
				return;
			}
		}
		System.out.println("Es wurde keine Datei gespeichert!");
		HilfUI.promtEnterKey();
		HilfUI.printBefehleControllerUIClear();
	}
	
	private void dateiAnzeige() throws IOException {
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
			DateiUIDeleteDatei duiDelete = new DateiUIDeleteDatei(serviceLocator.getDateienContainer(), serviceLocator.getPapierkorb());
			duiDelete.deleteDatei(user);
			saveall();
		}
		else if (anzeigeFenster.getBefehl().compareTo("search") == 0) {
			String tmpSuche = "";
			Scanner ss = new Scanner(System.in);
			System.out.print("Bitte einen Dateinamen, Tag, Kommentar oder Dateiformat eingeben: ");    /// ????? tag unten
			tmpSuche = ss.nextLine();
			serviceLocator.getDateienContainer().sucheDatei(tmpSuche);
			HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
		}
		else if (anzeigeFenster.getBefehl().compareTo("searchtag") == 0) {                 
			TagUISearchTag tuiSearchTag = new TagUISearchTag(serviceLocator.getTagsContainer(), serviceLocator.getDateienContainer());
			tuiSearchTag.searchtag();
		}
		else if(anzeigeFenster.getBefehl() == "open") {
			DateiUIOpenDatei duiOpen = new DateiUIOpenDatei(serviceLocator.getDateienContainer());
			duiOpen.startOpeningDatei();
			saveall();
		}
		else if(anzeigeFenster.getBefehl() == "worktags") {
			DateiUIWorktags duiWorktag = new DateiUIWorktags(serviceLocator.getDateienContainer(), serviceLocator.getTagsContainer());
			duiWorktag.worktags();
			saveall();
			
		}
		else if(anzeigeFenster.getBefehl() == "workkomm") {
			DateiUIWorkkomm duiWorkkomm = new DateiUIWorkkomm(serviceLocator.getDateienContainer());
			duiWorkkomm.workkomm();
			saveall();
		}
		else if(anzeigeFenster.getBefehl() == "workbind") {
			DateiUIWorkBind duiWorkbind = new DateiUIWorkBind(serviceLocator.getDateienContainer());
			duiWorkbind.workbind();
			saveall();
			HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
		}
		else if(anzeigeFenster.getBefehl() == "bin") {
			PapierkorbUI puiRestore = new PapierkorbUI(serviceLocator.getPapierkorb(), serviceLocator.getDateienContainer());
			puiRestore.startBin();
			saveall();
		}
	}
}

