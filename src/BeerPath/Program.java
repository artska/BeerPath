package BeerPath;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import BeerPath.DataTypes.Beer;
import BeerPath.DataTypes.Brewery;
import BeerPath.DataTypes.Database;
import BeerPath.DataTypes.LatLong;


public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LatLong home = new LatLong(51.74250300, 19.43295600);
        PathFinder finder = new PathFinder();
        Printer printer = new Printer();
        try {
            Database database = new Database();
            LinkedList<Brewery> path = finder.getPath(database, home);
            LinkedList<Beer> beers = finder.getBeerList(database, path);
            printer.printBreweries(path, database, home);
            printer.printBeers(beers);
        } catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
