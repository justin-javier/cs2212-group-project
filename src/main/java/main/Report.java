package main;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;

public class Report extends Viewer 
{
	private static String type;
	private static JTextArea report;
	private static JScrollPane outputScrollPane;


	public Report(Model m)
	{
		type = "Report";
		report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage;
		reportMessage = m.getTitle() + "\n";
		for (int i = 0; i < m.getSize(); i++)
		{
			if (m.getNumStats() == 1)
			{
				reportMessage += (m.getStartYear() + i) + "\n";
				reportMessage += m.getStatNames()[0].toString() + ": ";
				reportMessage += (m.getValue(0,i)) + "\n";
			}
			else if (m.getNumStats() == 2)
			{
				reportMessage += (m.getStartYear() + i) + "\n";
				reportMessage += m.getStatNames()[0] + ": ";
				reportMessage += (m.getValue(0,i) + "\n");
				reportMessage += m.getStatNames()[1] + ": ";
				reportMessage += (m.getValue(1,i)) + "\n";
			}
			else
			{
				reportMessage += (m.getStartYear() + i) + "\n";
				reportMessage += m.getStatNames()[0] + ": ";
				reportMessage += (m.getValue(0,i) + "\n");
				reportMessage += m.getStatNames()[1] + ": ";
				reportMessage += (m.getValue(1,i)) + "\n";
				reportMessage += m.getStatNames()[2] + ": ";
				reportMessage += (m.getValue(2,i)) + "\n";
			}
		}
		report.setText(reportMessage);
		outputScrollPane = new JScrollPane(report);
		
	}
	public void update(Model m)
	{
		report.removeAll();
		String reportMessage;
		reportMessage = m.getTitle() + "\n";
		for (int i = 0; i < m.getSize(); i++)
		{
			if (m.getNumStats() == 1)
			{
				reportMessage += (m.getStartYear() + i) + "\n";
				reportMessage += m.getStatNames()[0].toString() + ": ";
				reportMessage += (m.getValue(0,i)) + "\n";
			}
			else if (m.getNumStats() == 2)
			{
				reportMessage += (m.getStartYear() + i) + "\n";
				reportMessage += m.getStatNames()[0] + ": ";
				reportMessage += (m.getValue(0,i) + "\n");
				reportMessage += m.getStatNames()[1] + ": ";
				reportMessage += (m.getValue(1,i)) + "\n";
			}
			else
			{
				reportMessage += (m.getStartYear() + i) + "\n";
				reportMessage += m.getStatNames()[0] + ": ";
				reportMessage += (m.getValue(0,i) + "\n");
				reportMessage += m.getStatNames()[1] + ": ";
				reportMessage += (m.getValue(1,i)) + "\n";
				reportMessage += m.getStatNames()[2] + ": ";
				reportMessage += (m.getValue(2,i)) + "\n";
			}
		}
		report.setText(reportMessage);
	}
	public String getType()
	{
		return type;
	}
	public JScrollPane getPane()
	{
		return outputScrollPane;
	}
	public ChartPanel getChartPanel()
	{
		return null;
	}
}
