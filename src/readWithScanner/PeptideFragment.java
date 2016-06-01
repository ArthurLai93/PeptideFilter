package readWithScanner;

public class PeptideFragment {
	
	Double  molWeight; // H-a1
	Double intensity;
	String pepName;
	boolean ha = false;
	boolean da = false;
	
	public PeptideFragment(Double att1, Double att2, String n){
		
		molWeight = att1;
		intensity = att2;
		pepName = n;
	}

}
