/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.ManagementEvents;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class Reconnect extends EventHandle implements EventSubscriber {

    private Subscriber subscriber;
    public static final int LIMIT_TO_RECCONECT = 90;

    public Reconnect(ManagementEvents managementEvents, Subscriber subscriber, int round) {
        super(managementEvents, round);
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
        if (this.subscriber.isFree()) {
            takePhone();
            reestablishConnection();
        }
    }

    private void takePhone() {
        System.out.println("Telefone Retirado do Ganho...");
    }

    private void reestablishConnection() {
        Calling lastCallingSubscriber = this.managementEvents.lastCalling(this.subscriber);
        TurnOff lastTurnOffSubscriber = this.managementEvents.lastTurnOff(this.subscriber);
        try{
            if (lastTurnOffSubscriber.getRound() > lastCallingSubscriber.getRound()) {
//                Calling call = (Calling) lastCallingSubscriber.getEvent();
                if (lastCallingSubscriber.getReceiver().getId() == this.subscriber.getId()) {
                    int timeReconnection = this.getRound();
                    int time = timeReconnection - lastTurnOffSubscriber.getRound();
//                    Round lastRound = this.managementEvents.lastRound();
//                    int time = lastRound.getRound() - roundLastTurnOffSubscriber.getRound();
                    if(time < LIMIT_TO_RECCONECT){
                        lastCallingSubscriber.getCaller().setBusy();
                        lastCallingSubscriber.getReceiver().setBusy();
                        System.out.println("Ligação entre assinante " + lastCallingSubscriber.getCaller().getId() + " e " + lastCallingSubscriber.getReceiver().getId() + " reconectada.");
                    }else{
                        System.out.println("Tempo de reconexão esgotado!");
                    }
                } else {
                    System.out.println("Não é possível retormar ligação, porque este assinante foi o responsável pela discagem.");
                }
            } else {
                System.out.println("Esse assinante encontra-se com uma ligação em curso no momento.");
            }
        }catch(NullPointerException e){
            System.err.println("O assinante não possui eventos de ligar ou desligar!");
        }
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (subscriber.getId() == this.subscriber.getId());
    }

}
