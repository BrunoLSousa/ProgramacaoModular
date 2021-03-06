/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible to generate the events output
 * @author Bruno e Allan
 */
public class EventOutput {
    
    private List<String> events;
    private static final String FILE_OUTPUT = "events.txt";
    
    /**
     * Constructor method of this class
     * 
     */
    public EventOutput(){
        this.events = new ArrayList<>();
    }
    
    /**
     * Add description of events
     * 
     */
    public void addEvent(String event){
        this.events.add(event);
    }
    
    /**
     * Export a txt file with all events description
     * 
     */
    public void export(String path){
        FileWriter writer = null;
        try {
            File file = new File(FILE_OUTPUT);
            writer = new FileWriter(file);
            writer.write("---------- Eventos acionados ---------\n");
            for(int i = 0; i < this.events.size(); i++){
                writer.write("Evento " + i + ": " + this.events.get(i) + "\n");
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
