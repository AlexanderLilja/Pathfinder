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
    private double latitude;
    private double longitude;
    private double gCost;
    private double hCost;
    private double totalCost;
    private Node previous;
    private ArrayList<Node> neighbours;
    
    
    //      -----------
    //      CONSTRUCTOR
    //      -----------
    public Node(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totalCost = 0;
        this.gCost = 0;
        this.hCost = 0;
        this.neighbours = new ArrayList<>();
        this.previous = null;
    }
    
    
    //      --------------------------------------
    //      GET & SET METHODS USED FOR CONSTRUCTOR
    //      --------------------------------------

    public String getName(){
        return name;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }
    
    public double getTotalCost(){
        return totalCost;
    }
    
    public void updateTotalCost(){
        this.totalCost = this.gCost + this.hCost;
    }
    
    public double getGcost(){
        return gCost;
    }
    
    public void setGcost(double gcost){
        this.gCost = gcost;
        this.updateTotalCost();
    }
    
    public void setHcost(double hcost){
        this.hCost = hcost;
        this.updateTotalCost();
    }

    public void setPrevious(Node previous){
        this.previous = previous;
    }
    
    public Node getPrevious(){
        return this.previous;
    }

    public void addNeighbours(Node ...n){
        for(Node node : n)
        this.neighbours.add(node);
    }

    public ArrayList<Node> getNeighbours(){
        return this.neighbours;
    }
    
    //      -----------------------------------------------
    //      METHOD USED FOR GETTING GCOST & HCOST FOR NODES
    //      -----------------------------------------------
    public double calcDistance(Node target){

        double lon1 = this.longitude * Math.PI/180.0;
        double lat1 = this.latitude * Math.PI/180.0;
        double lon2 = target.getLongitude() * Math.PI/180.0;
        double lat2 = target.getLatitude() * Math.PI/180.0;

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double km = 6367 * c;

        return km;
    }
    
    
}