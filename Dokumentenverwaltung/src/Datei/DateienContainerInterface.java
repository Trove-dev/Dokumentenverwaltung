package Datei;

import java.nio.file.Path;
import java.util.ArrayList;

import Tag.TagsContainerInterface;

public interface DateienContainerInterface {
	public boolean hochladeDatei(Path file, String name);
	public ArrayList<Datei> getAlleDateien();
	public void zeigeAlleDateienDetails();
	public void sucheDatei (String name);
	public void sucheDateiTags (ArrayList<String> names);
}
