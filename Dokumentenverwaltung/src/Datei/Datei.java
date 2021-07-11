package Datei;

import Datum.Datum;
import Tag.Tag;
import Tag.TagsContainer;

public class Datei {
	
	private String name;
	private String ersteller;
	private Datum erstellungsDatum;
	private String DateiPfed;
	private String kommentar;
	private TagsContainer tags[];
	private Datei verknuepfung[];
	private Datum datumVonletzterAenderung;
	private String format;
	private int haeufigkeitVonOeffnung;
	private double groesse;
	
	public Datei(Nutzer nutzer) {
		super();
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

	public Datum getErstellungsDatum() {
		return erstellungsDatum;
	}

	public void setErstellungsDatum(Datum erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	public String getDateiPfed() {
		return DateiPfed;
	}

	public void setDateiPfed(String dateiPfed) {
		DateiPfed = dateiPfed;
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

	public Datum getDatumVonletzterAenderung() {
		return datumVonletzterAenderung;
	}

	public void setDatumVonletzterAenderung(Datum datumVonletzterAenderung) {
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

	public void setGroesse(double groesse) {
		this.groesse = groesse;
	}
	
	
	
	
	
}
