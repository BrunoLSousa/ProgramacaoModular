/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import structure.Subscriber;

/**
 * This interface has services reference to Subscribers
 * @author Bruno e Allan
 */
public interface EventSubscriber {

    /**
     * Verify if has a Suscriber
     */
    public boolean hasSubscriber(Subscriber subscriber);

}
