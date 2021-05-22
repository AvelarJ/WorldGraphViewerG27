package httpTest;
import java.util.ArrayList;

/** 
 * Represents a result object containing the type of analysis performed, a list of values for each series, and a list of years
 * @author Sasa Vecerak
 */
public class Result {
	
	/**
	 * The type of analysis chosen.
	 */
	private String type;
	
	/**
	 * A list containing a list of values for each series.
	 */
	private ArrayList<ArrayList<Float>> values;	
	
	/**
	 * A list containing the years to fetch data for.
	 */
	private ArrayList<Integer> years;
	
	
	/**
	 * Gets the type of analysis chosen.
	 * @return This result's analysis type.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Changes the type of this analysis.
	 * @param type This result's analysis type.
	 */
	public void setType(String type) {
		this.type=type;
	}

	/**
	 * Gets the values of each series.
	 * @return list of lists containing values.
	 */
	public ArrayList<ArrayList<Float>> getValues() {
		return new ArrayList<ArrayList<Float>>(this.values);
	}
	
	/**
	 * Changes the values of each series.
	 * @param values This result's values.
	 */
	public void setValues(ArrayList<ArrayList<Float>> values) {
		this.values = new ArrayList<ArrayList<Float>>(values);
	}
	
	/**
	 * Gets the list of years to fetch data for.
	 * @return This list of years.
	 */
	public ArrayList<Integer> getYears() {
		return new ArrayList<Integer>(this.years);
	}
	
	/**
	 * Changes the list of years to fetch data for.
	 * @param years The list of years.
	 */
	public void setYears(ArrayList<Integer> years ) {
		this.years = new ArrayList<Integer>(years);
	}
	
}
