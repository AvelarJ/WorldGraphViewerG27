package httpTest;

import java.util.ArrayList;

/**
 * 
 * @author Jordan Avelar
 *
 */

public class AnalysisF implements Analysis {
	
	// Hospital beds (per 1,000 people) and Current health expenditure US$ (per 1,000 people)
	// (2-Series graph) + computations


	/** Issues HTTP GET Request using Reader
	 * 
	 */
	public Data readData(Selection selection, String indicatorID) {
				
		// Fetch using Reader
				
		Data data = Reader.fetch(selection.getCountry(), selection.getYearStart(), selection.getYearEnd(), indicatorID );

		return data;
	}
	
	public Result calculate(Selection selection) {
		
		// Fetch data using Reader
				
		Data hospitalBedData = this.readData(selection, "SH.MED.BEDS.ZS");
		Data healthExpenData = this.readData(selection, "SH.XPD.CHEX.PC.CD");
		
		// Save 
		
		String analysisType = selection.getAnalysisType();
		ArrayList<Integer> years = hospitalBedData.getYears(); // Returns array of all the years using getYears on Data object populated by JSON data (Not sure about this)

		// Series One
		
		ArrayList<Float> valuesBeds = hospitalBedData.getValues();
		
		// Series Two
		
		ArrayList<Float> valuesHealthExpen = healthExpenData.getValues();
		
		
		// List Of Lists
		
		ArrayList<ArrayList<Float>> listOfValues = new ArrayList<ArrayList<Float>>();
		listOfValues.add(valuesBeds);
		listOfValues.add(valuesHealthExpen);
				
		//Must compute health expenditure per 1000 people not per capita as it is before this
		
		for (int i=0; i<listOfValues.get(1).size(); i++) {
			listOfValues.get(1).set(i, (listOfValues.get(1).get(i)*1000));
		}
		
		// Populate the Result object
		
		Result result = new Result();	
		result.setType(analysisType);
		result.setYears(years);
		result.setValues(listOfValues);
		
		return result;
	}

}
