/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.Calling;
import event.EventHandle;
import event.Round;
import structure.ManagementRounds;
import structure.Network;
import structure.Subscriber;
import view.Output;

/**
 *
 * @author bruno
 */
public class FactoryCalling extends FactoryEventChain{

    private Subscriber caller;
    private Subscriber receiver;

    public FactoryCalling(ManagementRounds managementRound, Network network, String code) {
        super(managementRound, network, code);
    }

    @Override
    public EventHandle create(Round round, String[] infoEvent, Output output) {
        this.caller = this.network.getSubscriberByID(Integer.parseInt(infoEvent[2]));
        this.receiver = this.network.getSubscriberByID(Integer.parseInt(infoEvent[3]));
        return new Calling(managementRound, round, caller, receiver, output);
    }
    
}
