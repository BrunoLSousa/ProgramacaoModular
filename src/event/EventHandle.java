/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.ManagementRounds;
import view.Output;

/**
 *
 * @author bruno
 */
public abstract class EventHandle {
    
    protected ManagementRounds managementRound;
    protected Output output;
    protected boolean sucess;
    private final Round round;
    
    public EventHandle(ManagementRounds managements, Round round, Output output){
        this.managementRound = managements;
        this.round = round;
        this.output = output;
    }
    
    public abstract void trigger();
    
    public int timeRound(){
        return this.round.getValue();
    }
    
    public boolean isSucess(){
        return this.sucess;
    }
    
}
