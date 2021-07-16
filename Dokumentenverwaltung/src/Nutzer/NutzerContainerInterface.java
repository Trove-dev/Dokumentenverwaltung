package Nutzer;

import java.util.ArrayList;

public interface NutzerContainerInterface {
	public boolean addNeuenNutzer(String name, Rechte rechte, String nameVollstaendig);
	public ArrayList<Nutzer> getListeNutzer();
	public Nutzer sucheNutzer(String name);
}
