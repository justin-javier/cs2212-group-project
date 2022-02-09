package main;
/**
 * 2nd concrete strategy class 
 * @author 	Michael
 */
public class Analysis2Strategy extends Strategy {
	/**
	 * retrieves performs an algorithm on the requested set of data and returns that data
	 * @param startYear	the first year in the range of years to retrieve data for 
	 * @param endYear	the last year in the range of years to retrieve data for 
	 * @param country	the country to retrieve the data for
	 * @return	the computed set of data
	 */
	@Override
	public double[][] doAlgorithm(int startYear, int endYear, String country) {
		double[][] temp = new double[2][];
		temp[0] = Reader.retrieve(startYear, endYear, country, "AG.LND.FRST.ZS");
		temp[1] = Reader.retrieve(startYear, endYear, country, "EN.ATM.PM25.MC.M3");

		return temp;
	}

}
