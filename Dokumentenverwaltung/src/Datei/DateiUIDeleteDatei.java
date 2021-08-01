package Datei;

import java.util.Iterator;
import java.util.Scanner;
import Nutzer.Nutzer;
import Nutzer.Rechte;
import Papierkorb.Papierkorb;
import UI.HilfUI;

/**
 * Klasse, welche das Löschen von Dateien verwaltet
 */
public class DateiUIDeleteDatei {

DateienContainerInterface dci;
Papierkorb p;

/**
 * Kontruktor, legt das DateienContainerInterface und den Papierkorb fest
 * @param dci
 * @param p
 */
public DateiUIDeleteDatei(DateienContainerInterface dci, Papierkorb p) {

	this.dci = dci;
	this.p = p;
}

/**
 * Löschen von Dateien bei richtigen Rechten mittels Iterator<Datei>
 * Beim Löschen wird die Datei in den Papierkorb gelegt
 * 
 * @param user Übergabe des Nutzers, um Rechte zu überprüfen
 */
	public void deleteDatei(Nutzer user) {
		boolean erfolg = false;
		if(user != null && user.getRechte() != Rechte.admin) {
			System.out.println("Nur Nutzer mit dem Recht admin darf Dateien löschen");
			HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
		}
		else {
			Scanner s = new Scanner(System.in);
			dci.zeigeAlleDateienDetails();
			while (true) {
				System.out.print("Welche Datei möchten Sie löschen? (exit zum beenden): ");
				String dateiName = s.nextLine();
				if (dateiName.equals("exit")) {
					System.out.println("Papierkorb wurde beendet...");
					HilfUI.promtEnterKey();
					HilfUI.printBefehleControllerUIClear();
					break;
				}
				else {
					Iterator<Datei> it = dci.getAlleDateien().iterator();
					while (it.hasNext()) {
						Datei datei = it.next();
						if (datei.getName().equals(dateiName)) {
							p.hochladeDateiPapierkorb(datei);
							it.remove();
							erfolg = true;
							System.out.println("\nDatei mit dem Namen: " + dateiName + " wurde erfolgreich entfernt!");
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
		}
	}
}
