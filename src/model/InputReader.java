/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allan
 */
public class InputReader {
    private ArrayList<String> input;

    public InputReader(String path) {
        
        input = new ArrayList<>();
        File f = new File(path);
        String line = new String();
        
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            if(br.ready())
                line = br.readLine();    

            while(!(line.isEmpty())){
                //System.out.println(line);
                
                input.add(line);
                
                if(br.ready())
                    line = br.readLine();
            }
 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public ArrayList<String> getInput() {
        return input;
    }

    private void setInput(ArrayList<String> input) {
        this.input = input;
    }
    
    
    
    
    
    
}
