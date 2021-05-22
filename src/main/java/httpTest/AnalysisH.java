package httpTest;
import java.util.ArrayList;
/**
 * This class performs the 8th type of analysis in the system, Government expenditure on education, total (% of GDP) vs Current health expenditure (% of GDP) (2-series graphs)
 * @author Filip Maletic
 *
 */
public class AnalysisH implements Analysis {
	/**
	 * This method issues an HTTP get request using the Reader class.
	 */
	public Data readData(Selection selection, String indicatorID) {
		Data data = Reader.fetch(selection.getCountry(), selection.getYearStart(), selection.getYearEnd(), indicatorID );
		return data;
	}
	/**
	 * This method returns a populated Result object.
	 */
	public Result calculate(Selection selection) {
		Data educationExpenditure = this.readData(selection, "SE.XPD.TOTL.GD.ZS");			// Fetches data using Reader
		Data healthExpenditureGDP = this.readData(selection, "SH.XPD.CHEX.GD.ZS");
		
		String analysisType = selection.getAnalysisType();
		ArrayList<Integer> years = educationExpenditure.getYears();		// Returns array of all the years using getYears on Data object populated by JSON data
		
		ArrayList<Float> eeValues = educationExpenditure.getValues();	// Series one
		ArrayList<Float> heValues = healthExpenditureGDP.getValues();	// Series two
		
		ArrayList<ArrayList<Float>> listOfValues = new ArrayList<ArrayList<Float>>();		// List of Lists
		listOfValues.add(eeValues);
		listOfValues.add(heValues);
		// Populates a result object with the 3 corresponding attributes. 
		Result result = new Result();
		result.setType(analysisType);
		result.setYears(years);
		result.setValues(listOfValues);
		return result;
	}

}
