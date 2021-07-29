package UI;

import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class HilfUI {
	
	private static String OS = null;
	
	public static void clearScreen() {
		for(int i=1; i<100; i++) {
			System.out.println();
		}
	}
	
	public static void printBefehleControllerUI() {
		System.out.println("\nWillkommen im Hauptmenü vom Dokumentenmanager!\n"
				+ "upload \t\t- wechselt in die Ansicht, um Dokumente hinzuzufügen\n"
				+ "view \t\t- wechselt in die Ansicht, um Dokumente anzusehen\n"
				+ "saveall \t- speichert alle Dokumente ab\n"
				+ "loadall \t- ruft gespeicherte Dokumente ab\n"
				+ "end \t\t- beendet das Programm\n"
				+ "help \t\t- zeigt alle verfügbaren Befehle an\n"
				+ "---------------------");
	}
	
	public static void printBefehleControllerUIClear() {
		clearScreen();
		printBefehleControllerUI();
	}

	public static void printBefehleDateiAnzeigeUIClear() {
		clearScreen();
		System.out.println("\nlistall\t\t- listet alle gespeicherten Dateien auf");
		System.out.println("search\t\t- sucht nach einer Datei");   ///System.out.println("search\t\t- sucht nach einer Datei mittels Name, Tag oder Format");
		System.out.println("searchtag\t- sucht nach einem Tag");
		System.out.println("open \t\t- öffnet eine Datei");
		System.out.println("worktags\t- arbeitet mit Tags für eine Datei");
		System.out.println("workkomm\t- fügt Kommentare zu Dateien hinzu, ändert die");
		System.out.println("workbind\t- arbeitet mit Verlinkungen zwischen Dateien");
		System.out.println("delete\t\t- entfernt eine Datei");
		System.out.println("bin\t\t- öffent den Papierkorb");
	    System.out.println("back\t\t- wechselt zurück in die Hauptansicht");
	    System.out.println("help \t\t- zeigt alle verfügbaren Befehle an");
	    System.out.println("---------------------");
	}

	public static void printBefehleDateiEinlesenUIClear() {
		clearScreen();
		System.out.println("\ndir\t\t\t- listet alle Dateien und Unterverzeichnisse auf");
	    System.out.println("cd <dir>\t\t- wechselt in das angegebene Verzeichnis <dir>");
	    System.out.println("cd ..\t\t\t- wechselt in das vorige Verzeichnis");
	    System.out.println("info <name>\t\t- listet Informationen einer Datei/Ordner auf");
	    System.out.println("save <name>\t\t- speichert Informationen einer Datei/Ordner ab");	    
	    System.out.println("help \t\t\t- zeigt alle verfügbaren Befehle an");
	    System.out.println("back\t\t\t- wechselt zurück in die Hauptansicht");
	    System.out.println("----------------");	
	}
	
	public static void printBefehleEditNutzer() {
		System.out.println("username \t- ändert den Nutzername");
		System.out.println("recht \t\t- ändert das Racht");
		System.out.println("vollname \t- ändert den vollständigen Name");
		System.out.println("end \t\t- beendet die Bearbeitung");
	}
	
	public static void printBefehleNutzerUI() {
		System.out.println("\n-------Arbeit mit Nutzer-------");
		System.out.println("login \t\t- meldet sich an");
		System.out.println("create \t\t- erzeugt neuen Nutzer");
		System.out.println("edit \t\t- bearbeitet den Nutzer");
		System.out.println("del \t\t- löscht den Nutzer");
		System.out.println("end \t\t- beendet das Programm");
		System.out.println("---------------------------------\n");
	}
	
	public static void printBefehleTags() {
		System.out.println("\n-------Arbeit mit Tags--------");
		System.out.println("add \t\t- ein neues Tag hinzufügen");
		System.out.println("del \t\t- ein Tag löschen");
		System.out.println("unlink\t\t- ein Tag und die Datei trennen");
		System.out.println("exit \t\t- zurück ins Hauptmenü");
		System.out.println("--------------------------------");
	}
	
	public static void printBefehleSucheNachTags() {
		System.out.println("\n--------------------------");
		System.out.println("\nstart \t\t- startet die Suche");
		System.out.println("add \t\t- addiert einen Tag");
		System.out.println("--------------------------");
	}
	
	public static void printBefehleVerlinkung() {
		System.out.println("\n--------------Arbeit mit Verlinkung-------------");
		System.out.println("bind \t\t- bindet eine Datei an");
		System.out.println("unlink \t\t- trennt Dateien");
		System.out.println("exit \t\t- beendet die Arbeit mit Verlinkungen");
		System.out.println("-------------------------------------------------");
	}
	
	public static void printBefehlePapierkorb() {
		System.out.println("\n--------------Arbeit mit Papierkorb-------------");
		System.out.println("restore\t\t- Datei wiederherstellen");
		System.out.println("delall\t\t- gesamten Papierkorb löschen");
		System.out.println("back\t\t- zurück ins Hauptmenü");
		System.out.println("-------------------------------------------------");
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
	public static void printFileTime(FileTime fileTime) {
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy\thh:mm:ss");
	    System.out.print(dateFormat.format(fileTime.toMillis()) + "\t");
	}
	
}