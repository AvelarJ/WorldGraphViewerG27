package httpTest;

/** 
 * Analysis Interface to be realized by concrete Analysis classes. 
 * @author Sasa Vecerak
 *
 */
public interface Analysis {
	
	/** 
	 * Issues HTTP GET Request using Reader, returns Data object.
	 */
	public Result calculate(Selection selection);
	
	/** 
	 * Returns populated Result object, using Facade pattern to fetch data for each Indicator ID. 
	 */
	public Data readData(Selection selection, String indicatorID);


}
