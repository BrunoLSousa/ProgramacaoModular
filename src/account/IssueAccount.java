/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import event.Round;
import java.util.List;
import output.Output;

/**
 * Abstract Class to implement basic services from a issue account
 * @author Bruno e Allan
 */
public abstract class IssueAccount {

    protected int period;
    protected List<Round> rounds;
    protected Account[][] accounts;
    protected Output output;

    /**
     * Constructor method of this class
     * 
     * @param period  issue account period
     * @param rounds  list of rounds
     * @param amountSubscribers  amount of subscribers
     * @param output  output to generate results
     */    
    public IssueAccount(int period, List<Round> rounds, int amountSubscribers, Output output) {
        this.period = period;
        this.rounds = rounds;
        this.output = output;
        createAccounts(amountSubscribers);
    }

    /**
     * Create accounts
     * 
     */ 
    private void createAccounts(int amountSubscribers) {
        int issues = amountIssue();
        this.accounts = new Account[issues][amountSubscribers];
        for (int i = 0; i < issues; i++) {
            for (int j = 0; j < amountSubscribers; j++) {
                this.accounts[i][j] = new Account(j);
            }
        }
    }

    /**
     * Return amount of issues
     * 
     */ 
    protected int amountIssue() {
        if ((this.rounds.size() % this.period) == 0) { 
            return (this.rounds.size() / this.period);
        }
        return ((this.rounds.size() / this.period) + 1);
    }

    /**
     * Abstract method that the subclasses implement to issue account
     */
    public abstract void issue();

}
