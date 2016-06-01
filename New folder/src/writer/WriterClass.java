package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterClass {
	public static void main(String[] args) {
		try {
			
			List<List<String>> biglist = new ArrayList<List<String>>();
			for(int i=0; i<5; i++){
				List<String> smalllist = new ArrayList<String>();
				smalllist.add("att1");
				smalllist.add("att2");
				smalllist.add("att3");
				biglist.add(smalllist);
			}
			String content = "This is the content to write into file";
 
			File file = new File("/Users/Arthur Lai/filename.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.newLine();
			for(int j=0;j<biglist.size();j++){
				bw.write(biglist.get(j).get(0) + " ");
				bw.write(biglist.get(j).get(1) + " ");
				bw.write(biglist.get(j).get(2));
				bw.newLine();
				bw.newLine();
			}
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
