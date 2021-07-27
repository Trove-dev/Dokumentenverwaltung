package Datei;

import UI.HilfUI;
import hilf.SichereEingabe;

public class DateiUIWorkBind {
	DateienContainerInterface dci;
	Datei dokument;

	public DateiUIWorkBind(DateienContainerInterface dci) {
		this.dci = dci;
		if(dci.getAlleDateien().size() <2) {
			System.err.println("Für die Arbeit mit Verlinkungen müssen mindestens 2 Dateien vorhanden sein");
			return;
		}
		dci.zeigeAlleDateienDetails();
		dokument = checkDateien();
		if(dokument == null) {
			System.out.println("Diese Datei ist nicht im System");
			return;
		}
		printDateiVerlinkund(dokument);
	}
	
	public void workbind() {
		
		HilfUI.printBefehleVerlinkung();
		String command = SichereEingabe.liesCharacters();
		switch(command){
		case "bind":				
			Datei dok2 = checkDateien();
			if(dokument.getVerknuepfung() != null) {
				if(dokument == dok2) {
					System.err.println("Eine Datei kann nicht an sich angebunden sein");
					break;
				}else if(dokument.searchBinds(dok2)) {
					System.err.println("Die Dateien sind schon verbunden");
					break;
				}
			}
			try{
				dokument.bindDokument(dok2);
				printDateiVerlinkund(dokument);
				printDateiVerlinkund(dok2);
				break;
			}catch (Exception i) {
				i.printStackTrace();
			}
		case "unlink":
			if(dokument.getVerknuepfung() == null || dokument.getVerknuepfung().isEmpty()) {
				System.out.println("Die Datei hat keine Dateien zum Trennen");
				break;
			}			
			dok2 = checkDateien();
			if(dokument.searchBinds(dok2) == false) {
				System.out.println("Die Datei " + dokument + " und die Datei " + dok2.getName() 
				+ " sind nicht verbunden\n");
				break;
			}
			try{
				dokument.unlinkDokument(dok2);
				printDateiVerlinkund(dokument);
				printDateiVerlinkund(dok2);
			}catch (Exception i) {
				i.printStackTrace();
			}
		case "exit":
			break;
		default:
			System.err.println("Unbekannter Befehl\n");
			workbind();
		}	
	}
	
	public Datei checkDateien() {
		Datei dok = null;
		System.out.print("Welche Datei möchten Sie für die Arbeit mit Verlinkung ? "
					+ "\nSie können auch exit eintippen, um dieses Prozess zu beenden.\n");
		while(true) {
			String dateiName = SichereEingabe.liesCharacters();	
			if(dateiName.compareTo("exit") == 0) break;
			dok = dci.checkFile(dateiName);
			if(dok != null) break;
			else {
				System.out.println("Ein falscher Name. Geben Sie den nochmal bitte\n");
				continue;
			}
		}
		return dok;
	}
	
	public void printDateiVerlinkund(Datei dok) {
		System.out.println("--------------------------------------------");
		System.out.print("Für die Datei " + dok.getName() + " gilt :");
		dok.printVerknuepfungForInfo();
		System.out.println("\n------------------------------------------\n");

	}
	
}
