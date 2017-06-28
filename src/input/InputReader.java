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
 * This class is responsible to read the input provided for this application
 * @author Bruno e Allan
 */
public class InputReader {

    private List<String> input;
    private int numberCentral;
    private List<String> connectionCentral;
    private int numberSubscribers;
    private List<String> connectionSubscriber;
    private int issueAccount;
    private List<String> events;

    /**
     * Constructor method of this class
     * 
     * @param path  Input file path provided by user
     */
    public InputReader(String path) {
        this.input = new ArrayList<>();
        this.connectionCentral = new ArrayList<>();
        this.connectionSubscriber = new ArrayList<>();
        this.events = new ArrayList<>();
        load(path);
        parser();
    }

    /**
     * Read file and load the information
     * 
     * @param path  Input file path provided by user
     */
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

    /**
     * Perform the parser of this input file loaded as input
     * 
     * @param path  Input file path provided by user
     */
    private void parser() {
        parserInfoCentral();
        parserInfoCentralPar();
        parserInfoSubscriber();
        parserIssueAccount();
        parserEvents();
    }

    /**
     * Parser information about amount central
     * 
     */
    private void parserInfoCentral() {
        String[] brokenString = this.input.get(0).split(" ");
        this.numberCentral = Integer.parseInt(brokenString[1]);
    }

    /**
     * Parser information about connections between central
     * 
     */
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
    
    /**
     * Parser information about amount subscriber
     * 
     */
    private void parserInfoSubscriber(){
        int index = (this.connectionCentral.size() + 3) - 1;
        String[] brokenString = this.input.get(index).split(" ");
        this.numberSubscribers = Integer.parseInt(brokenString[1]);
        parserInfoSubscriberConnections(index+1);
    }
    
    /**
     * Parser information about connections between subscriber and central
     * 
     */
    private void parserInfoSubscriberConnections(int firstConnection) {
        int index = 0;
        while(index < this.numberSubscribers){
            this.connectionSubscriber.add(this.input.get(index + firstConnection));
            index++;
        }
    }
    
    /**
     * Parser information about issue account period
     * 
     */
    private void parserIssueAccount(){
        int index = (this.connectionSubscriber.size() + this.connectionCentral.size() + 4) - 1;
        String[] brokenString = this.input.get(index).split(" ");
        this.issueAccount = Integer.parseInt(brokenString[1]);
    }
    
    /**
     * Parser information about events
     * 
     */
    private void parserEvents(){
        int index = (this.connectionSubscriber.size() + this.connectionCentral.size() + 5) - 1;
        while(index < this.input.size()){
            this.events.add(this.input.get(index));
            index++;
        }
    }

    /**
     * Open input file
     * 
     */
    private BufferedReader openFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        return bufferReader;
    }

    /**
     * Read a line of the input file
     * 
     */
    private void addLine(BufferedReader buffer) throws IOException {
        String line;
        while (buffer.ready()) {
            line = buffer.readLine();
            this.input.add(line);
        }
    }
    
    /**
     * Split the data of events load from input file
     * 
     */
    public String[] parseEvent(int index){
        return this.events.get(index).split(" ");
    }
    
    /**
     * Return number of central
     * 
     */
    public int getNumberOfCentral(){
        return this.numberCentral;
    }
    
    /**
     * Return number of subscribers
     * 
     */
    public int getNumberOfSubscribers(){
        return this.numberSubscribers;
    }
    
    /**
     * Return issue account period
     * 
     */
    public int getIssueAccount(){
        return this.issueAccount;
    }
    
    /**
     * Return list of central connections
     * 
     */
    public List<String> getConnectionsCentral(){
        return this.connectionCentral;
    }
    
    /**
     * Return list of subcriber connections with central
     * 
     */
    public List<String> getConnectionSubscribers(){
        return this.connectionSubscriber;
    }
    
    /**
     * Return list of events
     * 
     */
    public List<String> getEvents(){
        return this.events;
    }

}
