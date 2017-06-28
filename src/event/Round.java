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
 * This class implements each round provided by input
 * @author Bruno e Allan
 */
public class Round {
    
    private int value;
    private List<EventHandle> events;
    
    /**
     * Constructor method of this class
     * 
     * @param value  Value of the round
     */
    public Round(int value){
        this.value = value;
        this.events = new ArrayList<>();
    }
    
    /**
     * Add new event at this round
     * 
     * @param event   event hat will be added
     */
    public void addEvent(EventHandle event){
        this.events.add(event);
    }
    
    /**
     * Return the last event of a subscriber
     * @param subscriber  Subscriber of the event to be found
     */
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
    
    /**
     * Return the last calling event of a subscriber
     * @param subscriber  Subscriber of the event to be found
     */
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
    
    /**
     * Return the last turnoff event of a subscriber
     * @param subscriber  Subscriber of the event to be found
     */
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
    
    /**
     * Return the amount events at this round
     */
    public int amountEvents(){
        return this.events.size();
    }
    
    /**
     * Return last event of this round
     */
    public EventHandle getLastEvent(){
        int size = this.events.size();
        return this.events.get(size - 1);
    }
    
    /**
     * Return a event by index
     */
    public EventHandle getEventByIndex(int index){
        return this.events.get(index);
    }
    
    /**
     * Return the vakue of this r
     */
    public int getValue(){
        return this.value;
    }
    
}
