package UI;

import java.util.Scanner;

import Datei.Datei;
import Tag.Tag;
import Tag.TagsContainerInterface;
import hilf.SichereEingabe;

public class TagUI {
	
	TagsContainerInterface tci;
	Scanner sc;

	public TagUI(TagsContainerInterface tci, Datei dok) {
		this.tci = tci;
		dok.printTagsVonDatei();
	} 
	
	public void ausfuerungBefehl(Datei dok) {
		HilfUI.printBefehleTags();
		String input = "";
		sc = new Scanner(input);	
		input = SichereEingabe.liesCharacters();		
		if(input.compareTo("add") == 0) {
			anzeigeTagsCloud();
			input = eingabeTag(dok);
			checkAddTag(input, dok);
		}else if(input.compareTo("del") == 0) {
			anzeigeTagsCloud();
			input = eingabeTag(dok);
			checkDelTag(dok, input);
			anzeigeTagsCloud();
		}
		else if(input.compareTo("unlink") == 0){
			anzeigeTagsCloud();
			dok.printTagsVonDatei();
			checkUnLinkTag(dok);
			dok.printTagsVonDatei();
		}
		else if(input.compareTo("exit") == 0) {
			return;
		}else {
			System.out.println("Unbekannter Befehl");			
		}
		ausfuerungBefehl(dok);
	}

	public void anzeigeTagsCloud() {
		String input = "";
		sc = new Scanner(input);
		if(tci.getTagsListe().isEmpty()) {
			System.out.println("Es gibt noch keine Tags ");
		}else tci.printTagsListe();		
	}
	
	public String eingabeTag(Datei dok) {
		String input = "";
		sc = new Scanner(input);
		System.out.println("\nGeben Sie bitte ein Tag ein :\n");
		input = SichereEingabe.liesCharacters();
		return input;
	}
	
	public void checkUnLinkTag(Datei dok) {
		System.out.println("\nWelches Tag wollen Sie abbinden ?");
		String name = eingabeTag(dok);
		String mesUnlink = "Dieses Tag und die Datei " + dok.getName() + " sind nicht mehr angebunden";
		if(checkGlobal(name) == null) {
			System.out.println("Dieses Tag gibt es nicht in der Tag Kollektion\n");
		}else if(checkLocal(dok, name) == null) {
			System.out.println(mesUnlink);
		}else {
			Tag unlinkTag = checkLocal(dok, name);
			dok.loescheTag(unlinkTag);
			unlinkTag.disconnectDokument(dok);
			String mesDel = "";
			if(unlinkTag.getListeDateien().isEmpty()) {
				tci.loescheTag(unlinkTag);
				mesDel = ",\ndieses Tag hat nun keine Anbindungen und wurde gelöscht\n";
			}
			System.out.println(mesUnlink + mesDel);
		}
	}
	
	public void checkDelTag(Datei dok, String name) {
		Tag delTag = checkGlobal(name);
		if(delTag != null) {
			dok.loescheTag(delTag);
			tci.loescheTag(delTag);
			System.out.println("Das Tag wurde entfernt");
		}else {
			System.out.println("Dieses Tag gibt es nicht in der Tag Kollektion\n");
		}
	}
	
	public void checkAddTag(String name, Datei dok) {
		if(checkGlobal(name) == null) {                        // not in global not in local
			tci.addiereNeuesTag(dok, name);                    //  new tag ; bind
			System.out.println("Das neue Tag wurde kreiert und an die Datei " + dok.getName() + " angebunden.");
		}else if(checkLocal(dok, name) == null) {              // in global, not in local
			Tag adddedTag = tci.sucheTag(name);
			tci.addiereEinTag(dok, adddedTag);
			System.out.println("Das existierende Tag wurde an die Datei " + dok.getName() + " angebunden.");
		}else System.out.println("Dieses Tag existiert schon");
	}

	private Tag checkLocal(Datei dok, String name) {
		if(dok.getTags() != null) {
			for(Tag t: dok.getTags()){
				if(t.getKey().compareTo(name) == 0) return t;
			}
		}
		return null;
	}

	private Tag checkGlobal(String name) {
		return tci.sucheTag(name);
	}	
}
