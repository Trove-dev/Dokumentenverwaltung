package Datei;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

import UI.HilfUI;

public class DateiUIOpenDatei {

	DateienContainerInterface dci;

	public DateiUIOpenDatei(DateienContainerInterface dci) {
		this.dci = dci;
	}
	
	public void startOpeningDatei() throws IOException {
		Scanner sc = new Scanner(System.in);
		dci.zeigeAlleDateienDetails();
		System.out.print("Geben Sie bitte den Namen der Datei ein: ");
		String dateiName = sc.next();
		Datei dok = dci.checkFile(dateiName);
		if(dok != null) {
			openDatei(dok, dateiName);
		}else {  
			File actPath = new File(".");
			Path pathGet;
			if (HilfUI.isWindows() == true) {
				pathGet = Paths.get(Paths.get(actPath.getCanonicalPath()) + "\\" + dateiName);				
				
			}else if (HilfUI.isMac() == true) {
				pathGet = Paths.get(Paths.get(actPath.getCanonicalPath()) + "/" + dateiName);
				
			}else {
				System.out.println("Betriebssystem wird nicht unterstüzt!");
				return;
			}
			if(pathGet != null) {
				if(dci.hochladeDatei(pathGet, dateiName) == true) {
					System.out.println("Die Datei sit nun im System");
					openDatei(dci.checkFile(dateiName), dateiName);
				}else {
					System.out.println("Die Daten konnten nicht gespeichert werden");
					HilfUI.promtEnterKey();
					HilfUI.printBefehleControllerUIClear();
				}
			}
			}		
	}
	
	public void openDatei(Datei dok, String dateiName) {
		if (dok.getName().equals(dateiName)) {	
				try {
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().open(new File(dok.getDateiPfad()));
						
					}
				}catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
	}
}
	
	

