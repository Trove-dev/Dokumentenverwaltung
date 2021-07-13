package UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Mitmain {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Willkommen bei dem Dokumentenmanager!");
		System.out.println("upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzufügen");
	    System.out.println("end\t\t- beendet das Programm");
	    System.out.println("----------------");
	    
	    String input = "";
	    Scanner sc = new Scanner(System.in);
	    
	    while(true) {
	    	System.out.print("Bitte den Befehl eingeben: ");
	    	input = sc.nextLine();
	        input = input.trim();
	        if( input.startsWith("upload") ){                
	            DateiAuswahlUI.DateiAuswahlUIAuswahl();
	        }
	        else if( input.startsWith("end") ) {
	        	System.out.println("Programm wird beendet. \n\nAuf Wiedersehen!");
	        	break;
	        }
	        else{
	            System.out.println("Unbekannter Befehl");
	        } 
	    }

        
    }
}