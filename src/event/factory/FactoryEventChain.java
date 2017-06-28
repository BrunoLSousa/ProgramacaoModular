/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.EventHandle;
import event.Round;
import structure.ManagementRounds;
import structure.Network;
import output.Output;

/**
 * Abstract call to implement basig services from a factory
 * @author Bruno e Allan
 */
public abstract class FactoryEventChain {

    protected ManagementRounds managementRound;
    protected Network network;
    protected final String code;
    
     /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param network  Object of the network
     * @param code  Token of this class
     */
    public FactoryEventChain(ManagementRounds managementRound, Network network, String code){
        this.managementRound = managementRound;
        this.network = network;
        this.code = code;
    }
    
    /**
     * Abstract method that the subclasses implement to create an event
     */
    public abstract EventHandle create(Round round, String[] infoEvent, Output output);
    
     /**
     * Verify if this code is equals to token of this class
     * @param code  Token for verification
     */
    public boolean isCode(String code){
        return (code.equals(this.code));
    }

}
