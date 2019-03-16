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
    public String name;
    public double lat;
    public double lon;
    public double costFromStart;
    public double costToGoal;
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
        double currentValue = Score(costFromStart, costToGoal);
        double otherValue = ((Node)other).Score(costFromStart, costToGoal);
        
        double value = currentValue - otherValue;
        return(value > 0)?1:(value<0)?-1:0; //SIGN FUNCTION
    }   
    
    //      --------------------------------------
    //      GET & SET METHODS USED FOR CONSTRUCTOR
    //      --------------------------------------
       
     //CALCULATES THE TOTAL SCORE FOR EACH NODE/STATION
    public double Score(double gcost, double hcost){
        return gcost + hcost;
    }
    public void set_gcost(double gcost){
        this.costToGoal = gcost;
    }
    public void set_hcost(double hcost){
        this.costFromStart = hcost;
    }
    
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