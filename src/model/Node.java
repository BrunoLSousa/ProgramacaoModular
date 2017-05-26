/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author allan
 */
public class Node {
    public static int id;
    private boolean isCentral;
    private ArrayList<Node> adjacentes;

    public Node(boolean isCentral) {
        id++;
        this.isCentral = isCentral;
        this.adjacentes = new ArrayList<>();
    }

    public boolean addAdjacente(Node n) {
        return adjacentes.add(n);        
    }
    
    public boolean removeAdjacente(Node n) {
        return adjacentes.remove(n);        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsCentral() {
        return isCentral;
    }

    public void setIsCentral(boolean isCentral) {
        this.isCentral = isCentral;
    }

    public ArrayList<Node> getAdjacentes() {
        return adjacentes;
    }

    public void setAdjacentes(ArrayList<Node> adjacentes) {
        this.adjacentes = adjacentes;
    }
    
    
    
    
    
    
    
    
}
