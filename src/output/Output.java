/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import account.Account;

/**
 * This class is responsible to generate the output.
 * @author Bruno e Allan
 */
public class Output {
    
    private InvoiceOutput account;
    private EventOutput events;
    private SignalOutput signal;
    private int indexEvent;
    
    /**
     * Constructor method of this class
     * 
     */
    public Output(){
        this.account = new InvoiceOutput();
        this.events = new EventOutput();
        this.signal = new SignalOutput();
        this.indexEvent = -1;
    }
    
    /**
     * Incremente the current event
     * 
     */
    private void incrementEvent(){
        this.indexEvent++;
    }
    
    /**
     * Create a new event of signal map
     * 
     */
    public void addNewEvent(String event){
        incrementEvent();
        this.events.addEvent(event);
    }
    
    /**
     * Add a new signal of signal list
     * 
     */
    public void addNewSignal(String signal){
        if(this.signal.lastEvent() < this.indexEvent){
            this.signal.addNewEvent(indexEvent);
        }
        this.signal.addSignal(indexEvent, signal);
    }
    
    /**
     * Set the invoces issued
     * 
     * @param accounts  has the all invoices issued by each subscriber
     */
    public void setIssuesAccount(Account[][] accounts){
        this.account.setAccounts(accounts);
    }
    
    /**
     * Export of output files
     * 
     */
    public void export(String path){
        this.events.export(path);
        this.signal.export(path);
        this.account.export(path);
    }
    
}
