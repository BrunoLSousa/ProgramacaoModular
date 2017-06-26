/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.ManagementRounds;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class Reconnect extends EventHandle implements EventSubscriber {

    private Subscriber subscriber;
    public static final int LIMIT_TO_RECCONECT = 90;

    public Reconnect(ManagementRounds managementRound, Subscriber subscriber, Round round) {
        super(managementRound, round);
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
        Calling lastCallingSubscriber = this.managementRound.lastCalling(this.subscriber);
        TurnOff lastTurnOffSubscriber = this.managementRound.lastTurnOff(this.subscriber);
        try{
            if (lastTurnOffSubscriber.timeRound() > lastCallingSubscriber.timeRound()) {
                if (lastCallingSubscriber.getReceiver().getId() == this.subscriber.getId()) {
                    int timeReconnection = this.timeRound();
                    int time = timeReconnection - lastTurnOffSubscriber.timeRound();
                    if(time < LIMIT_TO_RECCONECT){
                        lastCallingSubscriber.getCaller().setBusy();
                        lastCallingSubscriber.getCaller().setCurrentCommunication(lastCallingSubscriber.getReceiver());
                        lastCallingSubscriber.getReceiver().setBusy();
                        lastCallingSubscriber.getReceiver().setCurrentCommunication(lastCallingSubscriber.getCaller());
                        this.sucess = true;
                        System.out.println("Ligação entre assinante " + lastCallingSubscriber.getCaller().getId() + " e " + lastCallingSubscriber.getReceiver().getId() + " reconectada.");
                    }else{
                        this.sucess = false;
                        System.out.println("Tempo de reconexão esgotado!");
                    }
                } else {
                    this.sucess = false;
                    System.out.println("Não é possível retormar ligação, porque este assinante foi o responsável pela discagem.");
                }
            } else {
                this.sucess = false;
                System.out.println("Esse assinante encontra-se com uma ligação em curso no momento.");
            }
        }catch(NullPointerException e){
            this.sucess = false;
            System.err.println("O assinante não possui eventos de ligar ou desligar!");
        }
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (subscriber.getId() == this.subscriber.getId());
    }

}
