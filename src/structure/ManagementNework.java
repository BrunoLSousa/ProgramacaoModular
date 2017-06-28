/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import input.InputReader;

/**
 * Class responsible o manage network
 * @author Bruno e Allan
 */
public class ManagementNework {
    
    private Network network;
    private InputReader stream;
    
    /**
     * Constructor method of this class
     * 
     * @param stream Reader object containing informations about network
     */
    public ManagementNework(InputReader stream){
        this.network = new Network();
        this.stream = stream;
    }
    
    /**
     * Construct central and subscribers e create connections
     * 
     */
    public void init(){
        this.network.buildNetwork(this.stream.getNumberOfCentral(), this.stream.getNumberOfSubscribers());
        createConnectionToCentral();
        createConnectionToSubscriber();
    }
    
    /**
     * Create connections between central
     * 
     */
    private void createConnectionToCentral(){
        for(String info : this.stream.getConnectionsCentral()){
            String[] brokenString = info.split(" ");
            int centralA = Integer.parseInt(brokenString[1]);
            int centralB = Integer.parseInt(brokenString[2]);
            this.network.connectCentralToCentral(centralA, centralB);
        }
    }
    
    /**
     * Create connections between subscriber and central
     * 
     */
    private void createConnectionToSubscriber(){
        for(String info : this.stream.getConnectionSubscribers()){
            String[] brokenString = info.split("c_a")[1].split(" ");
            int subscriber = Integer.parseInt(brokenString[0]);
            int central = Integer.parseInt(brokenString[1]);
            this.network.connectSubscriberToCentral(subscriber, central);
        }
    }
    
    /**
     * Return network
     * 
     */
    public Network getNetwork(){
        return this.network;
    }
    
}
