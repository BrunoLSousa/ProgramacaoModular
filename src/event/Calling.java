/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.ArrayList;
import java.util.List;
import search.DepthFirstSearch;
import search.Visitor;
import structure.ManagementRounds;
import structure.Subscriber;

/**
 *
 * @author bruno
 */
public class Calling extends EventHandle implements EventSubscriber{

    private Subscriber subscriberCaller;
    private Subscriber subscriberReceiver;
    private List<Integer> route;
    
    public Calling(ManagementRounds managementRound, Round round, Subscriber subscriberCaller, Subscriber subscriberReceiver){
        super(managementRound, round);
        this.subscriberCaller = subscriberCaller;
        this.subscriberReceiver = subscriberReceiver;
        this.route = new ArrayList<>();
    }
    
    @Override
    public void trigger() {
        if(this.subscriberCaller.isFree()){
            takePhone();
            diskNumber();
            try {
                searchReceiver();
                startCall();
            } catch (NullPointerException e) {
                System.err.println("Assinante não encontrado!!!");
            }
        }else{
            System.out.println("Não foi possível completar chamada.");
        }
    }
    
    private void takePhone(){
        System.out.println("Telefone Retirado do Ganho...");
    }
    
    private void diskNumber(){
        System.out.println("Discando Número...");
    }
    
    private void searchReceiver() throws NullPointerException{
        System.out.println("Completando Ligação...");
        Visitor dfs = new DepthFirstSearch(this.subscriberCaller, this.subscriberReceiver);
        this.route = dfs.search();
        System.out.println("Rota da Ligação: " + printRoute());
    }
    
    private void startCall(){
        if(this.subscriberReceiver.isFree()){
            this.subscriberCaller.setCurrentCommunication(subscriberReceiver);
            this.subscriberCaller.setBusy();
            this.subscriberReceiver.setCurrentCommunication(subscriberCaller);
            this.subscriberReceiver.setBusy();
            this.sucess = true;
            System.out.println("Ligação Completada!");
        }else{
            System.out.println("Linha Ocupada.");
        }
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return ((subscriber.getId() == this.subscriberCaller.getId()) || (subscriber.getId() == this.subscriberReceiver.getId()));
    }
    
    private String printRoute(){
        String r = String.valueOf(this.route.get(0));
        int index = 1;
        while(index < this.route.size()){
            r = r + " - " + this.route.get(index);
            index++;
        }
        return r;
    }
    
    public Subscriber getCaller(){
        return this.subscriberCaller;
    }
    
    public Subscriber getReceiver(){
        return this.subscriberReceiver;
    }
    
    public List<Integer> getRouter(){
        return this.route;
    }
    
}
