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
import output.Output;

/**
 *
 * @author bruno
 */
public class SystemFacade {
    
    public SystemFacade(){
        //empty constructor
    }
    
    public void init() {
        InputReader input = new InputReader("input_file.txt");
        ReaderToken token = new ReaderToken("event_token.txt");
        Output output = new Output();
        ManagementNework managementNetwork = new ManagementNework(input);
        managementNetwork.init();
        ManagementRounds managementRound = new ManagementRounds(input, managementNetwork.getNetwork(), token);
        managementRound.init(output);
        IssueAccount account = new AccountByRound(input.getIssueAccount(), managementRound.getRounds(), input.getNumberOfSubscribers(), output);
        account.issue();
        output.export();
    }
    
}
