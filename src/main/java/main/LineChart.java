package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

/**
 * @author justin, jason
 * LineChart class will produce a line chart for the viewer class to display to the user when requested
 */
public class LineChart extends Viewer 
{
	private static TimeSeries series1;
	private static TimeSeries series2;
	private static TimeSeries series3;
	private static TimeSeriesCollection dataset1;
	private static TimeSeriesCollection dataset2;
	private static ChartPanel chartPanel;
	private static String type;

	/**
	 * @param model contains information on how to form/render the line chart
	 */
	public LineChart(Model model)
	{
		type = "Line Chart";
		series1 = new TimeSeries(model.getStatNames()[0]);
		series2 = new TimeSeries(model.getStatNames()[1]);
		dataset1 = new TimeSeriesCollection();
		dataset2 = new TimeSeriesCollection();

		if (model.getNumStats() == 1)
		{
			for (int i = 0; i < model.getSize(); i++)
			{
				int year = model.getStartYear() + i;
				series1.add(new Year(year), model.getValue(0, i));
			}
			dataset1.addSeries(series1);
		}
		else if (model.getNumStats() == 2)
		{
			for (int i = 0; i < model.getSize(); i++)
			{
				int year = model.getStartYear() + i;
				series1.add(new Year(year), model.getValue(0, i));
				series2.add(new Year(year), model.getValue(1, i));
			}
			dataset1.addSeries(series1);
			dataset2.addSeries(series2);
			
		}
		else if (model.getNumStats() == 3)
		{
			series3 = new TimeSeries(model.getStatNames()[2]);
			for (int i = 0; i < model.getSize(); i++)
			{
				int year = model.getStartYear() + i;
				series1.add(new Year(year), model.getValue(0, i));
				series2.add(new Year(year), model.getValue(1, i));
				series3.add(new Year(year), model.getValue(2, i));
			}
			dataset1.addSeries(series1);
			dataset1.addSeries(series2);
			dataset2.addSeries(series3);

		}
		
		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		XYSplineRenderer splinerenderer2 = new XYSplineRenderer();
		
		plot.setDataset(0, dataset1);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(model.getAxisNames()[0]));
		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

		if (model.getNumStats() > 1)
		{
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, splinerenderer2);
			plot.setRangeAxis(1, new NumberAxis(model.getAxisNames()[1]));
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		}
		
		JFreeChart chart = new JFreeChart(model.getTitle(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
	}
	/**
	 *@return returns the chartpanel
	 */
	public ChartPanel getChartPanel()
	{
		return chartPanel;
	}
	/**
	 * @param contains information on how the line chart needs to be updated
	 */
	public void update(Model model)
	{
		series1.clear();
		series2.clear();
		dataset1.removeAllSeries();
		dataset2.removeAllSeries();

		if (model.getNumStats() == 1)
		{
			for (int i = 0; i < model.getSize(); i++)
			{
				int year = model.getStartYear() + i;
				series1.add(new Year(year), model.getValue(0, i));
			}
			dataset1.addSeries(series1);
		}
		else if (model.getNumStats() == 2)
		{
			for (int i = 0; i < model.getSize(); i++)
			{
				int year = model.getStartYear() + i;
				series1.add(new Year(year), model.getValue(0, i));
				series2.add(new Year(year), model.getValue(1, i));
			}
			dataset1.addSeries(series1);
			dataset2.addSeries(series2);
		}
		else if (model.getNumStats() == 3)
		{
			series3.clear();
			for (int i = 0; i < model.getSize(); i++)
			{
				int year = model.getStartYear() + i;
				series1.add(new Year(year), model.getValue(0, i));
				series2.add(new Year(year), model.getValue(1, i));
				series3.add(new Year(year), model.getValue(2, i));
			}
			dataset1.addSeries(series1);
			dataset1.addSeries(series2);
			dataset2.addSeries(series3);
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
