/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integerfactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.concurrent.locks.*;
/**
 *
 * @author tskin
 */
public class Assembler implements Runnable
{
    ps_queue<Integer> q;
    
    public Assembler(ps_queue<Integer> psq)
    {
        q = psq;
    }

    
    
    @Override
    public void run() 
    {
        
        int count = 0;
        while(count < 10){
        try
        {
            //System.out.println("Assembler Runs");
            Random rand = new Random();
            int r = rand.nextInt(10);
            if(q.RL.tryLock((long) 0.1, SECONDS))
            {
                //System.out.println("Assembler Locked");
                q.Produce(r);
                System.out.println("Producing "+count+": "+ r);
                q.RL.unlock();
                //System.out.println("Assembler Unlocked");
                count++;
            }
            Thread.sleep(rand.nextInt(500));
        }
        
        catch (Exception e)
        {
            System.out.println("help");
        }
    } 
        System.out.println("Job Done!");
}
}

