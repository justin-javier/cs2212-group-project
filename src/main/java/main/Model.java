package main;

import java.util.ArrayList;
//IMPORT STATEMENTS
import java.util.List;

/**
* Model class
* @author 	Michael, Justin
*/

public class Model {
	//INSTANCE VARIABLES
	/**
	 * Title of the analysis chosen
	 */
	private String title;
	/**
	 * Array containing the stats; 3xsize to hold up to 3 sets of stats. 
	 */
	private double[][] stats; 
	/**
	 * The start year specified.
	 */
	private int startYear;
	/**
	 * List containing viewers currently attached to the model.
	 */
	private List<Viewer> viewerList;
	/**
	 * Array containing the name of the statistic respective to the stats.
	 */
	private String[] statNames;
	/**
	 * Array containing the appropriate axis names respective to the stats.
	 */
	private String[] axisNames;
	
	//CONSTRUCTOR
	/**
	 * Constructor for the Model Class
	 * @param size to initialize object with     
	 * @param numStats	numStats to initialize object with   
	 * @param stats	 sets of stats of country info
	 */
	public Model(String title, String[] statNames, String[] axisNames, 
				 int startYear, double[][] stats) {
		this.title = title;
		this.statNames = statNames;
		this.axisNames = axisNames;
		this.startYear = startYear;
		this.stats = stats;
		viewerList = new ArrayList<Viewer>();
	}
	
	//INSTANCE METHODS
	/**
	 * getSize accessor method 
	 * @return title of analysis
	 */
	public String getTitle(){
		return title;
	}
	/**
	 * getStatNames accessor method 
	 * @return array of statNames
	 */
	public String[] getStatNames(){
		return statNames;
	}
	/**
	 * getAxisNames accessor method 
	 * @return array of axis names
	 */
	public String[] getAxisNames(){
		return axisNames;
	}
	/**
	 * getValue accessor method 
	 * @param stat index of stat to    
	 * @param numStats	numStats to initialize object with   
	 */
	public double getValue(int index, int year){
		return stats[index][year];
	}
	/**
	 * setStats mutator method 
	 * @param stats	 new set of stats
	 */
	public void setStats(double[][] stats){
		 this.stats = stats;
	}
	/**
	 * getSize accessor method 
	 * @return size of Model
	 */
	public int getSize(){
		return stats[0].length;
	}
	/**
	 * getNumStats accessor method 
	 * @return number of stats of Model
	 */
	public int getNumStats(){
		return stats.length;
	}
	/**
	 * getStartYear accessor method 
	 * @return start year int
	 */
	public int getStartYear()
	{
		return startYear;
	}
	
	public void setStartYear(int year)
	{
		this.startYear = year;
	}
	/**
	 * attachViewer attaches viewer to model
	 * @param newViewer	the new viewer to be attached
	 */
	public void attachViewer(Viewer newViewer) {
		viewerList.add(newViewer);
	}
	/**
	 * detachViewer attaches viewer to model
	 * @param oldViewer	the old viewer to be detached 
	 */
	public void detachViewer(String oldViewer) {
		
		for (int i = 0; i < viewerList.size(); i++)
		{
			if (oldViewer == viewerList.get(i).getType())
			{
				viewerList.remove(i);
			}
		}
	}
	public Viewer getViewer(String type)
	{
		System.out.println(viewerList.size());
		for (int i = 0; i < viewerList.size(); i++)
		{
			System.out.println(viewerList.get(i).getType());
			if (type == viewerList.get(i).getType()) return viewerList.get(i);
		}
		return null;
	}
	public List<Viewer> getViewerList()
	{
		return viewerList;
	}
	/**
	 * notifyObservers	notifies the viewers that are attached to the model
	 */
	public void notifyObservers() {
		for (int i = 0; i < viewerList.size(); i++) {
			viewerList.get(i).update(this);
		}
	}
	public boolean checkViewer(String type)
	{
		for (int i = 0; i < viewerList.size(); i++)
		{
			if (type == viewerList.get(i).getType()) return true;
		}
		return false;
	}

}
