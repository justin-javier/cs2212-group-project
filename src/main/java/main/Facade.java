package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Facade class
 * @author 	Michael
 */
public class Facade {
	//INSTANCE VARIABLES
	/**
	* model object that stores the computed stats that viewers display
	*/
	private Model data; 
	/**
	* List of specific types of strategies to be applied depending on what data is needed
	*/
	private Strategy strategy;
	
	public Facade()
	{
		//set up example model
		strategy = new Analysis1Strategy();
		String title = "CO2 Emissions vs Energy Use vs PM2.5 Air Pollution";
		String[] statNames = {"CO2 Emissions in Tons/Capita", "Energy Use in kg of oil/Capita", 
							"PM2.5 Air Pollution in micrograms/Cubic m"};
		String[] axisNames = {"","Micrograms/Cubic meter"};
		
		data = new Model(title, statNames, axisNames, 2010, strategy.doAlgorithm(2010, 2015,"CAN"));
		BarChart barchart = new BarChart(data);
		LineChart linechart = new LineChart(data);
		
		data.attachViewer(barchart);
		data.attachViewer(linechart);
              
	}
	//INSTANCE METHODS 
	/**
	* calculates the stats for the provided country/years/analysis-type and updates viewers accordingly
	*/
	public void recalculate(int startYear, int endYear, String country, int analysisType){
		//check if the selected analysis type can be performed for the selected country 
		boolean analysisCanBePerformed = false;
		try {
		    FileReader fr=new FileReader("src/country_analysis.txt");    
		    BufferedReader br = new BufferedReader(fr);
		    String thisLine, key;
		    String[] line;
		  
		    while ((thisLine = br.readLine()) != null) {
		    	//save array of the file's line
		    	line = thisLine.split(",");
		    	//get the key of the analysis
				key = line[0];
				
				//new array without the key
			    String[] temp = new String[line.length - 1];
				if (analysisType == Integer.parseInt(key)) {
					for (int i = 0; i < line.length - 1; i++)
					{
						temp[i] = line[i+1];
					}
					for(String element : temp) {
						if (element.equals(country))
							analysisCanBePerformed = true;
					}
				}
		    }
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		} catch (NumberFormatException e) {
		    System.out.println("Error in analysis file: analysis type is not integer.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//do the analysis if the selected analysis in valid for the selected country. print message if it is not
		if(!analysisCanBePerformed) {
			System.out.println("Sorry! Data fetching and/or processing is not available for the selected country");
		}else {
			//set strategy to the appropriate strategy
			switch (analysisType) {
				case(1):
					strategy = new Analysis1Strategy();
					break;
				case(2):
					strategy = new Analysis2Strategy();
					break;
				case(3):
					strategy = new Analysis3Strategy();
					break;
				case(4):
					strategy = new Analysis4Strategy();
					break;
				case(5):
					strategy = new Analysis5Strategy();
					break;
				case(6):
					strategy = new Analysis6Strategy();
					break;
				case(7):
					strategy = new Analysis7Strategy();
					break;
				case(8):
					strategy = new Analysis8Strategy();
					break;
			}
			//perform strategy
			data.setStats(strategy.doAlgorithm(startYear, endYear, country));
			data.setStartYear(startYear);
			data.notifyObservers();
		}
	}
	/**
	* mutator to return model object
	 * @return 
	*/
	public Model getModel(){
		return data;
	}
	/**
	 * sets the model given the data in model
	 */
	public void setModel(Model model)
	{
		this.data = model;
	}
}
