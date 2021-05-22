package httpTest;
import java.util.ArrayList;

/**
 * Represents the selection object containing all user selections.
 * @author Sasa Vecerak
 */
public class Selection {

	/**
	 * The name of the selected country.
	 */
	private String country;
	
	/**
	 * The chosen start year.
	 */
	private int yearStart;
	
	/**
	 * The chosen end year.
	 */
	private int yearEnd;
	
	/**
	 * The type of analysis to perform.
	 */
	private String analysisType;
	
	/**
	 * A list of Viewer objects, each representing a different kind of visual graph.
	 */
	private ArrayList<Viewer> viewers;

	/**
	 * Gets the name of the selected country.
	 * @return This country's name.
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * Changes the name of the selected country.
	 * @param country The name of the newly selected country.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Gets the selected start year.
	 * @return The start year.
	 */
	public int getYearStart() {
		return this.yearStart;
	}
	
	/**
	 * Changes the selected start year.
	 * @param yearStart The year of the newly selected start year.
	 */
	public void setYearStart(int yearStart) {
		this.yearStart = yearStart;
	}
	
	/**
	 * Gets the selected end year.
	 * @return The end year.
	 */
	public int getYearEnd() {
		return this.yearEnd;
	}
	
	/**
	 * Changes the selected end year.
	 * @param yearEnd The year of the newly selected end year.
	 */
	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}
	
	/**
	 * Gets the list of selected Viewers. 
	 * @return List of Viewers objects.
	 */
	public ArrayList<Viewer> getViewers() {
		return new ArrayList<Viewer>(this.viewers);
	}
	
	/**
	 * Changes the list of currently selected Viewers.
	 * @param viewers The list of viewer objects.
	 */
	public void setViewers(ArrayList<Viewer> viewers) {
		this.viewers = new ArrayList<Viewer>(viewers);
	}
	
	/**
	 * Gets the type of analysis chosen.
	 * @return Chosen analysis type.
	 */
	public String getAnalysisType() {
		return this.analysisType;
	}
	
	/**
	 * Changes the currently chosen analysi type.
	 * @param analysisType The analysis type.
	 */
	public void setAnalysisType (String analysisType) {
		this.analysisType = analysisType;
	}
}