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
import structure.Subscriber;
import view.Output;

/**
 *
 * @author bruno
 */
public class FactoryReactiveLineSC extends FactoryEventChain{

    private Central central;
    private Subscriber subscriber;

    public FactoryReactiveLineSC(ManagementRounds managementRound, Network network, String code) {
        super(managementRound, network, code);
    }

    @Override
    public EventHandle create(Round round, String[] infoEvent, Output output) {
        this.subscriber = this.network.getSubscriberByID(Integer.parseInt(infoEvent[3]));
        this.central = this.network.getCentralByID(Integer.parseInt(infoEvent[4]));
        return new SuspendLineSC(managementRound, round, network, subscriber, central, output);
    }
    
}