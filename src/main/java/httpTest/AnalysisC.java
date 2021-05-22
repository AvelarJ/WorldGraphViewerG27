package httpTest;
import java.util.ArrayList;

/**
 * 
 * @author rishidhir
 *
 */
public class AnalysisC implements Analysis {

	// Ratio of CO2 emissions (metric tons per capita) and GDP per capita (current US$)


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
		
		Data carbonEmissionData = this.readData(selection, "EN.ATM.CO2E.PC");
		Data gdpCapitaData = this.readData(selection, "NY.GDP.PCAP.CD");
		
		// Save 
		
		String analysisType = selection.getAnalysisType();
		ArrayList<Integer> years = carbonEmissionData.getYears(); // Returns array of all the years using getYears on Data object populated by JSON data

		// First value
		
		ArrayList<Float> valuesCarbon = carbonEmissionData.getValues();
		
		// Second Value
		
		ArrayList<Float> valuesGDP = gdpCapitaData.getValues();
		
		ArrayList<Float> ratio = new ArrayList<Float>();
		for(int i = 0; i < valuesCarbon.size(); i++) {
			ratio.add(valuesCarbon.get(i)/valuesGDP.get(i));
		}
		
		// List Of Lists
		
		ArrayList<ArrayList<Float>> listOfValues = new ArrayList<ArrayList<Float>>();
		listOfValues.add(ratio);
		//listOfValues.add(valuesCarbon);
		//listOfValues.add(valuesGDP);
		
	
		// Populate the Result object
		
		Result result = new Result();	
		result.setType(analysisType);
		result.setYears(years);
		result.setValues(listOfValues);
		
		return result;
	}


}
