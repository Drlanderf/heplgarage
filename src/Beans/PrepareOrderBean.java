package Beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;

public class PrepareOrderBean implements PropertyChangeListener{
    @Override
    public void propertyChange(PropertyChangeEvent pce)
    {
        ReceivingBean commandeATrier = (ReceivingBean) pce.getOldValue();
        JOptionPane.showMessageDialog(null, commandeATrier.toString(), "commandeATrier !!!!!",JOptionPane.INFORMATION_MESSAGE);  
    }
}
