package Tag;

import java.util.TreeSet;

import Datei.Datei;

public interface TagsContainerInterface {
	public Tag sucheTag(String key);
	public void addiereNeuesTag(Datei dok,String key);
	public void printTagsListe();
	public TreeSet<Tag> getTagsListe();
	public void loescheTag(String name);
}
