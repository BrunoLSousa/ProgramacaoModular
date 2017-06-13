/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.*;
import structure.Central;
import structure.ManagementEvents;
import structure.Network;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class FactoryADDLineSC extends FactoryEventChain{
    
    private Central central;
    private Subscriber subscriber;

    public FactoryADDLineSC(ManagementEvents managementEvents, Network network, String code) {
        super(managementEvents, network, code);
    }
    
//    public FactoryADDLineSC(ManagementEvents managementEvents, int round, Network network, Central central, Subscriber subscriber) {
//        this.managementEvents = managementEvents;
//        this.round = round;
//        this.network = network;
//        this.central = central;
//        this.subscriber = subscriber;
//    }
//
//    @Override
//    public EventHandle createEvent() {
//        return new ADDLineSC(managementEvents, round, network, central, subscriber);
//    }

    @Override
    public EventHandle create(int round, String[] infoEvent) {
        this.subscriber = this.network.getSubscriberByID(Integer.parseInt(infoEvent[3]));
        this.central = this.network.getCentralByID(Integer.parseInt(infoEvent[4]));
        return new ADDLineSC(managementEvents, round, network, central, subscriber);
    }
    
}
