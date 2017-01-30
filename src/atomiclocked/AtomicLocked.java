package atomiclocked;
import java.util.concurrent.locks.*;

/**
* Author: John D. Snurr
* Date: January 11, 2017
*/
public class AtomicLocked 
{
    public static void main(String[] args) 
    {
        SecondThread sT = new SecondThread(); // Declare and instantiate SecondThread object
        
        Thread runAtomicOne = new Thread(sT); // Declare and initialize runAtomicOne thread
        Thread runAtomicTwo = new Thread(sT); // Declare and initialize runAtomicTwo thread
        Thread runAtomicThree = new Thread(sT); // Declare and initialize runAtomicThree thread
        
        runAtomicOne.setName("FIRST THREAD"); // Set runAtomicOne thread name
        runAtomicTwo.setName("SECOND THREAD"); // Set runAtomicTwo thread name
        runAtomicThree.setName("THIRD THREAD"); // Set runAtomicThree thread name
        
        runAtomicOne.start(); // Start runAtomicOne thread
        runAtomicTwo.start(); // Start runAtomicTwo thread
        runAtomicThree.start(); // Start runAtomicThree thread
    }
}

class SecondThread implements Runnable 
{   
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(); // Declare and initialize new lock
    
    @Override
    public void run() // Override run() of Runnable object to automatically call atomic()
    {
        atomic(); // Call atomic()
    }
    
    public void atomic()
    {     
        rwl.writeLock().lock(); // Lock method from writing
        
        try { // Try to run for loop
            for (int i = 0; i < 50; i++) // For loop to display processed thread
            {
                System.out.println("Running atomic from " + Thread.currentThread().getName());
            }
       } finally { // After the loop has finished, unlock the method
            rwl.writeLock().unlock();
        }
    }
}
