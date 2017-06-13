/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Network {
    
    private List<Central> central;
    private List<Subscriber> subscribers;
    
    public Network(){
        this.central = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }
    
    public void buildNetwork(int amountCentral, int amountSubscriber){
        buildCentral(amountCentral);
        buildSubscribers(amountSubscriber);
    }
    
    private void buildCentral(int amount){
        for(int index = 0; index < amount; index++){
            Central newCentral = new Central(index, this);
            this.central.add(newCentral);
        }
    }
    
    private void buildSubscribers(int amount){
        for(int index = 0; index < amount; index++){
            Subscriber newSubscriber = new Subscriber(index);
            this.subscribers.add(newSubscriber);
        }
    }
    
    public void connectCentralToCentral(int indexA, int indexB){
        Central centralA = this.central.get(indexA);
        Central centralB = this.central.get(indexB);
        centralA.addConnectionCentral(indexB);
        centralB.addConnectionCentral(indexA);
    }
    
    public void connectSubscriberToCentral(int indexSubscriber, int indexCentral){
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        s.setCentral(c);
        c.addConnectionSubscriber(indexSubscriber);
    }
    
    public void removeCentralFromCentral(int indexCentralA, int indexCentralB){
        Central cA = this.central.get(indexCentralA);
        Central cB = this.central.get(indexCentralB);
        cA.removeConnectionCentral(indexCentralB);
        cB.removeConnectionCentral(indexCentralA);
    }
    
    public void removeSubscriberFromCentral(int indexSubscriber, int indexCentral){
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        c.removeConnectionSubscriber(indexSubscriber);
        s.removeCentral();
    }
    
    public void suspendCentralFromCentral(int indexCentralA, int indexCentralB){
        Central cA = this.central.get(indexCentralA);
        Central cB = this.central.get(indexCentralB);
        cA.suspendConnectionCentral(indexCentralB);
        cB.suspendConnectionCentral(indexCentralA);
    }
    
    public void suspendSubscriberFromCentral(int indexSubscriber, int indexCentral){
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        c.suspendConnectionSubscriber(indexSubscriber);
        s.suspendLine();
    }
    
    public void reactiveCentralToCentral(int indexCentralA, int indexCentralB){
        Central cA = this.central.get(indexCentralA);
        Central cB = this.central.get(indexCentralB);
        cA.reactiveConnectionCentral(indexCentralB);
        cB.reactiveConnectionCentral(indexCentralA);
    }
    
    public void reactiveSubscriberToCentral(int indexSubscriber, int indexCentral){
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        c.reactiveConnectionCentral(indexSubscriber);
        s.reactiveLine();
    }
    
    public void printConnectionsCentral(){
        for(Central c : this.central){
            c.printCentral();
            c.printSubscriber();
        }
    }
    
    public void printConnectionsSubscriber(){
        for(Subscriber s : this.subscribers){
            s.printCentral();
        }
    }
    
    public Subscriber getSubscriberByID(int idSubscriber){
        for(Subscriber s : this.subscribers){
            if(s.getId() == idSubscriber){
                return s;
            }
        }
        return null;
    }
    
    public Central getCentralByID(int idCentral){
        for(Central c : this.central){
            if(c.getId() == idCentral){
                return c;
            }
        }
        return null;
    }
        
}
