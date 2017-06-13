/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.Subscriber;

/**
 *
 * @author bruno
 */
public interface EventSubscriber {
    
    public boolean hasSubscriber(Subscriber subscriber);
    
}
