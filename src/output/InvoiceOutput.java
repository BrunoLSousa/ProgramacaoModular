/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import account.Account;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class InvoiceOutput {
    
    private Account[][] accounts;
    private static final String FILE_OUTPUT = "fatura.txt";
    
    public InvoiceOutput(){
        //empty constructor.
    }
    
    public void setAccounts(Account[][] accounts){
        this.accounts = accounts;
    }
    
    public void export(){
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(FILE_OUTPUT));
            writer.write("---------- Emissão de Faturas ---------\n\n");
            for (int i = 0; i < this.accounts.length; i++) {
                writer.write("---------- Fatura " + (i + 1) + " ---------\n");
                for(int j = 0; j < this.accounts[i].length; j++){
                    writer.write("Assinante " + this.accounts[i][j].getSubscriber() + ": " + this.accounts[i][j].getValueSpend() + " pulsos gastos.\n");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(EventOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(EventOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
