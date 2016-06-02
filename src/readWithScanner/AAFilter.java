package readWithScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class AAFilter {

	// list of desired peptide fragments
	// input: loIP => list of ionized peptides    and    amino acid filter 
	// fun: for every peptide, see if desired aa is in its list, if so, add IP to list of desired ionized peptides loDIP
	// output: list of Ionized Peptides that have the desired amino acid filter
	/*public static DefaultListModel<IonizedPeptide> filterSearch(DefaultListModel<IonizedPeptide> loIP, String s, boolean ha){
		DefaultListModel<IonizedPeptide> temploDIP = new DefaultListModel<>();

		if (ha) {
			for (int i = 0; i < loIP.size(); i++) {
				if (loIP.get(i).containedHA1CAN.contains(s.trim())) {
					temploDIP.addElement(loIP.get(i));
				}
			}
		}else{
			for (int i = 0; i < loIP.size(); i++) {
				if (loIP.get(i).containedDA1CAN.contains(s.trim())) {
					temploDIP.addElement(loIP.get(i));
				}
			}
		}
		return temploDIP;
	}*/
	
	public static DefaultListModel<IonizedPeptide> filterSearch(DefaultListModel<IonizedPeptide> model, List<String> lof, boolean ha){
		DefaultListModel<IonizedPeptide> temploDIP = new DefaultListModel<>();
		
		if (ha) {
			for (int i = 0; i < model.size(); i++) {
				for (int j = 0; j < lof.size(); j++) {
					if (model.get(i).containedHA1CAN.contains(lof.get(j))) {
						if (!temploDIP.contains(model.get(i))) {
							temploDIP.addElement(model.get(i));
						}
					}
				}
			}
		}else{
			for (int i = 0; i < model.size(); i++) {
				for (int j = 0; j < model.get(i).containedDA1CAN.size(); j++) {
					if (model.get(i).containedDA1CAN.contains(lof.get(j))) {
						if (!temploDIP.contains(model.get(i))) {
							temploDIP.addElement(model.get(i));
						}
					}
				}
			}
			
			
		}
		return temploDIP;
		
	}
	
	

	// in: ionized peptide
	// fun: find pf that have labels
	public static void findlabelCand(IonizedPeptide ip, Double minWeight){
		for(int i=0;i<ip.loAA.size();i++){
			Double tempwei = ip.loAA.get(i).molWeight;
			Double range = 0.05;
			if(ip.loAA.get(i).intensity>minWeight){
				if((tempwei>=58.0657-range)&&(tempwei<=120.0963+range)){
					// Glycine
					if((tempwei>=58.0657-range)&&(tempwei<=62.0908+range)){
						// HA1
						if((tempwei>=58.0657-range)&&(tempwei<=58.0657+range)){
							foundHA1Can(ip, ip.loAA.get(i), "Glycine");
						}
						// DA1 
						else if((tempwei>=62.0908-range)&&(tempwei<=62.0908+range)){
							foundDA1Can(ip, ip.loAA.get(i), "Glycine");
						}
					}
					// Alanine
					else if((tempwei>=72.0813-range)&&(tempwei<=76.1064+range)){
						// HA1
						if((tempwei>=72.0813-range)&&(tempwei<=72.0813+range)){
							foundHA1Can(ip, ip.loAA.get(i), "Alanine");
						}
						// DA1 
						else if((tempwei>=76.1064-range)&&(tempwei<=76.1064+range)){
							foundDA1Can(ip, ip.loAA.get(i), "Alanine");
						}
					}
					// Proline, Serine
					else if((tempwei>=84.0813-range)&&(tempwei<=92.1013+range)){
						// HA1 of Proline, Serine
						if((tempwei>=84.0813-range)&&(tempwei<=88.0762+range)){
							// HA1 Proline
							if((tempwei>=84.0813-range)&&(tempwei<=84.0813+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Proline");
							}
							// HA1 Serine
							else if((tempwei>=88.0762-range)&&(tempwei<=88.0762+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Serine");
							}
						}
						// DA1 of Proline, Serine
						else if((tempwei>=88.1064-range)&&(tempwei<=92.1013+range)){
							// DA1 Proline
							if((tempwei>=88.1064-range)&&(tempwei<=88.1064+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Proline");
							}
							// DA1 Serine
							else if((tempwei>=92.1013-range)&&(tempwei<=92.1013+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Serine");
							}
						}
					}
					//Valine, Cysteine, Threonine
					else if((tempwei>=100.1126-range)&&(tempwei<=106.117+range)){
						// HA1 of Valine, Cysteine, Threonine
						if((tempwei>=100.1126-range)&&(tempwei<=102.0919+range)){
							// HA1 Valine
							if((tempwei>=100.1126-range)&&(tempwei<=100.1126+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Valine");
							}
							// HA1 Cysteine
							else if((tempwei>=102.0377-range)&&(tempwei<=102.0377+range)){	
								foundHA1Can(ip, ip.loAA.get(i), "Cysteine");
							}
							// HA1 Threonine
							else if((tempwei>=102.0919-range)&&(tempwei<=102.0919+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Threonine");
							}
						}
						// DA1 of Valine, Cysteine, Threonine
						else if((tempwei>=104.1377-range)&&(tempwei<=106.117+range)){
							// DA1 Valine
							if((tempwei>=104.1377-range)&&(tempwei<=104.1377+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Valine");
							}
							// DA1 Cysteine
							else if((tempwei>=106.0628-range)&&(tempwei<=106.0628+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Cysteine");
							}
							// DA1 Threonine
							else if((tempwei>=106.117-range)&&(tempwei<=106.117+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Threonine");
							}
						}
					}
					// Isoleucine/Leucine, Asparagine, Aspartate
					else if ((tempwei>=114.1283-range)&&(tempwei<=120.0963+range)){
						// HA1 of  Isoleucine/Leucine, Asparagine, Aspartate
						if((tempwei>=114.1283-range)&&(tempwei<=116.0712+range)){
							// HA1 Isoleucine/Leucine
							if((tempwei>=114.1283-range)&&(tempwei<=114.1283+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Isoleucine/Leucine");
							}
							// HA1 Asparagine
							else if((tempwei>=115.0871-range)&&(tempwei<=115.0871+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Asparagine");
							}
							// HA1 Aspartate
							else if((tempwei>=116.0712-range)&&(tempwei<=116.0712+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Aspartate");
							}
						}
						// DA1 of Isoleucine/Leucine, Asparagine, Aspartate
						else if((tempwei>=118.1534-range)&&(tempwei<=120.0963+range)){
							// DA1 Isoleucine/Leucine
							if((tempwei>=118.1534-range)&&(tempwei<=118.1534+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Isoleucine/Leucine");
							}
							// DA1 Asparagine
							else if((tempwei>=119.1122-range)&&(tempwei<=119.1122+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Asparagine");
							}
							// DA1 Aspartate
							else if((tempwei>=120.0963-range)&&(tempwei<=120.0963+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Aspartate");
							}
						}
					}
				}else if((tempwei>=129.1028-range)&&(tempwei<=191.1486+range)){
					// Glutamine, Glutamate, Methionine, Histidine
					if((tempwei>=129.1028-range)&&(tempwei<=142.1282+range)){
						// HA1 of  Glutamine, Glutamate, Methionine
						if((tempwei>=129.1028-range)&&(tempwei<=132.0847+range)){
							// HA1 GLutamine
							if((tempwei>=129.1028-range)&&(tempwei<=129.1028+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Glutamine");
							}
							// HA1 Glutamate
							else if((tempwei>=130.0868-range)&&(tempwei<=130.0868+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Glutamate");
							}
							// HA1 Methionine
							else if((tempwei>=132.0847-range)&&(tempwei<=132.0847+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Methionine");
							}
						}
						// DA1 of Glutamine, Glutamate, Methionine
						else if((tempwei>=133.1279-range)&&(tempwei<=136.1098+range)){
							// DA1 GLutamine
							if((tempwei>=133.1279-range)&&(tempwei<=133.1279+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Glutamine");
							}
							// DA1 Glutamate
							else if((tempwei>=134.1119-range)&&(tempwei<=134.1119+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Glutamate");
							}
							// DA1 Methionine
							else if((tempwei>=136.1098-range)&&(tempwei<=136.1098+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Methionine");
							}
						}
						// HA1 of Histidine
						else if((tempwei>=138.1031-range)&&(tempwei<=138.1031+range)){
							foundHA1Can(ip, ip.loAA.get(i), "Histidine");
						}
						// DA1 of Histidine
						else if((tempwei>=142.1282-range)&&(tempwei<=142.1282+range)){
							foundDA1Can(ip, ip.loAA.get(i), "Histidine");
						}
					}
					// Phenylalanine, Arginine, Lysine, Tyrosine
					else if((tempwei>=148.1126-range)&&(tempwei<=168.1326+range)){
						// Arginine, Lysine
						if((tempwei>=157.1453-range)&&(tempwei<=161.1956+range)){
							// HA1 of Arg
							if((tempwei>=157.1453-range)&&(tempwei<=157.1453+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Arginine");
							}
							// DA1 of Arg
							else if((tempwei>=161.1704-range)&&(tempwei<=161.1704+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Arginine");
							}
							// HA1 of Lysine
							else if((tempwei>=157.1705-range)&&(tempwei<=157.1705+range)){
								foundHA1Can(ip, ip.loAA.get(i), "Lysine");
							}
							// DA1 of Lysine
							else if((tempwei>=161.1956-range)&&(tempwei<=161.1956+range)){
								foundDA1Can(ip, ip.loAA.get(i), "Lysine");
							}
						}
						// HA1 of Phe
						else if((tempwei>=148.1126-range)&&(tempwei<=148.1126+range)){
							foundHA1Can(ip, ip.loAA.get(i), "Phenylalanine");
						}
						// DA1 of Phe
						else if((tempwei>=152.1377-range)&&(tempwei<=152.1377+range)){
							foundDA1Can(ip, ip.loAA.get(i), "Phenylalanine");
						}
						// HA1 of Tyrosine
						else if((tempwei>=164.1075-range)&&(tempwei<=164.1075+range)){
							foundHA1Can(ip, ip.loAA.get(i), "Tyrosine");
						}
						// DA1 of Tyrosine
						else if((tempwei>=168.1326-range)&&(tempwei<=168.1326+range)){
							foundDA1Can(ip, ip.loAA.get(i), "Tyrosine");
						}	
					}
					// Tryptophan
					else if((tempwei>=187.1235-range)&&(tempwei<=191.1486+range)){
						// HA1 
						if((tempwei>=187.1235-range)&&(tempwei<=187.1235+range)){
							foundHA1Can(ip, ip.loAA.get(i), "Tryptophan");
						}
						// DA1 
						else if((tempwei>=191.1486-range)&&(tempwei<=191.1486+range)){
							foundDA1Can(ip, ip.loAA.get(i), "Tryptophan");
						}
					}
				}
			}
		}
	}

	public static PeptideFragment findHeaviestHA1(IonizedPeptide ip){
		PeptideFragment heaviest = ip.loHA1CAN.get(0);
		for(int k=1;k<ip.loHA1CAN.size();k++){
			if(heaviest.intensity<ip.loHA1CAN.get(k).intensity){
				heaviest = ip.loHA1CAN.get(k);
			}
		}
		return heaviest;
	}

	public static PeptideFragment findHeaviestDA1(IonizedPeptide ip){
		PeptideFragment heaviest = ip.loDA1CAN.get(0);
		for(int k=1;k<ip.loDA1CAN.size();k++){
			if(heaviest.intensity<ip.loDA1CAN.get(k).intensity){
				heaviest = ip.loDA1CAN.get(k);
			}
		}
		return heaviest;
	}

	public static void foundHA1Can(IonizedPeptide ip, PeptideFragment pf, String s){
		pf.pepName = s;
		pf.ha = true;
		if(!ip.loHA1CAN.contains(pf)){
			ip.loHA1CAN.add(pf);
		}
		if(!ip.containedHA1CAN.contains(s)){
			ip.containedHA1CAN.add(s);
		}
		ip.heaviestHA1 = findHeaviestHA1(ip);
	}

	public static void foundDA1Can(IonizedPeptide ip, PeptideFragment pf, String s){
		pf.pepName = s;
		pf.da = true;
		if(!ip.loDA1CAN.contains(pf)){
			ip.loDA1CAN.add(pf);
		}
		if(!ip.containedDA1CAN.contains(s)){
			ip.containedDA1CAN.add(s);
		}
		ip.heaviestDA1 = findHeaviestDA1(ip);

	}

	public static DefaultListModel<IonizedPeptide> findSimWeight(IonizedPeptide ip, DefaultListModel<IonizedPeptide> d){
		DefaultListModel<IonizedPeptide> tempmod = new DefaultListModel<IonizedPeptide>();
		for(int i=0; i<d.size();i++){
			if (!d.get(i).equals(ip)) {
				if (d.get(i).attribute1 < (ip.attribute1 + 4)
						&& d.get(i).attribute1 > ip.attribute1) {
					if (ip.heaviestHA1.pepName == d.get(i).heaviestDA1.pepName) {
						tempmod.addElement(d.get(i));
					}
				}
			}
		}

		return tempmod;
	}

	public static void quickSort(DefaultListModel<IonizedPeptide> d, int left, int right){
		int p;
		p = partition(d,left,right);
		if(left<p-1){
			quickSort(d, left, (p-1));
		}
		if(right>p+1){
			quickSort(d, p+1, right);
		}

	}

	// output: position of pivot
	public static int partition(DefaultListModel<IonizedPeptide> d, int left, int right){
		IonizedPeptide pivot = d.get(left);
		int j = left;
		int k = right ;
		while(j<=k){
			while(d.get(++j).attribute1<pivot.attribute1&&(k>=j)){

			}
			while(d.get(--k).attribute1>pivot.attribute1&&(k>=j)){

			}
			if(j<=k){
				IonizedPeptide tempip = d.get(j);
				d.remove(j);
				d.add(j, d.get(k-1)); 
				d.remove(k);
				d.add(k, tempip);
				j++;
				k--;
			}

		}
		if(k==left){
			return k;
		}

		d.remove(left);
		d.add(left, d.get(k-1)); 
		d.remove(k);
		d.add(k, pivot);

		return k;
	}
}
