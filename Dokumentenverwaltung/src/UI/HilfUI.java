package UI;

public class HilfUI {
	
	public static void clearScreen() {
		for(int i=1; i<100; i++) {
			System.out.println();
		}
	}
	
	public static void printBefehleControllerUI() {
		System.out.println("\nWillkommen im Hauptmenü vom Dokumentenmanager!\n"
				+ "upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzufügen\n"
				+ "view \t\t- wechselt in die Ansicht, um Dokumente anzusehen\n"
				+ "saveall \t- speichert alle Dokumente ab (not working!)\n"
				+ "loadall \t- ruft gespeicherte Dokumente ab (not working!)\n"
				+ "help \t\t- zeigt alle verfügbaren Befehle an\n"
				+ "----------------");
	}
	
	public static void printBefehleControllerUIClear() {
		clearScreen();
		printBefehleControllerUI();
	}

	public static void printBefehleDateiAnzeigeUIClear() {
		clearScreen();
		System.out.println("\nlistall\t\t- listet alle gespeicherten Dateien auf");
	    System.out.println("back\t\t- wechselt zurück in die Hauptansicht");
	    System.out.println("help \t\t- zeigt alle verfügbaren Befehle an");
	    System.out.println("----------------");
		
	}

	public static void printBefehleDateiEinlesenUIClear() {
		clearScreen();
		System.out.println("\ndir\t\t- listet alle Dateien und Unterverzeichnisse auf");
	    System.out.println("cd <dir>\t- wechselt in das angegebene Verzeichnis <dir>");
	    System.out.println("cd ..\t\t- wechselt in das vorige Verzeichnis");
	    System.out.println("info <name>\t- listet Informationen einer Datei/Ordner auf");
	    System.out.println("save <name>\t- speichert Informationen einer Datei/Ordner ab");
	    System.out.println("help \t\t- zeigt alle verfügbaren Befehle an");
	    System.out.println("back\t\t- wechselt zurück in die Hauptansicht");
	    System.out.println("----------------");
		
	}
	
}
