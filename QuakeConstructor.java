package earthquakeapi;

import java.util.Date;

/**
 *
 * @author Guillermo
 */
public class QuakeConstructor {
    //fields for earthquake information
    private Date date;
    private String details;
    private String location;
    private double magnitude;
    private String link;
    
    //this constructor takes initial values
    public QuakeConstructor(Date date, String details, String location, double magnitude, String link) {
        this.date = date;
        this.details = details;
        this.location = location;
        this.magnitude = magnitude;
        this.link = link;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getDetails() {
        return details;
    }
    
    public String getLocation() {
        return location;
    }
            
    public double getMagnitude() {
        return magnitude;
    }
    
    public String getLink() {
        return link;
    }
    
}
