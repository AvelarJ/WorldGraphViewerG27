package httpTest;

/**
 * 
 * @author Jordan Avelar
 *
 */
public interface Viewer {
	
	//public void display(Result res);
	public void draw(Result res);
	
	public void update(ComputationServer changedSubject, Result res);
	
}