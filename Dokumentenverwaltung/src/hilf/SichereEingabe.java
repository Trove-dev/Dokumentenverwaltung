package hilf;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import Nutzer.NutzerContainerInterface;
import Nutzer.Rechte;

public class SichereEingabe {
	
	static char [] symboleUserName_NameVoll = {'!', '§', '%', '&', '/', '=', '?', '`', '#', '*', '+', '-', '<', '>', '|', '{', '}', '[', ']', '@', '.'};
	static char [] symboleUserName = {'(', ')', '´', '-', '|', '\'', ' '};
	
	public static boolean checkUebereinstimmung(String checkedString, char[]array) {
		for(char s:array) {
			if(checkedString.indexOf(Character.toString(s))!=-1) return false;
		}
		return true;
	}
	
	public static String checkName(NutzerContainerInterface nc) {
		
		System.out.println("Username muss sich aus mindestens 4 Symbole ohne Leerzeichen setzen und muss nicht enthalten :");
		
		for(char a:symboleUserName_NameVoll) System.out.print(a + " ");
		for(char b:symboleUserName) System.out.print(b + " ");
		System.out.print("\nIhr Username: ");			
		String inputName = "";	
		Scanner input = new Scanner(System.in);
		boolean erfolg = false;
		while(erfolg != true) {
			inputName = input.next();
			if(inputName.compareTo("exit") == 0) {
				System.out.println("Sie verlassen das Programm");
				input.close();
				System.exit(0);
			}
			if(nc.sucheNutzer(inputName) != null) {
				System.err.print("Dieser Username ist belegt. Geben Sie anderen Name ein: ");
				continue;
			}
			
			if(!checkUebereinstimmung(inputName, symboleUserName_NameVoll) 
					|| !checkUebereinstimmung(inputName, symboleUserName)) {
				System.err.print("Username enthält falsche Symbole. Geben Sie den nochmal: ");
				continue;
			}
			
			if(inputName.length() < 4) {
				System.err.println("Username muss mindestens 4 Symbole enthalten");
				continue;
			}				
			erfolg = true;
		}
		return inputName;
	}
		
	public static Rechte checkRechte() {
		Scanner input = new Scanner(System.in);
		System.out.println("Wählen Sie das Recht aus: ");
		Rechte []rechte = Rechte.values();		
		for(Rechte r:rechte) System.out.println(r.ordinal() + " "+ r);				
		int inputRechte;		
		Rechte r = null;		
		while(r == null) {
			while(!input.hasNextInt()) {
				System.err.println("Der falsche Befehl. Geben Sie bitte die Zahl: ");
				input.next();
			}
			inputRechte = input.nextInt();
			switch(inputRechte) {			
				case 0: {
					r = Rechte.admin;
					System.out.println("Das Recht :  admin");
					break;
				}
				case 1: {
					r = Rechte.user;
					System.out.println("Das Recht :  user");
					break;
				}
				default: {
					System.err.println("Der falsche Befehl. Wählen Sie bitte das Recht aus: ");
					continue;
				}
			}
		}
		return r;
	}
	 
	public static String checkVollstaendigenName() {  		
		System.out.print("Ihr vollständiger Name: ");
		boolean erfolg = false;
		String inputnameVollstandig = "";		
		while(erfolg != true) {
			inputnameVollstandig = SichereEingabe.liesCharacters();			
			if(!SichereEingabe.checkUebereinstimmung(inputnameVollstandig, symboleUserName_NameVoll)){
				System.err.print("Geben Sie bitte Ihren richtigen Namen ein: ");	
				continue;
			}
			if(inputnameVollstandig.length() <2) {
				System.err.print("Geben Sie bitte Ihren richtigen Namen ein: ");	
				continue;
			}
			erfolg = true;
		}	
		return inputnameVollstandig;
	}
	 
	public static String liesCharacters(){
		String eingabe = "";
		while(true) {
			Scanner input = new Scanner(System.in);
			eingabe = input.nextLine();
			Scanner test = new Scanner(eingabe);		
			if(test.hasNextInt()) {
				EingabeException("String", "int");
				continue;
			}
			if(test.hasNextDouble()) {
			    EingabeException("String", "double");
			    continue;
			}
			test.close();
			break;
		}		
		return eingabe;
	}
	
	public static void EingabeException(String erwartet, String eingegeben) {
		System.err.println("Fehlerhafte Eingabe: Erwartet wurde "+erwartet+" und eingegeben wurde "+eingegeben+"!");
	}
}
