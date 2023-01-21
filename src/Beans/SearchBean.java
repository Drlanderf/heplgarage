package Beans;

import javax.swing.JOptionPane;

public class SearchBean {
    private ReceivingBean commandeachercher;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public SearchBean()
    {
        setcommanderecue(null);
    }
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public SearchBean(ReceivingBean commandeAChercher)
    {
        setcommanderecue(commandeAChercher);
    }
    
    public void setcommanderecue(ReceivingBean commandeAChercher)
    {
        commandeachercher = commandeAChercher;
        JOptionPane.showMessageDialog(null, commandeachercher.getcommanderecue(), "SearchBean",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public ReceivingBean getcommanderecue()
    {
        return commandeachercher;
    }
    
    /*protected PropertyChangeSupport GestProp = new PropertyChangeSupport(this); 
    
    @Override
    public void commandesReceived(InStockEvent instockEvent)
    {
        setCommandes(instockEvent.getCommandes(), instockEvent.getLibelle(),instockEvent.getType(), instockEvent.getQuantite());
    }
    
    public void setCommandes(ArrayList<String> commandes, String libelle, String type, int quantite){
        GestProp.firePropertyChange("commandeATrier",new Commandes(commandes, libelle, type, quantite),"commandeATrier"); 
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l)
    {
        GestProp.addPropertyChangeListener(l);
    }
    public void removePropertyChangeListener(PropertyChangeListener l)
    {
        GestProp.removePropertyChangeListener(l);
    }*/
}
