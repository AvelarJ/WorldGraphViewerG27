package httpTest;
import java.util.ArrayList;

/**
 * Represents a Data object storing information retrieved from API. 
 * @author Sasa Vecerak
 */
public class Data {
	
	/**
	 * The type of analysis the data was fetched for.
	 */
	private String type; 
	
	/**
	 * The list of values for a given year.
	 */
	private ArrayList<Float> values;
	
	/**
	 * The list of years data was fetched for.
	 */
	private ArrayList<Integer> years;
	

	/** 
	 * Gets the type of analysis performed.
	 * @return Analysis type.
	 */
	public String getType() {
		return this.type;
	}

	/** 
	 * Changes the current type of analysis.
	 * @param type The analysis type.
	 */
	public void setType(String type) {
		this.type=type;
	}
	
	/** 
	 * Gets the list of years.
	 * @return List of years.
	 */
	public ArrayList<Integer> getYears() {
		return new ArrayList<Integer>(this.years);
	}
	
	/** 
	 * Changes the list of years.
	 * @param years List of years.
	 */
	public void setYears(ArrayList<Integer> years) {
		this.years = new ArrayList<Integer>(years);
	}

	/** 
	 * Gets the list of values corresponding to the years.
	 * @return List of values.
	 */
	public ArrayList<Float> getValues() {
		return new ArrayList<Float>(this.values);
	}

	/** 
	 * Changes list of values for each Data object corresponding to one analysis Indicator ID
	 * @param values List of values.
	 */
	public void setValues(ArrayList<Float> values) {
		this.values = new ArrayList<Float>(values);

	}
	
	
}