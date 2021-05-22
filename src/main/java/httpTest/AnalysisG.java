package httpTest;
import java.util.ArrayList;
/**
 * This class performs the 7th type of analysis in the system, Current health expenditure per capita (current US$) vs Mortality rate, infant (per 1,000 live births) (2-series graphs)
 * @author Filip Maletic
 *
 */
public class AnalysisG implements Analysis {
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
		Data healthExpenditurePC = this.readData(selection, "SH.XPD.CHEX.PC.CD");		// Fetches data using Reader
		Data mortalityRate = this.readData(selection, "SP.DYN.IMRT.IN");
		
		String analysisType = selection.getAnalysisType();
		ArrayList<Integer> years = healthExpenditurePC.getYears();		// Returns array of all the years using getYears on Data object populated by JSON data
		
		ArrayList<Float> heValues = healthExpenditurePC.getValues();	// Series one
		ArrayList<Float> mrValues = mortalityRate.getValues();			// Series two
		
		ArrayList<ArrayList<Float>> listOfValues = new ArrayList<ArrayList<Float>>();		// List of Lists
		listOfValues.add(heValues);
		listOfValues.add(mrValues);
		// Populates a result object with the 3 corresponding attributes. 
		Result result = new Result();
		result.setType(analysisType);
		result.setYears(years);
		result.setValues(listOfValues);
		return result;
	}

}
