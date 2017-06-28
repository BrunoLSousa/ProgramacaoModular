/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.*;
import structure.Central;
import structure.ManagementRounds;
import structure.Network;
import output.Output;

/**
 * This class implements a factory to SuspendLineCC event
 * @author Bruno e Allan
 */
public class FactorySuspendLineCC extends FactoryEventChain{
    
    private Central centralA;
    private Central centralB;

     /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param network  Object of the network
     * @param code  Token of this class
     */
    public FactorySuspendLineCC(ManagementRounds managementRound, Network network, String code) {
        super(managementRound, network, code);
    }

     /**
     * Create an RemoveLineCC event
     * 
     * @param round   Object to manage rounds
     * @param infoEvent  content of event
     * @param output   Object required by event
     */
    @Override
    public EventHandle create(Round round, String[] infoEvent, Output output) {
        this.centralA = this.network.getCentralByID(Integer.parseInt(infoEvent[3]));
        this.centralB = this.network.getCentralByID(Integer.parseInt(infoEvent[4]));
        return new SuspendLineCC(managementRound, round, network, centralA, centralB, output);
    }
    
}
