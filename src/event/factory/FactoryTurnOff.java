/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.*;
import structure.ManagementRounds;
import structure.Network;
import structure.Subscriber;
import output.Output;

/**
 *
 * @author bruno
 */
public class FactoryTurnOff extends FactoryEventChain{
    
    private Subscriber subscriber;

    public FactoryTurnOff(ManagementRounds managementRound, Network network, String code) {
        super(managementRound, network, code);
    }

    @Override
    public EventHandle create(Round round, String[] infoEvent, Output output) {
        this.subscriber = this.network.getSubscriberByID(Integer.parseInt(infoEvent[2]));
        return new TurnOff(managementRound, round, subscriber, output);
    }
    
}
