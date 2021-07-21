package Tag;

import java.util.TreeSet;

import Datei.Datei;

import java.io.Serializable;

public class TagsContainer implements TagsContainerInterface, Serializable{
	private Tag tag;
	private static TagsContainer uniqueInstance = null;
	private TreeSet<Tag> tagsListe = new TreeSet<>();
	
	private TagsContainer() {
	}
	
	@Override
	public void addiereNeuesTag(Datei dok, String key) {
		tag =  new Tag(key);
		tagsListe.add(tag);
		tag.bindDokument(dok);
		dok.addiereTag(tag, key);
	}
		
	public void loescheTag(String key){
		if(sucheTag(key) != null) tagsListe.remove(tag);
	}
	
	@Override
	public Tag sucheTag(String key) {
		for(Tag suchTag: tagsListe) {
			if(suchTag.getKey() == key) return suchTag;
		}
		return null;
	}
	
	public static TagsContainer getInstance() {
		if(uniqueInstance == null) uniqueInstance = new TagsContainer();
		return uniqueInstance;
	}
	
	public void printTagsListe() {
		int i = 0;
		for(Tag tags:tagsListe) {
				System.out.print("  "+ tags.getKey() + "  ");	
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
