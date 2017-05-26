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
public class Central extends Node{
    private int idCentral;
    public ArrayList<Assinante> assinantes;

    public Central() {
        super(Boolean.TRUE);
        this.idCentral = super.getId();
        assinantes = new ArrayList<>();
    }
    
    
    
             
}
