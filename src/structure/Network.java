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

    public Network() {
        this.central = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void buildNetwork(int amountCentral, int amountSubscriber) {
        buildCentral(amountCentral);
        buildSubscribers(amountSubscriber);
    }

    private void buildCentral(int amount) {
        for (int index = 0; index < amount; index++) {
            Central newCentral = new Central(index, this);
            this.central.add(newCentral);
        }
    }

    private void buildSubscribers(int amount) {
        for (int index = 0; index < amount; index++) {
            Subscriber newSubscriber = new Subscriber(index);
            this.subscribers.add(newSubscriber);
        }
    }

    public boolean connectCentralToCentral(int indexA, int indexB) {
        Central centralA = this.central.get(indexA);
        Central centralB = this.central.get(indexB);
        if (!centralA.hasCentral(indexB)) {
            centralA.addConnectionCentral(indexB);
            centralB.addConnectionCentral(indexA);
            return true;
        }
        return false;
    }

    public boolean connectSubscriberToCentral(int indexSubscriber, int indexCentral) {
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        if (!c.hasSubscriber(indexSubscriber)) {
            s.setCentral(c);
            c.addConnectionSubscriber(indexSubscriber);
            return true;
        }
        return false;
    }

    public boolean removeCentralFromCentral(int indexCentralA, int indexCentralB) {
        Central cA = this.central.get(indexCentralA);
        Central cB = this.central.get(indexCentralB);
        if (cA.hasCentral(indexCentralB)) {
            cA.removeCentral(indexCentralB);
            cB.removeCentral(indexCentralA);
            return true;
        }
        return false;
    }

    public boolean removeSubscriberFromCentral(int indexSubscriber, int indexCentral) {
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        if (c.hasSubscriber(indexSubscriber)) {
            c.removeSubscriber(indexSubscriber);
            s.removeCentral();
            return true;
        }
        return false;
    }

    public boolean suspendCentralFromCentral(int indexCentralA, int indexCentralB) {
        Central cA = this.central.get(indexCentralA);
        Central cB = this.central.get(indexCentralB);
        if (cA.hasCentralConnection(indexCentralB)) {
            cA.suspendConnectionCentral(indexCentralB);
            cB.suspendConnectionCentral(indexCentralA);
            return true;
        }
        return false;
    }

    public boolean suspendSubscriberFromCentral(int indexSubscriber, int indexCentral) {
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        if (c.hasSubscriberConnection(indexSubscriber)) {
            c.suspendConnectionSubscriber(indexSubscriber);
            s.suspendLine();
            return true;
        }
        return false;
    }

    public boolean reactiveCentralToCentral(int indexCentralA, int indexCentralB) {
        Central cA = this.central.get(indexCentralA);
        Central cB = this.central.get(indexCentralB);
        if (cA.hasCentralSuspend(indexCentralB)) {
            cA.reactiveConnectionCentral(indexCentralB);
            cB.reactiveConnectionCentral(indexCentralA);
            return true;
        }
        return false;
    }

    public boolean reactiveSubscriberToCentral(int indexSubscriber, int indexCentral) {
        Subscriber s = this.subscribers.get(indexSubscriber);
        Central c = this.central.get(indexCentral);
        if (c.hasSubscriberSuspend(indexSubscriber)) {
            c.reactiveConnectionCentral(indexSubscriber);
            s.reactiveLine();
            return true;
        }
        return false;
    }

//    public void printConnectionsCentral(){
//        for(Central c : this.central){
//            c.printCentral();
//            c.printSubscriber();
//        }
//    }
//    
//    public void printConnectionsSubscriber(){
//        for(Subscriber s : this.subscribers){
//            s.printCentral();
//        }
//    }
    
    public Subscriber getSubscriberByID(int idSubscriber) {
        for (Subscriber s : this.subscribers) {
            if (s.getId() == idSubscriber) {
                return s;
            }
        }
        return null;
    }

    public Central getCentralByID(int idCentral) {
        for (Central c : this.central) {
            if (c.getId() == idCentral) {
                return c;
            }
        }
        return null;
    }

}
