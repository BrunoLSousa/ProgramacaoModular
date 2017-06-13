/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.ManagementEvents;

/**
 *
 * @author bruno
 */
public abstract class EventHandle {
    
    protected ManagementEvents managementEvents;
    private final int round;
    
    public EventHandle(ManagementEvents managements, int round){
        this.managementEvents = managements;
        this.round = round;
    }
    
    public abstract void trigger();
    
    public int getRound(){
        return this.round;
    }
    
//    public void computePulse();
    
}
