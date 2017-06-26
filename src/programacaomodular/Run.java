/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaomodular;

import account.AccountByRound;
import account.IssueAccount;
import input.InputReader;
import input.ReaderToken;
import structure.ManagementNework;
import structure.ManagementRounds;

/**
 *
 * @author allan
 */
public class Run {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InputReader input = new InputReader("input_file.txt");
        ReaderToken token = new ReaderToken("event_token.txt");
        ManagementNework managementNetwork = new ManagementNework(input);
        managementNetwork.init();
        ManagementRounds managementRound = new ManagementRounds(input, managementNetwork.getNetwork(), token);
        managementRound.init();
        IssueAccount account = new AccountByRound(input.getIssueAccount(), managementRound.getRounds(), input.getNumberOfSubscribers());
        account.issue();
    }
    
}
