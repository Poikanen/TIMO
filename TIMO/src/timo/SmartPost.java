/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

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
        
        public double getDistanceTo(GeoPoint other){
            double difLatDegree = abs(Double.parseDouble(this.getLat()) - Double.parseDouble(other.getLat()));
            double difLonDegree = abs(Double.parseDouble(this.getLon()) - Double.parseDouble(other.getLon()));
            double difLatKm = difLatDegree*111;
            //Times 50 is an approximation
            double difLonKm = difLonDegree*50;
            return sqrt(difLatKm*difLatKm + difLonKm*difLonKm);
        }
    }
    
    private GeoPoint gp;
    private String name, city, postnumber, address, availability;

    public SmartPost(String name, String city, String postnumber, String address, String availability, String lat, String lon) {
        this.name = name;
        this.city = city;
        this.postnumber = postnumber;
        this.address = address;
        this.availability = availability;
        this.gp = new GeoPoint(lat, lon);
    }
    
    public SmartPost(String name, String city, String postnumber, String address, String availability, GeoPoint gp) {
        this.name = name;
        this.city = city;
        this.postnumber = postnumber;
        this.address = address;
        this.availability = availability;
        this.gp = gp;
    }
    
    public SmartPost(SmartPost copy){
        this.name = copy.name;
        this.city = copy.city;
        this.postnumber = copy.postnumber;
        this.address = copy.address;
        this.availability = copy.availability;
        this.gp = copy.gp;
    }
    
    public SmartPost(){
        this.name = "";
        this.city = "";
        this.postnumber = "";
        this.address = "";
        this.availability = "";
        this.gp = new GeoPoint("", "");
    }

    public String getAvailability() {
        return availability;
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
