package main;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

/**
 * @author justin
 * PieChart class creates a piechart to be rendered back to the user given their data selected
 *
 */
public class PieChart extends Viewer{
	
	private static DefaultCategoryDataset dataset;
	private static JFreeChart pieChart;
	private static ChartPanel chartPanel;
	private static String type;
	
	/**
	 * @param model contains information on the user's data comparison selection
	 * creates the pie chart to be displayed to the user
	 */
	public PieChart(Model model)
	{
		type = "Pie Chart";//set the type to pie chart
		dataset = new DefaultCategoryDataset();
		int endYear = model.getStartYear() + model.getSize() - 1;
		String yearPeriod = model.getStartYear() + " to " + endYear;
		
		dataset.addValue(model.getValue(0, 0), model.getStatNames()[0], "From " + yearPeriod);
		dataset.addValue(model.getValue(0, 1), "Other", "From " + yearPeriod);
		
		pieChart = ChartFactory.createMultiplePieChart(model.getTitle(), dataset, TableOrder.BY_COLUMN, true, 
				  true, false);
		
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
	}
	/**
	 *@return return the chartpanel
	 */
	public ChartPanel getChartPanel()
	{
		return chartPanel;
	}
	/**
	 * update will update the pie chart based on the user's selection
	 */
	public void update(Model model)
	{
		if (model.getNumStats() == 1)
		{
			int endYear = model.getStartYear() + model.getSize() - 1;
			String yearPeriod = model.getStartYear() + " to " + endYear;

			dataset.setValue(model.getValue(0, 0), model.getStatNames()[0], "From " + yearPeriod);
			dataset.setValue(100 - model.getValue(0, 0), "Other", "From " + yearPeriod);
			//System.out.println(dataset.getValue(model.getStatNames()[0], "Analysis"));
			/*
			pieChart = ChartFactory.createMultiplePieChart(model.getTitle(), dataset, TableOrder.BY_COLUMN, true, 
					  true, false);
			chartPanel.setChart(pieChart);
			*/
		
		}
	}
	/**
	 *@return returns a string containing the type
	 */
	public String getType()
	{
		return type;
	}
	public JScrollPane getPane()
	{
		return null;
	}

}
