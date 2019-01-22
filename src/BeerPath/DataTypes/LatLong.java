/*
 * Data type holding latitude and longitude.
 */
package BeerPath.DataTypes;

public class LatLong {
    int R = 6371; //radius of the earth in kilometers;
    private double latitude;
    private double longitude;
    
    public LatLong(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
     /**
     * Calculates the distance from this point to another in kilometers.
     * @param to - Point two, in latitude and longitude
     * @return distance between the two points in kilometers
     */
    public double getDistance(LatLong to) {
        double dLatitude = Math.toRadians(this.latitude-to.latitude);
        double dLongitude = Math.toRadians(this.longitude-to.longitude);
        double a = Math.sin(dLatitude/2) * Math.sin(dLatitude/2) + 
                Math.cos(Math.toRadians(this.latitude)) * 
                Math.cos(Math.toRadians(to.latitude)) *
                Math.sin(dLongitude/2) * Math.sin(dLongitude/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return d;
    }

    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
}
