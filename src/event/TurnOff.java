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
public class TurnOff extends EventHandle implements EventSubscriber {

    private Subscriber subscriber;

    public TurnOff(ManagementRounds managementRound, Round round, Subscriber subscriber) {
        super(managementRound, round);
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
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
                System.out.println("Ligação Desligada pelo Assinante " + this.subscriber.getId() + "!");
            } else {
                this.sucess = false;
                System.out.println("Assinante " + this.subscriber.getId() + " não possui nenhum evento Ligar ativo no momento!");
            }
        } catch (NullPointerException e) {
            System.err.println("Não há ligações referente ao assinante " + this.subscriber.getId());
        }
    }

    private boolean verifyComunication(Calling call){
        return (call.getCaller().hasComunication(call.getReceiver()) && call.getReceiver().hasComunication(call.getCaller()));
    }
    
    private void putPhoneOnHook() {
        System.out.println("Telefone Colocado no Ganho...");
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (subscriber.getId() == this.subscriber.getId());
    }

}
