package httpTest;
import java.util.ArrayList;

/**
 * PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) vs Forest area (% of land area) (2-series graphs) 
 * @author Sasa Vecerak
 */
public class AnalysisB implements Analysis {

	/** 
	 * Issues HTTP GET Request using Reader, returns Data object.
	 */
	public Data readData(Selection selection, String indicatorID) {
		
		// Fetch using Reader
		
		Data data = Reader.fetch(selection.getCountry(), selection.getYearStart(), selection.getYearEnd(), indicatorID );

		return data;
	}
	
	
	/** 
	 * Returns populated Result object, using Facade pattern to fetch data for each Indicator ID. 
	 */
	public Result calculate(Selection selection) {
		
		// Fetch data using Reader
		
		Data airPollutionData = this.readData(selection, "EN.ATM.PM25.MC.M3");
		Data forestAreaData = this.readData(selection, "AG.LND.FRST.ZS");
		
		// Save 
		
		String analysisType = selection.getAnalysisType();
		ArrayList<Integer> years = airPollutionData.getYears(); 

		// Series One
		
		ArrayList<Float> valuesAir = airPollutionData.getValues();
		
		// Series Two
		
		ArrayList<Float> valuesForest = forestAreaData.getValues();
		
		// List Of Lists
		
		ArrayList<ArrayList<Float>> listOfValues = new ArrayList<ArrayList<Float>>();
		listOfValues.add(valuesAir);
		listOfValues.add(valuesForest);
			
		// Populate the Result object
		
		Result result = new Result();	
		result.setType(analysisType);
		result.setYears(years);
		result.setValues(listOfValues);
		
		return result;
	}


}