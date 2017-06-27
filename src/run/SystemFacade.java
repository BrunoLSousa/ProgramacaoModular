/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import account.AccountByRound;
import account.IssueAccount;
import input.InputReader;
import input.Token;
import structure.ManagementNework;
import structure.ManagementRounds;
import output.Output;

/**
 *
 * @author bruno
 */
public class SystemFacade {

    private String fileInput;
    private String pathOutput;

    public SystemFacade(String[] args) {
        this.fileInput = args[0];
    }

    public void init() {
        InputReader input = new InputReader(fileInput);
        Token token = new Token();
        Output output = new Output();
        ManagementNework managementNetwork = new ManagementNework(input);
        managementNetwork.init();
        ManagementRounds managementRound = new ManagementRounds(input, managementNetwork.getNetwork(), token);
        managementRound.init(output);
        IssueAccount account = new AccountByRound(input.getIssueAccount(), managementRound.getRounds(), input.getNumberOfSubscribers(), output);
        account.issue();
        output.export(pathOutput);
    }

}
