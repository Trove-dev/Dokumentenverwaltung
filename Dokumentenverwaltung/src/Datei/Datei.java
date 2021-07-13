package Datei;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

import Datum.Datum;
import Nutzer.Nutzer;
import Tag.Tag;
import Tag.TagsContainer;

public class Datei {
	
	private String name;
	private String ersteller;
	private FileTime erstellungsDatum;
	private Path dateiPfad;
	private String kommentar;
	private TagsContainer tags[];
	private Datei verknuepfung[];
	private FileTime datumVonletzterAenderung;
	private String format;
	private int haeufigkeitVonOeffnung;
	private long groesse;
	
//	public Datei(String name , String ersteller, Datum erstellungsDatum, String dateiPfad, String kommentar, TagsContainer tags[], Datei verknuepfung[], Datum datumVonletzterAenderung, String format, int haeufigkeitVonOeffnung, double groesse) {
//		this.name = name;
//		this.ersteller = ersteller;
//		this.erstellungsDatum = erstellungsDatum;
//		this.dateiPfad = dateiPfad;
//		this.kommentar = kommentar;
//		this.tags = tags;
//		this.verknuepfung = verknuepfung;
//		this.datumVonletzterAenderung = datumVonletzterAenderung;
//		this.format = format;
//		this.haeufigkeitVonOeffnung = haeufigkeitVonOeffnung;
//		this.groesse = groesse;
//	}
	
	public Datei(String name, FileTime creationTime, FileTime lastAccessTime, FileTime lastModifiedTime,
			boolean directory, boolean regularFile, boolean symbolicLink, boolean other, long size, Path file) {
		this.name = name;
		this.ersteller = null;
		this.erstellungsDatum = creationTime;
		this.dateiPfad = file;
		this.kommentar = null;
		this.tags = null;
		this.verknuepfung = null;
		this.datumVonletzterAenderung = lastModifiedTime;
		this.format = null;
		this.haeufigkeitVonOeffnung = 0;
		this.groesse = size;
		
	}

	public void addiereTag() {
		
	}
	
	public void addiereKommentar() {
		
	}
	
	public void loescheTag(Tag tag) {
		
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

	public FileTime getErstellungsDatum() {
		return erstellungsDatum;
	}

	public void setErstellungsDatum(FileTime erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	public Path getDateiPfed() {
		return dateiPfad;
	}

	public void setDateiPfed(Path dateiPath) {
		dateiPfad = dateiPfad;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public TagsContainer[] getTags() {
		return tags;
	}

	public void setTags(TagsContainer[] tags) {
		this.tags = tags;
	}

	public Datei[] getVerknuepfung() {
		return verknuepfung;
	}

	public void setVerknuepfung(Datei[] verknuepfung) {
		this.verknuepfung = verknuepfung;
	}

	public FileTime getDatumVonletzterAenderung() {
		return datumVonletzterAenderung;
	}

	public void setDatumVonletzterAenderung(FileTime datumVonletzterAenderung) {
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
