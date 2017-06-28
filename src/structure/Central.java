/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction for Central Component
 * @author Bruno e Allan
 */
public class Central {

    private int idCentral;
    private Network network;
    private List<Integer> centralConnected;
    private List<Integer> subscribersConnected;
    private List<Integer> centralSuspended;
    private List<Integer> subscribersSuspended;

    /**
     * Constructor method of this class
     * 
     * @param id Central id
     * @param network Network which this central is reference
     */
    public Central(int id, Network network) {
        this.network = network;
        this.idCentral = id;
        this.centralConnected = new ArrayList<>();
        this.subscribersConnected = new ArrayList<>();
        this.centralSuspended = new ArrayList<>();
        this.subscribersSuspended = new ArrayList<>();
    }

    /**
     * Add central to connected list
     * 
     * @param idCentral  Central id to be connected
     */
    public void addConnectionCentral(Integer idCentral) {
        this.centralConnected.add(idCentral);
    }

    /**
     * Add subscriber to connected list
     * 
     * @param idSubscriber  Subscriber id to be connected
     */
    public void addConnectionSubscriber(Integer idSubscriber) {
        this.subscribersConnected.add(idSubscriber);
    }
    
    /**
     * Remove central from this central
     * 
     * @param idCentral  Central id to be removed
     */
    public void removeCentral(int idCentral){
        removeConnectionCentral(idCentral);
        removeCentralSuspend(idCentral);
    }
    
    /**
     * Remove subscriber from this central
     * 
     * @param idSubscriber  Subscriber id to be removed
     */
    public void removeSubscriber(int idSubscriber){
        removeConnectionSubscriber(idSubscriber);
        removeSubscriberSuspend(idSubscriber);
    }

    /**
     * Remove central from connected list
     * 
     * @param idCentral  Central id to be removed
     */
    public void removeConnectionCentral(Integer idCentral) {
        if (hasCentralConnection(idCentral)) {
            this.centralConnected.remove(idCentral);
        }
    }

    /**
     * Remove subscriber from connected list
     * 
     * @param idSubscriber  Subscriber id to be removed
     */
    public void removeConnectionSubscriber(Integer idSubscriber) {
        if (hasSubscriberConnection(idSubscriber)) {
            this.subscribersConnected.remove(idSubscriber);
        }
    }
    
    /**
     * Remove central from suspend list
     * 
     * @param idCentral  Central id to be removed
     */
    private void removeCentralSuspend(int idCentral){
        if(hasCentralSuspend(idCentral)){
            this.centralSuspended.remove(idCentral);
        }
    }
    
    /**
     * Remove subscriber from suspend list
     * 
     * @param idSubscriber   Subscriber id to be removed
     */
    private void removeSubscriberSuspend(int idSubscriber){
        if(hasSubscriberSuspend(idCentral)){
            this.subscribersSuspended.remove(idCentral);
        }
    }

    /**
     * Suspend a central from connected list
     * 
     * @param idCentral  Central id to be suspended
     */
    public void suspendConnectionCentral(Integer idCentral) {
        if (hasCentralConnection(idCentral)) {
            this.subscribersSuspended.add(idCentral);
            this.subscribersConnected.remove(idCentral);
        }
    }

    /**
     * Suspend a subscriber from connected list
     * 
     * @param idSubscriber  Subscriber id to be suspended
     */
    public void suspendConnectionSubscriber(Integer idSubscriber) {
        if (hasSubscriberConnection(idSubscriber)) {
            this.subscribersSuspended.add(idSubscriber);
            this.subscribersConnected.remove(idSubscriber);
        }
    }

    /**
     * Reactive a central
     * 
     * @param idCentral  Central id to be reactived
     */
    public void reactiveConnectionCentral(Integer idCentral) {
        if (hasCentralSuspend(idCentral)) {
            this.centralConnected.add(idCentral);
            this.centralSuspended.remove(idCentral);
        }
    }

    /**
     * Reactive a subscriber
     * 
     * @param idCentral  Subscriber id to be reactived
     */
    public void reactiveConnectionSubscriber(Integer idSubscriber) {
        if (hasSubscriberSuspend(idSubscriber)) {
            this.subscribersConnected.add(idSubscriber);
            this.subscribersSuspended.remove(idSubscriber);
        }
    }

    /**
     * Verify if central connection list has one specific central
     * 
     * @param idCentral  Central id to be verified
     */
    public boolean hasCentralConnection(Integer idCentral) {
        for (Integer id : this.centralConnected) {
            if (id == idCentral) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if subscriber connection list has one specific subscriber
     * 
     * @param idSubscriber  Subscriber id to be verified
     */
    public boolean hasSubscriberConnection(Integer idSubscriber) {
        for (Integer id : this.subscribersConnected) {
            if (id == idSubscriber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if central is supended
     * 
     * @param idCentral  Central id to be verified
     */
    public boolean hasCentralSuspend(Integer idCentral) {
        for (Integer id : this.centralSuspended) {
            if (id == idCentral) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * Verify if subscriber is supended
     * 
     * @param idSubscriber   Subscriber id to be verified
     */
    public boolean hasSubscriberSuspend(Integer idSubscriber) {
        for (Integer id : this.subscribersSuspended) {
            if (id == idSubscriber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if subscriber exists in connected list
     * 
     * @param idSubscriber  Subscriber id to be verified
     */
    public boolean hasSubscriber(Integer idSubscriber) {
        return (hasSubscriberConnection(idSubscriber) || hasSubscriberSuspend(idSubscriber));
    }

    /**
     * Verify if central exists in connected list
     * 
     * @param idCentral Central id to be verified
     */
    public boolean hasCentral(Integer idCentral) {
        return (hasCentralConnection(idCentral) || hasCentralSuspend(idCentral));
    }

    /**
     * Return connection list
     * 
     */
    public List<Integer> getCentralConnected() {
        return this.centralConnected;
    }
    
     /**
     * Return to central by id
     * 
     */
    public Central getCentralByID(int id){
        return this.network.getCentralByID(id);
    }

     /**
     * Return the id of this central
     * 
     */
    public int getId() {
        return this.idCentral;
    }

}
