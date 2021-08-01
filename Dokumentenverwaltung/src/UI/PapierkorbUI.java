package UI;

import java.util.Scanner;
import Datei.Datei;
import Datei.DateienContainerInterface;
import Papierkorb.Papierkorb;

/**
 * Klasse, welche die Oberfläche von dem Papierkorb verwaltet
 *
 */
public class PapierkorbUI {
	
	Papierkorb pk;
	DateienContainerInterface dci;
	
	/**
	 * Kontruktor, legt den Papierkorb und das link DateienContainerInterface fest
	 * @param pk
	 * @param dci
	 */
	public PapierkorbUI(Papierkorb pk, DateienContainerInterface dci) {
		this.pk = pk;
		this.dci = dci;
	}
	
	/**
	 * Menüoberfläche des Papierkorbes mit den Befehlen
	 */
	public void startBin() {
		while (true) {
			Scanner s = new Scanner(System.in);
			UI.HilfUI.printBefehlePapierkorb();
			System.out.print("Bitte Befehl eingeben: ");
			String tmp = s.nextLine();
			if (tmp.equals("restore")) {
				startRestore();
				break;
			}
			else if(tmp.equals("delall")) {
				startDelAll();
				break;
			}
			else if(tmp.equals("back")) {
				break;
			}
		}
	}
	
	/**
	 * Vorbereiten zum Wiederherstellen von einer Datei
	 */
	private void startRestore() {
		boolean erfolg = false;
		pk.papierkorbAnzeigen();
		while (erfolg == false) {
			if (pk.istLeer() == false){
				String tmpBin = "";
				Scanner sb = new Scanner(System.in);
				System.out.print("Bitte einen Dateinamen eingeben von der Datei, die Sie wiederherstellen möchten: ");
				tmpBin = sb.nextLine();
				Datei tmp = pk.wiederherstelle(tmpBin);
				if (tmp != null) {
					dci.hochladeObjekt(tmp);
					System.out.println("Datei wurde erfolgreich wiederhergestellt!");
					erfolg = true;
				}
				else if (tmpBin.equals("exit")) {
					System.out.println("Papierkorb wurde beendet");
					break;
				}
				else {
					System.out.println("Datei konnte nicht wiederhergestellt werden! (Dateiname überprüfen und erneut versuchen...)\n");
					
				}
			}
			else {
				System.out.println("Es liegen keine Dateien im Papierkorb vor!");
				break;
			}
		}
		HilfUI.promtEnterKey();
		HilfUI.printBefehleControllerUIClear();
	}
	
	/**
	 * Vorbereiten zum Löschen des gesamten Papierkorbes
	 * erneute Sicherheitsabfrage vor der Ausführung
	 */
	private void startDelAll() {
		Scanner s = new Scanner (System.in);
		if (pk.istLeer() == false) {
			while (true) { 
				System.out.print("Möchten Sie wirklich den gesamten Papierkorb leeren? (y or n): ");
				String eingabe = s.nextLine();
				if (eingabe.equals("y")) {
					pk.leerePapierkorb();
					System.out.println("Papierkorb wurde geleert!");
					break;
				}
				else if (eingabe.equals("n")) {
					break;
				}
			}
			HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
		}
		else {
			System.out.println("Es konnten keine Dateien im Papierkorb gefunden werden!");
			HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
		}
	}
}
