package AppGestion;

import static AppGestion.Accueil.*;
import Persistancedata.PersistanceTexte;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import network.*;

public class ReceiveCommande extends javax.swing.JFrame 
{
    
    private NetworkBasicServer server;
    private NetworkBasicClient client;
    private String message;
    public static boolean connecte1 = false, connecte2 = false, connecte3 = false;
    String Choix;
    
    public ReceiveCommande(String ChoixReception) 
    {
        initComponents();
        setTitle(getTitle() + ChoixReception + " : " + Utilisateurpersonnel);
        Choix = ChoixReception;
        switch(ChoixReception)
        {
            case "Pièces":      server = new NetworkBasicServer(Integer.valueOf(configproperties.get("port_garagereception1").toString()), this.CB_CommandePrete);
                                break;
            case "Pneus":       server = new NetworkBasicServer(Integer.valueOf(configproperties.get("port_garagereception2").toString()), this.CB_CommandePrete);
                                break;
            case "Lubrifiants": server = new NetworkBasicServer(Integer.valueOf(configproperties.get("port_garagereception3").toString()), this.CB_CommandePrete);
                                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CB_CommandePrete = new javax.swing.JCheckBox();
        ReceiveCommande = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage HEPL - Réception de ");

        CB_CommandePrete.setText("Commande prête");

        ReceiveCommande.setText("Réceptionner");
        ReceiveCommande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReceiveCommandeActionPerformed(evt);
            }
        });

        CancelButton.setText("Annuler");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CB_CommandePrete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ReceiveCommande, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(CancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(CB_CommandePrete)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReceiveCommande)
                    .addComponent(CancelButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        this.dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void ReceiveCommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReceiveCommandeActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        switch(Choix)
        {
            case "Pièces":
            message = server.getMessage();
            if(!message.equals("RIEN"))
            {
                if(connecte1 == false)
                {
                    client = new NetworkBasicClient(configproperties.get("ip_centrale1").toString(), Integer.valueOf(configproperties.get("port_centrale1").toString()));
                    connecte1 = true;
                }
                server.sendMessage("Commande bien reçue !");

                String[] messageparts = message.split("_");
                JOptionPane.showMessageDialog(null, message, "Message",JOptionPane.INFORMATION_MESSAGE);
                if(messageparts[0].equals("Disponible"))
                {
                    String Commande = messageparts[1] + " " + messageparts[2] + " " + messageparts[3] + " " + messageparts[4] + " " + messageparts[5] + " " + messageparts[6] ;
                    PersistanceTexte.Write(Commande + "__\n", chemin_commande);
                }
            }
            break;
            case "Pneus":
            message = server.getMessage();
            if(!message.equals("RIEN"))
            {
                if(connecte2 == false)
                {
                    client = new NetworkBasicClient(configproperties.get("ip_centrale2").toString(), Integer.valueOf(configproperties.get("port_centrale2").toString()));
                    connecte2 = true;
                }
                server.sendMessage("Commande bien reçue !");

                String[] messageparts = message.split("_");
                JOptionPane.showMessageDialog(null, message, "Message",JOptionPane.INFORMATION_MESSAGE);
                if(messageparts[0].equals("Disponible"))
                {
                    String Commande = messageparts[1] + " " + messageparts[2] + " " + messageparts[3] + " " + messageparts[4] + " " + messageparts[5] + " " + messageparts[6] ;
                    PersistanceTexte.Write(Commande + "__\n", chemin_commande);
                }
            }
            break;
            case "Lubrifiants":
            message = server.getMessage();
            if(!message.equals("RIEN"))
            {
                if(connecte3 == false)
                {
                    client = new NetworkBasicClient(configproperties.get("ip_centrale3").toString(), Integer.valueOf(configproperties.get("port_centrale3").toString()));
                    connecte3 = true;
                }
                server.sendMessage("Commande bien reçue !");

                String[] messageparts = message.split("_");
                JOptionPane.showMessageDialog(null, message, "Message",JOptionPane.INFORMATION_MESSAGE);
                if(messageparts[0].equals("Disponible"))
                {
                    String Commande = messageparts[1] + " " + messageparts[2] + " " + messageparts[3] + " " + messageparts[4] + " " + messageparts[5] + " " + messageparts[6] ;
                    PersistanceTexte.Write(Commande + "__\n", chemin_commande);
                }
            }
            break;
        }
    }//GEN-LAST:event_ReceiveCommandeActionPerformed

    public static void main(String args[])
    {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReceiveCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiveCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiveCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiveCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(() -> 
        {
            new ReceiveCommande("").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB_CommandePrete;
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton ReceiveCommande;
    // End of variables declaration//GEN-END:variables
}
