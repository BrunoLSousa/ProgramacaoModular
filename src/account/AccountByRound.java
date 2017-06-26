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

/**
 *
 * @author bruno
 */
public class AccountByRound extends IssueAccount {

    private int[][] issueManager;

    public AccountByRound(int period, List<Round> rounds, int amountSubscribers) {
        super(period, rounds, amountSubscribers);
        this.issueManager = new int[amountSubscribers][2];
    }

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
        printAccounts();
    }

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

    private void pulseCalling(EventHandle event, int idRound) {
        Calling call = (Calling) event;
        int valueRound = this.rounds.get(idRound).getValue();
        if (call.isSucess()) {
            int caller = call.getCaller().getId();
            this.issueManager[caller][0] = valueRound;
        }
    }

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

    private void pulseReconnect(EventHandle event, int idRound) {
        Reconnect reconnect = (Reconnect) event;
        int valueRound = this.rounds.get(idRound).getValue();
        if (reconnect.isSucess()) {
            int subscriber = reconnect.getSubscriberReconnect().getId();
            this.issueManager[subscriber][0] = valueRound;
        }
    }

    private void storeIssue(int indexIssue) {
        for (int i = 0; i < this.accounts[indexIssue].length; i++) {
            this.accounts[indexIssue][i].setValue(Double.parseDouble(String.valueOf(this.issueManager[i][1])));
            this.issueManager[i][1] = 0;
        }
    }

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
