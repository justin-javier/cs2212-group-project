package main;
/**
 * 1st concrete strategy class 
 * @author 	Michael
 */
public class Analysis1Strategy extends Strategy {
	@Override
	/**
	 * retrieves performs an algorithm on the requested set of data and returns that data
	 * @param startYear	the first year in the range of years to retrieve data for 
	 * @param endYear	the last year in the range of years to retrieve data for 
	 * @param country	the country to retrieve the data for
	 * @return	the computed set of data
	 */
	public double[][] doAlgorithm(int startYear, int endYear, String country) {
		double[][] temp = new double[3][endYear - startYear + 1];
		temp[0] = Reader.retrieve(startYear, endYear, country, "EN.ATM.CO2E.PC");
		temp[1] = Reader.retrieve(startYear, endYear, country, "EG.USE.PCAP.KG.OE");
		temp[2] = Reader.retrieve(startYear, endYear, country, "EN.ATM.PM25.MC.M3");
		
		/*
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < temp[0].length; j++) 
				data[i][j] = temp[i][j];
		}
		*/
		return temp;
	}
}
