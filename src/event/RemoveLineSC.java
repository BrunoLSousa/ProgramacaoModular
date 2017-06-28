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
 * This class implements the RemoveLine Event between Subscriber and Central
 * @author Bruno e Allan
 */
public class RemoveLineSC extends EventHandle implements EventSystem {

    protected Network network;
    private Central central;
    private Subscriber subscriber;

    /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param round  Round which this event was 
     * @param network  Network which this event will be trigged
     * @param central  Central that will be removed
     * @param subscriber  Subscriber that will be removed 
     * @param output  Output object to generate the output informations
     */
    public RemoveLineSC(ManagementRounds managementRound, Round round, Network network, Subscriber subscriber, Central central, Output output) {
        super(managementRound, round, output);
        this.network = network;
        this.central = central;
        this.subscriber = subscriber;
    }

    /**
     * Method responsible to trigger the event
     */  
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

    /**
     * Verify if this object has a specific subscriber
     * 
     * @param subscriber  Param used to compare if this subscriber exists at this object.
     */
    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (central.getId() == this.central.getId());
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
