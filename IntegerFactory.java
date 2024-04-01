/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.integerfactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tskin
 */
public class IntegerFactory {

    public static void main(String[] args) 
    {
        ps_queue<Integer> intQ = new ps_queue();
        ps_queue<String> stringQ = new ps_queue();
        
        Thread t1 = new Thread(new Assembler(intQ));
        Thread t2 = new Thread(new Packager(intQ, stringQ));
        Thread t3 = new Thread(new Shipper(stringQ));
        
        t1.start();
        t2.start();
        t3.start();
        
        try{
        t1.join();
        t2.join();
        t3.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
