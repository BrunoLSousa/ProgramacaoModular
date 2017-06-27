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
import view.Output;

/**
 *
 * @author bruno
 */
public class Calling extends EventHandle implements EventSubscriber{

    private Subscriber subscriberCaller;
    private Subscriber subscriberReceiver;
    private List<Integer> route;
    
    public Calling(ManagementRounds managementRound, Round round, Subscriber subscriberCaller, Subscriber subscriberReceiver, Output output){
        super(managementRound, round, output);
        this.subscriberCaller = subscriberCaller;
        this.subscriberReceiver = subscriberReceiver;
        this.route = new ArrayList<>();
    }
    
    @Override
    public void trigger() {
        output.addNewEvent("Iniciar Ligação entre o Assinante " + subscriberCaller.getId() + " e o Assinante " + subscriberReceiver.getId());
        if(this.subscriberCaller.isFree()){
            takePhone();
            diskNumber();
            try {
                searchReceiver();
                startCall();
            } catch (NullPointerException e) {
                output.addNewSignal("Assinante não encontrado!!!");
            }
        }else{
            output.addNewSignal("Não foi possível completar chamada.");
        }
    }
    
    private void takePhone(){
        output.addNewSignal("Telefone Retirado do Gancho...");
    }
    
    private void diskNumber(){
        output.addNewSignal("Discando Número...");
    }
    
    private void searchReceiver() throws NullPointerException{
        output.addNewSignal("Completando Ligação...");
        Visitor dfs = new DepthFirstSearch(this.subscriberCaller, this.subscriberReceiver);
        this.route = dfs.search();
        output.addNewSignal("Rota da Ligação: " + printRoute());
    }
    
    private void startCall(){
        if(this.subscriberReceiver.isFree()){
            this.subscriberCaller.setCurrentCommunication(subscriberReceiver);
            this.subscriberCaller.setBusy();
            this.subscriberReceiver.setCurrentCommunication(subscriberCaller);
            this.subscriberReceiver.setBusy();
            this.sucess = true;
            output.addNewSignal("Ligação Completada!");
        }else{
            output.addNewSignal("Linha Ocupada.");
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
