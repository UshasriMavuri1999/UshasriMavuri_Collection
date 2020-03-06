package com.mycompany.collections;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileDescriptor;
import java.io.FileOutputStream;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class CollectionsList<E> 
{
	static PrintStream myout = new PrintStream(new FileOutputStream(FileDescriptor.out));
	private static final Logger logger = LogManager.getLogger(CollectionsList.class);
	static Properties props = new Properties();
    //Size of list
    private int size = 0;
     
    //Default capacity of list is 10
    private static final int capacity = 10;
     
    //This array will store all elements added to list
    private Object elements[];
 
    //Default constructor
    public CollectionsList() 
    {
        elements = new Object[capacity];
    }
 
    //Add method
    public void add(E val) 
    {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = val;
    }
     
    //Get method 
    @SuppressWarnings("unchecked")
	public E get(int i) 
    {
    	try 
    	{
    		if (i >= size || i < 0) 
    		{
    			throw new IndexOutOfBoundsException("Given Index : " + i + ", List Size : " + size);
    		}
    	}
    	catch(IndexOutOfBoundsException e) {
    		myout.print(e);
    		myout.print("Given index is out of Bound");
    	}
        return (E) elements[i];
    }
     
    //Remove method
    @SuppressWarnings("unchecked")
    public E remove(int i) 
    {
    	try 
    	{
    		if (i >= size || i < 0) 
    		{
    			throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
    		}
    	}
    	catch(IndexOutOfBoundsException e)
    	{
    		myout.print(e);
    		myout.print("Given index is out of Bound");
    	}
        Object item = elements[i];
        int numElements = elements.length - ( i + 1 ) ;
        System.arraycopy( elements, i + 1, elements, i, numElements ) ;
        size--;
        return (E) item;
    }
     
    //Get Size of list
    public int size() 
    {
        return size;
    }
     
    //Print method
    public String display() 
    {
         StringBuilder sb = new StringBuilder();
         sb.append('[');
         for(int i = 0; i < size ;i++) 
         {
             sb.append(elements[i].toString());
             if(i<size-1)
             {
                 sb.append(",");
             }
         }
         sb.append(']');
         return sb.toString();
    }
     
    private void ensureCapacity() 
    {
        int newSize = elements.length + 1;
        elements = Arrays.copyOf(elements, newSize);
    }
    public static void main(String[] args) 
    {
    	BasicConfigurator.configure();
    	logger.debug("Debugging started");
    	try {
			props.load(new FileInputStream("log4j.properties"));
		} catch (FileNotFoundException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	    PropertyConfigurator.configure(props);
        CollectionsList<Integer> list = new CollectionsList<>();
        
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
        //Adding elements
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
         
        boolean start = true;
		while(start)
        {
        	myout.print("-------------List Operations-------------  \n 1. Insert \n 2. Delete \n 3. Display List \n 4. Get element");
        	logger.warn("Enter option within the given above");
        	myout.print("Enter your Option : ");
        	int option = scan.nextInt();
        	if(option==1)
        	{
        		myout.print("Enter the element to insert : ");
        		int element = scan.nextInt();
        		list.add(element);
        		
        	}
        	//Remove elements with index
        	if(option==2)
        	{
        		logger.info("Enter index value");
        		myout.print("Enter the element to delete : ");
        		int element = scan.nextInt();
        		list.remove(element);
        	}
        	if(option==3)
        	{
        		myout.print("List is : "+list.display());
        	}
        	 //Get element with index
        	if(option==4)
        	{
        		logger.info("Enter index value");
        		myout.print("Enter index to get element : ");
        		int element = scan.nextInt();
        		myout.print("Element is : "+list.get(element));
        	}
        	myout.print("\nWould you like to start over? (y,n) : ");
            String startOver = scan.next();
            
            if("n".equals(startOver))
            {
                start = false;
                myout.print("---------------Thanks----------------");
            }
        	
        }
    }

	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}


}
