package Tag;

import java.util.TreeSet;
import Datei.Datei;
import java.io.Serializable;

/**
 * Der TagContainer dient zur Verwaltung von Tags
 */
public class TagsContainer implements TagsContainerInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private Tag tag;
	private static TagsContainer uniqueInstance = null;
	private TreeSet<Tag> tagsListe = new TreeSet<>();
	
	/**
	 * Konstruktor und Singleton
	 */
	private TagsContainer() {
	}
	public static TagsContainer getInstance() {
		if(uniqueInstance == null) uniqueInstance = new TagsContainer();
		return uniqueInstance;
	}
	
	/**
	 * Hinzufügen von neuen Tags
	 * 
	 * @param dok Datei, welche das Tag erhalten soll
	 * @param key Tag, welches erzeugt und angebunden werden soll
	 */
	public void addiereNeuesTag(Datei dok, String key) {
		tag =  new Tag(key);
		tagsListe.add(tag);
		addiereEinTag(dok, tag);
	}
	
	/**
	 * Verknüpfen von Datei und Tag
	 * 
	 * @param dok Datei, welche das Tag erhalten soll
	 * @param key Tag, welches erzeugt und angebunden werden soll 
	 */
	public void addiereEinTag(Datei dok, Tag tag) {
		tag.bindDokument(dok);
		dok.addiereTag(tag);
	}
	
	/**
	 * Löschen von Tags
	 * 
	 * @param tag Tag, welcher gelöscht werden soll
	 */
	public void loescheTag(Tag tag){
		tagsListe.remove(tag);
		tag = null;
	}
	
	/**
	 * Tags suchen
	 * 
	 * @param key Name eines Tags, welcher gesucht werden soll
	 * 
	 * @return Tagobjekt wird zurückgegeben
	 */
	public Tag sucheTag(String key) {
		for(Tag suchTag: tagsListe) {
			if(suchTag.getKey().compareTo(key) == 0) return suchTag;
		}
		return null;
	}

	/**
	 * Ausgabe aller Tags im System durch Konsole
	 */
	public void printTagsListe() {
		System.out.print("Diese Tags sind schon im System: ");
		int i = 0;
		for(Tag tags:tagsListe) {
				System.out.print(tags.getKey() + "\t\t");	
				i ++;
				if(i%5 == 0) System.out.print("\n");
		}	
	}

	public TreeSet<Tag> getTagsListe() {
		return tagsListe;
	}

	public void setTagsListe(TreeSet<Tag> tagsListe) {
		this.tagsListe = tagsListe;
	}
	

	
}
