package Tag;

import java.util.ArrayList;

import Datei.Datei;

public class Tag {
	
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
	
	
	
}
