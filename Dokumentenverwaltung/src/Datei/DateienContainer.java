package Datei;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class DateienContainer implements DateienContainerInterface{
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
	
	public static Datei hochladeDatei(Path file, String name) {
		try {
			BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
			
			Datei dokument = new Datei(name, attr.creationTime(), attr.lastAccessTime(), attr.lastModifiedTime(), attr.isDirectory(), attr.isRegularFile(), attr.isSymbolicLink(), attr.isOther(), attr.size(), file);
			System.out.println(dokument + " wurde gespeichert!");
		}
		catch(IOException e) {
			System.out.println("Datei konnte nicht geladen werden! (Nicht vorhanden oder korrupt)");
		}
		
		
		
		return null;
	}

}
