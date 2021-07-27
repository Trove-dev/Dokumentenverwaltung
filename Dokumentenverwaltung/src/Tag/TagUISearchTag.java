package Tag;

import java.util.ArrayList;
import java.util.Scanner;

import Datei.DateienContainerInterface;
import UI.HilfUI;
import hilf.SichereEingabe;

public class TagUISearchTag {
	
	TagsContainerInterface tci;
	DateienContainerInterface dci;

	public TagUISearchTag(TagsContainerInterface tci, DateienContainerInterface dci) {
		this.tci = tci;
		this.dci = dci;
	}
	
	public void searchtag() {
		ArrayList<String> tagsNames = new ArrayList<>();
		boolean added = false;
		while(added == false) {
			String tmpSuche = ""; 
			Scanner sc = new Scanner(System.in);
			tci.printTagsListe();
			System.out.println("\nGeben Sie bitte ein Tag ein oder exit, wenn Sie die Suche beenden wollen:");
			tmpSuche = sc.next();
			if(tmpSuche.compareTo("exit") == 0) {
				tagsNames = null;
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();
				break; 
			}
			if(tci.sucheTag(tmpSuche) != null) {
				tagsNames.add(tmpSuche);
				HilfUI.printBefehleSucheNachTags();
				String command = SichereEingabe.liesCharacters();
				if(command.compareTo("start") == 0) {
					if(tagsNames != null && !tagsNames.isEmpty()) { 							
						dci.sucheDateiTags(tagsNames);
						HilfUI.promtEnterKey();
						HilfUI.printBefehleControllerUIClear();
						added = true;
						break; 
					}else {
						System.out.println("Es gibt keine eingegebenen Tags");
						continue;
					}
				}else if(command.compareTo("add") == 0) {
					continue;
				}else {
					System.err.println("Unbekannter Befehl");
				}
				continue;
			}else {
				System.out.println("Dieses Tag gibt es nicht in der Tag Kollektion\n");
				continue;
			}			
		}
	}
	
}
