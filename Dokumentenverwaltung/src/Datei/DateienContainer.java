package Datei;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class DateienContainer implements DateienContainerIF{
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
	
	public Datei hochladeDatei(Path file, String name) {
		try {
			BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
			
			Datei tmp = new Datei(name, attr.creationTime(), attr.lastAccessTime(), attr.lastModifiedTime(), attr.isDirectory(), attr.isRegularFile(), attr.isSymbolicLink(), attr.isOther(), attr.size(), file);
			dateienListe.add(tmp);
			System.out.println(tmp + " wurde gespeichert!");
		}
		catch(IOException e) {
			System.out.println("Datei konnte nicht geladen werden! (Nicht vorhanden oder korrupt)");
		}
		return null;
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
	
	

}
