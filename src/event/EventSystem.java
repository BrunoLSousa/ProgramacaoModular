/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.Central;
import structure.Subscriber;

/**
 * This interface has services reference to Central
 * @author Bruno e Allan
 */
public interface EventSystem {
    
    /**
     * Verify if has a Suscriber
     */
    public boolean hasSubscriber(Subscriber subscriber);
    
    /**
     * Verify if has a Central
     */
    public boolean hasCentral(Central central);
    
}
