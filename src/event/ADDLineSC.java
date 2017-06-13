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
public class ADDLineSC extends EventHandle implements EventSystem{
    
    protected Network network;
    private Central central;
    private Subscriber subscriber;
    
    public ADDLineSC(ManagementEvents managementEvents, int round, Network network, Central central, Subscriber subscriber) {
        super(managementEvents, round);
        this.network = network;
        this.central = central;
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
        this.network.connectSubscriberToCentral(this.subscriber.getId(), this.central.getId());
        System.out.println("Assinante " + this.subscriber.getId() + " conectada com Central " + this.central.getId());
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
