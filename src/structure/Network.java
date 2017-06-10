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
            Central newCentral = new Central(index);
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
        centralA.addConnectionCentral(centralB);
        centralB.addConnectionCentral(centralA);
    }
    
    public void connectSubscriberToCentral(int indexSubscriber, int indexCentral){
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        s.setCentral(c);
        c.addConnectionSubscriber(s);
    }
    
    public void removeSubscriberFromCentral(int indexSubscriber, int indexCentral){
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        c.removeConnectionSubscriber(s);
        s.removeCentral();
    }
    
    public void removeCentralFromCentral(int indexCentralA, int indexCentralB){
        Central cA = this.central.get(indexCentralA);
        Central cB = this.central.get(indexCentralB);
        cA.removeConnectionCentral(cB);
        cB.removeConnectionCentral(cA);
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
    
}
