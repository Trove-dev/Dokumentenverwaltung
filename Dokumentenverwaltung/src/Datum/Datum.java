package Datum;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Datum {
	
	private int jahr;
	private int monat;
	private int tag;
		
	public Datum(int jahr, int monat, int tag) {
		
		GregorianCalendar cal = new GregorianCalendar();
		this.tag = cal.get(Calendar.DATE);
		this.monat = cal.get(Calendar.MONTH) + 1;
		this.jahr = cal.get(Calendar.YEAR);
	}

	@Override
	public String toString() {
		return this.tag + "." + this.monat + "." + this.jahr;
	}

	public void aendereDatum(Datum date) {
		
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public int getMonat() {
		return monat;
	}

	public void setMonat(int monat) {
		this.monat = monat;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
	
	
}
