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
    
    //RECONSTRUCT PATH
    public List reConstructPath(Node node){
        
        LinkedList route = new LinkedList();
        while(node != null) {
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
       
        _start.costFromStart = calcDistance(_start, _start);
        _start.costToGoal = calcDistance(_start, goal);
     
        _start.previous = null;
        open.add(_start);
        
        //LOOPING THROUGH ENTRIES AS LONG AS OPEN LIST IS NOT EMPTY
        while(!open.isEmpty()) {
            
            //DELETE CURRENT NODE FROM OPEN LIST (THAT HAS SMALLEST COST)
            Node current = (Node)open.getFirst();
            open.remove(current);
            
            current.Score(current.costFromStart, current.costToGoal);
            //IF WE HAVE REACHED THE GOAL WE NEED TO RECONSTRUCT THE PATH FROM 
            // START TO GOAL
            if(current == goal){
                //BUILD PATH FROM GOAL TO START
                return reConstructPath(goal);
            }
            
            HashMap<String, Node> neighbours = current.getNeighbours();
            for(String key : neighbours.keySet()) {
                
                //GET NEIGHBOURING/CHILD CITIES
                Node childNode = (Node)neighbours.get(key);
                double tentative_Gcost = current.costFromStart + current.Score(childNode.costFromStart, childNode.costToGoal);
                
                
                //CHECK IF THE NEIGHBOUR NODE HAS ALREADY BEEN COUNTED FOR 
                //OR IF A SHORTER ROUTE IS AVAILABLE
                if((!open.contains(childNode) && !closed.contains(childNode)) || tentative_Gcost < childNode.costFromStart) {
                    
                    //SET PARENT NODES DATA
                    childNode.previous = current;
                    childNode.costFromStart = tentative_Gcost;
                    childNode.costToGoal = calcDistance(childNode, goal);
                            
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
    //      ---------------------------
    //      DISTANCE CALCULATION METHOD
    //      ---------------------------
    public double calcDistance(Node A, Node B) {
        
        double lon1 = A.getLongitude();
        double lat1 = A.getLatitude();
        double lon2 = B.getLongitude();
        double lat2 = B.getLatitude();
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
    
        


    
}
