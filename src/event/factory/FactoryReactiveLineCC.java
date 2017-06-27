/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.*;
import structure.Central;
import structure.ManagementRounds;
import structure.Network;
import output.Output;

/**
 *
 * @author bruno
 */
public class FactoryReactiveLineCC extends FactoryEventChain{

    private Central centralA;
    private Central centralB;

    public FactoryReactiveLineCC(ManagementRounds managementRound, Network network, String code) {
        super(managementRound, network, code);
    }

    @Override
    public EventHandle create(Round round, String[] infoEvent, Output output) {
        this.centralA = this.network.getCentralByID(Integer.parseInt(infoEvent[3]));
        this.centralB = this.network.getCentralByID(Integer.parseInt(infoEvent[4]));
        return new SuspendLineCC(managementRound, round, network, centralA, centralB, output);
    }
    
}
