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

import Tag.TagsContainerInterface;

public class DateienContainer implements DateienContainerInterface, Serializable{
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
		for (Datei element : inputList) {
			if (!results.contains(element)) {
				results.add(element);
			}
		}
		return results;
	}
	
	public void sucheDatei(String suchWort) {
		ArrayList <Datei> tmp = new ArrayList<>();
		for (Datei a:dateienListe) {				//Suche nach Namen
			if (a.getName().contains(suchWort)) {
				tmp.add(a);
			}
		}
		for (Datei b:dateienListe) {				//Suche nach Dateitypen
			if (b.getFormat().contains(suchWort)) {
				tmp.add(b);
			}
		}
		ArrayList <Datei> results = dublikatEntfernen(tmp);
		if (results.isEmpty() == false) {		//Ausgabe
			for (Datei x:results) {
				x.anzeigeDateiDetail();
			}
		}
		else {
			System.out.println("\nEs konnten keine Dateien mit den Namen " + suchWort + " gefunden werden!");
		}
	}
}
