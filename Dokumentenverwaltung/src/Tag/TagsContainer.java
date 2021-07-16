package Tag;

import java.util.ArrayList;
import java.io.Serializable;

public class TagsContainer implements TagsContainerInterface, Serializable{
	private Tag listeTags[];
	private Tag tag;
	private static TagsContainer uniqueInstance = null;
	private ArrayList<Tag> tagsListe = new ArrayList<>();
	
	private TagsContainer() {
	}
	
	@Override
	public void addiereNeuesTag(String key) {
		tag =  new Tag(key);
		tagsListe.add(tag);
	}
	
	public void addiereNeuesTag(Tag tag) {
		tagsListe.add(tag);
	}

	public void loescheTag(String key){
		if(sucheTag(key) != null) tagsListe.remove(tag);
	}
	
	@Override
	public Tag sucheTag(String key) {
		
		for(Tag suchTag: tagsListe) {
			if(suchTag.getKey() == key) tagsListe.remove(tag);
			return suchTag; 
		}
		return null;
	}
	
	

	public static TagsContainer getInstance() {
		if(uniqueInstance == null) uniqueInstance = new TagsContainer();
		return uniqueInstance;
	}
	
}
