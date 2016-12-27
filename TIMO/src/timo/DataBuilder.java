/*
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DataBuilder {

    private ArrayList<SmartPost> allSmartPosts;
    
    public DataBuilder() {
        allSmartPosts = new ArrayList<SmartPost>();
        loadSmartPostData();
    }
    private void loadSmartPostData(){
        try {
            URL url = new URL("http://smartpost.ee/fi_apt.xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            //Read the url to content
            String content = "";
            String line;
            while((line = br.readLine()) != null){
                content += line + "\n";
            }
            //Parse content
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(content)));
            doc.getDocumentElement().normalize();
            
            NodeList nodes = doc.getElementsByTagName("place");
            for (int i = 0; i < nodes.getLength(); i++){
                Element e = (Element) nodes.item(i);
                String name = e.getElementsByTagName("postoffice").item(0).getTextContent();
                //Every <postoffice> in the XML-document starts with "Pakettiautomaatti, ", removes it
                name = name.replace("Pakettiautomaatti, ", "");
                String city = e.getElementsByTagName("city").item(0).getTextContent();
                String postnumber = e.getElementsByTagName("code").item(0).getTextContent();
                String address = e.getElementsByTagName("address").item(0).getTextContent();
                String availability = e.getElementsByTagName("availability").item(0).getTextContent();
                String lon = e.getElementsByTagName("lng").item(0).getTextContent();
                String lat = e.getElementsByTagName("lat").item(0).getTextContent();
                allSmartPosts.add(new SmartPost(name, city, postnumber, address, availability, lat, lon));
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(DataBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(DataBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<SmartPost> getAllSmartPosts() {
        return allSmartPosts;
    }
    
    public ArrayList<String> getCities(){
        ArrayList<String> cities = new ArrayList<String>();
        for(int i = 0; i < allSmartPosts.size(); i++){
            if(cities.contains(allSmartPosts.get(i).getCity())){
                //Already in the list, continue
                continue;
            }else{
                //Not in the list, add it
                cities.add(allSmartPosts.get(i).getCity());
            }
        }
        return cities;
    }
    
    public ArrayList<SmartPost> getCitysSmartPosts(String city){
        ArrayList<SmartPost> smartPosts = new ArrayList<SmartPost>();
        for(int i = 0; i < allSmartPosts.size(); i++){
            if(allSmartPosts.get(i).getCity().equals(city)){
                smartPosts.add(allSmartPosts.get(i));
            }
        }
        return smartPosts;
    }
    
    public SmartPost getSmartPost(String smartPost){
        for(int i = 0; i < this.allSmartPosts.size(); i++){
            if(this.allSmartPosts.get(i).toString().equals(smartPost)){
                return this.allSmartPosts.get(i);
            }
        }
        return null;
    }
}
