/*
 * Class to hold data for a brewery.
 */
package BeerPath.DataTypes;

public class Brewery {
    private int id;
    private String name;
    
    public Brewery(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }
}
