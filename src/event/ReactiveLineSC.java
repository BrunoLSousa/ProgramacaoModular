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
public class ReactiveLineSC extends EventHandle implements EventSystem {

    protected Network network;
    private Central central;
    private Subscriber subscriber;

    public ReactiveLineSC(ManagementRounds managementRound, Round round, Network network, Central central, Subscriber subscriber, Output output) {
        super(managementRound, round, output);
        this.network = network;
        this.central = central;
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
        output.addNewEvent("Reativar Assinante " + subscriber.getId() + " à Central " + central.getId());
        this.sucess = this.network.reactiveSubscriberToCentral(this.subscriber.getId(), this.central.getId());
        if (sucess) {
            output.addNewSignal("Assinante " + this.subscriber.getId() + " reativado com Central " + this.central.getId());
        } else {
            output.addNewSignal("Conexão entre Assinante " + this.subscriber.getId() + " e Central " + this.central.getId() + " não se encontra suspensa");
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
