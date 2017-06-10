/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 *
 * @author bruno
 */
public class Subscriber{
    
    private int idSubscriber;
    private Central central;
    private Status status;

    public Subscriber(int id) {
        this.idSubscriber = id;
        this.status = Status.FREE;
    }

    public void setCentral(Central central) {
        this.central = central;
    }

    public void removeCentral() {
        this.central = null;
    }
    
    public Central getCentral(){
        return this.central;
    }
    
    public boolean isFree(){
        return (this.status == Status.FREE);
    }
    
    public void changeStatus(){
        if(this.status == Status.FREE){
            this.status = Status.BUSY;
        }else{
            this.status = Status.FREE;
        }
    }
    
    public void printCentral(){
        System.out.println("Connection Subscriber " + this.idSubscriber + " with Central");
        System.out.println(this.central.getId() + "  ");
        System.out.println("");
    }
    
    public int getId(){
        return this.idSubscriber;
    }
    
    private enum Status{
        BUSY, FREE;
    }
    
}
