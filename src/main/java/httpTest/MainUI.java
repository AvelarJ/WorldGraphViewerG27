package httpTest;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class for the main user interface
 * @author Jordan Avelar
 *
 */

public class MainUI extends JFrame {
	//West JPanel for viewer use
	final static JPanel west = new JPanel();
	
	private static final long serialVersionUID = 1L;

	private static MainUI instance;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}
	//ComputationServer object used to store all current viewers and analysis
	ComputationServer cs = new ComputationServer();
	
	ArrayList<Viewer> currentViewers = new ArrayList<Viewer>();

	/**
	 * MainUI class to define most attributes for the MainUI window
	 */
	private MainUI() {
		super("Country Statistics");
		
		//Counties and years on top row
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("USA");
		countriesNames.add("Canada");
		countriesNames.add("France");
		countriesNames.add("China");
		countriesNames.add("Brazil");
		countriesNames.add("Mexico");
		countriesNames.add("Sudan");
		countriesNames.add("Russia");
		countriesNames.add("Germany");
		countriesNames.add("Chile");
		countriesNames.sort(null);
		final JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		final JComboBox<Integer> fromList = new JComboBox<Integer>();
		final JComboBox<Integer> toList = new JComboBox<Integer>();
		
		for (int i = 2021; i >= 1962; i--) {
			fromList.addItem(i);
			toList.addItem(i);
		}
		//Add all components to top bar
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");

		JLabel viewsLabel = new JLabel("Available Views: ");
		
		Vector<String> viewsNames = new Vector<String>();	//Add items to comboBox
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Time Series");
		final JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		
		
		//Add viewer to list of viewers
		JButton addView = new JButton("+");
		addView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String viewerType = viewsList.getSelectedItem().toString();
				
				//If viewer type matches certain type of chart
				if(viewerType.equals("Bar Chart")) {
					BarGraph newView = new BarGraph(cs);	//Make a new graph
					currentViewers.add(newView);		//Add new graph to viewer list
				}
				else if(viewerType.equals("Pie Chart")) {
					PieGraph newView = new PieGraph(cs);
					currentViewers.add(newView);
				}
				else if(viewerType.equals("Line Chart")) {
					LineGraph newView = new LineGraph(cs);
					currentViewers.add(newView);
				}
				else if(viewerType.equals("Scatter Chart")) {
					ScatterPlot newView = new ScatterPlot(cs);
					currentViewers.add(newView);
				}
				else if(viewerType.equals("Time Series")) {
					TimeGraph newView = new TimeGraph(cs);
					currentViewers.add(newView);
				}
				
			}
		});
		
		JButton removeView = new JButton("-");
		removeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Remove viewer from list
				
				String viewerType = viewsList.getSelectedItem().toString();
				
				if(viewerType.equals("Bar Chart")) {
					for(Viewer view : currentViewers) {	//Loop through list of viewers and dettach matching graphs
						if(view instanceof BarGraph) cs.dettach(view);
					}
				}
				else if(viewerType.equals("Scatter Chart")) {
					for(Viewer view : currentViewers) {
						if(view instanceof ScatterPlot) cs.dettach(view);
					}
				}
				else if(viewerType.equals("Pie Chart")) {
					for(Viewer view : currentViewers) {
						if(view instanceof PieGraph) cs.dettach(view);
					}
				}
				else if(viewerType.equals("Line Chart")) {
					for(Viewer view : currentViewers) {
						if(view instanceof LineGraph) cs.dettach(view);
					}
				}
				else if(viewerType.equals("Time Series")) {
					for(Viewer view : currentViewers) {
						if(view instanceof TimeGraph) cs.dettach(view);
					}
				}
			}
		});
		

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		//All available methods stored in array then ComboBox
		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("CO2 emissions vs Energy use vs PM2.5 air pollution");
		methodsNames.add("PM2.5 air pollution vs Forest area");
		methodsNames.add("Ratio of CO2 emissions and GDP per capita");
		methodsNames.add("Average forest area (% of land area)");
		methodsNames.add("Government expendeture on education (% of GDP)");
		methodsNames.add("Hospital beds vs health expendeture (Both per 1000)");
		methodsNames.add("Health expendeture per capita vs Infant mortality rate");
		methodsNames.add("Government expendeture on education vs Health expendeture");

		final JComboBox<String> methodsList = new JComboBox<String>(methodsNames);
		
		//When recalculate button is pressed
		recalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String analysis = methodsList.getSelectedItem().toString();
				//Find the "anaysis code" chosen
				int aCode = methodsList.getSelectedIndex();
				if(aCode == 0) analysis = "AnalysisA";
				else if (aCode == 1) analysis = "AnalysisB";
				else if (aCode == 2) analysis = "AnalysisC";
				else if (aCode == 3) analysis = "AnalysisD";
				else if (aCode == 4) analysis = "AnalysisE";
				else if (aCode == 5) analysis = "AnalysisF";
				else if (aCode == 6) analysis = "AnalysisG";
				else if (aCode == 7) analysis = "AnalysisH";
				
				Selection selection = new Selection();
				
				String country = countriesList.getSelectedItem().toString();
				String countryCode = null;
				// Convert chosen country to the ISO 3 digit code
				if(country.equals("USA")) countryCode = "USA";
				else if(country.equals("Canada")) countryCode = "CAN";
				else if(country.equals("France")) countryCode = "FRA";
				else if(country.equals("China")) countryCode = "CHN";
				else if(country.equals("Brazil")) countryCode = "BRA";
				else if(country.equals("Mexico")) countryCode = "MEX";
				else if(country.equals("Sudan")) countryCode = "SDN";
				else if(country.equals("Russia")) countryCode = "RUS";
				else if(country.equals("Germany")) countryCode = "DEU";
				else if(country.equals("Chile")) countryCode = "CHL";
				
				ArrayList<Viewer> vis = new ArrayList<Viewer>();
				
				Boolean check = IncompatibleChecker.validateSelection(country, Integer.valueOf(
						fromList.getSelectedItem().toString()),Integer.valueOf(toList.getSelectedItem().toString()), analysis);
				
				if(check) {
					
					west.removeAll();
					west.revalidate();
					west.repaint();
					
					// Add all info to selection object
					selection.setAnalysisType(analysis);
					selection.setCountry(countryCode);
					selection.setViewers(vis);
					selection.setYearStart(Integer.valueOf(fromList.getSelectedItem().toString()));
					selection.setYearEnd(Integer.valueOf(toList.getSelectedItem().toString()));
					
					// Setup result from recalculate
					Result res = cs.recalculateButton(selection);
				}
				else {
					JOptionPane.showMessageDialog(null, 
                            "Incompatible years/analysis type chosen", 
                            "Error", 
                            JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		
		methodsList.addItemListener (new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        west.removeAll();
		        west.revalidate();
				west.repaint();
		    }
		});
		
		
		//Add components to south bar
		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(4, 600));	//So window is decent size at start

		// Set charts region
		
		west.setLayout(new GridLayout(2, 0));


		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		
	}
	
	/**
	 * Return the array corresponding to each type "code"
	 * index 0 is full description, 1 is first part of analysis with 2 and 3 being more types if included
	 * @param res
	 * @return
	 */
	public static String[] decodeType(Result res) {
		if(res.getType().equals("AnalysisA")) {
			return new String[] {"CO2 emissions vs Energy use vs PM2.5 air pollution", "CO2 emissions (metric tons per capita)",
					"Energy use (kg of oil equivalent per capita)", "PM2.5 air pollution (micrograms per cubic meter) "};
		}
		else if(res.getType().equals("AnalysisB")) {
			return new String[] {"PM2.5 air pollution vs Forest area", "PM2.5 air pollution (micrograms per cubic meter)",
					"Forest area (% of land)"};
		}
		else if(res.getType().equals("AnalysisC")) {
			return new String[] {"Ratio of CO2 emissions and GDP per capita", "CO2 emissions/GDP (Metric tons/US$) per capita",};
		}
		else if(res.getType().equals("AnalysisD")) {
			return new String[] {"Average forest area (% of land area)", "Average forest area (% of land area)"};
		}
		else if(res.getType().equals("AnalysisE")) {
			return new String[] {"Government expendeture on education (% of GDP)", "Government expendeture on education (% of GDP)"};
		}
		else if(res.getType().equals("AnalysisF")) {
			return new String[] {"Hospital beds vs health expendeture", "Hospital beds (per 1000 people)",
					"Current health expenditure US$ (per 1000 people)"};
		}
		else if(res.getType().equals("AnalysisG")) {
			return new String[] {"Health expendeture per capita vs Infant mortality rate", "Health expendeture per capita (US$)",
					"Infant mortality rate (per 1000 live births)"};
		}
		else if(res.getType().equals("AnalysisH")) {
			return new String[] {"Ratio of Government expendeture on education vs Health expendeture", " Government expenditure on education (% of GDP)",
					"Health expenditure (% of GDP)"};
		}
		else return null;
	}
	
	/**
	 * Draw viewers to JPanel
	 * @param chart
	 */
	public static void draw(ChartPanel chart) {
		
		west.add(chart);
		west.revalidate();
		west.repaint();
		
	}

}