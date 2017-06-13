/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.Central;
import structure.ManagementEvents;
import structure.Network;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class SuspendLineSC extends EventHandle implements EventSystem{

    protected Network network;
    private Central central;
    private Subscriber subscriber;
    
    public SuspendLineSC(ManagementEvents managementEvents, int round, Network network, Subscriber subscriber, Central central) {
        super(managementEvents, round);
        this.network = network;
        this.subscriber = subscriber;
        this.central = central;
    }

    @Override
    public void trigger() {
        this.network.suspendSubscriberFromCentral(this.subscriber.getId(), this.central.getId());
        System.out.println("Assinante " + this.subscriber.getId() + " suspendida da Central " + central.getId());
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (subscriber.getId() == this.subscriber.getId());
    }

    @Override
    public boolean hasCentral(Central central) {
        return (central.getId() == this.central.getId());
    }
    
}
