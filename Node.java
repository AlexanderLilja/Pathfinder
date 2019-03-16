package pathfinder;
import java.util.*;

/**
 *
 * @author Alexander
 */
public class Node implements Comparable {
    
    //      --------------------------------
    //      BASIC VARIABLES NEEDED FOR NODES
    //      --------------------------------
    private String name;
    private double lat;
    private double lon;
    public HashMap<String, Node> neighbours = new HashMap<>();
    Node previous;
    
    Calc calc = new Calc();
    
    //      -----------
    //      CONSTRUCTOR
    //      -----------
    public Node(String name, double latitude, double longitude){
        this.name = name;
        this.lat = latitude;
        this.lon = longitude;
    }
    
    @Override
    public int compareTo(Object other){
        double currentValue = calc.Score();
        double otherValue = ((Node)other).calc.Score();
        
        double value = currentValue - otherValue;
        return(value > 0)?1:(value<0)?-1:0; //SIGN FUNCTION
    }
    
    //      --------------------------------------
    //      GET & SET METHODS USED FOR CONSTRUCTOR
    //      --------------------------------------
    
    //  NAME METHODS
    //GET METHOD FOR NAME
    public String getName(){
        return name;
    }
    
    //  LATITUDE METHODS
    //GET METHOD FOR LATITUDE
    public double getLatitude(){
        return lat;
    }
    
    //  LONGITUDE METHODS
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