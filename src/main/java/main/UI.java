package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @author justin 
 * UI class renders the main program after logging in.
 * the UI will display graphs and buttons for data, year, graph selection
 */
public class UI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static UI instance;
	private static JComboBox<String> countriesList;
	private static JComboBox<String> fromList;
	private static JComboBox<String> toList;
	private static JComboBox<String> viewsList;
	private static JComboBox<String> methodsList;
	private static JPanel north;
	private static JPanel east;	
	private static JPanel south;
	private static JPanel west;

	private static Facade facade;
	
	/**
	 * @return return a new instance of UI
	 */
	public static UI getInstance()
	{
		if (instance == null)
			instance = new UI();

		return instance;
	}

	/**
	 * creates the labels and data drop downs for data selection by the user
	 */
	public UI() 
	{
		// Set window title
		super("Country Statistics");

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("USA");
		countriesNames.add("CAN");
		countriesNames.add("FRA");
		countriesNames.add("CHN");
		countriesNames.add("BRA");
		countriesNames.sort(null);
		countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		//add the number of years available
		for (int i = 2021; i >= 2010; i--) 
		{
			years.add("" + i);
		}
		fromList = new JComboBox<String>(years);
		toList = new JComboBox<String>(years);
		//add data north
		north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");
		recalculate.addActionListener(this); //Adding listener to Recalculate
		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Report");
		viewsList = new JComboBox<String>(viewsNames);
		
		JButton addView = new JButton("+");
		addView.addActionListener(this); //Adding listener to +
		JButton removeView = new JButton("-");
		removeView.addActionListener(this); //Adding listener to -

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("CO2 Emissions vs Energy Use vs PM2.5 Air Pollution");
		methodsNames.add("PM2.5 Air Pollution vs Forest Area");
		methodsNames.add("Ratio of CO2 Emissions and GDP per capita");
		methodsNames.add("Average Forest Area");
		methodsNames.add("Average of Government Expenditure on Education");
		methodsNames.add("Ratio of Hospital Beds and Current Health Expenditure per 1000 people");
		methodsNames.add("Current Health Expenditure per capita vs Mortality Rate, infant");
		methodsNames.add("Ratio of Government Expenditure on Education and Current Health Expenditure");

		methodsList = new JComboBox<String>(methodsNames);
		//add data south
		south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		east = new JPanel();

		// Set charts region
		west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		west.setPreferredSize(new Dimension(1200, 600));

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		
		facade = new Facade();
		for (int i = 0; i < facade.getModel().getViewerList().size(); i++)
		{
			west.add(facade.getModel().getViewerList().get(i).getChartPanel());
		}
		
	}

	/**
	 * when an action is performed, check which and perform the user's desired function
	 */
	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand();
		//add the desired graph
		if (action.equals("+"))
		{
			String viewerName = viewsList.getSelectedItem().toString();
			System.out.println("+ was pressed!");
			System.out.println(viewerName);
	
			if (viewerName.equals("Pie Chart"))
			{
				if (facade.getModel().getNumStats() > 1)
				{
					JOptionPane.showMessageDialog(null, "Error: Stat invalid");
				}
				else if (facade.getModel().checkViewer("Pie Chart"))
				{
					JOptionPane.showMessageDialog(null, "Error: Pie Chart already added");
				}
				else
				{
					PieChart pieChart = new PieChart(facade.getModel());
					west.add(pieChart.getChartPanel());
					facade.getModel().attachViewer(pieChart);
				}
			}
			else if (viewerName.equals("Line Chart"))
			{
				if (facade.getModel().checkViewer("Line Chart"))
				{
					JOptionPane.showMessageDialog(null, "Error: Line Chart already added");
				}
				else
				{
					LineChart lineChart = new LineChart(facade.getModel());
					west.add(lineChart.getChartPanel());
					facade.getModel().attachViewer(lineChart);
				}
			}
			else if (viewerName.equals("Bar Chart"))
			{
				if (facade.getModel().checkViewer("Bar Chart"))
				{
					JOptionPane.showMessageDialog(null, "Error: Bar Chart already added");
				}
				else
				{
					BarChart barChart = new BarChart(facade.getModel());
					west.add(barChart.getChartPanel());
					facade.getModel().attachViewer(barChart);
				}
			}
			else //REPORT
			{
				Report report = new Report(facade.getModel());
				west.add(report.getPane());
				facade.getModel().attachViewer(report);
			}
			west.revalidate();
			west.repaint();
		}
		//remove the graph 
		else if (action.equals("-"))
		{
			System.out.println("- was pressed!");
			String viewerName = viewsList.getSelectedItem().toString();
			if (!facade.getModel().checkViewer(viewerName))
			{
				JOptionPane.showMessageDialog(null, "Error: Viewer is not in list");
			}
			else
			{
				Viewer v = facade.getModel().getViewer(viewerName);
				if (v.getType() != "Report")
				{
					west.remove(v.getChartPanel());
				}
				else
				{
					west.remove(v.getPane());
				}
				facade.getModel().detachViewer(viewerName);
			}
			west.revalidate();
			west.repaint();
		}
		//recalculate the graph based on user's selection
		else if (action.equals("Recalculate")) 
		{
			String analysis = methodsList.getSelectedItem().toString();
			String country = countriesList.getSelectedItem().toString();
			int fromYear = Integer.parseInt(fromList.getSelectedItem().toString());
			int toYear = Integer.parseInt(toList.getSelectedItem().toString());
			
			
			if (fromYear >= toYear)
			{
				JOptionPane.showMessageDialog(null, "Error: Choose different years");
			}
			//If the same analysis
			else if (analysis.equals(facade.getModel().getTitle()))
			{				
				int index = 0;
				while (index < 8)
				{
					System.out.println(index);
					if (methodsList.getItemAt(index).equals(analysis))
					{
						break;
					}
					index++;
				}
				facade.recalculate(fromYear, toYear, country, index + 1);
			}
			else
			{
				if (methodsList.getItemAt(0).equals(analysis))
				{
					String[] statNames = {"CO2 Emissions","Energy Use","PM2.5 Air Pollution"};
					String[] axisNames = {"Per Capita", "Micrograms per m^3"};
					Model m = new Model(analysis, statNames, axisNames, fromYear, null);
					facade.setModel(m);
					facade.recalculate(fromYear, toYear, country, 1);
				}
				else if (methodsList.getItemAt(1).equals(analysis))
				{
					String[] statNames = {"CO2 Emissions","Energy Use","PM2.5 Air Pollution"};
					String[] axisNames = {"Per Capita", "Micrograms per m^3"};
					Model m = new Model(analysis, statNames, axisNames, fromYear, null);
					facade.setModel(m);
					facade.recalculate(fromYear, toYear, country, 2);
				}
				else if (methodsList.getItemAt(2).equals(analysis))
				{
					String[] statNames = {"CO2 Emissions","Energy Use","PM2.5 Air Pollution"};
					String[] axisNames = {"Per Capita", "Micrograms per m^3"};
					Model m = new Model(analysis, statNames, axisNames, fromYear, null);
					facade.setModel(m);
					facade.recalculate(fromYear, toYear, country, 3);
				}
				else if (methodsList.getItemAt(3).equals(analysis))
				{
					String[] statNames = {"CO2 Emissions","Energy Use","PM2.5 Air Pollution"};
					String[] axisNames = {"Per Capita", "Micrograms per m^3"};
					Model m = new Model(analysis, statNames, axisNames, fromYear, null);
					facade.setModel(m);
					facade.recalculate(fromYear, toYear, country, 4);
				}
				else if (methodsList.getItemAt(4).equals(analysis))
				{
					String[] statNames = {"CO2 Emissions","Energy Use","PM2.5 Air Pollution"};
					String[] axisNames = {"Per Capita", "Micrograms per m^3"};
					Model m = new Model(analysis, statNames, axisNames, fromYear, null);
					facade.setModel(m);
					facade.recalculate(fromYear, toYear, country, 5);
				}
				else if (methodsList.getItemAt(5).equals(analysis))
				{
					String[] statNames = {"CO2 Emissions","Energy Use","PM2.5 Air Pollution"};
					String[] axisNames = {"Per Capita", "Micrograms per m^3"};
					Model m = new Model(analysis, statNames, axisNames, fromYear, null);
					facade.setModel(m);
					facade.recalculate(fromYear, toYear, country, 6);
				}
				else if (methodsList.getItemAt(6).equals(analysis))
				{
					String[] statNames = {"CO2 Emissions","Energy Use","PM2.5 Air Pollution"};
					String[] axisNames = {"Per Capita", "Micrograms per m^3"};
					Model m = new Model(analysis, statNames, axisNames, fromYear, null);
					facade.setModel(m);
					facade.recalculate(fromYear, toYear, country, 7);
				}
				else
				{
					String[] statNames = {"CO2 Emissions","Energy Use","PM2.5 Air Pollution"};
					String[] axisNames = {"Per Capita", "Micrograms per m^3"};
					Model m = new Model(analysis, statNames, axisNames, fromYear, null);
					facade.setModel(m);
					facade.recalculate(fromYear, toYear, country, 8);
				}
				west.removeAll();
			}
		
			west.repaint();
			west.revalidate();
			System.out.println("Recalculate was pressed!");

		}
	}

}
