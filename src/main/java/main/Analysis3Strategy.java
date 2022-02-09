package main;
/**
 * 3rd concrete strategy class 
 * @author 	Michael
 */
public class Analysis3Strategy extends Strategy{
	/**
	 * retrieves performs an algorithm on the requested set of data and returns that data
	 * @param startYear	the first year in the range of years to retrieve data for 
	 * @param endYear	the last year in the range of years to retrieve data for 
	 * @param country	the country to retrieve the data for
	 * @return	the computed set of data
	 */
	@Override
	public double[][] doAlgorithm(int startYear, int endYear, String country) {
		double[][] data = new double[3][];
		double[][] temp = new double[2][];
		temp[0] = Reader.retrieve(startYear, endYear, country, "EN.ATM.CO2E.PC");
		temp[1] = Reader.retrieve(startYear, endYear, country, "NY.GDP.PCAP.CD");
		for(int i = 0; i < temp[0].length; i++) 
			
			data[0][i] = temp[0][i]/temp[1][i];
		
		return data;
	}

}
