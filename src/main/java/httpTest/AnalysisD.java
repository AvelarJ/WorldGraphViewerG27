package httpTest;
import java.util.ArrayList;

/**
 * 
 * @author rishidhir
 *
 */
public class AnalysisD implements Analysis {

	//Average Forest Area (% of land area) for the selected years (1 series - graph)

	
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
				
		Data forestArea = this.readData(selection, "AG.LND.FRST.ZS");

		
		// Save 
		
		String analysisType = selection.getAnalysisType();
		ArrayList<Integer> years = forestArea.getYears(); // Returns array of all the years using getYears on Data object populated by JSON data 

		// Series One
		
		ArrayList<Float> valuesForest = forestArea.getValues();
		
		
		// List Of Lists
		
		ArrayList<ArrayList<Float>> listOfValues = new ArrayList<ArrayList<Float>>();
		listOfValues.add(valuesForest);

		// No calculation, Pass Data object directly to Result object
		
		// Populate the Result object
		
		Result result = new Result();	
		result.setType(analysisType);
		result.setYears(years);
		result.setValues(listOfValues);
		
		return result;
	}

}
