package httpTest;

import java.util.ArrayList;

/**
 * 
 * @author Jordan Avelar
 *
 */

public class AnalysisE implements Analysis {
	
	// Average of Government expenditure on education, total (% of GDP) for the selected years
	// (1-Series graph)


	/** Issues HTTP GET Request using Reader
	 * 
	 */
	public Data readData(Selection selection, String indicatorID) {
			
		// Fetch using Reader
			
		Data data = Reader.fetch(selection.getCountry(), selection.getYearStart(), selection.getYearEnd(), indicatorID );

		return data;
	}
	
	/** Returns populated Result object
	 * 
	 */
	public Result calculate(Selection selection) {
		
		// Fetch data using Reader
		
		Data EducationExpen = this.readData(selection, "SE.XPD.TOTL.GD.ZS");
		
		// Save 
		
		String analysisType = selection.getAnalysisType();
		ArrayList<Integer> years = EducationExpen.getYears(); // Returns array of all the years using getYears on Data object populated by JSON data

		// Series One
		
		ArrayList<Float> valuesEdu = EducationExpen.getValues();
		
		
		// List Of Lists
		
		ArrayList<ArrayList<Float>> listOfValues = new ArrayList<ArrayList<Float>>();
		listOfValues.add(valuesEdu);
			
		// Populate the Result object
		
		Result result = new Result();	
		result.setType(analysisType);
		result.setYears(years);
		result.setValues(listOfValues);
		
		return result;
	}

}
