/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

/**
 *
 * @author Toivo
 */
public class SmartPost {
    
    public class GeoPoint{
        private double lat, lon;

        public GeoPoint(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }
    }
    
    private GeoPoint gp;
    private String name, city, postnumber;

    public SmartPost(double lat, double lon) {
        this.gp = new GeoPoint(lat, lon);
    }

    public GeoPoint getGp() {
        return gp;
    }

    @Override
    public String toString() {
        return city + name;
    }
    
}
