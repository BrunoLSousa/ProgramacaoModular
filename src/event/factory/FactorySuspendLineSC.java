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
import structure.Subscriber;
import output.Output;

/**
 * This class implements a factory to SuspendLineSC event
 * @author Bruno e Allan
 */
public class FactorySuspendLineSC extends FactoryEventChain{

    private Central central;
    private Subscriber subscriber;

     /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param network  Object of the network
     * @param code  Token of this class
     */
    public FactorySuspendLineSC(ManagementRounds managementRound, Network network, String code) {
        super(managementRound, network, code);
    }

     /**
     * Create an SuspendLineSC event
     * 
     * @param round   Object to manage rounds
     * @param infoEvent  content of event
     * @param output   Object required by event
     */
    @Override
    public EventHandle create(Round round, String[] infoEvent, Output output) {
        this.subscriber = this.network.getSubscriberByID(Integer.parseInt(infoEvent[3]));
        this.central = this.network.getCentralByID(Integer.parseInt(infoEvent[4]));
        return new SuspendLineSC(managementRound, round, network, subscriber, central, output);
    }
    
}
