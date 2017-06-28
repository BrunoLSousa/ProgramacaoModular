/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.ManagementRounds;
import structure.Subscriber;
import output.Output;

/**
 * This class implements the TurnOff Event by one especific Subscriber
 * @author Bruno e Allan
 */
public class TurnOff extends EventHandle implements EventSubscriber {

    private Subscriber subscriber;

    /**
     * Constructor method of this class
     * 
     * @param managementRound  Object to manage rounds
     * @param round  Round which this event was 
     * @param subscriber   Subscriber that turnoff the call
     * @param output  Output object to generate the output informations
     */
    public TurnOff(ManagementRounds managementRound, Round round, Subscriber subscriber, Output output) {
        super(managementRound, round, output);
        this.subscriber = subscriber;
    }

    /**
     * Method responsible to trigger the event
     */  
    @Override
    public void trigger() {
        output.addNewEvent("Assinante " + subscriber.getId() + " solicitou um evento de desligar uma ligação");
        turnOffPhone();
        putPhoneOnHook();
    }

    /**
     * Method responsible by finish the lsta call
     */
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

    /**
     * Verify if exists communication between two subscriber
     * 
     * @param call   last calling of the subscriber
     */
    private boolean verifyComunication(Calling call){
        return (call.getCaller().hasComunication(call.getReceiver()) && call.getReceiver().hasComunication(call.getCaller()));
    }
    
    /**
     * Put phone on hool event
     */
    private void putPhoneOnHook() {
        output.addNewSignal("Telefone Colocado no Ganho...");
    }
    
    /**
     * Return the subscriber
     */
    public Subscriber getSubscriber(){
        return this.subscriber;
    }

    /**
     * Verify if this object has a specific subscriber
     * 
     * @param subscriber  Param used to compare if this subscriber exists at this object.
     */
    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return (subscriber.getId() == this.subscriber.getId());
    }

}
