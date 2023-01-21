package Beans;

import java.util.*;
import javax.swing.JOptionPane;
 
//ReceivingBean
public class BeanEmetteur implements SearchFoundListener, InStockListener {
    private static ArrayList<PropertyChangeListener> commandeList;
    
    public BeanEmetteur (){
        commandeList = new ArrayList<>();
    }
    
    public static ArrayList<PropertyChangeListener> getCommandeList(){
        return commandeList;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l){
        commandeList.add(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l){
        commandeList.remove(l);
    }
    
    public void NotifyPropertyChangeEvent(String c){ //Comme c# on utilise le notify propertychange
        PropertyChangeEvent p = new PropertyChangeEvent(c);
        JOptionPane.showMessageDialog(null, c, "ReceivingBean",JOptionPane.INFORMATION_MESSAGE);        
        for (PropertyChangeListener li:commandeList)
        {
            li.PropertyChangeDetected(p);
        }
    }
    
    @Override
    public void InStockDetected(InStockEvent i) {
        System.out.println("Event detected !!!");
        System.out.println("Commande recue = " + i.getnbrJour());
    }

    @Override
    public void SearchFoundDetected(SearchFoundEvent s) {
        System.out.println("Event detected !!!");
        System.out.println("Commande libelle = " + s.getLibelle());
    }
}