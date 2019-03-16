/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.util.*;

/**
 *
 * @author Alexander
 */
public class Pathfinder {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        
        //INITIALISING SCANNER
        Scanner input = new Scanner(System.in);
        Calc calc = new Calc();
        
        //STORING NODES 
        HashMap<String, Node> node = Draw.createGraph();
        
        
        boolean quit = false;
        
        //      --------
        //      CLI LOOP
        //      --------
        while(!quit){
            System.out.println("----------------------------");
            System.out.println("Pathfinder");
            System.out.println("----------------------------");
            System.out.println("1. Show All Stations");
            System.out.println("2. Calculate Route");
            System.out.println("3. Exit.");
            
            int selected = input.nextInt();
            
            switch(selected) {
                case 1: 
                    Draw.showNodesAndLinks(node);
                    
                    break;
                case 2:
                    
                    System.out.println("\nPlease Select Start Location: \n");
                    node.keySet().forEach((_start) -> {
                        System.out.println(_start + "\n");
                    });
                    String start = input.next();
                    System.out.println("\nPlease Select Destination: \n");
                    node.keySet().forEach((_dest) -> {
                        System.out.println(_dest + "\n");
                    }) ;
                    String dest = input.next();
                    Node source = node.get(start);
                    Node destination = node.get(dest);
                    List<Node> path = calc.getRoute(source, destination);
                    for(int i = 0; i < path.size(); i++) {
                        System.out.println(i + 1 + " " + path.get(i).getName());
                    }
                    
                    //System.out.println("\nFrom: " + start + " to: " + dest);
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }   
        }  
       
        
    }
    
}
