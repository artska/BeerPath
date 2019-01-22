/*
 * Class to hold data on the coordinates of a brewery.
 */
package BeerPath.DataTypes;

public class Geocode {
    private int brewery_id;
    private LatLong coordinates;
    
    public Geocode(int id, double latitude, double longitude) {
        this.brewery_id = id;
        coordinates = new LatLong(latitude, longitude);
    }

    public LatLong getCoordinates() {
        return coordinates;
    }
}
