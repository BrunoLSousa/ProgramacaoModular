/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.ManagementRounds;
import structure.Subscriber;
import view.Output;

/**
 *
 * @author bruno
 */
public class TurnOff extends EventHandle implements EventSubscriber {

    private Subscriber subscriber;

    public TurnOff(ManagementRounds managementRound, Round round, Subscriber subscriber, Output output) {
        super(managementRound, round, output);
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
        output.addNewEvent("Assinante " + subscriber.getId() + " solicitou um evento de desligar uma ligação");
        turnOffPhone();
        putPhoneOnHook();
    }

    private void turnOffPhone() {
        EventHandle event = this.managementRound.searchCallingBySubscriber(this.subscriber);
        try {
            Calling call = (Calling) event;
            if (call.getCaller().isBusy() && call.getReceiver().isBusy() && verifyComunication(call)) {
                call.getCaller().setFree();
                call.getCaller().finishComunication();
                call.getReceiver().setFree();
                call.getReceiver().finishComunication();
                this.sucess = true;
                output.addNewSignal("Ligação Desligada pelo Assinante " + this.subscriber.getId() + "!");
            } else {
                output.addNewSignal("Assinante " + this.subscriber.getId() + " não possui nenhum evento Ligar ativo no momento!");
            }
        } catch (NullPointerException e) {
            output.addNewSignal("Não há ligações referente ao assinante " + this.subscriber.getId());
        }
    }

    private boolean verifyComunication(Calling call){
        return (call.getCaller().hasComunication(call.getReceiver()) && call.getReceiver().hasComunication(call.getCaller()));
    }
    
    private void putPhoneOnHook() {
        output.addNewSignal("Telefone Colocado no Ganho...");
    }
    
    public Subscriber getSubscriber(){
        return this.subscriber;
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (subscriber.getId() == this.subscriber.getId());
    }

}
