/*
 * Class to format and print all the available data.
 */
package BeerPath;

import java.util.LinkedList;
import BeerPath.DataTypes.Beer;
import BeerPath.DataTypes.Brewery;
import BeerPath.DataTypes.Database;
import BeerPath.DataTypes.LatLong;

class Printer {
    public void printBreweries(LinkedList<Brewery> breweries, Database data, LatLong home){
        double totalDistance = 0;
        LatLong previous = home;
        System.out.println("Found " + breweries.size() + " beer factories:");
        System.out.printf("     -> HOME: %2.8f %2.8f distance 0km\r\n", 
                home.getLatitude(), home.getLongitude());
        for (Brewery b: breweries){
            LatLong coordinates = data.getCoordinates(b.getID());
            double distance = coordinates.getDistance(previous);
            System.out.printf("     -> [%d] %s: %2.8f %2.8f distance %3.2fkm\r\n", 
                    b.getID(), b.getName(), coordinates.getLatitude(), 
                    coordinates.getLongitude(), distance);
            previous = coordinates;
            totalDistance += distance;
        }
        totalDistance += previous.getDistance(home);
        System.out.printf("     <- HOME: %2.8f %2.8f distance %3.2fkm\r\n", 
                home.getLatitude(), home.getLongitude(), previous.getDistance(home));
        System.out.println();
        System.out.printf("Total distance travelled: %4.1fkm\r\n", totalDistance);
        System.out.println("\r\n");
    }
    
    public void printBeers(LinkedList<Beer> beers){
        System.out.println("Collected " + beers.size() + " beer types:");
        for (Beer beer: beers){
            System.out.println("     -> " + beer.getName());
        }
    }
}
