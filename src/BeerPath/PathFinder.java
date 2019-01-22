/*
 * Class holding methods to get an ideal path as well as return a list of beer
 * types obtained through said path.
 */
package BeerPath;

import java.util.LinkedList;
import BeerPath.DataTypes.Beer;
import BeerPath.DataTypes.Brewery;
import BeerPath.DataTypes.Database;
import BeerPath.DataTypes.LatLong;

public class PathFinder {
    /**
     * Finds an ideal path based on the number of different beer types at each
     * brewery, as well as the distance to get to it.
     * @param database - a database used to get information on breweries.
     * @param home - initial location.
     * @return a list of locations, in order of visiting, excluding the home
     * location.
     */
    public LinkedList<Brewery> getPath(Database database, LatLong home){
        LinkedList<Brewery> path = new LinkedList();
        LatLong current = home;
        double currentDistance = 0;
        Brewery b = database.getDestination(current, path, home, currentDistance);
        while (b != null) {
            currentDistance = currentDistance + database.getCoordinates(b.getID()).getDistance(current);
            current = database.getCoordinates(b.getID());
            path.add(b);
            b = database.getDestination(current, path, home, currentDistance);
        }
        return path;
    }

    /**
     * Using the database provided, finds the full list of beer types found in a 
     * list of beer factories, removing duplicates along the way.
     * @param database - a database holding information about the breweries.
     * @param path - list of breweries to search.
     * @return a list of beer types found across all of the breweries.
     */
    LinkedList<Beer> getBeerList(Database database, LinkedList<Brewery> path) {
        LinkedList<Beer> list = new LinkedList();
        for (Brewery b: path)
            for (Beer beer: database.getBeers(b.getID())){
                boolean isDuplicate = false;
                for (Beer comp: list)
                    if (comp.getName().equals(beer))
                        isDuplicate = true;
                if (!isDuplicate) 
                    list.add(beer);
            }
        return list;
    }
    
   
}
