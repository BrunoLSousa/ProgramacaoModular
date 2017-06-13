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
public class FactoryRemoveLineSC extends FactoryEventChain{
    
    private Central central;
    private Subscriber subscriber;

    public FactoryRemoveLineSC(ManagementEvents managementEvents, Network network, String code) {
        super(managementEvents, network, code);
    }
    
//    public FactoryRemoveLineSC(ManagementEvents managementEvents, int round, Network network, Subscriber subscriber, Central central) {
//        this.managementEvents = managementEvents;
//        this.round = round;
//        this.network = network;
//        this.central = central;
//        this.subscriber = subscriber;
//    }
//
//    @Override
//    public EventHandle createEvent() {
//        return new RemoveLineSC(managementEvents, round, network, subscriber, central);
//    }

    @Override
    public EventHandle create(int round, String[] infoEvent) {
        this.subscriber = this.network.getSubscriberByID(Integer.parseInt(infoEvent[3]));
        this.central = this.network.getCentralByID(Integer.parseInt(infoEvent[4]));
        return new RemoveLineSC(managementEvents, round, network, subscriber, central);
    }
    
}
