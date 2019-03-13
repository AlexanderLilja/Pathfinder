package pathfinder;
import java.util.*;



public class Node {
    
    //      BASIC VARIABLES NEEDED FOR NODES
    private String name;
    private double lat;
    private double lon;
    public HashMap<String, Node> neighbours = new HashMap<>();
    
    
    //      CONSTRUCTOR
    public Node(String name, double latitude, double longitude){
        setName(name);
        setLatitude(latitude);
        setLongitude(longitude);
    }
    
    //      GET & SET METHODS USED FOR CONSTRUCTOR
    
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
    
    //      DISTANCE CALCULATION METHOD
    public double getDistance(double lon1, double lat1, double lon2, double lat2) {
        
        lon1 = lon1*Math.PI/180.0;
        lat1 = lat1*Math.PI/180.0;
        lon2 = lon2*Math.PI/180.0;
        lat2 = lat2*Math.PI/180.0;

      
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) *        Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double km = 6367 * c;

        return km;
    }
    
    //      GRAPH METHOD
    public static HashMap<String, Node> createGraph()
    {
        //CREATES A NEW NODE FOR EACH STATION
        Node hki = new Node("Helsingfors", 60.1640504, 24.7600896);    
        Node tpe = new Node("Tammerfors", 61.6277369, 23.5501169);     
        Node tku = new Node("Åbo", 60.4327477, 22.0853171);            
        Node jyv = new Node("Jyväskylä", 62.1373432, 25.0954598);      
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);          
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);         
             
        //ADDS CONNECTIONS FOR HELSINKI 
        hki.addNeighbours("Tammerfors", tpe); //Tammerfors
        hki.addNeighbours("Åbo", tku); //Åbo
        hki.addNeighbours("Lahtis", lhi); //Lahtis
       
        //ADDS CONNECTIONS FOR TAMPERE
        tpe.addNeighbours("Helsingfors", hki); //Helsingfors
        tpe.addNeighbours("Åbo", tku); //Åbo
        tpe.addNeighbours("Jyväskylä", jyv); //Jyväskylä
        tpe.addNeighbours("Lahtis", lhi); //Lahtis
        
        //ADDS CONNECTIONS FOR TURKU
        tku.addNeighbours("Helsingfors", hki); //Helsingfors
        tku.addNeighbours("Tammerfors", tpe); //Tammerfors
         
        //ADDS CONNECTIONS FOR JYVÄSKYLÄ 
        jyv.addNeighbours("Tammerfors", tpe); //Tammerfors
        
        //ADDS CONNECTIONS FOR KUOPIO
        kpo.addNeighbours("Lahtis", lhi); //Lahtis
        
        //ADDS CONNECTIONS FOR LAHTI
        lhi.addNeighbours("Helsingfors", hki); //Helsingors
        lhi.addNeighbours("Tammerfors", tpe); //Tammerfors
        lhi.addNeighbours("Kuopio", kpo); //Kuopio
                
        //CREATES A HASHMAP FOR THE GRAPH
        HashMap<String, Node> graph = new HashMap();
        graph.put("Helsingfors", hki);
        graph.put("Tammerfors", tpe);
        graph.put("Åbo", tku);
        graph.put("Jyväskylä", jyv);
        graph.put("Kuopio", kpo);
        graph.put("Lahtis", lhi);
        
        return graph;
    }
    
    //      PRINT METHOD FOR SEEING THE STATIONS AND NEIGHBOURS
    public static void showNodesAndLinks(HashMap<String, Node> node){
        
        node.keySet().stream().map((key) -> {
            System.out.println(key);
            return key;
        }).map((key) -> node.get(key).getNeighbours()).forEachOrdered((close) -> {
            close.keySet().forEach((i) -> {
                System.out.println("    " + i);
            });
        });  
        
    }
}
