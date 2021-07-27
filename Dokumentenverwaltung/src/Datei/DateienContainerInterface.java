package Datei;

import java.nio.file.Path;
import java.util.ArrayList;

public interface DateienContainerInterface {
	public boolean hochladeDatei(Path file, String name);
	public ArrayList<Datei> getAlleDateien();
	public void zeigeAlleDateienDetails();
	public void sucheDatei (String name);
	public void sucheDateiTags (ArrayList<String> names);
	public Datei checkFile(String name);
}
