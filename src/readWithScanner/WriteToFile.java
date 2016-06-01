package readWithScanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
	
public class WriteToFile {

	public static void saveToFile(DefaultListModel<IonizedPeptide> d, File f){
		try {
			List<String> toWrite = new ArrayList<String>();
			for(int i=0; i<d.size();i++){
				String tempipstr = d.get(i).attribute1 + " " + d.get(i).intensity + " " + d.get(i).attribute3;
				toWrite.add(tempipstr);
				for(int j=0;j<d.get(i).loAA.size();j++){
					String temppfstr = String.valueOf((d.get(i).loAA.get(j).molWeight)) + " " + String.valueOf((d.get(i).loAA.get(j).intensity));
					toWrite.add(temppfstr);
				}
				toWrite.add(" ");
			}
			
			
 
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int j=0;j<toWrite.size();j++){
				bw.write(toWrite.get(j));
				bw.newLine();
			}
			bw.close();
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
