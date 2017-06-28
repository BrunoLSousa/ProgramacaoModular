/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

/**
 * This class implements the Account
 * @author Bruno e Allan
 */
public class Account {
    
    private int subscriber;
    private Double valueSpent;
    
    /**
     * Constructor method of this class
     * 
     * @param subscriber  Subscriber id
     */
    public Account(int subscriber){
        this.subscriber = subscriber;
        this.valueSpent = 0.0;
    }
    
    /**
     * Return Subscriber id
     */  
    public int getSubscriber(){
        return this.subscriber;
    }
    
    /**
     * Return value spent of account
     */ 
    public Double getValueSpend(){
        return this.valueSpent;
    }
    
    /**
     * Set Account value
     */ 
    public void setValue(Double value){
        this.valueSpent = value;
    }
    
}
