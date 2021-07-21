package UI;

import java.util.Scanner;

public class HilfUI {
	
	private static String OS = null;
	
	public static void clearScreen() {
		for(int i=1; i<100; i++) {
			System.out.println();
		}
	}
	
	public static void printBefehleControllerUI() {
		System.out.println("\nWillkommen im Hauptmen� vom Dokumentenmanager!\n"
				+ "upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzuf�gen\n"
				+ "view \t\t- wechselt in die Ansicht, um Dokumente anzusehen\n"
				+ "saveall \t- speichert alle Dokumente ab (not working!)\n"
				+ "loadall \t- ruft gespeicherte Dokumente ab (not working!)\n"
				+ "help \t\t- zeigt alle verf�gbaren Befehle an\n"
				+ "----------------");
	}
	
	public static void printBefehleControllerUIClear() {
		clearScreen();
		printBefehleControllerUI();
	}

	public static void printBefehleDateiAnzeigeUIClear() {
		clearScreen();
		System.out.println("\nlistall\t\t- listet alle gespeicherten Dateien auf");
		System.out.println("delete\t\t- entfernt eine Datei");
	    System.out.println("back\t\t- wechselt zur�ck in die Hauptansicht");
	    System.out.println("help \t\t- zeigt alle verf�gbaren Befehle an");
	    System.out.println("----------------");
	}

	public static void printBefehleDateiEinlesenUIClear() {
		clearScreen();
		System.out.println("\ndir\t\t- listet alle Dateien und Unterverzeichnisse auf");
	    System.out.println("cd <dir>\t- wechselt in das angegebene Verzeichnis <dir>");
	    System.out.println("cd ..\t\t- wechselt in das vorige Verzeichnis");
	    System.out.println("info <name>\t- listet Informationen einer Datei/Ordner auf");
	    System.out.println("save <name>\t- speichert Informationen einer Datei/Ordner ab");
	    System.out.println("help \t\t- zeigt alle verf�gbaren Befehle an");
	    System.out.println("back\t\t- wechselt zur�ck in die Hauptansicht");
	    System.out.println("----------------");	
	}
	
	public static void promtEnterKey() {
		System.out.println("\"Enter\" um weiterzugehen...");
		Scanner s = new Scanner(System.in);
		s.nextLine();
	}
	
	public static String getOsName() {
		if(OS == null) {
			OS = System.getProperty("os.name");
			return OS;
		}
		return OS;
	}
	public static boolean isWindows() {
		return getOsName().startsWith("Windows");
	}
	public static boolean isMac() {
		return getOsName().startsWith("Mac");
	}
	
}