package Tag;

import java.util.ArrayList;
import java.io.Serializable;
import Datei.Datei;

public class Tag implements TagsContainerInterface, Serializable{
	
	private Tag subTag;
	private String key;
	private ArrayList <Datei> listeDateien = new ArrayList <>();
	
	public Tag(String schluessel) {
		
		key = schluessel;
	}

	public String getKey() {
		return key;
	}
	
	public void addiereNewTag(Datei dokument, Tag tag, String key) {
		if(tag != null) tag.subTag = new Tag(key);
		listeDateien.add(dokument);
	}

	public ArrayList<Datei> getListeDateien() {
		return listeDateien;
	}

	@Override
	public Tag sucheTag(String key) {   ///
		
		return null;
	}

	@Override
	public void addiereNeuesTag(String key) {   ///
	}
	
	
	
}
