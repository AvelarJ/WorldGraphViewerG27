package httpTest;

/**
 * 
 * @author Sasa Vecerak
 *
 */
public class AnalysisFactory {
	
	/**
	 * Returns correct analysis object.
	 * @param type Selection object.
	 * @return New Analysis object.
	 */
	public static Analysis createAnalysis(Selection type) {
		
		if ("AnalysisA".equals(type.getAnalysisType())) {
			return new AnalysisA();
		}
		
		else if ("AnalysisB".equals(type.getAnalysisType())) {
			return new AnalysisB();
		}
		
		else if ("AnalysisC".equals(type.getAnalysisType())) {
			return new AnalysisC();
		}
		
		else if ("AnalysisD".equals(type.getAnalysisType())) {
			return new AnalysisD();
		}
		
		else if ("AnalysisE".equals(type.getAnalysisType())) {
			return new AnalysisE();
		}

		else if ("AnalysisF".equals(type.getAnalysisType())) {
			return new AnalysisF();
		}
		
		else if ("AnalysisG".equals(type.getAnalysisType())) {
			return new AnalysisG();
		}
		
		else if ("AnalysisH".equals(type.getAnalysisType())) {
			return new AnalysisH();
		}
		
		return null;
	}

}