package pathfinder;
import java.util.*;

/**
 *
 * @author Alexander
 */
public class Node {
    
    //      --------------------------------
    //      BASIC VARIABLES NEEDED FOR NODES
    //      --------------------------------
    private String name;
    private double lat;
    private double lon;
    public HashMap<String, Node> neighbours = new HashMap<>();
    
    //      -----------
    //      CONSTRUCTOR
    //      -----------
    public Node(String name, double latitude, double longitude){
        setName(name);
        setLatitude(latitude);
        setLongitude(longitude);
    }
    
    //      --------------------------------------
    //      GET & SET METHODS USED FOR CONSTRUCTOR
    //      --------------------------------------
    
    //  NAME METHODS
    //SET METHOD FOR NAME
    public void setName(String name){
        this.name = name;
    }
     //GET METHOD FOR NAME
    public String getName(){
        return name;
    }
    
    //  LATITUDE METHODS
    //SET METHOD FOR LATITUDE
    public void setLatitude(double latitude){
        this.lat = latitude;
    }
    //GET METHOD FOR LATITUDE
    public double getLatitude(){
        return lat;
    }
    
    //  LONGITUDE METHODS
    //SET METHOD FOR LONGITUDE
    public void setLongitude(double longitude){
        this.lon = longitude;
    }
    //GET METHOD FOR LONGITUDE
    public double getLongitude(){
        return lon;
    }
    
    //  NEIGHBOUR METHODS
    //ADD NEIGHBOUR NODES
    public void addNeighbours(String key, Node neighbours){
        this.neighbours.put(key, neighbours);
    }
    //GET METHOD FOR NEIGHBOUR NODES
    public HashMap<String, Node> getNeighbours(){
        return neighbours;
    }
    
}