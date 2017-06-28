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
 * This class implements the ADDLine Event between two Central
 * @author Bruno e Allan
 */
public class ADDLineCC extends EventHandle implements EventSystem {

    protected Network network;
    private Central centralA;
    private Central centralB;

    /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param round  Round which this event was 
     * @param network  Network which this event will be trigged
     * @param centralA  Central that will be connected
     * @param centralB  Another central that will be connected 
     * @param output  Output object to generate the output informations
     */
    public ADDLineCC(ManagementRounds managementRound, Round round, Network network, Central centralA, Central centralB, Output output) {
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
        output.addNewEvent("Conectar Central " + centralA.getId() + " à Central " + centralB.getId());
        this.sucess = this.network.connectCentralToCentral(centralA.getId(), centralB.getId());
        if (sucess) {
            output.addNewSignal("Central " + centralA.getId() + " conectada com Central " + centralB.getId());
        } else {
            output.addNewSignal("Central " + centralA.getId() + " já possui conexão com Central " + centralB.getId());
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
