package pathfinder;

import java.util.ArrayList;
import java.util.LinkedList;

public class Calc {
     
    static void getRoute(Node _start, Node goal){
        
        System.out.println("");
        System.out.println("Route from " + _start.getName() + " to " + goal.getName());
        System.out.println("");
        
        //CREATING OPEN AND CLOSED LISTS FOR KEEPING TRACK OF NODES
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();
        open.add(_start);
        
        //LOOPING THROUGH OPEN LIST AND GETTING VALUE WITH LOWEST TOTAL COST
        while(open.size() > 0){
            int lowest = 0;
            for(int i = 0; i < open.size(); i++){
                if(open.get(i).getTotalCost() < open.get(lowest).getTotalCost()){
                    lowest = i;
                }
            }
            //INITIALIZING CURRENT AS NODE WITH LOWEST COST
            Node current = open.get(lowest);
            
            //REACHED GOAL IF CURRENT == GOAL
            if(current == goal){
                printRoute(current);
                break;
            }
            //PRETTY STRAIGHTFORWARD JUST TO KEEP TRACK OF NODES
            open.remove(current);
            closed.add(current);
            
            //LOOP THROUGH NEIGHBOURS FOR THE CURRENT NODE
            //UPDATING COSTS FOR EACH NODE THAT IS AVAILABLE
            ArrayList<Node> neighbours = current.getNeighbours();
            
            for(int i = 0; i < neighbours.size(); i++){
                Node neighbour = neighbours.get(i);
                
                //UPDATE GCOST BY ADDING CURRENT GCOST PLUS ADDING COST FROM CURRENT TO NEIGHBOUR
                if(!closed.contains(neighbour)){
                    double gCost = current.getGcost() + current.calcDistance(neighbour);
                    
                    //CHECKING AND ADDING CHILD/NEIGHBOUR NODES TO OPEN
                    if(!open.contains(neighbour)){
                        open.add(neighbour);
                    } else if (gCost >= neighbour.getGcost()){
                        continue;
                    }
                    
                    //AT THE END OF EACH LOOP SETTING COSTS FOR EACH NEIGHBOUR
                    neighbour.setGcost(gCost);
                    double hCost = neighbour.calcDistance(goal);
                    neighbour.setHcost(hCost);
                    //ALSO SETTING VALUE OF CURRENT TO PREVIOUS
                    neighbour.setPrevious(current);
                   
                }
            }   
        }
    }
        
    static void printRoute(Node current){
        
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