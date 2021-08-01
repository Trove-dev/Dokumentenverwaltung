package Datei;

import java.util.Iterator;
import java.util.Scanner;
import Nutzer.Nutzer;
import Nutzer.Rechte;
import Papierkorb.Papierkorb;
import UI.HilfUI;

/**
 * Klasse, welche das L�schen von Dateien verwaltet
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
 * L�schen von Dateien bei richtigen Rechten mittels Iterator<Datei>
 * Beim L�schen wird die Datei in den Papierkorb gelegt
 * 
 * @param user �bergabe des Nutzers, um Rechte zu �berpr�fen
 */
	public void deleteDatei(Nutzer user) {
		boolean erfolg = false;
		if(user != null && user.getRechte() != Rechte.admin) {
			System.out.println("Nur Nutzer mit dem Recht admin darf Dateien l�schen");
			HilfUI.promtEnterKey();
			HilfUI.printBefehleControllerUIClear();
		}
		else {
			Scanner s = new Scanner(System.in);
			dci.zeigeAlleDateienDetails();
			while (true) {
				System.out.print("Welche Datei m�chten Sie l�schen? (exit zum beenden): ");
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
