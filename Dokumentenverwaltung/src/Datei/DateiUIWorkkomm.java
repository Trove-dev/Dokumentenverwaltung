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
		if (dci.getAlleDateien() == null) {
			System.out.println("Es wurden bisher noch keine Dateien hochgeladen!");
			return;
		}
		Scanner sc = new Scanner(System.in);
		dci.zeigeAlleDateienDetails();
		System.out.print("Welche Datei möchten Sie für die Arbeit mit den Kommentaren auswählen?: ");
		String dateiName = sc.next();
		Iterator<Datei> it = dci.getAlleDateien().iterator();
		boolean erfolg = false;
		while (it.hasNext()) {
			Datei datei = it.next();
			if (datei.getName().equals(dateiName)) {					
				erfolg = true;
				System.out.print("\nSie haben die Datei mit dem Namen: " + dateiName + " ausgewählt. ");
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
	
	public void printKommentare(Datei datei) {
		if(datei.getKommentar() == "" || datei.getKommentar() == null) System.out.println("Den Kommentar gibt es nicht");
		else System.out.println("Der Kommentar: " + datei.getKommentar());
	}

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
