/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaomodular;

import input.InputReader;
import input.ReaderToken;
import structure.ManagementEvents;
import structure.ManagementNework;

/**
 *
 * @author bruno
 */
public class ProgramacaoModular {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InputReader input = new InputReader("input_file.txt");
        ReaderToken token = new ReaderToken("event_token.txt");
        ManagementNework managementNetwork = new ManagementNework(input);
        managementNetwork.init();
        ManagementEvents managementEvents = new ManagementEvents(input, managementNetwork.getNetwork(), token);
        managementEvents.init();
    }
    
}
