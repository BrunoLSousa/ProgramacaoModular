/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class EventOutput {
    
    private List<String> events;
    private static final String FILE_OUTPUT = "events.txt";
    
    public EventOutput(){
        this.events = new ArrayList<>();
    }
    
    public void addEvent(String event){
        this.events.add(event);
    }
    
    public void export(){
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(FILE_OUTPUT));
            writer.write("---------- Eventos acionados ---------\n");
            for(int i = 0; i < this.events.size(); i++){
                writer.write("Evento " + i + ": " + this.events.get(i) + "\n");
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
