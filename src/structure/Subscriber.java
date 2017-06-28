/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 * Abstraction for Subscriber Component
 * @author Bruno e Allan
 */
public class Subscriber{
    
    private int idSubscriber;
    private Central central;
    private Status status;
    private Subscriber currentComunication;

    /**
     * Constructor method of this class
     * 
     * @param id Subscriber id
     */
    public Subscriber(int id) {
        this.idSubscriber = id;
        this.status = Status.FREE;
    }

     /**
     * Set a central 
     * 
     */
    public void setCentral(Central central) {
        this.central = central;
    }

     /**
     * Remove the central 
     * 
     */
    public void removeCentral() {
        this.central = null;
    }
    
     /**
     * Suspend line
     * 
     */
    public void suspendLine(){
        this.status = Status.SUSPENDED;
    }
    
     /**
     * Reactive line
     * 
     */
    public void reactiveLine(){
        setFree();
    }
    
     /**
     * Return central 
     * 
     */
    public Central getCentral(){
        return this.central;
    }
    
     /**
     * Verify if subscriber is empty
     * 
     */
    public boolean isFree(){
        return (this.status == Status.FREE);
    }
    
     /**
     * Verify if subscriber is busy
     * 
     */
    public boolean isBusy(){
        return (this.status == Status.BUSY);
    }
    
    /**
     * Set state to free
     * 
     */
    public void setFree(){
        this.status = Status.FREE;
    }
    
    /**
     * Set state to busy
     * 
     */
    public void setBusy(){
        this.status = Status.BUSY;
    }
    
    /**
     * Set current communication
     * 
     */
    public void setCurrentCommunication(Subscriber subscriber){
        this.currentComunication = subscriber;
    }
    
    /**
     * Finish communication
     * 
     */
    public void finishComunication(){
        setCurrentCommunication(null);
    }
    
    /**
     * Verify if subscriber has connection with a another specific subscriber
     * 
     */
    public boolean hasComunication(Subscriber subscriber){
        return (subscriber.getId() == this.currentComunication.getId());
    }
    
    /**
     * Return subscriber id
     * 
     */
    public int getId(){
        return this.idSubscriber;
    }
    
    /**
     * Possible enuns to Subscriber state
     * 
     */
    private enum Status{
        BUSY, FREE, SUSPENDED;
    }
    
}
