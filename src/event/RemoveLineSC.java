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
public class RemoveLineSC extends EventHandle implements EventSystem {

    protected Network network;
    private Central central;
    private Subscriber subscriber;

    public RemoveLineSC(ManagementRounds managementRound, Round round, Network network, Subscriber subscriber, Central central, Output output) {
        super(managementRound, round, output);
        this.network = network;
        this.central = central;
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
        output.addNewEvent("Remover Assinante " + subscriber.getId() + " da Central " + central.getId());
        this.sucess = this.network.removeSubscriberFromCentral(this.subscriber.getId(), this.central.getId());
        if (sucess) {
            output.addNewSignal("Assinante " + this.subscriber.getId() + " removido da Central " + central.getId());
        } else {
            output.addNewSignal("Conexão não existente entre o Assinante " + this.subscriber.getId() + " e a Central " + central.getId());
        }
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
