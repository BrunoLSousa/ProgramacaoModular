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
public class RemoveLineSC extends EventHandle implements EventSystem{
    
    protected Network network;
    private Central central;
    private Subscriber subscriber;
    
    public RemoveLineSC(ManagementEvents managementEvents, int round, Network network, Subscriber subscriber, Central central) {
        super(managementEvents, round);
        this.network = network;
        this.central = central;
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
        this.network.removeSubscriberFromCentral(this.subscriber.getId(), this.central.getId());
        System.out.println("Assinante " + this.subscriber.getId() + " removido da Central " + central.getId());
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (central.getId() == this.central.getId());
    }

    @Override
    public boolean hasCentral(Central central) {
        return (central.getId() == this.central.getId());
    }
    
}
