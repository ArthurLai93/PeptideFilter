package proteinSynthesizer;

public class Synthesizer {

	// What does the Synthesizer do?
	// When a list of peptide fragments (mini proteins) and a protein primer is handed to the synthesizer, it creates the most optimal protein
	// that can be made from the provided fragments (from the list) and the primer
	// The synthesizer first finds the most optimal fragment(s) from the list and attaches to the primer. The process is repeated for the 
	// newly attached fragment
	// Optimality is defined by the maximum number of optimal bonds between two fragments
	// An optimal bond is defined by maximizing the score of matching amino acids. A amino acid match is defined by a chart 
	// that characterizes the ability of an amino acid to successfully bind all other amino acids as numeric values. 
	// (e.g Lysine - Proline => 5)
	
	
	
}
