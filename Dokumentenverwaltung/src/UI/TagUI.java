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
		ausfuerungBefehl(dok);
	}
	
	public void ausfuerungBefehl(Datei dok) {
		meldeNutzer();
		String input = "";
		sc = new Scanner(input);	
		input = SichereEingabe.liesCharacters();		
		if(input == "add") {
			anzeigeTagsCloud();
			input = eingabeTag(dok);
			checkAddTag(input, dok);
		}else if(input == "del") {
			anzeigeTagsCloud();
			input = eingabeTag(dok);
			checkDelTag(input);
		}
		else if(input == "unlink"){
			anzeigeTagsCloud();
			input = eingabeTag(dok);
			
		}
		else if(input == "exit") {
			// zurück im menü
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
		System.out.println("Geben Sie bitte ein Tag ein :");
		input = SichereEingabe.liesCharacters();
		return input;
	}
	
	public void checkUnLinkTag(String name, Datei dok) {
		if(checkGlobal(name) == null) {
			System.out.println("Dieses Tag gibt es nicht in Tag Kollektion");
		}else if(checkLocal(dok, name) == null) {
			System.out.println("Dieses Tag und diese Datei sind nicht angebunden");
		}else {
			dok.loescheTag(name);
		}
	}
	
	public void checkDelTag(String name) {
		if(checkGlobal(name) != null) {
			tci.loescheTag(name);
		} 
	}
	
	public void checkAddTag(String name, Datei dok) {
		if(checkGlobal(name) == null) {                        // not in global not in local
			tci.addiereNeuesTag(dok, name);                    //  new tag ; bind
			System.out.println("Das neue Tag wurde kreiert und an die Datei angebunden.");
		}else if(checkLocal(dok, name) == null) {              // in global, not in local
			Tag adddedTag = tci.sucheTag(name);
			adddedTag.bindDokument(dok);                       // bind
			System.out.println("Das existierende Tag wurde an die Datei angebunden.");
		}
	}

	private Tag checkLocal(Datei dok, String name) {
		for(Tag t: dok.getTags()){
			if(t.getKey() == name) return t;
		}
		return null;
	}

	private Tag checkGlobal(String name) {
		return tci.sucheTag(name);
	}
		
	public void meldeNutzer() {
		System.out.println("add \t Ein neues Tag nuzufügen");
		System.out.println("del \t Ein Tag löschen");
		System.out.println("unlink \t Ein Tag und die Datei trennen");
		System.out.println("help \t Ein Tag löschen");
		System.out.println("exit \t zum Menü-Datei");    //Aussage!!
	}	
		
	
}
