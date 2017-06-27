/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import account.Account;

/**
 *
 * @author bruno
 */
public class Output {
    
    private InvoiceOutput account;
    private EventOutput events;
    private SignalOutput signal;
    private int indexEvent;
    
    public Output(){
        this.account = new InvoiceOutput();
        this.events = new EventOutput();
        this.signal = new SignalOutput();
        this.indexEvent = -1;
    }
    
    private void incrementEvent(){
        this.indexEvent++;
    }
    
    public void addNewEvent(String event){
        incrementEvent();
        this.events.addEvent(event);
    }
    
    public void addNewSignal(String signal){
        if(this.signal.lastEvent() < this.indexEvent){
            this.signal.addNewEvent(indexEvent);
        }
        this.signal.addSignal(indexEvent, signal);
    }
    
    public void setIssuesAccount(Account[][] accounts){
        this.account.setAccounts(accounts);
    }
    
    public void export(String path){
        this.events.export(path);
        this.signal.export(path);
        this.account.export(path);
    }
    
}
