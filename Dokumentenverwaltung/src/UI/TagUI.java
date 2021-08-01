package UI;

import java.util.Scanner;

import Datei.Datei;
import Tag.Tag;
import Tag.TagsContainerInterface;
import hilf.SichereEingabe;

/** 
 * Klasse, welche die Oberfläche zum Verwalten von Tags ermöglicht
 */
public class TagUI {
	
	TagsContainerInterface tci;
	Scanner sc;

	/**
	 * Konstruktor
	 * @param tci
	 * @param dok
	 */
	public TagUI(TagsContainerInterface tci, Datei dok) {
		this.tci = tci;
		dok.printTagsVonDatei();
	} 
	
	/**
	 * Menü zum Verwalten Tags eines Dokumentes
	 * 
	 * @param dok Dateiobjekt, wessen Tag bearbeitet werden soll
	 */
	public void ausfuerungBefehl(Datei dok) {
		HilfUI.printBefehleTags();
		String input = "";
		System.out.print("Bitte eine Befehl eingeben: ");
		sc = new Scanner(input);	
		input = SichereEingabe.liesCharacters();		
		if(input.compareTo("add") == 0) {
			anzeigeTagsCloud();
			input = eingabeTag(dok);
			checkAddTag(input, dok);
		}else if(input.compareTo("del") == 0) {
			anzeigeTagsCloud();
			if(tci.getTagsListe().isEmpty()) return;
			input = eingabeTag(dok);
			checkDelTag(dok, input);
			anzeigeTagsCloud(); 
		}
		else if(input.compareTo("unlink") == 0){
			dok.printTagsVonDatei();
			if(dok.getTags() != null && !dok.getTags().isEmpty())
				if(checkUnLinkTag(dok))	
					dok.printTagsVonDatei();
		}
		else if(input.compareTo("exit") == 0) {
			HilfUI.promtEnterKey();
			return;
		}else {
			System.out.println("Unbekannter Befehl");			
		}
		ausfuerungBefehl(dok);
	}

	/**
	 * Ausgabe aller Tags, welche sich im System befinden
	 */
	public void anzeigeTagsCloud() {
		String input = "";
		sc = new Scanner(input);
		if(tci.getTagsListe().isEmpty()) {
			System.out.println("Es gibt noch keine Tags!");
		}else tci.printTagsListe();		
	}
	
	/**
	 * Eingabe eines Tags
	 * 
	 * @param dok Dateiobjekt, zu dem ein Tag hinzugefügt werden soll
	 * @return den Tag, welcher eingegeben wurde
	 */
	public String eingabeTag(Datei dok){
		String input = "";
		sc = new Scanner(System.in);
		System.out.print("\nGeben Sie bitte ein Tag ein: ");
		input = sc.next();
		return input;
	}
	
	/**
	 * Tag von einem Dokument abbinden, jedoch nicht aus dem gesamten System löschen
	 * 
	 * @param dok Dateiobjekt, von dem der Tag abgelöst werden soll
	 */
	public boolean checkUnLinkTag(Datei dok) {
		boolean erfolg = false;
		System.out.print("\nWelches Tag wollen Sie abbinden? ");
		String name = eingabeTag(dok);
		String mesUnlink = "Dieses Tag und die Datei " + dok.getName() + " sind nicht angebunden";
		if(checkGlobal(name) == null) {
			System.err.println("Dieses Tag gibt es nicht in der Tag Kollektion\n");
		}else if(checkLocal(dok, name) == null) {
			System.out.println(mesUnlink);
			erfolg = true;
		}else {
			Tag unlinkTag = checkLocal(dok, name);
			dok.loescheTag(unlinkTag);
			unlinkTag.disconnectDokument(dok);
			String mesDel = "";
			if(unlinkTag.getListeDateien().isEmpty()) {
				tci.loescheTag(unlinkTag);
				mesDel = ",\ndieses Tag hat nun keine Anbindungen und wurde gelöscht\n";
				erfolg = true;
			}
			System.out.println(mesUnlink + mesDel);
		}
		return erfolg;
	}
	
	/**
	 * Entferne Tag von Datai
	 * 
	 * @param dok Datei, von dem der Tag gelöscht werden soll
	 * @param name Name des Tags, der gelöscht werden soll
	 */
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
	
	/**
	 * Anbinden eines Tags an eine Datei
	 * Wenn Tag bereits im Sytsem existiert, wird dieser an die neue Datei angebunden
	 * Wenn Tag nicht im System existiert, wird ein neuer Tag erstellt und angebunden
	 * 
	 * @param name Name des Tags
	 * @param dok Datei, an welche der Tag angebunden wird
	 */
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

	/**
	 * Überprüfen, ob Tag bereits an dieser Datei angebunden ist
	 * 
	 * @param dok Datei zur Überprüfung
	 * @param name Name des Tags zur Überprüfung
	 * 
	 * @return Tag, wenn Tag nicht an dieser Datei angebunden ist
	 */
	private Tag checkLocal(Datei dok, String name) {
		if(dok.getTags() != null) {
			for(Tag t: dok.getTags()){
				if(t.getKey().compareTo(name) == 0) return t;
			}
		}
		return null;
	}

	/**
	 * Überprüfen, ob der Tag im System bereits existiert
	 * 
	 * @param name Name des Tags zur Überprüfung
	 * 
	 * @return Tag, wenn dieser bereits existiert. null, wenn dieser nicht existiert
	 */
	private Tag checkGlobal(String name) {
		return tci.sucheTag(name);
	}	
}
