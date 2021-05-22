package httpTest;
import java.util.ArrayList;

/**
 * Initiates the computation of all individual pieces of data, and notifies chosen Viewers to visually display the results. 
 * @author Sasa Vecerak, Jordan Avelar
 *
 */
public class ComputationServer {
	
	/**
	 * Used to point to correct Analysis object.
	 */
	private Analysis currentAnalysis;
	
	/**
	 * List of Viewer objects.
	 */
	private ArrayList<Viewer> viewers = new ArrayList<Viewer>();
	
	/** 
	 * Add a new Viewer object to List.
	 * @param viewer Viewer object.
	 */
	public void attach(Viewer viewer) {
		viewers.add(viewer);
	}
	
	/** 
	 * Remove a viewer object from List.
	 * @param viewer Viewer object.
	 */
	public void dettach(Viewer viewer) {
		viewers.remove(viewer);
	}
	
	/**
	 * Loop through array of Viewer objects, calling update method on each viewer, which calls draw method
	 */
	public void notifyViewers(Result result) {
		for(Viewer viewer : viewers) {
			viewer.update(this, result);
		}
	}

	/** 
	 * Trigger the computation of the specific analysis using all the required individual pieces of data which have been collected.
	 * @param selection Selection object containing all user choices.
	 * @return Result Result object
	 */
	public Result recalculateButton(Selection selection) {
		selection.setViewers(viewers);
		
		// Return correct analysis  
		
		Analysis analysisObject = AnalysisFactory.createAnalysis(selection);
		
		// Set reference pointing to one of the 8 newly created analysis objects
		
		currentAnalysis=analysisObject; 
		
		// Create new result object
		
		Result result = new Result();
		
		// Strategy
		
		result = currentAnalysis.calculate(selection); 
		
		// Loop through List of Viewer objects, calling update on each one that is present in the List, pass in result object
		
		notifyViewers(result);
		
		return result;
	}
}