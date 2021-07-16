package Verarbeitung;

import java.io.Serializable;
import Datei.DateienContainer;
import Datei.DateienContainerInterface;
import Nutzer.NutzerContainer;
import Nutzer.NutzerContainerInterface;
import Tag.TagsContainer;
import Tag.TagsContainerInterface;
import Verlinkung.VerknuepfungVonDateien;
import db.PersistenzDB;
import db.PersistenzIF;
import papierkorb.Papierkorb;

public class ServiceLocator implements Serializable{
	
	private static ServiceLocator uniqueInstance = null;
	
	private DateienContainerInterface dc;
	private TagsContainerInterface tc; 
	private VerknuepfungVonDateien vd;
	private Papierkorb pk;
	private NutzerContainerInterface nc;
	private PersistenzIF p;
	
	public ServiceLocator() {
		
		dc = DateienContainer.getInstance();
		tc = TagsContainer.getInstance();
		vd = VerknuepfungVonDateien.getInstance();
		pk = Papierkorb.getInstance();
		nc = NutzerContainer.getInstance();
		p = PersistenzDB.getInstance();
	}
	
	public static ServiceLocator getInstance() {
		if (uniqueInstance == null) uniqueInstance = new ServiceLocator();
		return uniqueInstance;
	}

	public DateienContainerInterface getDc() {
		return dc;
	}

	public TagsContainerInterface getTc() {
		return tc;
	}

	public VerknuepfungVonDateien getVd() {
		return vd;
	}

	public Papierkorb getPk() {
		return pk;
	}

	public NutzerContainerInterface getNc() {
		return nc;
	}
	public void speicherAlleContainer(String dateiname, ServiceLocator sl) {
		p.speicher(dateiname, sl);
	}
	
	public ServiceLocator ladeAlleContainer(String dateiname) {
		ServiceLocator sl = p.lade(dateiname);
		return sl;
	}

	
	
	

}
