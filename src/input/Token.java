/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible to create the list of event tokens
 * @author Bruno e Allan
 */
public class Token {

    private Map<String, String> tokens;

    /**
     * Constructor method of this class
     * 
     */
    public Token() {
        createTokens();
    }
    
    /**
     * Creation of events tokens
     * 
     */
    private void createTokens(){
        this.tokens = new HashMap<>();
        this.tokens.put("0", "Calling");
        this.tokens.put("1", "TurnOff");
        this.tokens.put("2", "Reconnect");
        this.tokens.put("3a", "ADDLineSC");
        this.tokens.put("3c", "ADDLineCC");
        this.tokens.put("4a", "RemoveLineSC");
        this.tokens.put("4c", "RemoveLineCC");
        this.tokens.put("5a", "SuspendLineSC");
        this.tokens.put("5c", "SuspendLineCC");
        this.tokens.put("6a", "ReactiveLineSC");
        this.tokens.put("6c", "ReactiveLineCC");
    }

    /**
     * Return set of tokens keys created
     * 
     */
    public Set<String> getKeys() {
        return this.tokens.keySet();
    }

}
