package Nutzer;

/**
 * spezielle Enum Klasse, wessen Konstanten nicht ver�ndert werden k�nnen
 */
public enum Rechte {
	admin, 
	user;	
	
	private Rechte() {
	}
	
	/**
	 * Recht bekommen
	 * 
	 * @param num Eingabe einer Zahl, die auf ein Recht passt
	 * @return es wird das Recht zur�ckgeliefert
	 */
	public Rechte getRechte(int num) {
		switch(num) {
		
			case 0: return admin;
			case 1: return user;
			default: return null;
		}
	} 
}
