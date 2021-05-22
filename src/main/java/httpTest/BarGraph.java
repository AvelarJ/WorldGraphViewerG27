package httpTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 
 * @author Jordan Avelar
 *
 */

public class BarGraph implements Viewer {
	
	private ComputationServer subject;
	
	/**
	 * BarGraph constructor
	 * @param subject
	 */
	public BarGraph(ComputationServer subject) {
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
		JFreeChart chart = ChartFactory.createBarChart(null, null, null, null);
		
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
		
		if(res.getValues().size() == 3) {
			String[] anType = MainUI.decodeType(res);
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			//Add first set of data (index 0 in res values) with corresponding years
			for(int i=0;i<res.getYears().size();i++) {
				dataset.setValue(res.getValues().get(0).get(i), anType[1], res.getYears().get(i));
			}
			for(int i=0;i<res.getYears().size();i++) {
				dataset.setValue(res.getValues().get(2).get(i), anType[3], res.getYears().get(i));
			}
			
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			for(int i=0;i<res.getYears().size();i++) {
				dataset2.setValue(res.getValues().get(1).get(i), anType[2], res.getYears().get(i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			JFreeChart barChart = new JFreeChart(anType[0],
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
			

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
			
		}
		//Only requires one dataset if 2-series
		else if(res.getValues().size() == 2) {
			String[] anType = MainUI.decodeType(res);
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for(int i=0;i<res.getYears().size();i++) {
				dataset.setValue(res.getValues().get(0).get(i), anType[1], res.getYears().get(i));
			}
			for(int i=0;i<res.getYears().size();i++) {
				dataset.setValue(res.getValues().get(1).get(i), anType[2], res.getYears().get(i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			JFreeChart barChart = new JFreeChart(anType[0],
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
			

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
			
		}
		
		else if(res.getValues().size() == 1) {
			String[] anType = MainUI.decodeType(res);
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for(int i=0;i<res.getYears().size();i++) {
				dataset.setValue(res.getValues().get(0).get(i), anType[1], res.getYears().get(i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0); // 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			JFreeChart barChart = new JFreeChart(anType[0],
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
			

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
			
		}
		
	}
	
}
