package main;
/**
 * 7th concrete strategy class 
 * @author 	Michael
 */
public class Analysis7Strategy extends Strategy {
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
		temp[0] = Reader.retrieve(startYear, endYear, country, "SH.XPD.CHEX.PC.CD");
		temp[1] = Reader.retrieve(startYear, endYear, country, "SP.DYN.IMRT.IN");

		return temp;
	}
}
