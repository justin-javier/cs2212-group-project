package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author justin
 * BarChart class forms the bar chart that will be displayed to the user using the given information requested
 * by the user
 */
public class BarChart extends Viewer{
	private static DefaultCategoryDataset dataset1;
	private static DefaultCategoryDataset dataset2;
	private static CategoryPlot plot;
	private static JFreeChart barChart;
	private static ChartPanel chartPanel;
	private static String type;
	/**
	 * @param model contains the information requested by the user for the desired bar chart
	 */
	public BarChart(Model model)
	{
		type = "Bar Chart";//set type to bar chart
		dataset1 = new DefaultCategoryDataset();//create data sets to be loaded with data
		dataset2 = new DefaultCategoryDataset();
		BarRenderer barrenderer1 = new BarRenderer();//variables to be rendered based on data
		BarRenderer barrenderer2 = new BarRenderer();
		
		String statName1 = model.getStatNames()[0];		
		plot = new CategoryPlot();
		// check for the number of stats that will be needed for the comparison
		if (model.getNumStats() == 1)
		{
			//loop and set value of each dataset until the model data ends
			for (int j = 0; j < model.getSize(); j++)
			{
				String year = String.valueOf(model.getStartYear() + j);
				dataset1.setValue(model.getValue(0, j), statName1, year);
			}
			
		}
		//check if number of stats is 2
		else if (model.getNumStats() == 2)
		{
			String statName2 = model.getStatNames()[1];
			// loop and set the data of the dataset to be rendered
			for (int j = 0; j < model.getSize(); j++)
			{
				String year = String.valueOf(model.getStartYear() + j);
				dataset1.setValue(model.getValue(0, j), statName1, year);
				dataset1.setValue(model.getValue(1, j), statName2, year);
			}

			plot.setDataset(1, dataset1);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis(model.getAxisNames()[1]));

			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		}
		else if (model.getNumStats() == 3)
		{
			String statName2 = model.getStatNames()[1];
			String statName3 = model.getStatNames()[2];
			dataset2 = new DefaultCategoryDataset();

			for (int j = 0; j < model.getSize(); j++)
			{
				String year = String.valueOf(model.getStartYear() + j);
				dataset1.setValue(model.getValue(0, j), statName1, year);
				dataset1.setValue(model.getValue(1, j), statName2, year);
				//for the third, separate stat
				dataset2.setValue(model.getValue(2, j), statName3 , year);
			}			

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis(model.getAxisNames()[1]));

			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		}
		// after each check, form the bar chart using the given information with the data sets
		plot.setDataset(0, dataset1);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(model.getAxisNames()[0]));
		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

		barChart = new JFreeChart(model.getTitle(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		
		chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
	}
	/**
	 * update method will update the model based on the user's desired selection
	 */
	public void update(Model model)
	{

		String statName1 = model.getStatNames()[0];
		String statName2 = model.getStatNames()[1];
		dataset1.clear();
		dataset2.clear();
		//repeat the same process as creating a new bar chart
		if (model.getNumStats() == 1)
		{
			for (int j = 0; j < model.getSize(); j++)
			{
				String year = String.valueOf(model.getStartYear() + j);
				dataset1.setValue(model.getValue(0, j), statName1, year);
			}
			
		}
		else if (model.getNumStats() == 2)
		{
			for (int j = 0; j < model.getSize(); j++)
			{
				String year = String.valueOf(model.getStartYear() + j);
				dataset1.setValue(model.getValue(0, j), statName1, year);
				dataset1.setValue(model.getValue(1, j), statName2, year);
			}

		}
		else if (model.getNumStats() == 3)
		{
			String statName3 = model.getStatNames()[2];

			for (int j = 0; j < model.getSize(); j++)
			{
				String year = String.valueOf(model.getStartYear() + j);
				dataset1.setValue(model.getValue(0, j), statName1, year);
				dataset1.setValue(model.getValue(1, j), statName2, year);
				//for the third, separate stat
				dataset2.setValue(model.getValue(2, j), statName3 , year);
			}	

		}
	}
	/**
	 * @return return the chartpanel when requested
	 */
	public ChartPanel getChartPanel()
	{
		return chartPanel;
	}
	/**
	 * @return return a string containing the type
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
