/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.Central;
import structure.ManagementRounds;
import structure.Network;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class ADDLineSC extends EventHandle implements EventSystem {

    protected Network network;
    private Central central;
    private Subscriber subscriber;

    public ADDLineSC(ManagementRounds managementRound, Round round, Network network, Central central, Subscriber subscriber) {
        super(managementRound, round);
        this.network = network;
        this.central = central;
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
        this.sucess = this.network.connectSubscriberToCentral(this.subscriber.getId(), this.central.getId());
        if (sucess) {
            System.out.println("Assinante " + this.subscriber.getId() + " conectada com Central " + this.central.getId());
        } else {
            System.err.println("Assinante " + this.subscriber.getId() + " já possui conexão com Central " + this.central.getId());
        }
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
