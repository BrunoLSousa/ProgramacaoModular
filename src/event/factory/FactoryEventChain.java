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
 *
 * @author bruno
 */
public abstract class FactoryEventChain {

    protected ManagementRounds managementRound;
    protected Network network;
    protected final String code;
    
    public FactoryEventChain(ManagementRounds managementRound, Network network, String code){
        this.managementRound = managementRound;
        this.network = network;
        this.code = code;
    }
    
    public abstract EventHandle create(Round round, String[] infoEvent, Output output);
    
    public boolean isCode(String code){
        return (code.equals(this.code));
    }

}
