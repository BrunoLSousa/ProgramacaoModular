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
 *
 * @author bruno
 */
public class Token {

    private Map<String, String> tokens;

    public Token() {
        createTokens();
    }
    
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

    public Set<String> getKeys() {
        return this.tokens.keySet();
    }

}
