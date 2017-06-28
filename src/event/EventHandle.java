/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.ManagementRounds;
import output.Output;

/**
 * Abstract Class to implement basic services from a event
 * @author Bruno e Allan
 */
public abstract class EventHandle {
    
    protected ManagementRounds managementRound;
    protected Output output;
    protected boolean sucess;
    private final Round round;
    
    /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param round  Round which this event was 
     * @param output  Output object to generate the output informations
     */
    public EventHandle(ManagementRounds managements, Round round, Output output){
        this.managementRound = managements;
        this.round = round;
        this.output = output;
    }
    
    /**
     * Abstract method that the subclasses implement to trigger an event
     */
    public abstract void trigger();
    
    /**
     * Return the round of this event
     */
    public int timeRound(){
        return this.round.getValue();
    }
    
    /**
     * Verify if this event was applied correctly
     */
    public boolean isSucess(){
        return this.sucess;
    }
    
}
