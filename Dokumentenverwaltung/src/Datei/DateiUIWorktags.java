package Datei;

import java.util.Iterator;
import java.util.Scanner;
import Tag.TagsContainerInterface;
import UI.HilfUI;
import UI.TagUI;

/**
 * Klasse, welche die Kommentare verwaltet
 */
public class DateiUIWorktags {
	
	DateienContainerInterface dci;
	TagsContainerInterface tci;

	/**
	 * Kontruktor, legt das DateienContainerInterface und das TagsContainerInterface fest
	 * 
	 * @param dci
	 * @param tci
	 */
	public DateiUIWorktags(DateienContainerInterface dci, TagsContainerInterface tci) {
		this.dci = dci;
		this.tci = tci;
	}
	
	/**
	 * Bereitet das Hinzuf�gen von Tags vor
	 * Die Datei wird anhand des Namens gesucht
	 * Es wird aus TagUI ausf�hrungsBefehl(datei) gestartet
	 */
	public void worktags() {
		if (dci.getAlleDateien() != null) {
			Scanner sc = new Scanner(System.in);
			dci.zeigeAlleDateienDetails();
			System.out.print("Welche Datei m�chten Sie f�r die Arbeit mit Tags ausw�hlen?: ");
			String dateiName = sc.next();
			if (dci.getAlleDateien() != null) {
				Iterator<Datei> it = dci.getAlleDateien().iterator();
				boolean erfolg = false;
				while (it.hasNext()) {
					Datei datei = it.next();
					if (datei.getName().equals(dateiName)) {					
						erfolg = true;
						System.out.println("\nSie haben die Datei mit dem Namen: " + dateiName + " ausgew�hlt");
						TagUI tui = new TagUI(tci, datei);
						tui.ausfuerungBefehl(datei);
						HilfUI.promtEnterKey();
						HilfUI.printBefehleControllerUIClear();
						break; 
					}
				}
				if (erfolg == false) {
					System.out.println("\nEs konnte keine Datei mit den Namen "+ dateiName + " gefunden werden!");
					HilfUI.promtEnterKey();
					HilfUI.printBefehleControllerUIClear();
				}
			}
		}
		else {
			System.out.println("Es wurden bisher noch keine Dateinen gespeichert!");
			HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
		}
	}

	/**
	 * Bereitet das Hinzuf�gen von Tags vor
	 * Es wird aus TagUI ausf�hrungsBefehl(datei) gestartet
	 * Dieses Men� wird nur nach dem Hochladen einer Datei ausgef�hrt (ansonsten wird "worktags()" geladen), da eine extra Abfrage nach
	 * dem Namen der Datei nicht n�tig ist.
	 * 
	 * @param name Name der Datei, die einen Tag erhalten soll
	 */
	public void worktagsName(String name) {
		Iterator<Datei> it = dci.getAlleDateien().iterator();
		while (it.hasNext()) {
			Datei datei = it.next();
			if (datei.getName().equals(name)) {					
				System.out.println("\nSie haben die Datei mit dem Namen: " + name + " ausgew�hlt");
				TagUI tui = new TagUI(tci, datei);
				tui.ausfuerungBefehl(datei);
				break; 
			}
		}
	}
}
