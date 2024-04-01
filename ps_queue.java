/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integerfactory;

import java.util.concurrent.locks.*;


/**
 *
 * @author tskin
 */
public class ps_queue <T>
{

    public ps_queue() {}
    
    T [] data = (T[]) new Object[15];
    Lock RL = new ReentrantLock();
    int ProductionIndex = 0;
    int ConsumerIndex = 0;
    
    
    
    public void Produce(T x) throws Exception
    {
        if(ProductionIndex == (ConsumerIndex-1))
        {
            throw new Exception("Queue Full!");
        }
        else
        {
            data[ProductionIndex] = x;
        }
        ProductionIndex = (ProductionIndex + 1) % data.length;
    }
    
    
    
    public T Consume() throws Exception
    {
        T x;
        if(ConsumerIndex == ProductionIndex)
        {
            throw new Exception("Queue is Empty!");
        }
        else
        {
            
            x = data[ConsumerIndex] ;
            ConsumerIndex = (ConsumerIndex+1)%data.length;
        }
        return x;
        
       
    }  
}
