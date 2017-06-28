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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible to generate the signal output
 * @author Bruno e Allan
 */
public class SignalOutput {

    private Map<String, List<String>> signal;
    private static final String FILE_OUTPUT = "sinais.txt";

    /**
     * Constructor method of this class
     * 
     */
    public SignalOutput() {
        this.signal = new HashMap<>();
    }

    /**
     * Create a new event of signal map
     * 
     */
    public void addNewEvent(int indexEvent) {
        String key = "event" + indexEvent;
        this.signal.put(key, new ArrayList<String>());
    }

    /**
     * Add a new signal of signal list
     * 
     */
    public void addSignal(int indexEvent, String signal) {
        String key = "event" + indexEvent;
        this.signal.get(key).add(signal);
    }

    /**
     * Return current event
     * 
     */
    public int lastEvent() {
        return this.signal.size() - 1;
    }

    /**
     * Export a txt file with all signal description of events
     * 
     */
    public void export(String path) {
        FileWriter writer = null;
        try {
            File file = new File(FILE_OUTPUT);
            writer = new FileWriter(file);
            writer.write("---------- Sinais retornados ---------\n\n");
            for (int i = 0; i < this.signal.size(); i++) {
                writer.write("---------- Evento " + i + " ---------\n");
                String key = "event" + i;
                for (int j = 0; j < this.signal.get(key).size(); j++) {
                    writer.write(this.signal.get(key).get(j) + "\n");
                }
                writer.write("\n\n");
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
