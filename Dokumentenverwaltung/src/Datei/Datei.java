package Datei;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.HashSet;
import Datum.Datum;
import Nutzer.Nutzer;
import Tag.Tag;
import Tag.TagsContainer;
import Tag.TagsContainerInterface;

public class Datei implements Serializable{
	
	private String name;
	private String ersteller;                // ?? n�tig ?
	private String erstellungsDatum;
	private String dateiPfad;
	private String kommentar;
	private HashSet <Tag> tags;
	private Datei verknuepfung[];
	private String datumVonletzterAenderung;
	private String format;
	private int haeufigkeitVonOeffnung;		// nicht m�glich?
	private long groesse;
	TagsContainerInterface tci;

	public Datei(String name, UserPrincipal owner, FileTime creationTime, FileTime lastModifiedTime,
			String extension, long size, Path file) {
		this.name = name;
		this.ersteller = owner.toString();
		this.erstellungsDatum = creationTime.toString();
		this.dateiPfad = file.toString();
		this.kommentar = null;
		this.tags = null;
		this.verknuepfung = null;
		this.datumVonletzterAenderung = lastModifiedTime.toString();
		this.format = extension;
		this.haeufigkeitVonOeffnung = 0;	// nicht m�glich?
		this.groesse = size;	
	}
	
	public void anzeigeDateiDetail() {
		//System.out.println(this);				//debug
		System.out.println("Name der Datei:\t\t" + name);
		System.out.println("Ersteller der Datei:\t" + ersteller);
		System.out.println("Erstellungsdatum:\t" + erstellungsDatum);
		System.out.println("Dateifpad:\t\t" + dateiPfad);
		printKommentarForInfo();		
		printTagsForInfo();
		printVerknuepfungForInfo();
		System.out.println("Datum letzter �nderung:\t" + datumVonletzterAenderung);
		System.out.println("Format:\t\t\t" + format);
		System.out.println("H�ufigkeit von �ffnung:\t" + haeufigkeitVonOeffnung);
		System.out.println("Gr��e der Datei:\t" + groesse + " Bytes");
		System.out.println("----------------------");
	}
	
	public void printTagsForInfo() {
		System.out.print("Tags: \t\t\t");
		if(tags != null) {
			for(Tag t:tags) System.out.print(t.getKey() + "\t\t");
		}else System.out.print("noch keine\n");
	}
	
	public void printKommentarForInfo() {
		System.out.print("Kommentar: \t");
		if(kommentar != null) System.out.print(kommentar);
		else System.out.print("noch keine\n");
	}
	
	public void printVerknuepfungForInfo() {
		System.out.print("\nVerkn�pfung: \t");
		if(verknuepfung != null) {
			for(Datei d:verknuepfung) {
				System.out.println(d.dateiPfad);
			}
		}else System.out.print("noch keine\n");
	}

	public void addiereTag(Tag tag) {
		if(this.tags == null) this.tags = new HashSet<Tag>();
		this.tags.add(tag);
	}
	
	public void loescheTag(Tag delTag) {
		tags.remove(delTag);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErsteller() {
		return ersteller;
	}

	public void setErsteller(String ersteller) {
		this.ersteller = ersteller;
	}

	public String getErstellungsDatum() {
		return erstellungsDatum;
	}

	public void setErstellungsDatum(String erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	public String getDateiPfad() {
		return dateiPfad;
	}

	public void setDateiPfed(String dateiPath) {
		this.dateiPfad = dateiPath;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public HashSet<Tag> getTags() {
		return tags;
	}
	
	public void printTagsVonDatei() {
		if(tags == null) System.out.println("\nEs gibt keine Tags f�r diese Datei\n");
		else{
			System.out.println("\nDie Datei enth�lt diese Tag(s) :");
			int i = 0;
			for(Tag tag:tags) {
				System.out.print(tag.getKey() + "\t\t");
				if(i%5 == 0) System.out.print("\n");
			}
		}
	}

	public Datei[] getVerknuepfung() {
		return verknuepfung;
	}

	public void setVerknuepfung(Datei[] verknuepfung) {
		this.verknuepfung = verknuepfung;
	}

	public String getDatumVonletzterAenderung() {
		return datumVonletzterAenderung;
	}

	public void setDatumVonletzterAenderung(String datumVonletzterAenderung) {
		this.datumVonletzterAenderung = datumVonletzterAenderung;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getHaeufigkeitVonOeffnung() {
		return haeufigkeitVonOeffnung;
	}

	public void setHaeufigkeitVonOeffnung(int haeufigkeitVonOeffnung) {
		this.haeufigkeitVonOeffnung = haeufigkeitVonOeffnung;
	}

	public double getGroesse() {
		return groesse;
	}

	public void setGroesse(long groesse) {
		this.groesse = groesse;
	}
	
}
