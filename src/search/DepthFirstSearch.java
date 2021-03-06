/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.ArrayList;
import java.util.List;
import structure.Central;
import structure.Subscriber;

/**
 * This class is responsible to implement the DepthFirstSearch
 * @author Bruno e Allan
 */
public class DepthFirstSearch implements Visitor {

    private List<Integer> visited;
    private List<Integer> route;
    private Subscriber caller;
    private Subscriber receiver;
    private boolean isFind;

    /**
     * Constructor method of this class
     * 
     * @param caller Subscriber which the DepthFirstSearch is started
     * @param receiver Subscriber which the DepthFirstSearch is finished
     */
    public DepthFirstSearch(Subscriber caller, Subscriber receiver) {
        this.caller = caller;
        this.receiver = receiver;
        this.visited = new ArrayList<>();
        this.route = new ArrayList<>();
    }

    /**
     * Perform a search in the network and return a list with the route
     */
    @Override
    public List<Integer> search() {
        this.isFind = false;
        Central root = this.caller.getCentral();
        visit(root);
        return route;
    }

    private void visit(Central node) {
        incrementRoute(node.getId());
        if (!node.hasSubscriber(receiver.getId()) && !isVisited(node.getId())) {
            addVisited(node.getId());
            for (Integer id : node.getCentralConnected()) {
                if (!isFind) {
                    visit(node.getCentralByID(id));
                }
            }
            if (!isFind) {
                decrementRoute(node.getId());
            }
        } else if (node.hasSubscriber(receiver.getId())) {
            this.isFind = true;
        } else {
            decrementRoute(node.getId());
        }
    }

    /**
     * Get the central which the subscriber is connected
     */
    private Central getRoot(int idCaller) {
        return this.caller.getCentral();
    }

    /**
     * Add central in the route
     */
    private void incrementRoute(int idCentral) {
        this.route.add(idCentral);
    }

    /**
     * Remove central in the route
     */
    private void decrementRoute(int idCentral) {
        int index = this.route.size() - 1;
        this.route.remove(index);
    }

    /**
     * Add central visited
     */
    private void addVisited(int idVisited) {
        this.visited.add(idVisited);
    }

    /**
     * Verify if central already was visited
     */
    private boolean isVisited(int idNode) {
        for (Integer id : this.visited) {
            if (id == idNode) {
                return true;
            }
        }
        return false;
    }

}
