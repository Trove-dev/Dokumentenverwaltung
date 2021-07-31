package Datei;

import java.util.Iterator;
import java.util.Scanner;

import Tag.TagsContainerInterface;
import UI.HilfUI;
import UI.TagUI;
import hilf.EingabeException;

public class DateiUIWorktags {
	
	DateienContainerInterface dci;
	TagsContainerInterface tci;

	public DateiUIWorktags(DateienContainerInterface dci, TagsContainerInterface tci) {
		this.dci = dci;
		this.tci = tci;
	}
	
	public void worktags() {
		if (dci.getAlleDateien() != null) {
			Scanner sc = new Scanner(System.in);
			dci.zeigeAlleDateienDetails();
			System.out.print("Welche Datei möchten Sie für die Arbeit mit Tags auswählen?: ");
			String dateiName = sc.next();
			if (dci.getAlleDateien() != null) {
				Iterator<Datei> it = dci.getAlleDateien().iterator();
				boolean erfolg = false;
				while (it.hasNext()) {
					Datei datei = it.next();
					if (datei.getName().equals(dateiName)) {					
						erfolg = true;
						System.out.println("\nSie haben die Datei mit dem Namen: " + dateiName + " ausgewählt");
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
}
