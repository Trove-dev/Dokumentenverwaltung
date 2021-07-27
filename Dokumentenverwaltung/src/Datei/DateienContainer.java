package Datei;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Tag.Tag;
import Tag.TagsContainerInterface;
import Verlinkung.VerknuepfungVonDateien;

public class DateienContainer implements DateienContainerInterface, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6592664557941871449L;
	private static DateienContainer uniqueInstance = null;
	private ArrayList<Datei> dateienListe = new ArrayList<>();
	
	private DateienContainer() {
	}
	public static DateienContainer getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DateienContainer();
		}
		return uniqueInstance;
	}
	
	public boolean hochladeDatei(Path file, String name) {
		try {
			BasicFileAttributes a = Files.readAttributes(file, BasicFileAttributes.class);
			FileOwnerAttributeView b = Files.getFileAttributeView(file, FileOwnerAttributeView.class);
			
			if (a.isDirectory() == false) {
				String extension = "";
				int t = name.lastIndexOf(".");
				extension = name.substring(t);
				
				if (dublikatFinden(file) == true) {
					System.out.println("\nDiese Datei wurde bereits hinzugefügt!");
					return false;
				}
				else {
					Datei tmp = new Datei(name, b.getOwner(), a.creationTime(), a.lastModifiedTime(), extension ,a.size(), file);
					dateienListe.add(tmp);
					return true;
				}
			}
			else {
				System.out.println("\nEs handelt sich bei " + name + " um ein Ordner!");
				return false;
			}
		}
		catch(IOException e) {
			System.out.println("\nDatei konnte nicht geladen werden! (Nicht vorhanden oder korrupt)");
			return false;
		}
	}
	
	public void zeigeAlleDateien() {
		for(Datei a:dateienListe) {
			System.out.println(a);
		}
	}
	
	public void zeigeAlleDateienDetails() {
		for(Datei a:dateienListe) {
			a.anzeigeDateiDetail();
		}
		System.out.println();
	}
	
	public ArrayList<Datei> getAlleDateien(){
		if (dateienListe.isEmpty() == true) {
			return null;
		}
		else {
			return dateienListe;
		}
	}
	
	private boolean dublikatFinden(Path file) {
		for (Datei a:dateienListe) {
			if (a.getDateiPfad().equals(file.toString())) {
				return true;
			}
		}
		return false;
	}
	
	
	private ArrayList<Datei> dublikatEntfernen(ArrayList<Datei> inputList) {	//Dublikate entfernen
		ArrayList <Datei> results = new ArrayList<>();
		for (Datei a : inputList) {
			if (!results.contains(a)) {
				results.add(a);
			}
		}
		return results;
	}
	
	@Override
	public void sucheDateiTags(ArrayList<String> tagsNames) {                
		ArrayList <Datei> tmp = new ArrayList<>();		
		for (Datei d:dateienListe) {
			boolean[]gefunden = new boolean[tagsNames.size()];
			boolean isTrue = true;
			if(d.getTags() != null) {
				for(Tag t:d.getTags()) {
					for(int i = 0; i < tagsNames.size(); i++) {
						if(t.getKey().compareTo(tagsNames.get(i)) == 0) {
							gefunden[i] = true;
							break;
						}
					}
				}
			}
			for(boolean b:gefunden) {
				if(b == false) {
					isTrue = false;
					break;
				}
			}
			if(isTrue) tmp.add(d);
		}
		
		if(!tmp.isEmpty()) {
			System.out.println("Diese Dateien wurden gefunden : \n");
			for(Datei dat:tmp) {
				dat.anzeigeDateiDetail();
			}
		}else System.out.println("Es wurde keine Dateien gefunden\n");
	}
	
	public Datei checkFile(String name) {
		for (Datei a:dateienListe) {
			if(a.getName().compareTo(name) == 0) {
				return a;
			}
		}
		return null;
	}
	public void sucheDatei(String suchWort) {
		ArrayList <Datei> tmp = new ArrayList<>();
		for (Datei a:dateienListe) {				//Suche nach Namen
			if (a.getName().contains(suchWort)) {
				tmp.add(a);
			}
		}
		for (Datei b:dateienListe) {
			if(b.getTags() != null) {
				for (Tag t:b.getTags()) {
					if (t.getKey().contains(suchWort)) {	//Suche nach Tag
						tmp.add(b);
					}
				}
			}
		}
		for (Datei c:dateienListe) {				//Suche nach Dateitypen
			if (c.getFormat().contains(suchWort)) {
				tmp.add(c);
			}
		}
		for(Datei d:dateienListe) {                //Suche nach verlinkten Dateien
			HashSet<Datei> hs = d.getVerknuepfung();
			if(hs != null && !hs.isEmpty()) {
				for(Datei e:hs)
					if(e.getName().contains(suchWort)) {
						tmp.add(d);
					}
			}
		}
		for(Datei f:dateienListe) {
			if(f.getKommentar() != null && f.getKommentar().contains(suchWort))
				tmp.add(f);
		}
		ArrayList <Datei> results = dublikatEntfernen(tmp);
		if (results.isEmpty() == false) {		//Ausgabe
			for (Datei x:results) {
				x.anzeigeDateiDetail();
			}
		}
		else {
			System.out.println("\nEs konnten keine Dateien mit dem Suchwort " + suchWort + " gefunden werden!");
		}
	}
}
