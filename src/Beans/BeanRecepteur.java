package Beans;
import AppCentrale.CentraleAchat;

import java.util.*;
import javax.swing.JOptionPane;

//SearchBean
public class BeanRecepteur implements PropertyChangeListener {
    private static ArrayList<PropertyChangeListener> commandeRecue;
    private static ArrayList<SearchFoundListener> commande;
    private CentraleAchat a;
    String dispo = "Disponible";
    private BeanEmetteur emetteurC;
    
    public BeanRecepteur(){
        commandeRecue = BeanEmetteur.getCommandeList();
        
       // if(a.disponibilite.equals(dispo))
        {
            System.out.println("Commande disponible");
        }
        //else
            System.out.println("Commande pas disponible");
    }
    
    public void addPropertyChangeListener(SearchFoundListener l){
        commande.add(l);
    }
    
    public void removePropertyChangeListener(SearchFoundListener l){
        commande.remove(l);
    }
    
    public void NotifyPropertyChangeEvent(String c){
        SearchFoundEvent s = new SearchFoundEvent(c);
        JOptionPane.showMessageDialog(null, c, "ReceivingBean",JOptionPane.INFORMATION_MESSAGE);
        for (SearchFoundListener li:commande)
        {
            li.SearchFoundDetected(s);
        }
    }
       
    @Override
    public void PropertyChangeDetected(PropertyChangeEvent e) {
        System.out.println("Event detected !!!");
        System.out.println("Commande recue = " + e.getCommanderecue());
    }
}
