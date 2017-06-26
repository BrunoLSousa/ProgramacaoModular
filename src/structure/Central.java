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
public class Central {

    private int idCentral;
    private Network network;
    private List<Integer> centralConnected;
    private List<Integer> subscribersConnected;
    private List<Integer> centralSuspended;
    private List<Integer> subscribersSuspended;

    public Central(int id, Network network) {
        this.network = network;
        this.idCentral = id;
        this.centralConnected = new ArrayList<>();
        this.subscribersConnected = new ArrayList<>();
        this.centralSuspended = new ArrayList<>();
        this.subscribersSuspended = new ArrayList<>();
    }

    public void addConnectionCentral(Integer idCentral) {
        this.centralConnected.add(idCentral);
    }

    public void addConnectionSubscriber(Integer idSubscriber) {
        this.subscribersConnected.add(idSubscriber);
    }
    
    public void removeCentral(int idCentral){
        removeConnectionCentral(idCentral);
        removeCentralSuspend(idCentral);
    }
    
    public void removeSubscriber(int idSubscriber){
        removeConnectionSubscriber(idSubscriber);
        removeSubscriberSuspend(idSubscriber);
    }

    public void removeConnectionCentral(Integer idCentral) {
        if (hasCentralConnection(idCentral)) {
            this.centralConnected.remove(idCentral);
        }
    }

    public void removeConnectionSubscriber(Integer idSubscriber) {
        if (hasSubscriberConnection(idSubscriber)) {
            this.subscribersConnected.remove(idSubscriber);
        }
    }
    
    private void removeCentralSuspend(int idCentral){
        if(hasCentralSuspend(idCentral)){
            this.centralSuspended.remove(idCentral);
        }
    }
    
    private void removeSubscriberSuspend(int idSubscriber){
        if(hasSubscriberSuspend(idCentral)){
            this.subscribersSuspended.remove(idCentral);
        }
    }

    public void suspendConnectionCentral(Integer idCentral) {
        if (hasCentralConnection(idCentral)) {
            this.subscribersSuspended.add(idCentral);
            this.subscribersConnected.remove(idCentral);
        }
    }

    public void suspendConnectionSubscriber(Integer idSubscriber) {
        if (hasSubscriberConnection(idSubscriber)) {
            this.subscribersSuspended.add(idSubscriber);
            this.subscribersConnected.remove(idSubscriber);
        }
    }

    public void reactiveConnectionCentral(Integer idCentral) {
        if (hasCentralSuspend(idCentral)) {
            this.centralConnected.add(idCentral);
            this.centralSuspended.remove(idCentral);
        }
    }

    public void reactiveConnectionSubscriber(Integer idSubscriber) {
        if (hasSubscriberSuspend(idSubscriber)) {
            this.subscribersConnected.add(idSubscriber);
            this.subscribersSuspended.remove(idSubscriber);
        }
    }

    public boolean hasCentralConnection(Integer idCentral) {
        for (Integer id : this.centralConnected) {
            if (id == idCentral) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSubscriberConnection(Integer idSubscriber) {
        for (Integer id : this.subscribersConnected) {
            if (id == idSubscriber) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCentralSuspend(Integer idCentral) {
        for (Integer id : this.centralSuspended) {
            if (id == idCentral) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSubscriberSuspend(Integer idSubscriber) {
        for (Integer id : this.subscribersSuspended) {
            if (id == idSubscriber) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSubscriber(Integer idSubscriber) {
        return (hasSubscriberConnection(idSubscriber) || hasSubscriberSuspend(idSubscriber));
    }

    public boolean hasCentral(Integer idCentral) {
        return (hasCentralConnection(idCentral) || hasCentralSuspend(idCentral));
    }

//    public void printCentral() {
//        System.out.println("Connection Central " + this.idCentral + " with others central");
//        for (Integer id : this.centralConnected) {
//            System.out.print(id + "  ");
//        }
//        System.out.println("");
//    }

//    public void printSubscriber() {
//        System.out.println("Connection Central " + this.idCentral + " with subscribers");
//        for (Integer id : this.subscribersConnected) {
//            System.out.print(id + "  ");
//        }
//        System.out.println("");
//    }

    public List<Integer> getCentralConnected() {
        return this.centralConnected;
    }
    
    public Central getCentralByID(int id){
        return this.network.getCentralByID(id);
    }

    public int getId() {
        return this.idCentral;
    }

}
