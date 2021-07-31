
package Datei;

import java.util.Scanner;
import UI.HilfUI;
import hilf.SichereEingabe;

public class DateiUIWorkBind {
	DateienContainerInterface dci;
	Datei dokument;

	public DateiUIWorkBind(DateienContainerInterface dci) {
		this.dci = dci;
	}

	public void workbind() {

		if (dci.getAlleDateien().size() < 2) {
			System.err.println("Für die Arbeit mit Verlinkungen müssen mindestens 2 Dateien vorhanden sein");
			return;
		}
		dci.zeigeAlleDateienDetails();
		dokument = checkDateien();
		if (dokument == null) {
			return;
		}
		while (true) {
			System.out.println("\nSie arbeiten mit der Datei " + dokument.getName());
			printDateiVerlinkund(dokument);
			HilfUI.printBefehleVerlinkung();
			Scanner input = new Scanner(System.in);
			String command = input.next();
			if(command.compareTo("bind") == 0) {
				if(bindCommand(dokument) == false) break;
			}
			else if(command.compareTo("unlink") == 0) {
				if(unlinkCommand(dokument) == false) break;
			}
			else if(command.compareTo("exit") ==0) {
				break;
			}
			else {
				System.err.println("Unbekannter Befehl\n");
				
			}
		}
	}
	private boolean bindCommand(Datei dokument) {
		boolean isContinue = false;
		while(true) {
			Datei dok = checkDateien();
			if(dok == null) {
				isContinue = false;
				break;
			}
			if (dokument == dok) {
				System.err.println("Eine Datei kann nicht an sich angebunden sein");
				continue;
			}
			if (dokument.getVerknuepfung() != null) {
				if (dokument.searchBinds(dok)) {
					System.err.println("Die Dateien sind schon verbunden");
					continue;
				}else {
					isContinue = tryBinding(dokument, dok);
					break;
				}
			} 
			else {
				isContinue = tryBinding(dokument, dok);			
				break;
			}
		}
		return isContinue;
	}
	
	private boolean tryBinding(Datei dokument, Datei dok) {
		try {
				dokument.bindDokument(dok);
				printDateiVerlinkund(dokument);
				printDateiVerlinkund(dok);
				System.out.println("Die Dateien sind nun verbunden");
				return true;

			} catch (Exception i) {
				i.printStackTrace();
			}
		return false;
	}
	private boolean unlinkCommand(Datei dokument) {
		boolean isContinue = false;
		while(true) {
			if (dokument.getVerknuepfung() == null || dokument.getVerknuepfung().isEmpty()) {
				System.out.println("Die Datei hat keine Dateien zum Trennen");
				isContinue = true;
				break;
			}
			Datei dok = checkDateien();
			if(dok == null) {
				isContinue = false;
				break;
			}
			if(dokument == dok) {
				System.err.println("Eine Datei konnte mit sich nicht verbunden\n");
				continue;
			}
			if (dokument.searchBinds(dok) == false) {
				System.out.println("Die Datei " + dokument.getName() + " und die Datei " 
												+ dok.getName() + " sind nicht verbunden\n");
				continue;
			}
			try {
				dokument.unlinkDokument(dok);
				printDateiVerlinkund(dokument);
				printDateiVerlinkund(dok);
				System.out.println("Die Dateien " + dokument.getName() + " und " + dok.getName() + " sind nicht mehr verbunden");
				isContinue = true;
				break;
			} catch (Exception i) {
				i.printStackTrace();
			}	
		}
		return isContinue;
	}
	
	private Datei checkDateien() {
		Scanner input = new Scanner(System.in);
		Datei dok = null;
		System.out.print("Welche Datei möchten Sie für die Arbeit mit Verlinkung ? "
				+ "\nSie können auch exit eintippen, um dieses Prozess zu beenden.\n");
		while (true) {
			String dateiName = input.next();
			if (dateiName.compareTo("exit") == 0)
				break;
			dok = dci.checkFile(dateiName);
			if (dok != null)
				break;
			else {
				System.out.println("Ein falscher Name. Geben Sie den nochmal bitte\n");
				continue;
			}
		}
		return dok;
	}

	private void printDateiVerlinkund(Datei dok) {
		System.out.println("--------------------------------------------");
		System.out.print("Für die Datei " + dok.getName() + " gilt :");
		dok.printVerknuepfungForInfo();
		System.out.println("---------------------------------------------\n");

	}

}
