package pathfinder;

import java.util.HashMap;
import java.util.Scanner;


public class UI {
    Calc calc = new Calc();
    
    static void CLI(){
    
        Scanner input = new Scanner(System.in);
        
        HashMap<String, Node> node = Draw.createNodes();
        
        
        boolean quit = false;
        

        while(!quit){
            System.out.println("--------------------");
            System.out.println("Pathfinder");
            System.out.println("--------------------");
            System.out.println("1. Show All Stations");
            System.out.println("2. Calculate Route");
            System.out.println("3. Exit.");
            
            int selected = input.nextInt();
            
            switch(selected) {
                case 1: 
                    Draw.showNodesAndLinks(node);
                    break;
                case 2:
                    System.out.print("\n");
                    node.keySet().forEach((i) -> {
                        System.out.print(i + "\n");
                    });
                    System.out.print("\n");
                    System.out.println("Please Enter Start Location: ");
                    String start = input.next();
                    System.out.println("Please Enter Destination: ");
                    String dest = input.next();
                    Node source = node.get(start);
                    Node destination = node.get(dest);
                    
                    Calc.getRoute(source, destination);
                    
                    
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