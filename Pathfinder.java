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
        // TODO code application logic here
        HashMap<String, Node> node = Draw.createGraph();
        Draw.showNodesAndLinks(node);
        
    }
    
}
