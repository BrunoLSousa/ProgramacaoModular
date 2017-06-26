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
public class ReactiveLineCC extends EventHandle implements EventSystem {

    protected Network network;
    private Central centralA;
    private Central centralB;

    public ReactiveLineCC(ManagementRounds managementRound, Round round, Network network, Central centralA, Central centralB) {
        super(managementRound, round);
        this.network = network;
        this.centralA = centralA;
        this.centralB = centralB;
    }

    @Override
    public void trigger() {
        this.sucess = this.network.reactiveCentralToCentral(centralA.getId(), centralB.getId());
        if (sucess) {
            System.out.println("Central " + centralA.getId() + " reativada com Central " + centralB.getId());
        } else {
            System.err.println("Conexão entre Central " + centralA.getId() + " e Central " + centralB.getId() + " não se encontra suspensa");
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
