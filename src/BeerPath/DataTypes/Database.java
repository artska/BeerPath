/*
 * Class meant to load CSV files as well as take specific information
 * once loaded.
 */
package BeerPath.DataTypes;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.ExcelCSVParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;


public class Database {
    String s = "D:\\Java stuff\\JavaApplication1\\src\\BeerPath"; //needs to be changed according to file directory
    HashMap<Integer, Geocode> geocodes = new HashMap(1500);
    HashMap<Integer, LinkedList<Beer>> beers = new HashMap(1500);
    LinkedList<Brewery> breweries = new LinkedList();
    
    public Database() throws FileNotFoundException, IOException {
        readBreweries();
        readBeers();
        readGeocodes();
    }
    
    public void readBreweries() throws FileNotFoundException, IOException{
        CSVParser parser = new CSVParser(new FileReader("\\breweries.csv"));
        String[] line;
        line = parser.getLine();
        while ((line = parser.getLine()) != null) {
            if (!line.equals("")){
                int id = Integer.parseInt(line[0]);
                breweries.add(new Brewery(id, line[1]));
            }
        }
        parser.close();
    }
    
    public void readBeers() throws FileNotFoundException, IOException {
        ExcelCSVParser parser = new ExcelCSVParser(new FileReader(s + "\\beers.csv"));
        String[] line;
        line = parser.getLine();
        while ((line = parser.getLine()) != null) {
            if (!line.equals("")){
                int id = Integer.parseInt(line[0]);
                int key = Integer.parseInt(line[1]);
                Beer temp = new Beer(id, line[2]);
                beers.putIfAbsent(key, new LinkedList());
                beers.get(key).add(temp);
            }            
        }
        parser.close();
    }
    
    public void readGeocodes() throws FileNotFoundException, IOException{
        CSVParser parser = new CSVParser(new FileReader(s + "\\geocodes.csv"));
        String[] line;
        line = parser.getLine();
        while ((line = parser.getLine()) != null) {
            if (!line.equals("")){
                int brewery_id = Integer.parseInt(line[1]);
                double latitude = Double.parseDouble(line[2]);
                double longitude = Double.parseDouble(line[3]);
                geocodes.put(brewery_id, new Geocode(brewery_id, latitude, longitude));
            }
        }
        parser.close();
    }
    
    /**
     * Looks for an ideal location to head to from the current one.
     * @param current - current location
     * @param previous - list of prior breweries
     * @param home - initial location
     * @param currentDistance - total distance traveled so far
     * @return brewery to fly to next, or null if none can be traveled to.
     */
    public Brewery getDestination(LatLong current, LinkedList<Brewery> previous, LatLong home, double currentDistance) {
        boolean isPrevious = false;
        double minCost = 0;
        Brewery minBrewery = null;
        for (Brewery b: breweries){
            for (Brewery p: previous) {
                if (p.getName().equals(b.getName()))
                    isPrevious = true;
            }
            if (!isPrevious && geocodes.get(b.getID()) != null){
                LatLong destination = geocodes.get(b.getID()).getCoordinates();
                double distanceToDestination = current.getDistance(destination);
                double distanceToHome = destination.getDistance(home);
                if ((currentDistance + distanceToDestination + distanceToHome) < 2000 && beers.get(b.getID()) != null) {
                    double cost = distanceToDestination / beers.get(b.getID()).size();
                    if (cost < minCost || minBrewery == null){
                        minCost = cost;
                        minBrewery = b;
                    }
                }
            }
            isPrevious = false;
        }
        return minBrewery;
    }

    /**
     * Gets the latitude and longitude of a location based on its ID number.
     * @param id - ID number of a brewery in the database.
     * @return latitude and longitude of the location.
     */
    public LatLong getCoordinates(int id) {
        return geocodes.get(id).getCoordinates();
    }

    public LinkedList<Beer> getBeers(int id) {
        return beers.get(id);
    }
}
