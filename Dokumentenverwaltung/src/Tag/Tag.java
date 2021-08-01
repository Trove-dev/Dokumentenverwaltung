package Tag;

import java.util.ArrayList;
import java.io.Serializable;
import Datei.Datei;

/**
 * Klasse, welche Tag Objekte erzeugt
 */
public class Tag implements Serializable, Comparable<Tag>{

	private static final long serialVersionUID = 1L;
	private String key;
	private ArrayList <Datei> listeDateien = new ArrayList <>();

	/**
	 * Konstruktor zum Anlegen neuer Tagobjekte
	 * 
	 * @param schluessel Beschreibt den eigentlichen Tag (z.B. "Schule")
	 */
	public Tag(String schluessel) {		
		key = schluessel;
	}
	
	/**
	 * Binden von Dokumenten an den Tag
	 * 
	 * @param dokument Dokument, welches an den Tag gebunden wird
	 */
	public void bindDokument(Datei dokument) {
		listeDateien.add(dokument);
	}
	
	/**
	 * Löschen von Dokumenten von dem Tag
	 * 
	 * @param dokument Dokument, welches von dem Tag gelöst wird
	 */
	public void disconnectDokument(Datei dokument) {
		listeDateien.remove(dokument);
	}

	/**
	 * Rückgabe von Dateien, die einem Tag angehören
	 * 
	 * @return Rückgabe von ArrayList von allen Dateien
	 */
	public ArrayList<Datei> getListeDateien() {
		return listeDateien;
	}

	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int compareTo(Tag tag) {		
		return key.compareTo(tag.getKey());
	}
	
}
