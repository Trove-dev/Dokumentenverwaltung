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
	    
	    String input = "";
	    File actPath = new File(".");
	    Scanner sc = new Scanner(System.in);
	    
	    while(true){
	        System.out.print(actPath.getCanonicalPath() + "> ");
	        input = sc.nextLine();
	        input = input.trim();
	        if( input.startsWith("dir") ){                
	            dir(actPath);
	        }
	        else if( input.startsWith("cd ") ){               
	            actPath = cd(input.substring(3,input.length()), actPath);
	        }
	        else if( input.startsWith("back")){
	        	this.file = null;
	        	this.name = null;
	        	HilfUI.printBefehleControllerUIClear();
	            break;
	        }
	        else if (input.startsWith("info") ) {
	        	if (HilfUI.isWindows() == true) {
	        		Path pathGet = Paths.get(Paths.get(actPath.getCanonicalPath()) + "\\" + input.substring(5));
	        		info(pathGet, input.substring(5));
	        	}
	        	else if (HilfUI.isMac() == true){
	        		Path pathGet = Paths.get(Paths.get(actPath.getCanonicalPath()) + "/" + input.substring(5));
	        		info(pathGet, input.substring(5));
	        	}
	        	else {
	        		System.out.println("Betriebssystem wird nicht unterstützt!");
	        	}
				
	        }
	        else if (input.startsWith("save") ) {
	        	if (HilfUI.isWindows() == true) {
	        		String name = input.substring(5);
		        	Path pathGet = Paths.get(Paths.get(actPath.getCanonicalPath()) + "\\" + input.substring(5));
		        	this.file = pathGet;
		        	this.name = name;
		        	break;
	        	}
	        	else if (HilfUI.isMac() == true) {
	        		String name = input.substring(5);
		        	Path pathGet = Paths.get(Paths.get(actPath.getCanonicalPath()) + "/" + input.substring(5));
		        	this.file = pathGet;
		        	this.name = name;
		        	break;
	        	}
	        	else {
	        		System.out.println("Betriebssystem wird nicht unterstÃ¼tzt!");
	        	}
	        	
	        }
	        else if (input.startsWith("help")) {
	        	HilfUI.printBefehleDateiEinlesenUIClear();
	        }
	        else{
	            System.out.println("Unbekannter Befehl\n");
	        } 
	    }
	}
	
	public File cd(String neuVZ, File actPath) throws IOException {
        System.out.println("Aufruf von cd mit dem Parameter "+neuVZ);
        if(neuVZ.equals("..")){
            File neu = new File(actPath.getCanonicalFile().getParent());
            return neu;
        }
        File neu = new File(actPath.getCanonicalPath()+"/"+neuVZ);
        if(neu.exists()&&neu.isDirectory()){
            return neu;
        }
        System.out.println("Unbekanntes Verzeichnis");
        return actPath;
    }
	
	public void dir(File actPath) {
        System.out.println("Aufruf von dir");
        File[] liste = actPath.listFiles();
        int files = 0;
        int dirs = 0;
        DateFormat dateformat = DateFormat.getDateInstance();
        DateFormat timeformat = DateFormat.getTimeInstance();
        for(int i=0; i<liste.length; i++){
            System.out.print(dateformat.format(new Date(liste[i].lastModified()))+"\t");
            System.out.print(timeformat.format(new Date(liste[i].lastModified()))+"\t");
            if(liste[i].isFile()){
                System.out.print( liste[i].length() + "\t\t" );
                System.out.println( liste[i].getName() );
                files++;
            } else{
                System.out.println( "<DIR>\t\t" + liste[i].getName() );
                dirs++;
            }
        }
        String dateiString = (files==1) ? "Datei" : "Dateien";
        String verzString = (dirs==1) ? "Verzeichnis" : "Verzeichnisse";
        System.out.println( dirs + " " + verzString + ", " + files + " " + dateiString); 
    }
	
	public void info(Path file, String eingabe) {
		System.out.println("Aufruf von info mit dem Parameter " + eingabe);
		try {
			System.out.println("Name der Datei: \t\t" + file.getFileName());

			BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

			System.out.println("Erstellungsdatum: \t\t" + attr.creationTime());
			System.out.println("Letzter Zugriff: \t\t" + attr.lastAccessTime());
			System.out.println("Zuletzt bearbeitet: \t\t" + attr.lastModifiedTime());
			
			System.out.println("Größe: \t\t\t\t" + attr.size() + " Bytes");
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
