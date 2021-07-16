package UI;

import java.util.Scanner;

import Verarbeitung.ServiceLocator;

public class DateiAnzeigeUI {
	public static void DateiAnzeigeUIAnzeige() {
		
		HilfUI.clearScreen();
		System.out.println("\nlistall\t\t- listet alle gespeicherten Dateien auf");
	    System.out.println("back\t\t- wechselt zurück in die Hauptansicht");
	    System.out.println("help \t\t- zeigt alle verfügbaren Befehle an");
	    System.out.println("----------------");
	    
	    String input = "";
	    Scanner sc = new Scanner(System.in);
	    
	    while(true){
	        System.out.print("Bitte einen Befehl eingeben: ");
	        input = sc.nextLine();
	        input = input.trim();
	        if( input.startsWith("listall") ){
				  	if((Verarbeitung.ServiceLocator.getInstance().getDc().getAlleDateien()) != null) {
					System.out.println("Liste aller Dateien im Detail:\n");
					//zeigeAlleDateien();												//debug
					//System.out.println(getAlleDateien());								//debug
		        	Verarbeitung.ServiceLocator.getInstance().getDc().zeigeAlleDateienDetails();
				}
				else {
					System.out.println("Es wurden bisher keine Dateien gespeichert!\n");
				}
	        }
	        else if( input.startsWith("back")){
	        	HilfUI.clearScreen();
	        	System.out.println("\nWillkommen zurück im Hauptmenü!");
	        	System.out.println("upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzufügen");
	    		System.out.println("view \t\t- wechselt in die Ansicht, um Dokumente anzusehen");
	    		System.out.println("saveall \t- speichert alle Dokumente ab (not working!)");
	    		System.out.println("loadall \t- ruft gespeicherte Dokumente ab (not working!)");
	    	    System.out.println("help \t\t- zeigt alle verfügbaren Befehle an");
	    		System.out.println("end\t\t- beendet das Programm");
	    	    System.out.println("----------------");
	    	    break;
	        }
	        else if(input.startsWith("help")) {
	        	HilfUI.clearScreen();
	        	System.out.println("\nlistall\t\t- listet alle gespeicherten Dateien auf");
	    	    System.out.println("back\t\t- wechselt zurück in die Hauptansicht");
	    	    System.out.println("help \t\t- zeigt alle verfügbaren Befehle an");
	    	    System.out.println("----------------");
	        }
	        else{
	            System.out.println("Unbekannter Befehl");
	        } 
	    }
		
	}
}
