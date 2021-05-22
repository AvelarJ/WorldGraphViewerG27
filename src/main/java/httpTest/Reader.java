package httpTest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;


/**
 * Reader class to issue HTTP GET Request to World Bank API. Returns a populated data object.
 * @author Sasa Vecerak
 */
public class Reader {

	/**
	 * Returns a data object containing a list of years and list of values for the indicated analysis type.
	 * @param country 		The selected country name.
	 * @param yearStart	 	The selected starting year.
	 * @param yearEnd 		The selected ending year.
	 * @param analysisID 	The type of analysis chosen.
	 * @return
	 */
	public static Data fetch(String country, int yearStart, int yearEnd, String analysisID) {
		
		// End point
		
        String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/"+analysisID+"?date="+yearStart+":"+yearEnd+"&format=json", country);
        float valueForYear = 0;
        
        // Create empty Data object to house fetched data
        
        Data data = new Data();
        
        // Create empty lists to store values/years 
        
        ArrayList<Integer> years = new ArrayList<Integer>();
        ArrayList<Float> values = new ArrayList<Float>();

        try {
        	URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();

                // If response is 200, proceed, get line with results

            if (responsecode == 200) {
            	String inline = "";
                Scanner sc = new Scanner(url.openStream());

                while (sc.hasNext()) {
                	inline += sc.nextLine();
                }

                sc.close();

                // Process JSON as one line

                JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
                int size = jsonArray.size();
                int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
                int year = 0;

                for (int i = 0; i < sizeOfResults; i++) {

                	// Get the year for each entry

					year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
					years.add(year);

                    // Check if here is a value for the population in given year

                    if(jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
                    	valueForYear = 0;
                    	values.add(valueForYear);
                    }
                    
                    else {

                    // Get population For the given year from the “value” field

                    	valueForYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsFloat();
                        values.add(valueForYear);
                    }
                 }
             }

          } catch (IOException e) {
                // TODO Auto-generated catch block e.printStackTrace();
          }
        
        // Populate Data object for given Indicator ID
       
        data.setValues(values);
        data.setYears(years);
        
        return data;		
	}
}
