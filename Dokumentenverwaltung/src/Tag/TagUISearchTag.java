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
		if(tci.getTagsListe().isEmpty()) {
			System.out.println("Es gibt keine Tags");
			HilfUI.promtEnterKey();
			return;
		}
		ArrayList<String> tagsNames = new ArrayList<>();
		boolean added = true;
		while(added == true) {
			String tmpSuche = ""; 
			Scanner sc = new Scanner(System.in);
			tci.printTagsListe();
			System.out.print("\nGeben Sie bitte ein Tag ein oder exit, wenn Sie die Suche beenden wollen: ");
			tmpSuche = sc.next();
			if(tmpSuche.compareTo("exit") == 0) {
				tagsNames = null;
				HilfUI.promtEnterKey();
				break; 
			}
			if(tci.sucheTag(tmpSuche) != null) {
				tagsNames.add(tmpSuche);
				added = eingebeCommand(tagsNames, tmpSuche, added);
			}else {
				System.out.println("Dieses Tag gibt es nicht in der Tag Kollektion\n");
				continue;
			}			
		}
	}
	
	private boolean eingebeCommand(ArrayList<String> tagsNames, String tmpSuche, boolean added) {
		while(true) {
			HilfUI.printBefehleSucheNachTags();
			String command = SichereEingabe.liesCharacters();
			if(command.compareTo("start") == 0) {
				if(tagsNames != null && !tagsNames.isEmpty()) { 							
					dci.sucheDateiTags(tagsNames);
					HilfUI.promtEnterKey();  
					added = false;
					break; 
				}
			}else if(command.compareTo("add") == 0) {
				added = true;
				break;
			}else if(command.compareTo("exit") == 0) {
				added = false;
				HilfUI.promtEnterKey();
				HilfUI.printBefehleControllerUIClear();
				break;
			}else {
				System.err.println("Unbekannter Befehl");
				continue;
			}
		}
		return added;
	}
}


