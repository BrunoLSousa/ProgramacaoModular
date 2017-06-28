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
 * This class implements the ReactiveLine Event between two Central
 * @author Bruno e Allan
 */
public class ReactiveLineCC extends EventHandle implements EventSystem {

    protected Network network;
    private Central centralA;
    private Central centralB;

    /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param round  Round which this event was 
     * @param network  Network which this event will be trigged
     * @param centralA  Central that will be reactived
     * @param centralB  Another central that will be reactived 
     * @param output  Output object to generate the output informations
     */
    public ReactiveLineCC(ManagementRounds managementRound, Round round, Network network, Central centralA, Central centralB, Output output) {
        super(managementRound, round, output);
        this.network = network;
        this.centralA = centralA;
        this.centralB = centralB;
    }

    /**
     * Method responsible to trigger the event
     */  
    @Override
    public void trigger() {
        output.addNewEvent("Reativar Central " + centralA.getId() + " à Central " + centralB.getId());
        this.sucess = this.network.reactiveCentralToCentral(centralA.getId(), centralB.getId());
        if (sucess) {
            output.addNewSignal("Central " + centralA.getId() + " reativada com Central " + centralB.getId());
        } else {
            output.addNewSignal("Conexão entre Central " + centralA.getId() + " e Central " + centralB.getId() + " não se encontra suspensa");
        }
    }

    /**
     * Verify if this object has a specific subscriber
     * 
     * @param subscriber  Param used to compare if this subscriber exists at this object.
     */
    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return false;
    }

    /**
     * Verify if this object has a specific central
     * 
     * @param central  Param used to compare if this central exists at this object.
     */
    @Override
    public boolean hasCentral(Central central) {
        return ((central.getId() == this.centralA.getId()) || (central.getId() == this.centralB.getId()));
    }

}
