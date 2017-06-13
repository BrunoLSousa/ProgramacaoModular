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
public class FactoryReconnect extends FactoryEventChain {

    private Subscriber subscriber;

    public FactoryReconnect(ManagementEvents managementEvents, Network network, String code) {
        super(managementEvents, network, code);
    }

//    public FactoryReconnect(ManagementEvents managementEvents, int round, Subscriber subscriber) {
//        this.managementEvents = managementEvents;
//        this.round = round;
//        this.subscriber = subscriber;
//    }
//
//    @Override
//    public EventHandle createEvent() {
//        return new Reconnect(managementEvents, subscriber, round);
//    }

    @Override
    public EventHandle create(int round, String[] infoEvent) {
        this.subscriber = this.network.getSubscriberByID(Integer.parseInt(infoEvent[2]));
        return new Reconnect(managementEvents, subscriber, round);
    }

}
