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
public class ADDLineCC extends EventHandle implements EventSystem {

    protected Network network;
    private Central centralA;
    private Central centralB;

    public ADDLineCC(ManagementRounds managementRound, Round round, Network network, Central centralA, Central centralB, Output output) {
        super(managementRound, round, output);
        this.network = network;
        this.centralA = centralA;
        this.centralB = centralB;
    }

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

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return false;
    }

    @Override
    public boolean hasCentral(Central central) {
        return ((central.getId() == this.centralA.getId()) || (central.getId() == this.centralB.getId()));
    }

}
