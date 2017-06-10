/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allan
 */
public class InputReader {

    private List<String> input;
    private int numberCentral;
    private List<String> connectionCentral;
    private int numberSubscribers;
    private List<String> connectionSubscriber;
    private int issueAccount;
    private List<String> events;

    public InputReader(String path) {
        this.input = new ArrayList<>();
        this.connectionCentral = new ArrayList<>();
        this.connectionSubscriber = new ArrayList<>();
        this.events = new ArrayList<>();
        load(path);
        parser();
    }

    private void load(String path) {
        this.input = new ArrayList<>();
        try {
            try (BufferedReader bufferReader = openFile(path)) {
                addLine(bufferReader);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void parser() {
        parserInfoCentral();
        parserInfoCentralPar();
        parserInfoSubscriber();
        parserIssueAccount();
        parserEvents();
    }

    private void parserInfoCentral() {
        String[] brokenString = this.input.get(0).split(" ");
        this.numberCentral = Integer.parseInt(brokenString[1]);
    }

    private void parserInfoCentralPar() {
        String[] brokenString = this.input.get(1).split(" ");
        int amountPar = Integer.parseInt(brokenString[1]);
        int start = 2;
        int iterator = 0;
        while(iterator < amountPar){
            this.connectionCentral.add(this.input.get(iterator+start));
            iterator++;
        }
    }
    
    private void parserInfoSubscriber(){
        int index = (this.connectionCentral.size() + 3) - 1;
        String[] brokenString = this.input.get(index).split(" ");
        this.numberSubscribers = Integer.parseInt(brokenString[1]);
        parserInfoSubscriberConnections(index+1);
    }
    
    private void parserInfoSubscriberConnections(int firstConnection) {
        int index = 0;
        while(index < this.numberSubscribers){
            this.connectionSubscriber.add(this.input.get(index + firstConnection));
            index++;
        }
    }
    
    private void parserIssueAccount(){
        int index = (this.connectionSubscriber.size() + this.connectionCentral.size() + 4) - 1;
        String[] brokenString = this.input.get(index).split(" ");
        this.issueAccount = Integer.parseInt(brokenString[1]);
    }
    
    private void parserEvents(){
        int index = (this.connectionSubscriber.size() + this.connectionCentral.size() + 5) - 1;
        while(index < this.input.size()){
            this.events.add(this.input.get(index));
            index++;
        }
    }

    private BufferedReader openFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        return bufferReader;
    }

    private void addLine(BufferedReader buffer) throws IOException {
        String line;
        while (buffer.ready()) {
            line = buffer.readLine();
            this.input.add(line);
        }
    }
    
    public int getNumberOfCentral(){
        return this.numberCentral;
    }
    
    public int getNumberOfSubscribers(){
        return this.numberSubscribers;
    }
    
    public int getIssueAccount(){
        return this.issueAccount;
    }
    
    public List<String> getConnectionsCentral(){
        return this.connectionCentral;
    }
    
    public List<String> getConnectionSubscribers(){
        return this.connectionSubscriber;
    }
    
    public List<String> getEvents(){
        return this.events;
    }

}
