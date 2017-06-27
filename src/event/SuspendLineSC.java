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
import output.Output;

/**
 *
 * @author bruno
 */
public class SuspendLineSC extends EventHandle implements EventSystem {

    protected Network network;
    private Central central;
    private Subscriber subscriber;

    public SuspendLineSC(ManagementRounds managementRound, Round round, Network network, Subscriber subscriber, Central central, Output output) {
        super(managementRound, round, output);
        this.network = network;
        this.subscriber = subscriber;
        this.central = central;
    }

    @Override
    public void trigger() {
        output.addNewEvent("Suspender Assinante " + subscriber.getId() + " da Central " + central.getId());
        this.sucess = this.network.suspendSubscriberFromCentral(this.subscriber.getId(), this.central.getId());
        if (sucess) {
            output.addNewSignal("Assinante " + this.subscriber.getId() + " suspendida da Central " + central.getId());
        } else {
            output.addNewSignal("Assinante " + this.subscriber.getId() + " não está conectado ou ativo à Central " + central.getId());
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
