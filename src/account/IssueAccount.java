/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import event.Round;
import java.util.List;
import view.Output;

/**
 *
 * @author bruno
 */
public abstract class IssueAccount {

    protected int period;
    protected List<Round> rounds;
    protected Account[][] accounts;
    protected Output output;

    public IssueAccount(int period, List<Round> rounds, int amountSubscribers, Output output) {
        this.period = period;
        this.rounds = rounds;
        this.output = output;
        createAccounts(amountSubscribers);
    }

    private void createAccounts(int amountSubscribers) {
        int issues = amountIssue();
        this.accounts = new Account[issues][amountSubscribers];
        for (int i = 0; i < issues; i++) {
            for (int j = 0; j < amountSubscribers; j++) {
                this.accounts[i][j] = new Account(j);
            }
        }
    }

    protected int amountIssue() {
        if ((this.rounds.size() % this.period) == 0) { 
            return (this.rounds.size() / this.period);
        }
        return ((this.rounds.size() / this.period) + 1);
    }
    
    protected void printAccounts(){
        int issues = amountIssue();
        for(int i = 0; i < issues; i++){
            System.out.println("---------- Fatura " + i + "----------");
            for(int j = 0; j < this.accounts[i].length; j++){
                System.out.println("Assinante " + this.accounts[i][j].getSubscriber() + ": " + this.accounts[i][j].getValueSpend() + " pulsos gastos.");
            }
        }
    }

    public abstract void issue();

}
