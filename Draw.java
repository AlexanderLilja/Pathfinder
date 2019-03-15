/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.util.HashMap;

/**
 *
 * @author Alexander
 */
public class Draw {
    
    
    //      ------------
    //      GRAPH METHOD
    //      ------------
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
    
    //      ---------------------------------------------------
    //      PRINT METHOD FOR SEEING THE STATIONS AND NEIGHBOURS
    //      ---------------------------------------------------
    public static void showNodesAndLinks(HashMap<String, Node> node){
        
        //LOOP THROUGH NODE KEYS
        node.keySet().stream().map((key) -> {
            System.out.println(key);
            return key;
            //LOOP THROUGH NEIGHBOUR NODE KEYS
        }).map((key) -> node.get(key).getNeighbours()).forEachOrdered((close) -> {
            close.keySet().forEach((i) -> {
                System.out.println("    " + i);
            });
        });  
        
        
    }
  
}
