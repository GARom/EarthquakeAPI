package earthquakeapi;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.net.URLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<QuakeConstructor> earthquakes = getQuakes("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_day.atom");
        
        System.out.println("Today's Earthquakes 2.5 and Higher");
        SimpleDateFormat df = new SimpleDateFormat("HH:MM");
        System.out.printf("%s\t%-20s\t%s\t%s\n", "WHEN", "LAT / LONG", "MAGNITUDE", "DETAILS");
        for (QuakeConstructor q : earthquakes) {
            System.out.printf("%s\t%-20s\t%f\t%s\n", df.format(q.getDate()), q.getLocation(), q.getMagnitude(), q.getDetails() );
        }
        
    }

    private static ArrayList<QuakeConstructor> getQuakes(String url) {
        ArrayList<QuakeConstructor> quakes = new ArrayList<>();
        try {
        URL centerURL = new URL(url);
        URLConnection connection = centerURL.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)connection;
        int responseCode = httpConnection.getResponseCode();
        
        if(responseCode == HttpURLConnection.HTTP_OK) {
            InputStream in = httpConnection.getInputStream();
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            Document dom = db.parse(in);
            Element docElement = dom.getDocumentElement();
            NodeList n1 = docElement.getElementsByTagName("entry");
            if (n1 != null && n1.getLength() > 0) {
                for (int i=0; i < n1.getLength(); i++) {
                    Element entry = (Element) n1.item(i);
                    Element title = (Element) entry.getElementsByTagName("title").item(0);
                }
            }
            
            
        }
        
        } catch (MalformedURLException ex1) {
            System.out.printf("Error: %s", ex1);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return quakes;
    }
    
}
