package httpTest;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * 
 * @author Jordan Avelar
 *
 */

public class LineGraph implements Viewer {

	
	private ComputationServer subject;
	
	/**
	 * LineGraph constructor
	 * @param subject
	 */
	public LineGraph(ComputationServer subject) {
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
		JFreeChart chart = ChartFactory.createLineChart(null, null, null, null);
		
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
		//Adds each set of data to their own XYSeries
		//Either 3, 2, or 1 loop needed to add to series
		if(res.getValues().size() == 3) {
			String[] anType = MainUI.decodeType(res);
			
			XYSeries series1 = new XYSeries(anType[1]);
			for(int i=0;i<res.getYears().size();i++) {
				series1.add(res.getYears().get(i), res.getValues().get(0).get(i));
			}
			
			XYSeries series2 = new XYSeries(anType[2]);
			for(int i=0;i<res.getYears().size();i++) {
				series2.add(res.getYears().get(i), res.getValues().get(1).get(i));
			}
			
			XYSeries series3 = new XYSeries(anType[3]);
			for(int i=0;i<res.getYears().size();i++) {
				series3.add(res.getYears().get(i), res.getValues().get(2).get(i));
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);
			dataset.addSeries(series3);

			JFreeChart chart = ChartFactory.createXYLineChart(anType[0], "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			//Rest assembles the ChartPanel to be displayed on JPanel
			XYPlot plot = chart.getXYPlot();

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(new TextTitle(anType[0], new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
			
		}
		
		else if(res.getValues().size() == 2) {
			String[] anType = MainUI.decodeType(res);
			
			XYSeries series1 = new XYSeries(anType[1]);
			for(int i=0;i<res.getYears().size();i++) {
				series1.add(res.getYears().get(i), res.getValues().get(0).get(i));
			}
			
			XYSeries series2 = new XYSeries(anType[2]);
			for(int i=0;i<res.getYears().size();i++) {
				series2.add(res.getYears().get(i), res.getValues().get(1).get(i));
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);

			JFreeChart chart = ChartFactory.createXYLineChart(anType[0], "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(new TextTitle(anType[0], new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
			
		}
		
		else if(res.getValues().size() == 1) {
			String[] anType = MainUI.decodeType(res);
			
			XYSeries series1 = new XYSeries(anType[1]);
			for(int i=0;i<res.getYears().size();i++) {
				series1.add(res.getYears().get(i), res.getValues().get(0).get(i));
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);

			JFreeChart chart = ChartFactory.createXYLineChart(anType[0], "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(new TextTitle(anType[0], new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			MainUI.draw(chartPanel);
			
		}
		
	}
	
}
