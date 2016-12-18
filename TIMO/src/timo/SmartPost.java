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
        private String lat, lon;

        public GeoPoint(String lat, String lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public String getLon() {
            return lon;
        }
    }
    
    private GeoPoint gp;
    private String name, city, postnumber, address;

    public SmartPost(String name, String city, String postnumber, String address, String lat, String lon) {
        this.name = name;
        this.city = city;
        this.postnumber = postnumber;
        this.address = address;
        this.gp = new GeoPoint(lat, lon);
    }

    public GeoPoint getGp() {
        return gp;
    }

    @Override
    public String toString() {
        return city + " " + name;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPostnumber() {
        return postnumber;
    }

    public String getAddress() {
        return address;
    }
    
}
