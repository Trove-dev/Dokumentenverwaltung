package Datei;

import java.util.Iterator;
import java.util.Scanner;
import UI.HilfUI;

/**
 * Klasse, welche die Kommentare verwaltet
 */
public class DateiUIWorkkomm {
	DateienContainerInterface dci;
	
	/**
	 * Kontruktor, legt das DateienContainerInterface fest
	 * @param dci
	 */
	public DateiUIWorkkomm(DateienContainerInterface dci) {
		this.dci = dci;
	}

	/**
	 * F�gt einer Datei einen Kommentar hinzu
	 * Die Datei wird anhand des Namens gesucht
	 */
	public void workkomm() {
		if (dci.getAlleDateien() == null) {
			System.out.println("Es wurden bisher noch keine Dateien hochgeladen!");
			return;
		}
		Scanner sc = new Scanner(System.in);
		dci.zeigeAlleDateienDetails();
		System.out.print("Welche Datei m�chten Sie f�r die Arbeit mit den Kommentaren ausw�hlen?: ");
		String dateiName = sc.next();
		Iterator<Datei> it = dci.getAlleDateien().iterator();
		boolean erfolg = false;
		while (it.hasNext()) {
			Datei datei = it.next();
			if (datei.getName().equals(dateiName)) {					
				erfolg = true;
				System.out.print("\nSie haben die Datei mit dem Namen: " + dateiName + " ausgew�hlt. ");
				printKommentare(datei);
				System.out.print("Geben Sie einen Kommentar ein: ");
				sc = new Scanner(System.in);
				datei.setKommentar(sc.nextLine());
				printKommentare(datei);
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
	
	/**
	 * Listet alle Kommentare einer Datei auf
	 * 
	 * @param datei Datei, die auf ihre Kommentare untersucht werden soll
	 */
	public void printKommentare(Datei datei) {
		if(datei.getKommentar() == "" || datei.getKommentar() == null) System.out.println("Den Kommentar gibt es nicht");
		else System.out.println("Der Kommentar: " + datei.getKommentar());
	}

	/**
	 * F�gt einer Datei einen Kommentar hinzu
	 * Dieses Men� wird nur nach dem Hochladen einer Datei ausgef�hrt (ansonsten wird "workkomm()" geladen), da eine extra Abfrage nach
	 * dem Namen der Datei nicht n�tig ist.
	 * 
	 * @param name Name der Datei, die einen Kommentar erhalten soll
	 */
	public void workkommName(String name) {
		Scanner sc = new Scanner(System.in);
		Iterator<Datei> it = dci.getAlleDateien().iterator();
		while (it.hasNext()) {
			Datei datei = it.next();
			if (datei.getName().equals(name)) {
				System.out.print("Geben Sie einen Kommentar ein: ");
				sc = new Scanner(System.in);
				datei.setKommentar(sc.nextLine());
				printKommentare(datei);
				break; 
			}
		}
		
	}
}
