/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.factory;

import event.EventHandle;
import structure.ManagementEvents;
import structure.Network;

/**
 *
 * @author bruno
 */
public abstract class FactoryEventChain {

    protected ManagementEvents managementEvents;
    protected Network network;
//    private FactoryEventChain next;
    protected final String code;
//    private EventHandle product;
    
    public FactoryEventChain(ManagementEvents managementEvents, Network network, String code){
        this.managementEvents = managementEvents;
        this.network = network;
        this.code = code;
//        this.next = null;
//        this.product = null;
    }
    
//    public EventHandle createEvent(String code, int round, String[] infoEvent){
//        setProduct(code, round, infoEvent);
//        return product;
//    }
//    
//    private void setProduct(String code, int round, String[] infoEvent){
//        if(isCode(code)){
//            this.product = create(round, infoEvent);
//        }else{
//            if(this.next != null)
//                this.next.createEvent(code, round, infoEvent);
//        }
//    }
    
    public abstract EventHandle create(int round, String[] infoEvent);
    
//    public void setNext(FactoryEventChain factory){
//        if (next == null) {
//            this.next = factory;
//        } else {
//            this.next.setNext(factory);
//        }
//    }
    
    public boolean isCode(String code){
        return (code.equals(this.code));
    }

}
