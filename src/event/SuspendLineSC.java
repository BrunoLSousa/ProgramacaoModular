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
 * This class implements the SuspendLine Event between Subscriber and Central
 * @author Bruno e Allan
 */
public class SuspendLineSC extends EventHandle implements EventSystem {

    protected Network network;
    private Central central;
    private Subscriber subscriber;

    /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param round  Round which this event was 
     * @param network  Network which this event will be trigged
     * @param central  Central that will be suspended
     * @param subscriber  Subscriber that will be suspended 
     * @param output  Output object to generate the output informations
     */
    public SuspendLineSC(ManagementRounds managementRound, Round round, Network network, Subscriber subscriber, Central central, Output output) {
        super(managementRound, round, output);
        this.network = network;
        this.subscriber = subscriber;
        this.central = central;
    }

    /**
     * Method responsible to trigger the event
     */  
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

    /**
     * Verify if this object has a specific subscriber
     * 
     * @param subscriber  Param used to compare if this subscriber exists at this object.
     */
    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (subscriber.getId() == this.subscriber.getId());
    }

    /**
     * Verify if this object has a specific central
     * 
     * @param central  Param used to compare if this central exists at this object.
     */
    @Override
    public boolean hasCentral(Central central) {
        return (central.getId() == this.central.getId());
    }

}
