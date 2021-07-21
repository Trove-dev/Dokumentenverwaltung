package Nutzer;

import java.util.ArrayList;

public interface NutzerContainerInterface {
	public Nutzer addNeuenNutzer(String name, Rechte rechte, String nameVollstaendig);
	public ArrayList<Nutzer> getListeNutzer();
	public Nutzer sucheNutzer(String name);
	public void printNutzerList();
	public void loescheNutzer(String name);
}
