/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.*;
import structure.ManagementEvents;
import structure.Network;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class FactoryTurnOff extends FactoryEventChain{
    
    private Subscriber subscriber;

    public FactoryTurnOff(ManagementEvents managementEvents, Network network, String code) {
        super(managementEvents, network, code);
    }
    
//    public FactoryTurnOff(ManagementEvents managementEvents, int round, Subscriber subscriber){
//        this.managementEvents = managementEvents;
//        this.round = round;
//        this.subscriber = subscriber;
//    }
//
//    @Override
//    public EventHandle createEvent() {
//        return new TurnOff(managementEvents, round, subscriber);
//    }

    @Override
    public EventHandle create(int round, String[] infoEvent) {
        this.subscriber = this.network.getSubscriberByID(Integer.parseInt(infoEvent[2]));
        return new TurnOff(managementEvents, round, subscriber);
    }
    
}
