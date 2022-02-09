package main;

import org.jfree.chart.ChartPanel;
import javax.swing.JScrollPane;

/**
 * @author justin
 * viewer works in conjunction with the specific viewer classes to provide the user with their
 * desired data selection and graph
 */
public abstract class Viewer 
{	
	public abstract String getType();
	public abstract void update(Model model);
	public abstract ChartPanel getChartPanel();
	public abstract JScrollPane getPane();

}
