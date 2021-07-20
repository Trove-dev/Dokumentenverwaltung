package Tag;

import java.util.ArrayList;
import java.io.Serializable;
import Datei.Datei;

public class Tag implements Serializable{
	
	
	private String key;
	private ArrayList <Datei> listeDateien = new ArrayList <>();

	public Tag(String schluessel) {
		
		key = schluessel;
	}
	
	public void bindDokument(Datei dokument) {
		listeDateien.add(dokument);
	}
	
	public void disconnectDokument(Datei dokument, Tag tag) {
		tag.listeDateien.remove(dokument);
		if(tag.listeDateien.isEmpty()) {
			tag = null;
		}
	}

	public ArrayList<Datei> getListeDateien() {
		return listeDateien;
	}
	
	public void setListeDateien(ArrayList<Datei> listeDateien) {
			this.listeDateien = listeDateien;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
