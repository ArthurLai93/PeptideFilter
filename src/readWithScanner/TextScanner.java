package readWithScanner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextScanner {

	public static Scanner getFile(String fn) throws IOException{

		Path path = Paths.get(fn);
		Scanner scanner = new Scanner(path);

		return scanner;
	}

	// Input: text file containing MS data format 
	//		### ## #     <--- IP#1   Mass Intensity prop
	//		### ##       <--- Peptide Fragments of above IP
	//		### ##		 <--- Peptide Fragments of above IP
	//		(Space)		<-- indicates that the IP has ended
	//		### ## #     <--- IP#2   Mass Intensity prop
	//		### ##       <--- Peptide Fragments of above IP
	//		### ##		 <--- Peptide Fragments of above IP

	//
	// file parser based on above format
	public static List<List<String>> parseFile(String s) throws IOException{
		Path path = Paths.get(s);
		Scanner scanner = new Scanner(path);
		List<List<String>> loCandidates = new ArrayList<List<String>>();
		String tempstr;
		while(scanner.hasNextLine()){
			tempstr = scanner.nextLine();
			List<String> temp = Arrays.asList(tempstr.split("\\s+"));
			
			loCandidates.add(temp);	
		}
		System.out.println(loCandidates.size());
		scanner.close();
		return loCandidates;
	}

	// los: list of all Ionizied Peptides and peptide fragments based on parsed data, each list object corresponds to a line of the text file parsed
	// lop: inputed list of IP within sample (should start off as empty), at the end, lop will contain all the ionized peptides organized
	// into IP objects that contains a list of peptide fragments as a property
	public static void seperateToPepAndFrags(List<List<String>> los, List<IonizedPeptide> lop){
		int i = 0;
		int nOfIPInTot = 0;  
		while(i<los.size()){
			if(los.get(i).size()==3){
				nOfIPInTot++;
				String pepname = "Peptide " + nOfIPInTot;
				IonizedPeptide tempIP = new IonizedPeptide(Double.parseDouble(los.get(i).get(0)),los.get(i).get(1),los.get(i).get(2), pepname);
				lop.add(tempIP); 
				boolean sameProtein = true;
				i++;
				while(sameProtein&&i<los.size()){
					if(los.get(i).size()==2){
						i = initPep(los, i, tempIP);
					}else{
						
						sameProtein = false;
					}
				}
			}else i++;
		}
	}

	// initializes pf as unknown
	static int initPep(List<List<String>> los, int i, IonizedPeptide tempIonPep) {

		tempIonPep.numUnknowns++;
		PeptideFragment tempPep = new PeptideFragment(Double.parseDouble(los.get(i).get(0)),Double.parseDouble(los.get(i).get(1)), "Unknown");
		tempIonPep.loAA.add(tempPep);
		i++;

		return i;
	}










}
