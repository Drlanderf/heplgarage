package Beans;

//PrepareOrderBean

import java.util.*;

public class BeanOrder {
    int nbrJour = (int)(Math.random()+1);
    
    private static ArrayList<InStockListener> nj = new ArrayList<InStockListener>();
    String s = Integer.toString(nbrJour);
    
    public BeanOrder(){
        System.out.println("Livraions dans " + nbrJour + " jours");
    }   
    
    public void addPropertyChangeListener(InStockListener l){
        nj.add(l);
    }
    
    public void removePropertyChangeListener(InStockListener l){
        nj.remove(l);
    }
    
    public void NotifyPropertyChangeEvent(int n){
        InStockEvent i = new InStockEvent(n);
        for (InStockListener li:nj)
        {
            li.InStockDetected(i);
        }
    }
    
    /*@Override
    public String toString()
    {
        return "InStockEvent{" + "Livraions dans =" + nbrJour + '}';
    }*/
}