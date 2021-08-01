package Tag;

import java.util.ArrayList;
import java.util.Scanner;
import Datei.DateienContainerInterface;
import UI.HilfUI;
import hilf.SichereEingabe;

/**
 * Klasse, welche das Suchen nach Tags verwaltet
 */
public class TagUISearchTag {
	
	TagsContainerInterface tci;
	DateienContainerInterface dci;

	/**
	 * Konstruktor
	 * @param tci
	 * @param dci
	 */
	public TagUISearchTag(TagsContainerInterface tci, DateienContainerInterface dci) {
		this.tci = tci;
		this.dci = dci;
	}
	
	/**
	 * Vorbereiten der Suche nach einem Tag
	 * Hier werden Tags in eine ArrayList gespeichert, um eine Suche nach mehreren Tags zu ermöglichen
	 */
	public void searchtag() {
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
	
	/**
	 * Hier startet dann die Suche nach den Tags, oder es können auch noch mehr Tags zur Suche hinzugefügt werden
	 * 
	 * @param tagsNames Liste der Tags, nach den gesucht werden soll
	 * @param tmpSuche 
	 * @param added Wenn weitere Tags hinzugefügt werden sollen wird added = true
	 * @return
	 */
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


