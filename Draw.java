/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Alexander
 */
public class Draw {
     
    //      ------------
    //      GRAPH METHOD
    //      ------------
    static HashMap<String, Node> createNodes()
    {
        HashMap<String, Node> nodes = new HashMap<>();
        //CREATES A NEW NODE FOR EACH STATION
        Node hki = new Node("Helsingfors", 60.1640504, 24.7600896);    
        Node tpe = new Node("Tammerfors", 61.6277369, 23.5501169);     
        Node tku = new Node("Abo", 60.4327477, 22.0853171);            
        Node jyv = new Node("Jyvaskyla", 62.1373432, 25.0954598);      
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);          
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);         
             
        //ADDS CONNECTIONS FOR HELSINKI 
        hki.addNeighbours(tpe, tku, lhi);
       
        //ADDS CONNECTIONS FOR TAMPERE
        tpe.addNeighbours(hki, tku, jyv, lhi);
        
        //ADDS CONNECTIONS FOR TURKU
        tku.addNeighbours(hki, tpe);
         
        //ADDS CONNECTIONS FOR JYVÄSKYLÄ 
        jyv.addNeighbours(tpe);
        
        //ADDS CONNECTIONS FOR KUOPIO
        kpo.addNeighbours(lhi);
        
        //ADDS CONNECTIONS FOR LAHTI
        lhi.addNeighbours(hki, tpe, kpo);                
        
        nodes.put(hki.getName(), hki);
        nodes.put(tpe.getName(), tpe);
        nodes.put(tku.getName(), tku);
        nodes.put(jyv.getName(), jyv);
        nodes.put(kpo.getName(), kpo);
        nodes.put(lhi.getName(), lhi);
        
        return nodes;
    }
    
    //      ---------------------------------------------------
    //      PRINT METHOD FOR SEEING THE STATIONS AND NEIGHBOURS
    //      ---------------------------------------------------
    static void showNodesAndLinks(HashMap<String, Node> node){
        
        for(String keys : node.keySet()){
            
            //PRINTING OUT NODES
            System.out.print("\n" + keys + "\n");
            //PRINTING OUT NEIGHBOUR NODES
            for(Node neighbour : node.get(keys).getNeighbours()){
                System.out.println("    " + neighbour.getName());
                
            }
        }
        System.out.print("\n");
        
        
    }
    
    //      -------------------------------
    //      PRINT METHOD FOR PRINTING ROUTE
    //      -------------------------------
    static void printRoute(Node current){
        
        //CREATING ARRAYLIST FOR STORING PATH
        LinkedList<Node> route = new LinkedList();
        
        while(current != null){
            route.addFirst(current);
            current = current.getPrevious();
        }
        
        for(int i = 0; i < route.size(); i++){
            
            System.out.println(i + 1 + ". " + route.get(i).getName());
       
        }
        System.out.print("\n");
    }
}
