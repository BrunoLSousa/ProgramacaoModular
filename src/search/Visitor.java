/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.List;

/**
 * This interface has services reference Search
 * @author Bruno e Allan
 */
public interface Visitor {
    
    /**
     * Perform a search in the network and return a list with the route
     */
    public List<Integer> search();
    
}
