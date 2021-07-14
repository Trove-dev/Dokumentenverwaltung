package UI;

import java.util.Scanner;

import Datei.DateienContainer;

public class DateiAnzeigeUI {
	public static void DateiAnzeigeUIAnzeige() {
		System.out.println("\nlistall\t\t- listet alle gespeicherten Dateien auf");
	    System.out.println("back\t\t- wechselt zur�ck in die Hauptansicht");
	    System.out.println("----------------");
	    
	    String input = "";
	    Scanner sc = new Scanner(System.in);
	    
	    while(true){
	        System.out.print("Bitte einen Befehl eingeben: ");
	        input = sc.nextLine();
	        input = input.trim();
	        if( input.startsWith("listall") ){
				if(DateienContainer.getInstance().getAlleDateien() != null) {
					System.out.println("Liste aller Dateien im Detail:\n");
					//zeigeAlleDateien();												//debug
					//System.out.println(getAlleDateien());								//debug
		        	DateienContainer.getInstance().zeigeAlleDateienDetails();
				}
				else {
					System.out.println("Es wurden bisher keine Dateien gespeichert!\n");
				}
	        }
	        else if( input.startsWith("back")){
	        	hilfUI.clearScreen();
	        	System.out.println("\nWillkommen zur�ck im Hauptmen�!");
	            System.out.println("upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzuf�gen");
	            System.out.println("view \t\t- wechselt in die Ansicht, um Dokumente anzusehen");
	    	    System.out.println("end\t\t- beendet das Programm");
	    	    System.out.println("----------------");
	    	    break;
	        }
	        else{
	            System.out.println("Unbekannter Befehl");
	        } 
	    }
		
	}
}
