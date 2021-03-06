/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import event.Calling;
import event.EventHandle;
import event.EventSubscriber;
import event.Reconnect;
import event.Round;
import event.TurnOff;
import java.util.List;
import structure.Subscriber;
import output.Output;

/**
 * This class implements the issue account by 
 * @author Bruno e Allan
 */
public class AccountByRound extends IssueAccount {

    private int[][] issueManager;

    /**
     * Constructor method of this class
     * 
     * @param period  issue account period
     * @param rounds  list of rounds
     * @param amountSubscribers  amount of subscribers
     * @param output  output to generate results
     */  
    public AccountByRound(int period, List<Round> rounds, int amountSubscribers, Output output) {
        super(period, rounds, amountSubscribers, output);
        this.issueManager = new int[amountSubscribers][2];
    }

    /**
     * Abstract method that the subclasses implement to issue account
     */
    @Override
    public void issue() {
        int issues = amountIssue();
        int indexRound = 0;
        for (int i = 0; i < issues; i++) {
            int iterator = 0;
            while (indexRound < this.rounds.size() && iterator < this.period) {
                countPulse(indexRound);
                indexRound++;
                iterator++;
            }
            storeIssue(i);
        }
        output.setIssuesAccount(accounts);
    }

    /**
     * Compute pulse by round
     */  
    private void countPulse(int idRound) {
        int amountEvents = this.rounds.get(idRound).amountEvents();
        for (int i = 0; i < amountEvents; i++) {
            if (this.rounds.get(idRound).getEventByIndex(i) instanceof Calling) {
                pulseCalling(this.rounds.get(idRound).getEventByIndex(i), idRound);
            } else if (this.rounds.get(idRound).getEventByIndex(i) instanceof TurnOff) {
                pulseTurnOff(this.rounds.get(idRound).getEventByIndex(i), idRound);
            } else if (this.rounds.get(idRound).getEventByIndex(i) instanceof Reconnect) {
                pulseReconnect(this.rounds.get(idRound).getEventByIndex(i), idRound);
            }
        }
    }

    /**
     * Compute calling pulse
     */ 
    private void pulseCalling(EventHandle event, int idRound) {
        Calling call = (Calling) event;
        int valueRound = this.rounds.get(idRound).getValue();
        if (call.isSucess()) {
            int caller = call.getCaller().getId();
            this.issueManager[caller][0] = valueRound;
        }
    }

    /**
     * Compute turnoff pulse
     */ 
    private void pulseTurnOff(EventHandle event, int idRound) {
        TurnOff turnOff = (TurnOff) event;
        Round round = this.rounds.get(idRound);
        int valueRound = round.getValue();
        if (turnOff.isSucess()) {
            EventHandle e = searchStartCallInRounds(idRound, turnOff.getSubscriber());
            int caller = (e instanceof Calling) ? ((Calling) e).getCaller().getId() : ((Reconnect) e).getSubscriberReconnect().getId();
            this.issueManager[caller][1] = this.issueManager[caller][1] + (valueRound - this.issueManager[caller][0]);
        }
    }

    /**
     * Compute reconnect pulse
     */ 
    private void pulseReconnect(EventHandle event, int idRound) {
        Reconnect reconnect = (Reconnect) event;
        int valueRound = this.rounds.get(idRound).getValue();
        if (reconnect.isSucess()) {
            int subscriber = reconnect.getSubscriberReconnect().getId();
            this.issueManager[subscriber][0] = valueRound;
        }
    }

    /**
     * Store issue
     */ 
    private void storeIssue(int indexIssue) {
        for (int i = 0; i < this.accounts[indexIssue].length; i++) {
            this.accounts[indexIssue][i].setValue(Double.parseDouble(String.valueOf(this.issueManager[i][1])));
            this.issueManager[i][1] = 0;
        }
    }

    /**
     * Search calling event in rounds
     */ 
    private EventHandle searchStartCallInRounds(int indexRound, Subscriber subscriber) {
        for (int i = indexRound; i >= 0; i--) {
            Round round = this.rounds.get(i);
            for (int j = (round.amountEvents() - 1); j >= 0; j--) {
                EventHandle event = round.getEventByIndex(j);
                if ((event instanceof Calling) || (event instanceof Reconnect) && event.isSucess()) {
                    if (((EventSubscriber) event).hasSubscriber(subscriber)) {
                        return event;
                    }
                }
            }

        }
        return null;
    }

}
