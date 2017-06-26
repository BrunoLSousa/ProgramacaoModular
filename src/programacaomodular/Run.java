/*
 * Classe que inicia a Simulação de um Sistema de Telefonia
 * 
 * arg[0] Modo de Execução 
 *     0 - Console Mode 
 *     1 - FileMode
 * arg[1] Nome do Arquivo de Entrada
 * Exemplo1: 0
 * Exemplo2: 1 input_file.txt
 *  
*/
package model;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.logging.Level;
//import java.util.logging.Logger;

import java.util.ArrayList;

/**
 *
 * @author allan
 */
public class Run {
    
    
    private static int nc;
    private static int np;
    private static int pn;
    private static int na;
    private static int c_an;
    private static int em;
    private static int round;
    private static String e;
    private static ArrayList<String> in;
    
    private static void fileMode(String fileName) {

        EventHandler handler = new EventHandler();
        InputReader input = new InputReader(fileName);
        in = input.getInput();

        //in.forEach(s -> System.out.println(s)); 

        for(String s : in.subList(0, in.size())){ 
            System.out.println(s);
            handler.newEvent(s);
        }
        
    }

//    private static void ConsoleMode() {
//        EventHandler handler = new EventHandler();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 
//        while(handler.getState()){           
//            try {
//
//                String line = br.readLine();
//                handler.newEvent(line);
//
//            } catch (IOException ex) {
//                Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//    }
    
    public static void main(String[] args) {

        fileMode("input_file.txt");
        
        
    }
}
