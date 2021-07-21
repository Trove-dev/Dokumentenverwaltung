package Datei;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.HashSet;
import Datum.Datum;
import Nutzer.Nutzer;
import Tag.Tag;
import Tag.TagsContainer;
import Tag.TagsContainerInterface;

public class Datei implements Serializable{
	
	private String name;
	private String ersteller;                // ?? nötig ?
	private String erstellungsDatum;
	private String dateiPfad;
	private String kommentar;
	private HashSet <Tag> tags;
	private Datei verknuepfung[];
	private String datumVonletzterAenderung;
	private String format;
	private int haeufigkeitVonOeffnung;
	private long groesse;
	private TagsContainerInterface tc;

	public Datei(String name, FileTime creationTime, FileTime lastAccessTime, FileTime lastModifiedTime,
			boolean directory, boolean regularFile, boolean symbolicLink, boolean other, long size, Path file) {
		this.name = name;
		this.ersteller = null;
		this.erstellungsDatum = creationTime.toString();
		this.dateiPfad = file.toString();
		this.kommentar = null;
		this.tags = null;
		this.verknuepfung = null;
		this.datumVonletzterAenderung = lastModifiedTime.toString();
		this.format = null;
		this.haeufigkeitVonOeffnung = 0;
		this.groesse = size;	
	}
	
	public void anzeigeDateiDetail() {
		//System.out.println(this);				//debug
		System.out.println("Name der Datei:\t\t" + name);
		System.out.println("Ersteller der Datei:\t" + ersteller);
		System.out.println("Erstellungsdatum:\t" + erstellungsDatum);
		System.out.println("Dateifpad:\t\t" + dateiPfad);
		System.out.println("Kommentar:\t\t" + kommentar);
		System.out.println("Tags:\t\t\t" + tags);
		System.out.println("Verknüpfung:\t\t" + verknuepfung);
		System.out.println("Datum letzter Änderung:\t" + datumVonletzterAenderung);
		System.out.println("Format:\t\t\t" + format);
		System.out.println("Häufigkeit von Öffnung:\t" + haeufigkeitVonOeffnung);
		System.out.println("Größe der Datei:\t" + groesse + " Bytes");
		System.out.println("----------------------");
	}

	public void addiereTag(String key) {
		if(this.tags == null) this.tags = new HashSet<Tag>();
		this.tags.add(tc.sucheTag(key));
	}
	
	public void loescheTag(String key) {
		Tag delTag = tc.sucheTag(key);
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

	public String getDateiPfed() {
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
