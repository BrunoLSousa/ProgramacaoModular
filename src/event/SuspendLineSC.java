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
public class SuspendLineSC extends EventHandle implements EventSystem {

    protected Network network;
    private Central central;
    private Subscriber subscriber;

    public SuspendLineSC(ManagementRounds managementRound, Round round, Network network, Subscriber subscriber, Central central) {
        super(managementRound, round);
        this.network = network;
        this.subscriber = subscriber;
        this.central = central;
    }

    @Override
    public void trigger() {
        this.sucess = this.network.suspendSubscriberFromCentral(this.subscriber.getId(), this.central.getId());
        if (sucess) {
            System.out.println("Assinante " + this.subscriber.getId() + " suspendida da Central " + central.getId());
        } else {
            System.err.println("Assinante " + this.subscriber.getId() + " não está conectado ou ativo à Central " + central.getId());
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
