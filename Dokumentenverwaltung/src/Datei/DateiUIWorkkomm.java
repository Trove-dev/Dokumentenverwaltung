package Datei;

import java.util.Iterator;
import java.util.Scanner;
import UI.HilfUI;
import UI.TagUI;

public class DateiUIWorkkomm {
	DateienContainerInterface dci;
		
	public DateiUIWorkkomm(DateienContainerInterface dci) {
		
		this.dci = dci;
	}

	public void workkomm() {
		Scanner sc = new Scanner(System.in);
		dci.zeigeAlleDateienDetails();
		System.out.print("Welche Datei möchten Sie für die Arbeit mit Kommentaren ? (Bitte Dateinamen eingeben): ");
		String dateiName = sc.next();
		Iterator<Datei> it = dci.getAlleDateien().iterator();
		boolean erfolg = false;
		while (it.hasNext()) {
			Datei datei = it.next();
			if (datei.getName().equals(dateiName)) {					
				erfolg = true;
				System.out.print("\nSie haben die Datei mit dem Namen: " + dateiName + " ausgewählt. ");
				printKommentare(datei);
				System.out.println("Geben Sie einen Kommentar bitte ein :");
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
	
	public void printKommentare(Datei datei) {
		if(datei.getKommentar() == "" || datei.getKommentar() == null) System.out.println("Den Kommentar gibt es nicht");
		else System.out.println("Der Kommentar: " + datei.getKommentar());
	}
}
