package Tag;

import java.io.Serializable;

import Datei.Datei;

public class Tag implements TagsContainerInterface, Serializable{
	
	private Tag SubTags[];
	private String Schluessel;
	private Datei ListeDateien[];
	
	public Tag(Datei datei, String schluessel) {
		
		Schluessel = schluessel;
	}
	
	
	
	
}
