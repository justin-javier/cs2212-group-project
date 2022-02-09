package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
/**
 * @author justin, jason
 *Reader class will read infromation from the API for the viewer classes to render their respective graphs to the user
 */
public class Reader 
{
	public static double[] retrieve(int startYear, int endYear, String country, String stat) 
	{
		double[] data = new double[endYear - startYear + 1];
		//setup the URL string using the parameters provided
		String urlString = String.format("http://api.worldbank.org/v2/country/%1$s/indicator/%2$s?date=%3$s:%4$s&format=json", 
						   country, stat, String.valueOf(startYear), String.valueOf(endYear));
        System.out.println(urlString);
        
        //int to hold the value to be saved
        double yearData = 0;
        try
        {
        	//setting up URL and connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            
            int responseCode = conn.getResponseCode();
            //if connection was established
            if (responseCode == 200)
            {
            	String inline = "";
                Scanner sc = new Scanner(url.openStream());
                
                //while loop to place all lines on to one string 
                while (sc.hasNext()) 
                {
                    inline += sc.nextLine();
                }
                sc.close();
                //holds Json results from string 
                JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
                
                //counting how many results there are
                int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
                int year = 0;
                int j = sizeOfResults - 1; //array counter for data in decreasing order
                
				for (int i = 0; i < sizeOfResults; i++) 
				{
					year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
					
					//if there is no data for this year
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
						yearData = 0;
					else
						yearData = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsDouble();
					
					data[j] = yearData;
					j--;
					System.out.println("Data for : " + year + " is " + yearData);
				}
                System.out.println(Arrays.toString(data));
            }
            
        }
        catch (IOException e)
        {
        	
        }
		
		return data;
	}
	public static void main(String args[]) {
		Reader.retrieve(2005, 2006, "can", "SP.POP.TOTL"); 
	}
}
