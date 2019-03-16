/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;




/**
 *
 * @author Alexander
 */
public class Calc {
    
    //      ---------------------------------------------------
    //      DEFINING NEW MAPS AND VARIABLES NEEDED IN ALGORITHM
    //      --------------------------------------------------- 
    public Node current;
    public Node goal;
    public Node start;
    
    //      ---------------------------
    //      CALCULATION FOR H & G COSTS
    //      --------------------------- 
    //CALCULATES DISTANCE/COST FROM PREVIOUS STATION TO CURRENT
    public double G_Cost(Node _start){
        
        double g_cost = 0;
        Node _current = this.current;
        
        while(!(_start == _current)){
            
            g_cost += calcDistance(_current.previous.getLongitude(), _current.previous.getLatitude(), _current.getLongitude(), _current.getLatitude());
            _current = _current.previous;
        } 
            
        return g_cost;
    }
    //CALCULATES DISTANCE/COST FROM CURRENT STATION TO GOAL
    public double H_Cost(Node _goal){
        
        double h_cost;
        Node _current = this.current;
        
        if (!(_goal == _current)) {
            
            h_cost = calcDistance(_current.getLongitude(), _current.getLatitude(), _goal.getLongitude(), _goal.getLatitude());
            
        } else { h_cost = 0; }
        
        return h_cost;
    }
   
     //CALCULATES THE TOTAL SCORE FOR EACH NODE/STATION
    public double Score(){
        return H_Cost(goal) + G_Cost(start);
    }
    
    //      ---------------------------
    //      DISTANCE CALCULATION METHOD
    //      ---------------------------
    public double calcDistance(double lon1, double lat1, double lon2, double lat2) {
        
        lon1 = lon1*Math.PI/180.0;
        lat1 = lat1*Math.PI/180.0;
        lon2 = lon2*Math.PI/180.0;
        lat2 = lat2*Math.PI/180.0;

      
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double km = 6367 * c;

        return km;
    }
    
   //CREATING A PRIORITYQUEUE THAT AUTOMATICALLY ORDERS NODES BY PRIORITY
    //HIGHEST PRIORITY IS FIRST IN LIST
    public static class PriorityList extends LinkedList {
        
        public void add(Comparable object){
            for (int i = 0; i<size(); i++) {
                if(object.compareTo(get(i)) <= 0) {
                    add(i, object);
                    return;
                }
            }
            addLast(object);
        }
    }
    
    //RECONSTRUCT PATH, BUT SKIPPING START NODE
    protected List reConstructPath(Node node){
        LinkedList route = new LinkedList();
        while(node.previous != null) {
            route.addFirst(node);
            node = node.previous;
        }
        return route;
    }
    
    //GET ROUTE FROM START TO GOAL
    //RETURNS A LIST OF NODES OR NULL IF NO PATH IS FOUND
    public List getRoute(Node _start, Node goal){
        
        //CREATE OPEN AND CLOSED LISTS TO STORE NODES
        PriorityList open = new PriorityList();
        LinkedList closed = new LinkedList();
        
        //DEFINING COSTS FOR START NODE/STATION
        start = _start;
        current = _start;
        double costFromStart = 0;
        double costToGoal = H_Cost(goal);
        _start.previous = null;
        open.add(_start);
        
        //LOOPING THROUGH ENTRIES AS LONG AS OPEN LIST IS NOT EMPTY
        while(!open.isEmpty()) {
            //DELETE CURRENT NODE FROM OPEN LIST (THAT HAS SMALLEST COST)
            current = (Node)open.removeFirst();
            //IF WE HAVE REACHED THE GOAL WE NEED TO RECONSTRUCT THE PATH FROM 
            // START TO GOAL
            if(current == goal){
                
                return reConstructPath(goal);
            }
            
            HashMap <String, Node> neighbours = current.getNeighbours();
            for(String key : neighbours.keySet()) {
                
                //GET NEIGHBOURING/CHILD CITIES
                Node childNode = (Node)neighbours.get(key);
                double costStart = costFromStart + G_Cost(childNode);
                
                
                //CHECK IF THE NEIGHBOUR NODE HAS ALREADY BEEN COUNTED FOR 
                //OR IF A SHORTER ROUTE IS AVAILABLE
                if((!open.contains(childNode) && !closed.contains(childNode)) || costStart < costFromStart) {
                    
                    //SET PARENT NODES DATA
                    childNode.previous = current;
                    costFromStart = costStart;
                    costToGoal = H_Cost(goal);
                    
                    //IF NEIGHBOUR/CHILD IS FOUND IN CLOSED LIST
                    //REMOVE 
                    if(closed.contains(childNode)){
                        closed.remove(childNode);
                    }
                    //IF NEIGHBOUR/CHILD IS NOT IN OPEN LIST
                    //ADD
                    if(!open.contains(childNode)){
                        open.add(childNode);
                    }
                    
                }
   
            }
            closed.add(current);
        }
        // IF NO PATH IS FOUND
        return null;
    }
    
}
