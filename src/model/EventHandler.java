/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.StringTokenizer;

/**
 *
 * @author allan
 */
public class EventHandler {

    private Boolean state;

    public EventHandler() {
        state = new Boolean(Boolean.TRUE);
    }
    
    private void setState(Boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    void newEvent(String line) {
        
        StringTokenizer st = new StringTokenizer(line);
        
        if(st.countTokens() == 0){
            this.setState(Boolean.TRUE);
        }

        if(st.countTokens() == 1){
            if(st.nextToken().equalsIgnoreCase("q"))
                this.setState(Boolean.FALSE);
        }

        if (st.countTokens() == 2) {

            String token1 = st.nextToken();
            String token2 = st.nextToken();
            
            if (token1.equalsIgnoreCase("r")) {

                Integer i = new Integer(token2);
                System.out.println("i = " + i);
  
                this.setState(Boolean.TRUE);
            }

            if (token1.equalsIgnoreCase("nc")) {

                Integer i = new Integer(token2);
                System.out.println("i = " + i);

                this.setState(Boolean.TRUE);
            }

            if (token1.equalsIgnoreCase("np")) {

                Integer i = new Integer(token2);
                System.out.println("i = " + i);
                
                this.setState(Boolean.TRUE);
            }

            if (token1.equalsIgnoreCase("na")) {

                Integer i = new Integer(token2);
                System.out.println("i = " + i);
                
                this.setState(Boolean.TRUE);
            }

            if (token1.equalsIgnoreCase("em")) {

                Integer i = new Integer(token2);
                System.out.println("i = " + i);
                
                this.setState(Boolean.TRUE);
            }

        }
    }
        
//        if(line.equalsIgnoreCase("q")){
//            this.setState(Boolean.FALSE);
//        }
//        if(line.startsWith("nc")){
//            
//            this.setState(Boolean.FALSE);
//        }
        
        
}

    
    

