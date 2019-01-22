/*
 * Class to hold data for a beer type.
 */
package BeerPath.DataTypes;

public class Beer {
    private int id;
    private String name;
    
    public Beer(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
