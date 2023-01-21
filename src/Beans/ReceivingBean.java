package Beans;

import AppCentrale.CentraleAchat;

import java.beans.*;

public class ReceivingBean {    
    protected PropertyChangeSupport GestProp = new PropertyChangeSupport(this);
    private String commanderecue;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ReceivingBean(){
        setCommandes("");
    }
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ReceivingBean(String CommandeRecue)
    {
        setCommandes(CommandeRecue);
    }
    
    public String getcommanderecue()
    {
        return commanderecue;
    }
    
    public void setCommandes(String Commande){
        String oldValue = this.commanderecue;
        this.commanderecue = Commande;
        
        GestProp.firePropertyChange("Commande", oldValue, Commande);
        
        //GestProp.addPropertyChangeListener(listen());
    }
    
     private static PropertyChangeListener listen() {
        //JOptionPane.showMessageDialog(null, receiveC.getcommanderecue(), "ReceivingBean",JOptionPane.INFORMATION_MESSAGE);
        return null;
    }
    
   /* public void addPropertyChangeListener(PropertyChangeListener l)
    {
        GestProp.addPropertyChangeListener(l);
    }
    public void removePropertyChangeListener(PropertyChangeListener l)
    {
        GestProp.removePropertyChangeListener(l);
    }*/
}
