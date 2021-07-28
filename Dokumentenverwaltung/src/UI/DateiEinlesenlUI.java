package UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateiEinlesenlUI {
	private Path file;
	private String name;
	
	
	public void DateiEinlesenUI() throws IOException {
		HilfUI.printBefehleDateiEinlesenUIClear();
	    
	    String eingabe = "";
	    File aktPfad = new File(".");
	    Scanner s = new Scanner(System.in);
	    
	    while(true){
	        System.out.print(aktPfad.getCanonicalPath() + "> ");
	        eingabe = s.nextLine();
	        eingabe = eingabe.trim();
	        if (eingabe.startsWith("dir") ){                
	            dir(aktPfad);
	        }
	        else if (eingabe.startsWith("cd ") ){               
	            aktPfad = cd(eingabe.substring(3,eingabe.length()), aktPfad);
	        }
	        else if (eingabe.startsWith("back")){
	        	this.file = null;
	        	this.name = null;
	        	HilfUI.printBefehleControllerUIClear();
	            break;
	        }
	        else if (eingabe.startsWith("info") ) {
	        	if (HilfUI.isWindows() == true) {
	        		Path pathGet = Paths.get(Paths.get(aktPfad.getCanonicalPath()) + "\\" + eingabe.substring(5));
	        		info(pathGet, eingabe.substring(5));
	        	}
	        	else if (HilfUI.isMac() == true){
	        		Path pathGet = Paths.get(Paths.get(aktPfad.getCanonicalPath()) + "/" + eingabe.substring(5));
	        		info(pathGet, eingabe.substring(5));
	        	}
	        	else {
	        		System.out.println("Betriebssystem wird nicht unterstützt!");
	        	}
				
	        }
	        else if (eingabe.startsWith("save") ) {
	        	if (HilfUI.isWindows() == true) {
	        		String name = eingabe.substring(5);
		        	Path pathGet = Paths.get(Paths.get(aktPfad.getCanonicalPath()) + "\\" + eingabe.substring(5));
		        	this.file = pathGet;
		        	this.name = name;
		        	break;
	        	}
	        	else if (HilfUI.isMac() == true) {
	        		String name = eingabe.substring(5);
		        	Path pathGet = Paths.get(Paths.get(aktPfad.getCanonicalPath()) + "/" + eingabe.substring(5));
		        	this.file = pathGet;
		        	this.name = name;
		        	break;
	        	}
	        	else {
	        		System.out.println("Betriebssystem wird nicht unterstüzt!");
	        	}
	        	
	        }
	        else if (eingabe.startsWith("help")) {
	        	HilfUI.printBefehleDateiEinlesenUIClear();
	        }
	        else{
	            System.out.println("Unbekannter Befehl\n");
	        } 
	    }
	}
	
	public File cd(String eingabe, File aktPfad) throws IOException {
        System.out.println("Aufruf von cd mit dem Parameter "+eingabe);
        if(eingabe.equals("..")){
            File erg = new File(aktPfad.getCanonicalFile().getParent());
            return erg;
        }
        else {
        	if (HilfUI.isWindows()) {
        		File erg = new File(aktPfad.getCanonicalPath() + "\\" + eingabe);
        		if(erg.exists() && erg.isDirectory()){
                    return erg;
                }
                else {
                	System.out.println("Verzeichnis existiert nicht!");
                	return aktPfad;
                }
        	}
        	else if (HilfUI.isMac()) {
        		File erg = new File(aktPfad.getCanonicalPath() + "/" + eingabe);
        		if(erg.exists() && erg.isDirectory()){
                    return erg;
                }
                else {
                	System.out.println("Verzeichnis existiert nicht!");
                	return aktPfad;
                }
        	}
        }
		return aktPfad;
    }
	
	public void dir(File aktPfad) throws IOException {
        
		System.out.println("Aufruf von dir");
        
        for (final File file : aktPfad.listFiles()) {
        	Path fileNew = file.toPath();
        	BasicFileAttributes attr = Files.readAttributes(fileNew, BasicFileAttributes.class);
        	if (file.isDirectory()) {
            	HilfUI.printFileTime(attr.lastModifiedTime());
            	System.out.print(attr.size() + "\t<DIR>\t");
            	System.out.print(file.getName() + "\n");
            } 
        	else {
        		HilfUI.printFileTime(attr.lastModifiedTime());
        		System.out.print(attr.size() + "\t\t");
            	System.out.print(file.getName() + "\n");
            }
        }
	}
	
	public void info(Path file, String eingabe) {
		System.out.println("Aufruf von info mit dem Parameter " + eingabe);
		try {
			System.out.println("Name der Datei: \t\t" + file.getFileName());

			BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

			System.out.println("Erstellungsdatum: \t\t" + attr.creationTime());
			System.out.println("Letzter Zugriff: \t\t" + attr.lastAccessTime());
			System.out.println("Zuletzt bearbeitet: \t" + attr.lastModifiedTime());
			System.out.println("Größe: \t\t\t" + attr.size() + " Bytes");
			System.out.println("Pfad der Datei: \t\t" + file + "\n");
			if (attr.isDirectory() == true) {
				System.out.println("Es handelt sich um ein Ordner!");
			}
			if (attr.isRegularFile() == true) {
				System.out.println("Es handelt sich um eine regulär lesbare Datei!");
			}
			if (attr.isSymbolicLink() == true) {
				System.out.println("Es handelt sich um einen symbolischen Link!");
			}
			if (attr.isOther() == true) {
				System.out.println("Es handelt sich um etwas anderes als einen Ordner, lesbare Datei, symbolischer Link!");
			}
		}
		catch(IOException e) {
			System.out.println("Datei konnte nicht geladen werden! (Stellen Sie sicher, dass Sie den gesamten Namen inkl. Dateierweiterung eingegeben haben)\nFehlercode: " + e);
		}
	}
	
	//public static void save(Path file, String name) {
	//	System.out.println("Aufruf von save mit dem Parameter " + name);
	//	ServiceLocator.getInstance().getDateienContainer().hochladeDatei(file, name);
	//}
	
	public Path getPath() {
		return file;
	}
	
	public String getName() {
		return name;
	}

}
