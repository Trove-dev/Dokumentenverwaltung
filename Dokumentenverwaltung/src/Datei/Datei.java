package Datei;

import java.io.Serializable;
import java.util.HashSet;
import Tag.Tag;
import Tag.TagsContainerInterface;

/**
 * Klasse, welche Dateiobjekte anlegt
 */
public class Datei implements Serializable, Comparable<Datei>{
	

	private static final long serialVersionUID = 1L;
	private String name;
	private String ersteller;
	private String erstellungsDatum;
	private String dateiPfad;
	private String kommentar;
	private HashSet <Tag> tags;
	private HashSet<Datei> verknuepfung;
	private String datumVonletzterAenderung;
	private String format;
	private long groesse;
	private int haeufigkeitVonOeffnung;
	TagsContainerInterface tci;

	/**
	 * Kontruktor f�r das Anlegen neuer Objekte vom Typ Datei
	 * 
	 * @param name
	 * @param tmpOwner
	 * @param tmpCreationTime
	 * @param tmpLastModiefiedTime
	 * @param extension
	 * @param size
	 * @param tmpfile
	 */
	public Datei(String name, String tmpOwner, String tmpCreationTime, String tmpLastModiefiedTime,
			String extension, long size, String tmpfile) {
		this.name = name;
		this.ersteller = tmpOwner;
		this.erstellungsDatum = tmpCreationTime;
		this.dateiPfad = tmpfile;
		this.kommentar = null;
		this.tags = null;
		this.verknuepfung = null;
		this.datumVonletzterAenderung = tmpLastModiefiedTime;
		this.format = extension;
		this.groesse = size;	
		this.haeufigkeitVonOeffnung = 0;
	}
	
	/**
	 * Gibt die gespeicherten Informationen einer Datei in der Konsole aus
	 */
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
		System.out.println("H�ufigkeit von �ffnung " + haeufigkeitVonOeffnung );
		System.out.println("Gr��e der Datei:\t" + groesse + " Bytes");
		System.out.println("----------------------");
	}
	
	/**
	 * Gibt die gespeicherten Tags einer Datei in der Konsole aus
	 */
	public void printTagsForInfo() {
		System.out.print("Tags: \t\t\t");
		if(tags == null || tags.isEmpty()) {
			System.out.print("noch keine");
		}
		else{
			for(Tag t:tags) System.out.print(t.getKey() + "\t\t");
		} 
	}
	
	/**
	 * Gibt den gespeicherten Kommentar einer Datei in der Konsole aus
	 */
	public void printKommentarForInfo() {
		String komm = "";
		if(kommentar != "" && kommentar != null) {
			komm = kommentar;
		}
		else {
			komm = "noch keine";
		}
		System.out.println("Kommentar: \t\t" + komm);
		
	}
	
	/**
	 * Gibt die gespeicherten Verkn�pfungen einer Datei in der Konsole aus
	 */
	public void printVerknuepfungForInfo() {
		String bindDok = "";
		if(verknuepfung != null && !verknuepfung.isEmpty()) {
			for(Datei d:verknuepfung) {
				bindDok = bindDok + d.name + "; ";
			}
		}
		else {
			bindDok = "noch keine";
		}
		System.out.println("\nVerkn�pfung: \t\t" + bindDok);
	}

	/**
	 * Checkt ob das aktuelle Dateiobjekt bereits mit der Datei dok verkn�pft ist
	 * @param dok
	 * @return
	 */
	public boolean searchBinds(Datei dok) {
		if(verknuepfung != null) {
			if(verknuepfung.contains(dok))
				return true;
		}
		return false;
	}
	
	/**
	 * Gibt die Tags einer Datei in der Konsole aus
	 */
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
	
	/**
	 * F�gt dem aktuellen Dateiobjekt einen neuen Tag hinzu
	 * wenn noch keine Tags vorhanden sind, wird ein HashSet erzeugt
	 * @param tag
	 */
	public void addiereTag(Tag tag) {
		if(this.tags == null) {
			this.tags = new HashSet<Tag>();
		}
		this.tags.add(tag);
	}
	

	/**
	 * Gibt die Verlinkungen einer Datei in der Konsole aus
	 */
	public void printVerlinkungen() {
		String message = "";
		if(verknuepfung != null) {
			for(Datei a:verknuepfung) {
				message = message + a.getDateiPfad() + "/n";
			}
		}else message = "noch keine/n";
		System.out.println("Die verlinkte Dateien: " + message);
	}
	
	/**
	 * Verlinkung von Datei wird hergestellt
	 * @param dok
	 */
	public void bindDokument(Datei dok) {
		if(verknuepfung == null) {
			verknuepfung = new HashSet<>();
		}
		verknuepfung.add(dok);
		if(dok.verknuepfung == null) {
			dok.verknuepfung = new HashSet<Datei>();
		}
		dok.verknuepfung.add(this);
	}
	/**
	 * Verlinkung wird entfernt
	 * @param dok
	 */
	public void unlinkDokument(Datei dok) {
		verknuepfung.remove(dok);
		dok.verknuepfung.remove(this);
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
	
	public HashSet<Datei> getVerknuepfung() {
		return verknuepfung;
	}

	public void setVerknuepfung(HashSet<Datei> verknuepfung) {
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

	public long getGroesse() {
		return groesse;
	}

	public void setGroesse(long groesse) {
		this.groesse = groesse;
	}
	
	public void setTags(HashSet <Tag> tags) {
		this.tags = tags;
	}

	@Override
	public int compareTo(Datei dok) {
		
		return dok.getHaeufigkeitVonOeffnung() - getHaeufigkeitVonOeffnung();
	}

	public int getHaeufigkeitVonOeffnung() {
		return haeufigkeitVonOeffnung;
	}

	public void setHaeufigkeitVonOeffnung(int haeufigkeitVonOeffnung) {
		this.haeufigkeitVonOeffnung = haeufigkeitVonOeffnung;
	}
	

	
	
}
