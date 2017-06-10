/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Central{
    
    private int idCentral;
    private List<Central> centralConnected;
    private List<Subscriber> subscribersConnected;

    public Central(int id) {
        this.idCentral = id;
        this.centralConnected = new ArrayList<>();
        this.subscribersConnected = new ArrayList<>();
    }

    public void addConnectionCentral(Central central) {
        this.centralConnected.add(central);
    }

    public void addConnectionSubscriber(Subscriber subscriber) {
        this.subscribersConnected.add(subscriber);
    }
    
    public void removeConnectionCentral(Central central){
        if(hasCentral(central)){
            this.centralConnected.remove(central);
        }
    }
    
    public void removeConnectionSubscriber(Subscriber subscriber){
        if(hasSubscriber(subscriber)){
            this.subscribersConnected.remove(subscriber);
        }
    }
    
    private boolean hasCentral(Central central){
        for(Central c : this.centralConnected){
            if(c.getId() == central.getId()){
                return true;
            }
        }
        return false;
    }
    
    private boolean hasSubscriber(Subscriber subscriber){
        for(Subscriber s : this.subscribersConnected){
            if(s.getId() == subscriber.getId()){
                return true;
            }
        }
        return false;
    }
    
    public void printCentral(){
        System.out.println("Connection Central " + this.idCentral + " with others central");
        for(Central c : this.centralConnected){
            System.out.print(c.getId() + "  ");
        }
        System.out.println("");
    }
    
    public void printSubscriber(){
        System.out.println("Connection Central " + this.idCentral + " with subscribers");
        for(Subscriber s : this.subscribersConnected){
            System.out.print(s.getId() + "  ");
        }
        System.out.println("");
    }
    
    public int getId(){
        return this.idCentral;
    }
    
}
