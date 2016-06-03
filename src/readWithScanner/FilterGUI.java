package readWithScanner;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.JRadioButton;

public class FilterGUI extends JFrame {

	private JPanel contentPane;
	// Scanner to parse file
	Scanner userInput = new Scanner(System.in);
	String inputFileName;
	final DefaultListModel<IonizedPeptide> hamodelFIN = new DefaultListModel<>();
	final DefaultListModel<IonizedPeptide> damodelFIN = new DefaultListModel<>();
	DefaultListModel<IonizedPeptide> hamodel = new DefaultListModel<>();
	DefaultListModel<IonizedPeptide> damodel = new DefaultListModel<>();
	DefaultListModel<IonizedPeptide> excludedDA = new DefaultListModel<>();
	DefaultListModel<PeptideFragment> hapfmodel = new DefaultListModel<>();
	DefaultListModel<PeptideFragment> dapfmodel = new DefaultListModel<>();
	DefaultListModel<String> hamodelS = new DefaultListModel<>();
	DefaultListModel<String> hapfmodelS = new DefaultListModel<>();
	DefaultListModel<String> damodelS = new DefaultListModel<>();
	DefaultListModel<String> dapfmodelS = new DefaultListModel<>();
	// describes currently selected object w/i list
	String currSel;
	int currIndIP, currIndPF;
	JList<String> jlistHA1IPs = new JList<>(hamodelS);
	JList<String> jlistHA1PF = new JList<>(hapfmodelS);
	JList<String> jlistDA1IP= new JList<>(damodelS);
	JList<String> jlistDA1PF = new JList<String>(dapfmodelS);
	String hapfINFO, haINFO, dapfINFO, daINFO;
	JTextArea haIPModelTEXT = new JTextArea();
	JTextArea haPFModelTEXT1 = new JTextArea();
	JTextArea daIPModelTEXT = new JTextArea();
	JTextArea daPFModelTEXT = new JTextArea();
	JTextArea logText = new JTextArea();
	List<String> lof = new ArrayList<String>();
	JCheckBox chckbxAlanine = new JCheckBox("Alanine");
	JCheckBox chckbxArginine = new JCheckBox("Arginine");
	JCheckBox chckbxAsparagine = new JCheckBox("Asparagine");
	JCheckBox chckbxAspartate = new JCheckBox("Aspartate");
	JCheckBox chckbxCysteine = new JCheckBox("Cysteine");
	JCheckBox chckbxGlutamate = new JCheckBox("Glutamate");
	JCheckBox chckbxGlutamine = new JCheckBox("Glutamine");
	JCheckBox chckbxGlycine = new JCheckBox("Glycine");
	JCheckBox chckbxHistidine = new JCheckBox("Histidine");
	JCheckBox chckbxIsoleucine = new JCheckBox("Isoleucine/Leucine");
	JCheckBox chckbxLysine = new JCheckBox("Lysine");
	JCheckBox chckbxMethionine = new JCheckBox("Methionine");
	JCheckBox chckbxPhenylalanine = new JCheckBox("Phenylalanine");
	JCheckBox chckbxProline = new JCheckBox("Proline");
	JCheckBox chckbxSerine = new JCheckBox("Serine");
	JCheckBox chckbxThreonine = new JCheckBox("Threonine");
	JCheckBox chckbxTryptophan = new JCheckBox("Tryptophan");
	JCheckBox chckbxTyrosine = new JCheckBox("Tyrosine");
	JCheckBox chckbxValine = new JCheckBox("Valine");
	List<JCheckBox> loALLCHK = new ArrayList<JCheckBox>(); 
	JToggleButton tglbtnDaSearch = new JToggleButton("DA-1 Search");
	JToggleButton tglbtnAaSearch = new JToggleButton("HA-1 Search");
	JToggleButton btnWeightSearch = new JToggleButton("Weight Search");
	JTextField otherIntensity = new JTextField();
	private final JRadioButton minIntensity80 = new JRadioButton("80 (default)");
	private final JRadioButton minIntensity200 = new JRadioButton("200");
	private final JRadioButton minIntensityOther = new JRadioButton("");
	Double minIntensity = 80.0;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilterGUI frame = new FilterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// fun: setfilter function for checkboxes
	// called when a checkbox is clicked
	public void setFilter(JCheckBox j){
		
		String tempstr = j.getText().trim();
		if(j.isSelected()==false){
			lof.remove(tempstr);
		}else{
			if(!lof.contains(tempstr)){
				lof.add(tempstr);
			}
		}
	}
	
