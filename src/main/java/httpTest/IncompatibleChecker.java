package httpTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class IncompatibleChecker {

	public static boolean validateSelection(String country, int yearStart, int yearEnd, String analysisType) {
		
		ArrayList<String> strCountry = new ArrayList<String>();
		ArrayList<Integer> yrStart = new ArrayList<Integer>();
		ArrayList<Integer> yrEnd = new ArrayList<Integer>();
		ArrayList<String> strAnalyisType = new ArrayList<String>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("IncompatibleChecker.txt"));	
			in.readLine();
			String line;
			while((line = in.readLine()) != null) {
				String[] info = line.split(" ");
				strAnalyisType.add(info[0]);
				strCountry.add(info[1]);				
				yrStart.add(Integer.parseInt(info[2]));
				yrEnd.add(Integer.parseInt(info[3]));	
			} 
			in.close();
		}
		catch(Exception e) {
			System.out.println("Unable to read incompatible checker file");
			System.exit(0);
		}
		
		for(int i = 0; i < strCountry.size(); i++) {
			
			if(strCountry.get(i).contains(country) && strAnalyisType.get(i).contains(analysisType)) {
				if(yrStart.get(i) == yearStart && yrEnd.get(i) == yearEnd) {
					return false;
				}
				else if((yrStart.get(i) < yearStart && yrEnd.get(i) > yearStart) || (yrStart.get(i) < yearEnd && yrEnd.get(i) > yearEnd)) {
					return false;
				}
			}
		}
		return true;
	}
	
}
