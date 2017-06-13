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
public class FactoryRemoveLineCC extends FactoryEventChain{
    
    private Central centralA;
    private Central centralB;

    public FactoryRemoveLineCC(ManagementEvents managementEvents, Network network, String code) {
        super(managementEvents, network, code);
    }
    
//    public FactoryRemoveLineCC(ManagementEvents managementEvents, int round, Network network, Central centralA, Central centralB) {
//        this.managementEvents = managementEvents;
//        this.round = round;
//        this.network = network;
//        this.centralA = centralA;
//        this.centralB = centralB;
//    }
//
//    @Override
//    public EventHandle createEvent() {
//        return new RemoveLineCC(managementEvents, round, network, centralA, centralB);
//    }

    @Override
    public EventHandle create(int round, String[] infoEvent) {
        this.centralA = this.network.getCentralByID(Integer.parseInt(infoEvent[3]));
        this.centralB = this.network.getCentralByID(Integer.parseInt(infoEvent[4]));
        return new RemoveLineCC(managementEvents, round, network, centralA, centralB);
    }
    
}
