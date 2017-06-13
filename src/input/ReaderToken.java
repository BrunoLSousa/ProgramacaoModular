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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class ReaderToken {

    private Map<String, String> tokens;

    public ReaderToken(String path) {
        parser(path);
    }

    private void parser(String path) {
        this.tokens = new HashMap<>();
        try {
            try (BufferedReader bufferReader = openFile(path)) {
                String line;
                while (bufferReader.ready()) {
                    line = bufferReader.readLine();
                    String[] broken = line.split(",");
                    this.tokens.put(broken[0], broken[1]);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BufferedReader openFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        return bufferReader;
    }

    public Set<String> getKeys() {
        return this.tokens.keySet();
    }

}
