package httpTest;
import java.util.ArrayList;

/**
 * Represents the analysis for CO2 emissions (metric tons per capita) vs Energy use (kg of oil equivalent per capita) vs PM2.5 air pollution
 * 3-series
 * @author Sasa Vecerak
 */
public class AnalysisA implements Analysis {

	/** 
	 * Issues HTTP GET Request using Reader, returns Data object.
	 */
	public Data readData(Selection selection, String indicatorID) {
		
		// Fetch using Reader
		
		Data data = Reader.fetch(selection.getCountry(), selection.getYearStart(), selection.getYearEnd(), indicatorID);

		return data;
	}
		
	/** 
	 * Returns populated Result object, using Facade pattern to fetch data for each Indicator ID. 
	 */
	public Result calculate(Selection selection) {
		
		// Fetch data using Reader
				
		Data carbonEmissionData = this.readData(selection, "EN.ATM.CO2E.PC");
		Data energyUseData = this.readData(selection, "EG.USE.PCAP.KG.OE");
		Data airPollutionData = this.readData(selection, "EN.ATM.PM25.MC.M3");
		
		// Save 
		
		String analysisType = selection.getAnalysisType();
		ArrayList<Integer> years = carbonEmissionData.getYears(); 

		// Series One
		
		ArrayList<Float> valuesCarbon = carbonEmissionData.getValues();
		
		// Series Two
		
		ArrayList<Float> valuesEnergy = energyUseData.getValues();
		
		// Series Three
		
		ArrayList<Float> valuesAir = airPollutionData.getValues();
		
		// List Of Lists
		
		ArrayList<ArrayList<Float>> listOfValues = new ArrayList<ArrayList<Float>>();
		listOfValues.add(valuesCarbon);
		listOfValues.add(valuesEnergy);
		listOfValues.add(valuesAir);
				
		// No calculation, Pass Data object directly to Result object
		
		// Populate the Result object
		
		Result result = new Result();	
		result.setType(analysisType);
		result.setYears(years);
		result.setValues(listOfValues);
		
		return result;
	}

}