/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.Calling;
import event.EventHandle;
import structure.ManagementEvents;
import structure.Network;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class FactoryCalling extends FactoryEventChain{

    private Subscriber caller;
    private Subscriber receiver;

    public FactoryCalling(ManagementEvents managementEvents, Network network, String code) {
        super(managementEvents, network, code);
    }
    
//    public FactoryCalling(ManagementEvents managementEvents, int round, Subscriber caller, Subscriber receiver){
//        this.managementEvents = managementEvents;
//        this.round = round;
//        this.caller = caller;
//        this.receiver = receiver;
//    }
    
//    @Override
//    public EventHandle createEvent() {
//        return new Calling(managementEvents, round, caller, receiver);
//    }

    @Override
    public EventHandle create(int round, String[] infoEvent) {
        this.caller = this.network.getSubscriberByID(Integer.parseInt(infoEvent[2]));
        this.receiver = this.network.getSubscriberByID(Integer.parseInt(infoEvent[3]));
        return new Calling(managementEvents, round, caller, receiver);
    }
    
}
