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
public class TurnOff extends EventHandle implements EventSubscriber{
    
    private Subscriber subscriber;
    
    public TurnOff(ManagementEvents managementEvents, int round, Subscriber subscriber){
        super(managementEvents, round);
        this.subscriber = subscriber;
    }

    @Override
    public void trigger() {
        turnOffPhone();
        putPhoneOnHook();
    }
    
    private void turnOffPhone(){
        EventHandle event = this.managementEvents.searchCallingBySubscriber(this.subscriber);
        try {
            Calling call = (Calling) event;
            call.getCaller().setFree();
            call.getReceiver().setFree();
            System.out.println("Ligação Desligada pelo Assinante " + this.subscriber.getId() + "!");
        } catch (NullPointerException e) {
            System.err.println("Não há ligações referente ao assinante " + this.subscriber.getId());
        }
    }
    
    private void putPhoneOnHook(){
        System.out.println("Telefone Colocado no Ganho...");
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (subscriber.getId() == this.subscriber.getId());
    }
    
}
