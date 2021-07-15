//package UI;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Mitmain {
//
//	public static void main(String[] args) throws IOException {
//		
//		System.out.println("Willkommen bei dem Dokumentenmanager!");
//		System.out.println("upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzufügen");
//		System.out.println("view \t\t- wechselt in die Ansicht, um Dokumente anzusehen");
//	    System.out.println("end\t\t- beendet das Programm");
//	    System.out.println("----------------");
//	    
//	    String input = "";
//	    Scanner sc = new Scanner(System.in);
//	    
//	    while(true) {
//	    	System.out.print("Bitte einen Befehl eingeben: ");
//	    	input = sc.nextLine();
//	        input = input.trim();
//	        if( input.startsWith("upload") ){                
//	            DateiEinlesenlUI.DateiAuswahlUIAuswahl();
//	        }
//	        else if( input.startsWith("view") ){                
//	            DateiAnzeigeUI.DateiAnzeigeUIAnzeige();
//	        }
//	        else if( input.startsWith("end") ) {
//	        	System.out.println("Programm wird beendet. \n\nAuf Wiedersehen!");
//	        	sc.close();
//	        	break;
//	        }
//	        else{
//	            System.out.println("Unbekannter Befehl");
//	        } 
//	    }
//    }
//}
//// Moved to ControllerUI!!!