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
    
    public void suspendLine(){
        this.status = Status.SUSPENDED;
    }
    
    public void reactiveLine(){
        setFree();
    }
    
    public Central getCentral(){
        return this.central;
    }
    
    public boolean isFree(){
        return (this.status == Status.FREE);
    }
    
    public boolean isBusy(){
        return (this.status == Status.BUSY);
    }
    
    public void setFree(){
        this.status = Status.FREE;
    }
    
    public void setBusy(){
        this.status = Status.BUSY;
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
        BUSY, FREE, SUSPENDED;
    }
    
}
