/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.ArrayList;
import java.util.List;
import search.DepthFirstSearch;
import search.Visitor;
import structure.ManagementRounds;
import structure.Subscriber;
import view.Output;

/**
 *
 * @author bruno
 */
public class Reconnect extends EventHandle implements EventSubscriber {

    private Subscriber subscriberReconnect;
    private Subscriber subscriberReceiver;
    public static final int LIMIT_TO_RECCONECT = 90;
    private List<Integer> route;

    public Reconnect(ManagementRounds managementRound, Subscriber subscriber, Round round, Output output) {
        super(managementRound, round, output);
        this.subscriberReconnect = subscriber;
        this.route = new ArrayList<>();
    }

    @Override
    public void trigger() {
        output.addNewEvent("Assinante " + subscriberReconnect.getId() + " deseja reconectar sua última ligação");
        if (this.subscriberReconnect.isFree()) {
            takePhone();
            reestablishConnection();
        }
    }

    private void takePhone() {
        output.addNewSignal("Telefone Retirado do Ganho...");
    }

    private void reestablishConnection() {
        Calling lastCallingSubscriber = this.managementRound.lastCalling(this.subscriberReconnect);
        TurnOff lastTurnOffSubscriber = this.managementRound.lastTurnOff(this.subscriberReconnect);
        this.subscriberReceiver = lastCallingSubscriber.getCaller();
        try {
            if (lastTurnOffSubscriber.timeRound() > lastCallingSubscriber.timeRound()) {
                if (lastCallingSubscriber.getReceiver().getId() == this.subscriberReconnect.getId()) {
                    int timeReconnection = this.timeRound();
                    int time = timeReconnection - lastTurnOffSubscriber.timeRound();
                    if (time < LIMIT_TO_RECCONECT) {
                        searchReceiver();
                        reconnect();
                    } else {
                        output.addNewSignal("Tempo de reconexão esgotado!");
                    }
                } else {
                    output.addNewSignal("Não é possível retormar ligação, porque este assinante foi o responsável pela discagem.");
                }
            } else {
                output.addNewSignal("Esse assinante encontra-se com uma ligação em curso no momento.");
            }
        } catch (NullPointerException e) {
            output.addNewSignal("O assinante não possui eventos de ligar ou desligar!");
        }
    }

    private void searchReceiver() throws NullPointerException {
        output.addNewSignal("Verificando Rota...");
        Visitor dfs = new DepthFirstSearch(this.subscriberReconnect, this.subscriberReceiver);
        this.route = dfs.search();
        output.addNewSignal("Rota Encontrada: " + printRoute());
    }

    public Subscriber getSubscriberReconnect() {
        return this.subscriberReconnect;
    }

    public Subscriber getSubscriberReceiver() {
        return this.subscriberReceiver;
    }

    private void reconnect() {
        if (this.subscriberReceiver.isFree()) {
            this.subscriberReceiver.setBusy();
            this.subscriberReceiver.setCurrentCommunication(subscriberReconnect);
            this.subscriberReconnect.setBusy();
            this.subscriberReconnect.setCurrentCommunication(subscriberReceiver);
            this.sucess = true;
            output.addNewSignal("Ligação entre assinante " + this.subscriberReconnect.getId() + " e " + this.subscriberReceiver.getId() + " reconectada.");
        } else {
            output.addNewSignal("Linha Ocupada.");
        }
    }

    private String printRoute() {
        String r = String.valueOf(this.route.get(0));
        int index = 1;
        while (index < this.route.size()) {
            r = r + " - " + this.route.get(index);
            index++;
        }
        return r;
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return ((subscriber.getId() == this.subscriberReconnect.getId()) || (subscriber.getId() == this.subscriberReceiver.getId()));
    }

}
