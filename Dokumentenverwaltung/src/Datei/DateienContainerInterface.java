package Datei;

import java.nio.file.Path;
import java.util.ArrayList;

public interface DateienContainerInterface {
	public Datei hochladeDatei(Path file, String name);
	public ArrayList<Datei> getAlleDateien();
	public void zeigeAlleDateienDetails();

}
