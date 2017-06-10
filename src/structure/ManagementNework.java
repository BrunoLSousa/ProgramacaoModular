/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import input.InputReader;

/**
 *
 * @author bruno
 */
public class ManagementNework {
    
    private Network network;
    private InputReader stream;
    
    public ManagementNework(){
        this.network = new Network();
        this.stream = new InputReader("input_file.txt");
    }
    
    public void init(){
        this.network.buildNetwork(this.stream.getNumberOfCentral(), this.stream.getNumberOfSubscribers());
        createConnectionToCentral();
        createConnectionToSubscriber();
        this.network.printConnectionsCentral();
        this.network.printConnectionsSubscriber();
    }
    
    private void createConnectionToCentral(){
        for(String info : this.stream.getConnectionsCentral()){
            String[] brokenString = info.split(" ");
            int centralA = Integer.parseInt(brokenString[1]);
            int centralB = Integer.parseInt(brokenString[2]);
            this.network.connectCentralToCentral(centralA, centralB);
        }
    }
    
    private void createConnectionToSubscriber(){
        for(String info : this.stream.getConnectionSubscribers()){
            String[] brokenString = info.split("c_a")[1].split(" ");
            int subscriber = Integer.parseInt(brokenString[0]);
            int central = Integer.parseInt(brokenString[1]);
            this.network.connectSubscriberToCentral(subscriber, central);
        }
    }
    
    
    
}