	public void resIPmodel(boolean ha){
		if(ha){
			hamodel.clear();
			haIPModelTEXT.setText("");
			for(int j=0;j<hamodelFIN.size();j++){
				hamodel.addElement(hamodelFIN.get(j));
			}
			refIPmodelS(true);
		}else{
			damodel.clear();
			daIPModelTEXT.setText(" ");
			for(int j=0;j<damodelFIN.size();j++){
				damodel.addElement(damodelFIN.get(j));
			}
			refIPmodelS(false);
		}
	}
	
	public void refIPmodelS(boolean ha){
		if(ha){
			hamodelS.clear();
			for(int i=0;i<hamodel.size();i++){
				hamodelS.addElement(hamodel.get(i).name);
			}
		}else{
			damodelS.clear();
			for(int i=0;i<damodel.size();i++){
				damodelS.addElement(damodel.get(i).name);
			}
		}
	}
	
	public void refPFmodel(boolean ha){
		if(ha){
			hapfmodel.clear();
			hapfmodelS.clear();
			haPFModelTEXT1.setText("");
		}else{
			dapfmodel.clear();
			dapfmodelS.clear();
			daPFModelTEXT.setText("");
		}
	}

	
	
	public void onlyOneTgl(JToggleButton j){
		if(j.equals(tglbtnAaSearch)){
			tglbtnDaSearch.setSelected(false);
		}else if(j.equals(tglbtnDaSearch)){
			tglbtnAaSearch.setSelected(false);
		}
		
	}
	
	public void saveFileChooser(final JPanel j){
		
		/*String filename = File.separator+".txt";
		File file = new File(filename);*/

		try {
			JFileChooser fileChooser = new JFileChooser();

			// pop up an "Save File" file chooser dialog
			int userSelection = fileChooser.showSaveDialog(j);
			System.out.println("File to save: " + fileChooser.getSelectedFile());
			if(userSelection==JFileChooser.APPROVE_OPTION){
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				WriteToFile.saveToFile(hamodel, file);
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String openFileChooser(final JPanel j){
		
		String fp=null;
		try {
			JFileChooser fileChooser = new JFileChooser();
			int userSelection = fileChooser.showOpenDialog(j);
			System.out.println("File to open: " + fileChooser.getSelectedFile());
			if(userSelection==JFileChooser.APPROVE_OPTION){
				fp = fileChooser.getSelectedFile().getAbsolutePath();
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fp;
	}

	
	/**
	 * Create the fcframe.
	 * @throws IOException 
	 */
	public FilterGUI() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 1016, 556);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loALLCHK.add(chckbxAlanine);
		loALLCHK.add(chckbxArginine);
		loALLCHK.add(chckbxAsparagine);
		loALLCHK.add(chckbxAspartate);
		loALLCHK.add(chckbxCysteine);
		loALLCHK.add(chckbxGlutamate);
		loALLCHK.add(chckbxGlutamine);
		loALLCHK.add(chckbxGlycine);
		loALLCHK.add(chckbxHistidine);
		loALLCHK.add(chckbxIsoleucine);
		loALLCHK.add(chckbxLysine);
		loALLCHK.add(chckbxMethionine);
		loALLCHK.add(chckbxPhenylalanine);
		loALLCHK.add(chckbxProline);
		loALLCHK.add(chckbxSerine);
		loALLCHK.add(chckbxThreonine);
		loALLCHK.add(chckbxTryptophan);
		loALLCHK.add(chckbxTyrosine);
		loALLCHK.add(chckbxValine);
		
		chckbxAlanine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxAlanine);
				logText.setText(chckbxAlanine.getText());
			}
			});
		chckbxAlanine.setBounds(211, 104, 102, 23);
		loALLCHK.add(chckbxAlanine);
		contentPane.add(chckbxAlanine);
		
