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
public class Calc {
    
    //      ---------------------------------------------------
    //      DEFINING NEW MAPS AND VARIABLES NEEDED IN ALGORITHM
    //      --------------------------------------------------- 
    public Node start;
    public Node goal;
    public Node current;
    public Node previous;
    
    
    //      ---------------------------
    //      CALCULATION FOR H & G COSTS
    //      --------------------------- 
    //CALCULATES DISTANCE/COST FROM PREVIOUS STATION TO CURRENT
    public double G_Cost(Node _start){
        
        double g_cost = 0;
        Node _current = this.current;
        
        while(!(_start == _current)){
            
            g_cost += calcDistance(previous.getLongitude(), previous.getLatitude(), _current.getLongitude(), _current.getLatitude());
            _current = current;
        } 
            
        return g_cost;
    }
    //CALCULATES DISTANCE/COST FROM CURRENT STATION TO GOAL
    public double H_Cost(Node _goal){
        
        double h_cost;
        Node _current = this.current;
        
        if (!(_current == _goal)) {
            
            h_cost = calcDistance(_current.getLongitude(), _current.getLatitude(), _goal.getLongitude(), _goal.getLatitude());
            
        } else { h_cost = 0; }
        
        return h_cost;
    }
   
    //CALCULATES THE TOTAL SCORE FOR EACH NODE/STATION
    public double Score(double h, double g){
        return h + g;
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
    
    //      ------------------
    //      A*STAR CALCULATION
    //      ------------------
    public HashMap<String, Node> getRoute(Node _start, Node _goal){
        
        //  DEFINING VARIABLES NEEDED FOR ALGORITHM
        //MAPS FOR KEEPING TRACK OF OPENED AND CLOSED NODES
        HashMap<String, Node> open = new HashMap();
        HashMap<String, Node> closed = new HashMap();
        Node _current = this.start;
        boolean done = false;
        
        
        //HERE WE GO
        while(!done){
            
            double minF = 0;
            Node next = null;
         
            //LOOPING THROUGH NEIGHBOUR MAP
            for(int i = 0; i <= _current.neighbours.size(); i++){
                
                _current.neighbours.keySet().stream().filter((names) -> (!(open.containsKey(names)) && !(closed.containsKey(names)))).forEachOrdered((names) -> {
                    open.put(names, _current.neighbours.get(names));
                });
                
                previous.neighbours = _current.neighbours;
            }
            
            while(!(open.isEmpty())){
                
                if(_current == _goal){
                    done = true;
                    break;
                } else {
                    double f = Score(H_Cost(_goal), G_Cost(_start));
                    
                    if(minF == 0 || minF > f){
                        minF = f;
                        next = current;
                    }
                }
            }
            if(done) {
            } else {
                current = next;
                closed.put(current.getName(), current);
                open.remove(current);
            }
        }
        
        HashMap<String, Node> route = new HashMap();
        current = _goal;
        
        while(current != _start){
            route.put(current.getName() ,current);
            current = previous;
        }
        
        return route;
    }
    
}
