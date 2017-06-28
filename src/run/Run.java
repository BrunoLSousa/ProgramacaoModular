/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

/**
 * This class is main class
 * @author Bruno e Allan
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SystemFacade system = new SystemFacade(args);
        system.init();
    }

}
