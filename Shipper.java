/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integerfactory;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author tskin
 */
public class Shipper implements Runnable
{
    private ps_queue<String> stringQ;
    
    public Shipper(ps_queue<String> s)
    {
        stringQ = s;
    }

    @Override
    public void run() 
    {
        int count = 0;
        while(count < 10){
        System.out.println("Shipper Runs");
        
        try
        {
            if(stringQ.RL.tryLock())
            {
                try
                {
                    while(stringQ.ConsumerIndex == stringQ.ProductionIndex)
                    {
                        stringQ.RL.unlock();
                        Thread.sleep(100);
                    }
                    String temp = stringQ.Consume();
                    System.out.println("Shipping "+temp);
                }
                catch(Exception e)
                {
                    System.out.println("Shipper ERROR 0:"+e.getMessage());
                }
            }
        }
        catch(Exception f)
        {
            System.out.println("Shipper ERROR"+f.getMessage());
        }
        stringQ.RL.unlock();
        
    
    }
    }
}
