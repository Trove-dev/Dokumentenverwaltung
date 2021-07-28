package Datei;

import java.util.Iterator;
import java.util.Scanner;
import Nutzer.Nutzer;
import Nutzer.Rechte;
import Papierkorb.Papierkorb;
import UI.HilfUI;

public class DateiUIDeleteDatei {

DateienContainerInterface dci;
Papierkorb p;


public DateiUIDeleteDatei(DateienContainerInterface dci, Papierkorb p) {

	this.dci = dci;
	this.p = p;
}

public void deleteDatei(Nutzer user) {
	
	boolean erfolg = false;
	if(user != null && user.getRechte() != Rechte.admin) {
		System.out.println("Nur Nutzer mit dem Recht admin darf Dateien löschen");
		HilfUI.promtEnterKey();
		HilfUI.printBefehleControllerUIClear();
	}else {
		Scanner s = new Scanner(System.in);
		dci.zeigeAlleDateienDetails();
		System.out.print("Welche Datei möchten Sie löschen? (Bitte Dateinamen eingeben): ");
		String dateiName = s.next();
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
