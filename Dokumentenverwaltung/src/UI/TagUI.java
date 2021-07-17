package UI;

import java.util.Scanner;

import Datei.Datei;
import Tag.Tag;
import Tag.TagsContainerInterface;
import hilf.SichereEingabe;

public class TagUI {
	
	TagsContainerInterface tci;

	public TagUI(TagsContainerInterface tci) {
		this.tci = tci;
	}
	
	public void anzeigeTagsCloud() {
		System.out.println("Diese Tags sind verfügbar: ");
		tci.printTagsCloud();
	}
	public boolean eingabeTag(Datei dok, String key) {
		Tag searchTag = tci.sucheTag(key);
		if(searchTag != null) return false;
		else {
			tci.addiereNeuesTag(dok, key);
			return true;
		}
	}
	
	public void addNewTag(Datei dok) {
		System.out.println("Wollen Sie ein neues Tag addieren (ja/nein) ?");
		String antwort= "";
		boolean erfolg = false;
		while (erfolg != true) {
			 antwort = SichereEingabe.liesCharacters();
			if(antwort == "ja") {
				System.out.print("Name des Tags: ");
				antwort = SichereEingabe.liesCharacters();
				erfolg = eingabeTag(dok, antwort);
				
				if(erfolg != true) {
					System.out.println("Dieses Tag existiert schon");
					break;
				}
				else {
					System.out.println("Das Tag ist nun im System");
				}
			}
			if(antwort == "nein") {
				erfolg = true;
			}else {
				System.out.println("Unbekannter Befehl");
				continue;
			}
		}
	}
		
	public void deleteTag(Datei dok) {
		System.out.println("discon \t dieses Tag und die Datei trennen ");
		System.out.println("deltag \t dieses Tag löschen ");
		boolean erfolg = false;
		while (erfolg != true) {
			String antwort = SichereEingabe.liesCharacters();
		
			if(antwort == "discon") {
				
				erfolg = true;				
			}
			if(antwort == "deltag") {
				
				erfolg = true;	
				System.out.println("Das Tag ist nicht mehr im System");
			}else {
				System.out.println("Unbekannter Befehl");
				continue;
			}
			
		}
	}
	
}
