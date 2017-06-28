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
 * This class is responsible to generate the invoices output
 * @author Bruno e Allan
 */
public class InvoiceOutput {
    
    private Account[][] accounts;
    private static final String FILE_OUTPUT = "fatura.txt";
    
    /**
     * Constructor method of this class
     * 
     */
    public InvoiceOutput(){
        //empty constructor.
    }
    
    /**
     * Set the invoces issued
     * 
     * @param accounts  has the all invoices issued by each subscriber
     */
    public void setAccounts(Account[][] accounts){
        this.accounts = accounts;
    }
    
    /**
     * Export a txt file with all invoices 
     * 
     */
    public void export(String path){
        FileWriter writer = null;
        try {
            File file = new File(FILE_OUTPUT);
            writer = new FileWriter(file);
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
            System.err.println("Erro ao criar o arquivo " + FILE_OUTPUT + " !");
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                System.err.println("Erro ao criar o arquivo " + FILE_OUTPUT + " !");
            }
        }
    }
    
}
