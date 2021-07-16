package Nutzer;

public enum Rechte {
	admin, 
	user;	
	
	private Rechte() {
		
	}
	
	public Rechte getRechte(int num) {
		switch(num) {
		
			case 0: return admin;
			case 1: return user;
			default: return null;
		}
	} 
}
