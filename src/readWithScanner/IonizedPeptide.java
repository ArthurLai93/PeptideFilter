package readWithScanner;

import java.util.ArrayList;
import java.util.List;

public class IonizedPeptide {
	
	Double attribute1; // farthest left
	String intensity; // middle one
	String attribute3; // farthest right
	List<PeptideFragment> loAA = new ArrayList<PeptideFragment>();
	List<PeptideFragment> loHA1CAN = new ArrayList<PeptideFragment>();
	List<String> containedHA1CAN = new ArrayList<String>();
	List<PeptideFragment> loDA1CAN = new ArrayList<PeptideFragment>();
	List<String> containedDA1CAN = new ArrayList<String>();
	int numUnknowns;
	String name; // "Protein" + Count
	PeptideFragment heaviestHA1;
	PeptideFragment heaviestDA1;


	public IonizedPeptide(Double att1, String att2, String att3, String n){
		
		attribute1 = att1;
		intensity = att2;
		attribute3 = att3;
		name = n;
		
		
	}
	
	
}
