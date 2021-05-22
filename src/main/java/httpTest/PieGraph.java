package httpTest;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 
 * @author Jordan Avelar
 *
 */

public class PieGraph implements Viewer {
	
	
	private ComputationServer subject;
	
	/**
	 * LineGraph constructor
	 * @param subject
	 */
	public PieGraph(ComputationServer subject) {
		subject.attach(this);
		this.subject = subject;
		initView();
	}
	
	/**
	 * Updates viewer with info in Result res
	 * @param changedSubject
	 * @param res
	 */
	public void update(ComputationServer changedSubject, Result res) {
		if(changedSubject.equals(subject)) {
			draw(res);
		}
	}
	
	/**
	 * Initialize a blank viewer
	 */
	public void initView() {
		JFreeChart chart = ChartFactory.createPieChart(null, null);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		MainUI.draw(chartPanel);
	}
	
	/**
	 * Makes a new viewer with result data
	 * @param res
	 */
	public void draw(Result res) {
		
		// anType array holds analysis info in each index
		String[] anType = MainUI.decodeType(res);
		if(anType.length == 2) {	// length of 2 means its a 1 series dataset
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
					
			for(int i=0;i<res.getYears().size();i++) {	// Loop through all values in result
				if(res.getValues().get(0).get(i) > 0) {
					//Add normal value given then add remainder of pie chart (100-res....)
					dataset.addValue(res.getValues().get(0).get(i), anType[1], res.getYears().get(i));
					dataset.addValue(100-res.getValues().get(0).get(i), "", res.getYears().get(i));
				}
			}
					
			JFreeChart pieChart = ChartFactory.createMultiplePieChart(anType[0], dataset,
					TableOrder.BY_COLUMN, true, true, false);

			ChartPanel chartPanel = new ChartPanel(pieChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
					
		}
		// Else can't make a pie chart
		else {
			JOptionPane.showMessageDialog(null, 
                    "Cannot produce a pie chart with current analysis type", 
                    "Error", 
                    JOptionPane.WARNING_MESSAGE);
		}
		
	}

}
