/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Toivo
 */
public class DataBuilder {

    private ArrayList<SmartPost> allSmartPosts;
    
    public DataBuilder() {
        allSmartPosts = new ArrayList<>();
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
                String lon = e.getElementsByTagName("lng").item(0).getTextContent();
                String lat = e.getElementsByTagName("lat").item(0).getTextContent();
                allSmartPosts.add(new SmartPost(name, city, postnumber, address, lat, lon));
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
    
}