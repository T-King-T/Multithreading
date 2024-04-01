/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integerfactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tskin
 */
public class Packager implements Runnable
{
    private ps_queue<Integer> intQ;
    private ps_queue<String> stringQ;
   
    
    public Packager(ps_queue<Integer> _intQ, ps_queue<String> _stringQ)
    {
        intQ = _intQ;
        stringQ = _stringQ;
    }
    
    
    
    @Override
    public void run() 
    {
        int count = 0;
        while(count < 10){
            
        //System.out.println("Packeger Runs");
        try{
            {
            if(intQ.RL.tryLock())
            {
                //System.out.println("Packager Int Locked");
                if(stringQ.RL.tryLock())
                {
                    //System.out.println("Packager String Locked");
                    try
                    {
                        int temp = intQ.Consume();
                        System.out.println("Using "+temp);
                        String s = "<"+temp+">";
                        System.out.println("Packaging "+temp);
                        stringQ.Produce(s);
                        stringQ.RL.unlock();
                        //System.out.println("Packager String Unlocked");
                        Thread.sleep(100);      
                    }
                    catch(Exception f)
                    {
                    System.out.println("Package ERROR: "+f.getMessage());
                    }
                }
                intQ.RL.unlock();
                //System.out.println("Packager Int Unlocked");
            }
        }
         
           
        }
        catch(Exception e)
        {
            System.out.println("Package ERROR: "+e.getMessage());
        }
        
        
        count++;
        Random r = new Random();
        try {
            Thread.sleep(r.nextInt(700));
        } catch (InterruptedException ex) {
            Logger.getLogger(Packager.class.getName()).log(Level.SEVERE, null, ex);
        }        
        }
        stringQ.RL.unlock();
        
    }
}
