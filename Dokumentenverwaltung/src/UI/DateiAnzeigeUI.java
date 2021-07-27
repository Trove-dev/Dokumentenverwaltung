package UI;

import java.util.Scanner;

import Verarbeitung.ServiceLocator;

public class DateiAnzeigeUI {
	private String befehl = "";
	
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
	        }
	        else if (input.startsWith("bin")) {
	        	ServiceLocator.getInstance().getPapierkorb().papierkorbAnzeigen();
	        	System.out.println("Papierkorb Menü");
	        	while (true) {
	        		Scanner s = new Scanner(System.in);
	        		System.out.println("restore\t\t- Datei wiederherstellen");
	        		System.out.println("delall\t\t- gesamten Papierkorb löschen");
	        		System.out.println("back\t\t- zurück ins Hauptmenü");
	        		System.out.print("Bitte Befehl eingeben: ");
	        		String tmp = s.nextLine();
	        		if (tmp.equals("restore")) {
	        			befehl = "restore";
	        			break;
	        		}
	        		else if(tmp.equals("delall")) {
	        			befehl = "delall";
	        			break;
	        		}
	        		else if(tmp.equals("back")) {
	        			break;
	        		}
	        	}
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
