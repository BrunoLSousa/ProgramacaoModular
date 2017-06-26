/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import event.Calling;
import event.EventHandle;
import event.Round;
import event.TurnOff;
import event.factory.FactoryADDLineCC;
import event.factory.FactoryADDLineSC;
import event.factory.FactoryCalling;
import event.factory.FactoryEventChain;
import event.factory.FactoryReactiveLineCC;
import event.factory.FactoryReactiveLineSC;
import event.factory.FactoryReconnect;
import event.factory.FactoryRemoveLineCC;
import event.factory.FactoryRemoveLineSC;
import event.factory.FactorySuspendLineCC;
import event.factory.FactorySuspendLineSC;
import event.factory.FactoryTurnOff;
import input.InputReader;
import input.ReaderToken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ManagementRounds {

    private InputReader stream;
    private List<Round> rounds;
    private List<FactoryEventChain> factoryEventsChain;
    private ReaderToken tokens;
    private Network network;

    public ManagementRounds(InputReader stream, Network network, ReaderToken token) {
        this.stream = stream;
        this.rounds = new ArrayList<>();
        this.network = network;
        this.tokens = token;
        this.factoryEventsChain = new ArrayList<>();
        buildFactoryEventChain();

    }

    public void init() {
        Round round = new Round(0);
        for (int i = 0; i < this.stream.getEvents().size(); i++) {
            String[] content = this.stream.parseEvent(i);
            if (content[0].equals("r")) {
                int timeRound = Integer.parseInt(content[1]);
                round = new Round(timeRound);
                this.rounds.add(round);
            } else if (content[0].equals("e")) {
                String key = getTokenEvent(content);
                EventHandle e = getEvent(key, round, content);
                round.addEvent(e);
                e.trigger();
            }
        }
    }
    
    private EventHandle getEvent(String key, Round round, String[] content) {
        for (FactoryEventChain f : this.factoryEventsChain) {
            if (f.isCode(key)) {
                return f.create(round, content);
            }
        }
        return null;
    }

    private void buildFactoryEventChain() {
        for (String key : this.tokens.getKeys()) {
            switch (key) {
                case "0":
                    addChain(new FactoryCalling(this, network, key));
                    break;
                case "1":
                    addChain(new FactoryTurnOff(this, network, key));
                    break;
                case "2":
                    addChain(new FactoryReconnect(this, network, key));
                    break;
                case "3a":
                    addChain(new FactoryADDLineSC(this, network, key));
                    break;
                case "3c":
                    addChain(new FactoryADDLineCC(this, network, key));
                    break;
                case "4a":
                    addChain(new FactoryRemoveLineSC(this, network, key));
                    break;
                case "4c":
                    addChain(new FactoryRemoveLineCC(this, network, key));
                    break;
                case "5a":
                    addChain(new FactorySuspendLineSC(this, network, key));
                    break;
                case "5c":
                    addChain(new FactorySuspendLineCC(this, network, key));
                    break;
                case "6a":
                    addChain(new FactoryReactiveLineSC(this, network, key));
                    break;
                case "6c":
                    addChain(new FactoryReactiveLineCC(this, network, key));
                    break;
            }
        }
    }

    private void addChain(FactoryEventChain factory) {
        this.factoryEventsChain.add(factory);
    }

    private String getTokenEvent(String[] content) {
        if ((content[2].equals("a")) || (content[2].equals("c"))) {
            return content[1] + content[2];
        } else {
            return content[1];
        }
    }

    public EventHandle lastEventSubscriber(Subscriber subscriber) {
        int size = this.rounds.size();
        for (int i = (size - 1); i >= 0; i--) {
            EventHandle e = this.rounds.get(i).getEventSubscriber(subscriber);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    public EventHandle searchCallingBySubscriber(Subscriber subscriber) {
        int size = this.rounds.size();
        for (int i = (size - 1); i >= 0; i--) {
            EventHandle e = this.rounds.get(i).getEventCalling(subscriber);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    public Calling lastCalling(Subscriber subscriber) {
        int size = this.rounds.size();
        for (int i = (size - 1); i >= 0; i--) {
            EventHandle e = this.rounds.get(i).getEventCalling(subscriber);
            if (e != null) {
                return ((Calling) e);
            }
        }
        return null;
    }

    public TurnOff lastTurnOff(Subscriber subscriber) {
        int size = this.rounds.size();
        for (int i = (size - 1); i >= 0; i--) {
            EventHandle e = this.rounds.get(i).getTurnOff(subscriber);
            if (e != null) {
                return ((TurnOff) e);
            }
        }
        return null;
    }

    public List<Round> getRounds(){
        return this.rounds;
    }
    
    public EventHandle getlastEvent() {
        int size = this.rounds.size();
        Round round = this.rounds.get(size - 1);
        return round.getLastEvent();
    }

}
