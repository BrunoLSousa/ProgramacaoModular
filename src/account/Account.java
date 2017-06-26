/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

/**
 *
 * @author bruno
 */
public class Account {
    
    private int subscriber;
    private Double valueSpent;
    
    public Account(int subscriber){
        this.subscriber = subscriber;
        this.valueSpent = 0.0;
    }
    
    public int getSubscriber(){
        return this.subscriber;
    }
    
    public Double getValueSpend(){
        return this.valueSpent;
    }
    
    public void setValue(Double value){
        this.valueSpent = value;
    }
    
}
