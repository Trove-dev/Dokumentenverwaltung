package UI;

import java.util.Scanner;

/**
 * Klasse, f�r das Men� zur Verwaltung von Dokumenten
 */
public class DateiAnzeigeUI {
	private String befehl = "";
	
	/**
	 * Anzeige des Men�s
	 * Festlegen des Befehls
	 */
	public void DateiAnzeigeUIAnzeige() {
		
		HilfUI.printBefehleDateiAnzeigeUIClear();
	    String input = "";
	    Scanner sc = new Scanner(System.in);
	    
	    while(true){
	        System.out.print("Bitte einen Befehl eingeben: ");
	        input = sc.nextLine();
	        input = input.trim();
	        if( input.startsWith("listall") ){
				  	this.befehl = "listall";
				  	break;
	        }
	        else if (input.startsWith("delete")){
	        	this.befehl = "delete";
	        	break;
	        	
	        }
	        else if (input.compareTo("searchtag") == 0) {
	        	this.befehl = "searchtag";
	        	break;
	        }	        
	        else if (input.compareTo("search") == 0) {
	        	this.befehl = "search";
	        	break;
	        }
	        else if (input.startsWith("open")) {
	        	this.befehl = "open";
	        	break;
	        }
	        else if (input.startsWith("worktags")) {
	        	this.befehl = "worktags";
	        	break;
	        }
	        else if (input.startsWith("workkomm")) {
	        	this.befehl = "workkomm";
	        	break;
	        }
	        else if (input.startsWith("workbind")) {
	        	this.befehl = "workbind";
	        	break;
	        }
	        else if (input.startsWith("bin")) {
	        	this.befehl = "bin";
	        	break;
	        }
	        else if( input.startsWith("back")){
	        	HilfUI.printBefehleControllerUIClear();
	    	    break;
	        }
	        else if(input.startsWith("help")) {
	        	HilfUI.printBefehleDateiAnzeigeUIClear();
	        }
	        else{
	            System.out.println("Unbekannter Befehl");
	        } 
	    }
	}
	
	public String getBefehl() {
		return befehl;
	}
}
