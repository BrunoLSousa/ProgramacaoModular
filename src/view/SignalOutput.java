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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class SignalOutput {

    private Map<String, List<String>> signal;
    private static final String FILE_OUTPUT = "sinais.txt";

    public SignalOutput() {
        this.signal = new HashMap<>();
    }

    public void addNewEvent(int indexEvent) {
        String key = "event" + indexEvent;
        this.signal.put(key, new ArrayList<String>());
    }

    public void addSignal(int indexEvent, String signal) {
        String key = "event" + indexEvent;
        this.signal.get(key).add(signal);
    }

    public int lastEvent() {
        return this.signal.size() - 1;
    }

    public void export() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(FILE_OUTPUT));
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
            Logger.getLogger(SignalOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(SignalOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
