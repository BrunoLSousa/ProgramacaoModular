/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.ArrayList;
import java.util.List;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class Round {
    
    private int value;
    private List<EventHandle> events;
    
    public Round(int value){
        this.value = value;
        this.events = new ArrayList<>();
    }
    
    public void addEvent(EventHandle event){
        this.events.add(event);
    }
    
    public EventHandle getEventSubscriber(Subscriber subscriber){
        int size = this.events.size();
        for (int i = (size - 1); i >= 0; i--) {
            EventHandle e = this.events.get(i);
            if (e instanceof EventSubscriber) {
                if (((EventSubscriber) e).hasSubscriber(subscriber)) {
                    return e;
                }
            }
        }
        return null;
    }
    
    public EventHandle getEventCalling(Subscriber subscriber){
        int size = this.events.size();
        for (int i = (size - 1); i >= 0; i--) {
            EventHandle e = this.events.get(i);
            if (e instanceof Calling) {
                if (((Calling) e).hasSubscriber(subscriber)) {
                    return e;
                }
            }
        }
        return null;
    }
    
    public EventHandle getTurnOff(Subscriber subscriber){
        int size = this.events.size();
        for (int i = (size - 1); i >= 0; i--) {
            EventHandle e = this.events.get(i);
            if (e instanceof TurnOff) {
                if (((TurnOff) e).hasSubscriber(subscriber)) {
                    return ((TurnOff) e);
                }
            }
        }
        return null;
    }
    
    public int amountEvents(){
        return this.events.size();
    }
    
    public EventHandle getLastEvent(){
        int size = this.events.size();
        return this.events.get(size - 1);
    }
    
    public int getValue(){
        return this.value;
    }
    
}