		chckbxArginine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxArginine);
				logText.setText(chckbxArginine.getText());
			}
			});
		chckbxArginine.setBounds(3, 130, 102, 23);
		loALLCHK.add(chckbxArginine);
		contentPane.add(chckbxArginine);
		
		chckbxAsparagine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxAsparagine);
				logText.setText(chckbxAsparagine.getText());
			}
			});
		chckbxAsparagine.setBounds(211, 130, 102, 23);
		loALLCHK.add(chckbxAsparagine);
		contentPane.add(chckbxAsparagine);
		
		chckbxAspartate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxAspartate);
				logText.setText(chckbxAspartate.getText());
			}
			});
		chckbxAspartate.setBounds(3, 234, 102, 23);
		loALLCHK.add(chckbxAspartate);
		contentPane.add(chckbxAspartate);
		
		chckbxCysteine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxCysteine);
				logText.setText(chckbxCysteine.getText());
			}
			});
		chckbxCysteine.setBounds(107, 78, 102, 23);
		loALLCHK.add(chckbxCysteine);
		contentPane.add(chckbxCysteine);
		
		chckbxGlutamate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxGlutamate);
				logText.setText(chckbxGlutamate.getText());
			}
			});
		chckbxGlutamate.setBounds(211, 78, 102, 23);
		loALLCHK.add(chckbxGlutamate);
		contentPane.add(chckbxGlutamate);
		
		chckbxGlutamine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxGlutamine);
				logText.setText(chckbxGlutamine.getText());
			}
			});
		chckbxGlutamine.setBounds(3, 208, 102, 23);
		loALLCHK.add(chckbxGlutamine);
		contentPane.add(chckbxGlutamine);
		
		chckbxGlycine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxGlycine);
				logText.setText(chckbxGlycine.getText());
			}
			});
		chckbxGlycine.setBounds(3, 104, 102, 23);
		loALLCHK.add(chckbxGlycine);
		contentPane.add(chckbxGlycine);
		
		
		chckbxHistidine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxHistidine);
				logText.setText(chckbxHistidine.getText());
			}
			});
		chckbxHistidine.setBounds(3, 78, 102, 23);
		loALLCHK.add(chckbxHistidine);
		contentPane.add(chckbxHistidine);
		
		chckbxIsoleucine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxIsoleucine);
				logText.setText(chckbxIsoleucine.getText());
			}
			});
		chckbxIsoleucine.setBounds(107, 208, 145, 23);
		loALLCHK.add(chckbxIsoleucine);
		contentPane.add(chckbxIsoleucine);
		
		chckbxLysine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxLysine);
				logText.setText(chckbxLysine.getText());
			}
			});
		chckbxLysine.setBounds(3, 156, 102, 23);
		loALLCHK.add(chckbxLysine);
		contentPane.add(chckbxLysine);
		
		chckbxMethionine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxMethionine);
				logText.setText(chckbxMethionine.getText());
			}
			});
		chckbxMethionine.setBounds(3, 182, 102, 23);
		loALLCHK.add(chckbxMethionine);
		contentPane.add(chckbxMethionine);
		
		chckbxPhenylalanine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxPhenylalanine);
				logText.setText(chckbxPhenylalanine.getText());
			}
			});
		chckbxPhenylalanine.setBounds(107, 234, 102, 23);
		loALLCHK.add(chckbxPhenylalanine);
		contentPane.add(chckbxPhenylalanine);
		
		chckbxProline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxProline);
				logText.setText(chckbxProline.getText());
			}
			});
		chckbxProline.setBounds(107, 130, 102, 23);
		loALLCHK.add(chckbxProline);
		contentPane.add(chckbxProline);
		
		chckbxSerine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxSerine);
				logText.setText(chckbxSerine.getText());
			}
			});
		chckbxSerine.setBounds(107, 182, 102, 23);
		loALLCHK.add(chckbxSerine);
		contentPane.add(chckbxSerine);
		
		chckbxThreonine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxThreonine);
				logText.setText(chckbxThreonine.getText());
			}
			});
		chckbxThreonine.setBounds(107, 104, 102, 23);
		loALLCHK.add(chckbxThreonine);
		contentPane.add(chckbxThreonine);
		
		chckbxTryptophan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxTryptophan);
				logText.setText(chckbxTryptophan.getText());
			}
			});
		chckbxTryptophan.setBounds(211, 182, 102, 23);
		loALLCHK.add(chckbxTryptophan);
		contentPane.add(chckbxTryptophan);
		
		chckbxTyrosine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxTyrosine);
				logText.setText(chckbxTyrosine.getText());
			}
			});
		chckbxTyrosine.setBounds(211, 156, 102, 23);
		loALLCHK.add(chckbxTyrosine);
		contentPane.add(chckbxTyrosine);
		
		chckbxValine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFilter(chckbxValine);
				logText.setText(chckbxValine.getText());
			}
			});
		chckbxValine.setBounds(107, 156, 102, 23);
		loALLCHK.add(chckbxValine);
		contentPane.add(chckbxValine);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 292, 225, 164);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(jlistHA1IPs);
		
		// always lists objects in IP list (converted to model to use w/ jlist)
		
		jlistHA1IPs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currIndIP = jlistHA1IPs.getSelectedIndex();
				System.out.println("" + currIndIP);
				refPFmodel(true);
				haPFModelTEXT1.setText("");
				haINFO = hamodel.get(currIndIP).name + " " + hamodel.get(currIndIP).attribute1 + " " + hamodel.get(currIndIP).intensity + " " + hamodel.get(currIndIP).attribute3;
				haIPModelTEXT.setText(haINFO);
				if(!(hamodel.get(currIndIP).loHA1CAN.isEmpty())){
					for(int i=0;i<hamodel.get(currIndIP).loHA1CAN.size();i++){
						hapfmodel.addElement(hamodel.get(currIndIP).loHA1CAN.get(i));
						hapfmodelS.addElement(hamodel.get(currIndIP).loHA1CAN.get(i).pepName);
					}
				}else{
					haPFModelTEXT1.setText("No HA-1 Candidates");
				}
				if(hamodel.get(currIndIP).loHA1CAN.size()>0){
					logText.setText("" + hamodel.get(currIndIP).loHA1CAN.size() + " " + AAFilter.findHeaviestHA1(hamodel.get(currIndIP)).pepName);
				}else{
					logText.setText("" + hamodel.get(currIndIP).loHA1CAN.size() + " " + "none");
				}
				if(btnWeightSearch.isSelected()){
					resIPmodel(false);
					damodel = AAFilter.findSimWeight(hamodel.get(currIndIP), damodel);
					refIPmodelS(false);
				}
				
			}
		});
		
		JScrollPane scrollPane_01 = new JScrollPane();
		scrollPane_01.setBounds(260, 292, 225, 164);
		contentPane.add(scrollPane_01);
		scrollPane_01.setViewportView(jlistHA1PF);
		jlistHA1PF.setBorder(new CompoundBorder());
		
				// always lists objects in PF list (hapfmodel)
				jlistHA1PF.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
		
						currIndPF = jlistHA1PF.getSelectedIndex();
						System.out.println("" + currIndPF);
						
						hapfINFO = hapfmodel.get(currIndPF).pepName + " " +hapfmodel.get(currIndPF).molWeight + " " + hapfmodel.get(currIndPF).intensity;
						haPFModelTEXT1.setText(hapfINFO);
					}
				});
				
		JScrollPane scrollPane_02 = new JScrollPane();
		scrollPane_02.setBounds(504, 292, 225, 164);
		contentPane.add(scrollPane_02);
		jlistDA1IP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currIndIP = jlistDA1IP.getSelectedIndex();
				System.out.println("" + currIndIP);
				refPFmodel(false);
				daPFModelTEXT.setText("");
				daINFO = damodel.get(currIndIP).name + " " + damodel.get(currIndIP).attribute1 + " " + damodel.get(currIndIP).intensity + " " + damodel.get(currIndIP).attribute3;
				daIPModelTEXT.setText(daINFO);
				if(!(damodel.get(currIndIP).loDA1CAN.isEmpty())){
					for(int i=0;i<damodel.get(currIndIP).loDA1CAN.size();i++){
						dapfmodel.addElement(damodel.get(currIndIP).loDA1CAN.get(i));
						dapfmodelS.addElement(damodel.get(currIndIP).loDA1CAN.get(i).pepName);
					}
				}else{
					daPFModelTEXT.setText("No DA-1 Candidates");
				}
				if(damodel.get(currIndIP).loDA1CAN.size()>0){
					logText.setText("" + damodel.get(currIndIP).loDA1CAN.size() + " " + AAFilter.findHeaviestDA1(damodel.get(currIndIP)).pepName);
				}else{
					logText.setText("" + damodel.get(currIndIP).loDA1CAN.size() + " " + "none");
				}
				
				
			}
		});

		jlistDA1IP.setBorder(new CompoundBorder());
		scrollPane_02.setViewportView(jlistDA1IP);
		
		JScrollPane scrollPane_03 = new JScrollPane();
		scrollPane_03.setBounds(751, 292, 225, 164);
		contentPane.add(scrollPane_03);
		jlistDA1PF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				currIndPF = jlistDA1PF.getSelectedIndex();
				System.out.println("" + currIndPF);
				dapfINFO = dapfmodel.get(currIndPF).pepName + " " +dapfmodel.get(currIndPF).molWeight + " " + dapfmodel.get(currIndPF).intensity;
				daPFModelTEXT.setText(dapfINFO);
			}
		});
		
		jlistDA1PF.setBorder(new CompoundBorder());
		scrollPane_03.setViewportView(jlistDA1PF);
		
		// fun: once pressed, filters hamodel based on filters that have been checked
		// if no filters checked, returns original list from file
		JButton filterButton = new JButton("Filter");
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnAaSearch.isSelected()) {
					hamodel = hamodelFIN;
					logText.setText("" + lof.size());
					refPFmodel(true);
					if (lof.isEmpty()) {
						hamodel.clear();
						for (int j = 0; j < hamodelFIN.size(); j++) {
							hamodel.addElement(hamodelFIN.get(j));
							refIPmodelS(true);
						}
					} else {

						for (int i = 0; i < lof.size(); i++) {
							System.out.println(lof.get(i));
						}
						hamodel = AAFilter.filterSearch(hamodel, lof, true);
						refIPmodelS(true);
						logText.setText("" + hamodel.size());
						System.out.println(hamodel.size());
					}
				}
				else if(tglbtnDaSearch.isSelected()){
					damodel = damodelFIN;
					logText.setText("" + lof.size());
					refPFmodel(false);
					if (lof.isEmpty()) {
						damodel.clear();
						for (int j = 0; j < damodelFIN.size(); j++) {
							damodel.addElement(damodelFIN.get(j));
							refIPmodelS(false);
						}
					} else {

						
							for (int i = 0; i < lof.size(); i++) {
								System.out.println(lof.get(i));
							}
							damodel = AAFilter.filterSearch(damodel, lof, false);
							refIPmodelS(false);
							logText.setText("" + damodel.size());
							System.out.println(damodel.size());
						
					}
					
				}
			}
		});
		filterButton.setBounds(315, 78, 81, 150);
		contentPane.add(filterButton);
		
		haIPModelTEXT.setBounds(13, 467, 225, 50);
		contentPane.add(haIPModelTEXT);

		haPFModelTEXT1.setBounds(260, 467, 225, 50);
		contentPane.add(haPFModelTEXT1);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tglbtnAaSearch.isSelected()) {
					resIPmodel(true);
					refPFmodel(true);
				}else if(tglbtnDaSearch.isSelected()){
					resIPmodel(false);
					refPFmodel(false);
				}
				logText.setText("");
				lof.clear();
				for(int i=0;i<loALLCHK.size();i++){
					loALLCHK.get(i).setSelected(false); 
				}
				
			}
		});
		btnReset.setBounds(315, 234, 81, 23);
		contentPane.add(btnReset);
		
		logText.setBounds(523, 129, 453, 101);
		contentPane.add(logText);
		
		JLabel lblHaFilters = new JLabel("HA1/DA1 Candidate Filters");
		lblHaFilters.setBounds(10, 56, 173, 14);
		contentPane.add(lblHaFilters);
		tglbtnAaSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onlyOneTgl(tglbtnAaSearch);
			}
		});

		tglbtnAaSearch.setBounds(193, 52, 121, 23);
		contentPane.add(tglbtnAaSearch);
		
		daIPModelTEXT.setBounds(504, 467, 225, 50);
		contentPane.add(daIPModelTEXT);
		
		
		daPFModelTEXT.setBounds(751, 467, 225, 50);
		contentPane.add(daPFModelTEXT);
		tglbtnDaSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onlyOneTgl(tglbtnDaSearch);
			}
		});
		
		
		tglbtnDaSearch.setBounds(319, 52, 121, 23);
		contentPane.add(tglbtnDaSearch);
				
		JButton btnSave = new JButton("Save ");
		btnSave.setBounds(98, 11, 78, 21);
		contentPane.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileChooser(contentPane);
			}
		});
				
						
		// When button is pressed, string in filePath is used as a file path for a file
		// file is then scanned and parsed into Ionized Peptide objects
		JButton loadFileButton = new JButton("Load");
		loadFileButton.setBounds(10, 11, 78, 21);
		contentPane.add(loadFileButton);
		loadFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fp = openFileChooser(contentPane).trim();
				List<IonizedPeptide> temploPIS = new ArrayList<IonizedPeptide>();
				try {
					TextScanner.seperateToPepAndFrags(TextScanner.parseFile(fp), temploPIS);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				for(int i=0; i<temploPIS.size();i++){
					AAFilter.findlabelCand(temploPIS.get(i), minIntensity);
				}
				for(int i=0;i<temploPIS.size();i++){
					if(!(temploPIS.get(i).loHA1CAN.isEmpty())){
						hamodelFIN.addElement(temploPIS.get(i));
					}
				}
				for(int i=0;i<temploPIS.size();i++){
					if(!(temploPIS.get(i).loDA1CAN.isEmpty())){
						// if also in hamodel take out
						/*if (!(hamodelFIN.contains(temploPIS.get(i)))) {*/
							damodelFIN.addElement(temploPIS.get(i));
						/*}else{
								excludedDA.addElement(temploPIS.get(i));
							}*/
						}
					}
				//AAFilter.quickSort(damodelFIN, 0, (damodelFIN.size()-1));
				//AAFilter.quickSort(hamodelFIN, 0, (damodelFIN.size()-1));
				for(int i=0;i<hamodelFIN.size();i++){
					hamodel.addElement(hamodelFIN.get(i));
				}
				for(int i=0;i<damodelFIN.size();i++){
					damodel.addElement(damodelFIN.get(i));
				}
				refIPmodelS(true);
				refIPmodelS(false);
			}});
		btnWeightSearch.setBounds(445, 52, 121, 23);
		contentPane.add(btnWeightSearch);
		
		JLabel lblLog = new JLabel("     Log");
		lblLog.setBounds(523, 108, 46, 14);
		contentPane.add(lblLog);
		
		JLabel lblListOfHa = new JLabel("List of HA1");
		lblListOfHa.setBounds(13, 264, 79, 23);
		contentPane.add(lblListOfHa);
		
		JLabel lblListOfDa = new JLabel("List of DA1");
		lblListOfDa.setBounds(504, 264, 79, 23);
		contentPane.add(lblListOfDa);
		
		
		otherIntensity.setBounds(427, 183, 86, 20);
		contentPane.add(otherIntensity);
		otherIntensity.setColumns(10);
		
		JLabel lblMinimumIntensity = new JLabel("Minimum Intensity");
		lblMinimumIntensity.setBounds(406, 108, 131, 14);
		contentPane.add(lblMinimumIntensity);
		minIntensity80.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minIntensity200.setSelected(false);
				minIntensityOther.setSelected(false);
				minIntensity = 80.0;
			}
		});
		minIntensity80.setBounds(404, 130, 109, 23);
		
		contentPane.add(minIntensity80);
		minIntensity200.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minIntensity80.setSelected(false);
				minIntensityOther.setSelected(false);
				minIntensity = 200.0;
			}
		});
		minIntensity200.setBounds(404, 156, 109, 23);
		
		contentPane.add(minIntensity200);
		minIntensityOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minIntensity80.setSelected(false);
				minIntensity200.setSelected(false);
				minIntensity = Double.parseDouble(otherIntensity.getText());
			}
		});
		minIntensityOther.setBounds(404, 182, 21, 23);
		
		contentPane.add(minIntensityOther);
	}
}
