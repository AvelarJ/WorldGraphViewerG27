package httpTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

/**
 * 
 * @author Jordan Avelar
 *
 */

public class ScatterPlot implements Viewer {
	
	
	private ComputationServer subject;
	
	/**
	 * LineGraph constructor
	 * @param subject
	 */
	public ScatterPlot(ComputationServer subject) {
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
		JFreeChart chart = ChartFactory.createScatterPlot(null, null, null, null);
		
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
		
		// Check for 3 series
		if(res.getValues().size() == 3) {
			
			String[] anType = MainUI.decodeType(res);	// Gives array containing analysis info properly formated in each index
			
			TimeSeries series1 = new TimeSeries(anType[1]);
			TimeSeries series2 = new TimeSeries(anType[2]);
			TimeSeries series3 = new TimeSeries(anType[3]);
			// Loop through all values in first list of list (the get(0)
			for(int i=0;i<res.getYears().size();i++) {
				series1.add(new Year(res.getYears().get(i)), res.getValues().get(0).get(i));
			}
			// All three add years and corresponding values to timeseries
			for(int i=0;i<res.getYears().size();i++) {
				series2.add(new Year(res.getYears().get(i)), res.getValues().get(1).get(i));
			}
			
			for(int i=0;i<res.getYears().size();i++) {
				series3.add(new Year(res.getYears().get(i)), res.getValues().get(2).get(i));
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series3);
			
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			// Create the plot
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");	// x axis will always be year
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));	// other axis are blank so measurement info is in key

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			JFreeChart scatterChart = new JFreeChart(anType[0],
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);	//anType[0] gives title as analysis type

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
			
		}
		// Same is done for 2 and 1 series
		else if(res.getValues().size() == 2) {
			
			String[] anType = MainUI.decodeType(res);
			
			
			TimeSeries series1 = new TimeSeries(anType[1]);
			TimeSeries series2 = new TimeSeries(anType[2]);
			
			for(int i=0;i<res.getYears().size();i++) {
				series1.add(new Year(res.getYears().get(i)), res.getValues().get(0).get(i));
			}
			
			for(int i=0;i<res.getYears().size();i++) {
				series2.add(new Year(res.getYears().get(i)), res.getValues().get(1).get(i));
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			JFreeChart scatterChart = new JFreeChart(anType[0],
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
			
		}
		
		else if(res.getValues().size() == 1) {
			String[] anType = MainUI.decodeType(res);
			
			TimeSeries series1 = new TimeSeries(anType[1]);
			
			for(int i=0;i<res.getYears().size();i++) {
				series1.add(new Year(res.getYears().get(i)), res.getValues().get(0).get(i));
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			JFreeChart scatterChart = new JFreeChart(anType[0],
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
		}
		
	}

}
