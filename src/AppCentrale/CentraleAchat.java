package AppCentrale;

import Beans.*;

import static AppGestion.ReceiveCommande.connecte1;
import static AppGestion.ReceiveCommande.connecte2;
import static AppGestion.ReceiveCommande.connecte3;
import Persistancedata.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import network.*;

public class CentraleAchat extends javax.swing.JFrame 
{
    private static BeanEmetteur emetteurC = new BeanEmetteur();
    private static BeanRecepteur recepteurC = new BeanRecepteur();
    private static BeanOrder orderC = new BeanOrder();
    
    public static Properties configproperties;
    private NetworkBasicServer server;
    private NetworkBasicClient client;
    private NetworkBasicClient client2;
    private String message;
    
    String chemin_commandeencours;
    public static boolean connecte = false;
    boolean ready = false;
    DefaultTableModel Table_commande;
    String disponibilite="";
    String ChoixCentrale ="";
    
    public CentraleAchat(String TypeCentrale) 
    {
        initComponents();
        ChoixCentrale = TypeCentrale;
        setTitle(getTitle() + ChoixCentrale.toUpperCase());
        Centrale.setText(ChoixCentrale);
        configproperties = PersistanceUsersProperties.ChargerUsersProperties("data/config.properties");
        chemin_commandeencours = configproperties.get("chemin_commandeencours").toString();
        
        switch(ChoixCentrale)//config de base en fonction de la centrale utilisée
        {
            case "Pièces":      client2 = new NetworkBasicClient(configproperties.get("ip_garage").toString(), 
                    Integer.valueOf(configproperties.get("port_garagereception1").toString()));
                                server = new NetworkBasicServer(Integer.valueOf(configproperties.get("port_centrale1").toString()), 
                                        this.CB_MessageEntrant);
                                break;
            case "Pneus":       client2 = new NetworkBasicClient(configproperties.get("ip_garage").toString(), 
                    Integer.valueOf(configproperties.get("port_garagereception2").toString()));
                                server = new NetworkBasicServer(Integer.valueOf(configproperties.get("port_centrale2").toString()), 
                                        this.CB_MessageEntrant);
                                break;
            case "Lubrifiants": client2 = new NetworkBasicClient(configproperties.get("ip_garage").toString(), 
                    Integer.valueOf(configproperties.get("port_garagereception3").toString()));
                                server = new NetworkBasicServer(Integer.valueOf(configproperties.get("port_centrale3").toString()), 
                                        this.CB_MessageEntrant);
                                break;
        }
        PersistanceTexte.createWithOutDelete(chemin_commandeencours);
        ChargerCommmandes();
        
        BeanEmetteur be = emetteurC;
        BeanRecepteur br = recepteurC;
        BeanOrder bo = orderC;
        
        be.addPropertyChangeListener(br);     
    }
    
    void ChargerCommmandes()
    {
        ready = false;
        String allcommande = PersistanceTexte.Read(chemin_commandeencours);
        String[] commandeparts = allcommande.split("__");
        CB_Commande.removeAllItems();
        String[] parts = {"","","","",""};
        Table_commande = (DefaultTableModel)DetailCommande.getModel();
        Table_commande.setValueAt(parts[0], 0, 1);
        Table_commande.setValueAt(parts[1], 1, 1);
        Table_commande.setValueAt(parts[2], 2, 1);
        Table_commande.setValueAt(parts[3], 3, 1);
        Table_commande.setValueAt(parts[4], 4, 1);
        TF_CommandeRecue.setText(">> ");
        switch(ChoixCentrale)
        {
            case "Pièces":      for (String item : commandeparts)
                                {
                                    if(item.contains("pièces"))
                                    {
                                        String[] messageparts = item.split("_");
                                        String PartieMessage = messageparts[0] 
                                                + "_" + messageparts[2] 
                                                + "_" 
                                                + messageparts[3] 
                                                + "_" 
                                                + messageparts[4] + "_" 
                                                + messageparts[5];
                                        CB_Commande.addItem(PartieMessage);
                                    }
                                }
                                break;
            case "Pneus":       for (String item : commandeparts)
                                {
                                    if(item.contains("pneu"))
                                    {
                                        String[] messageparts = item.split("_");
                                        String PartieMessage = messageparts[0] 
                                                + "_" + messageparts[2] 
                                                + "_" + messageparts[3] 
                                                + "_" + messageparts[4] 
                                                + "_" + messageparts[5];
                                        CB_Commande.addItem(PartieMessage);
                                    }
                                }
                                break;
            case "Lubrifiants": for (String item : commandeparts)
                                {
                                    if(item.contains("lubrifiant"))
                                    {
                                        String[] messageparts = item.split("_");
                                        String PartieMessage = messageparts[0] 
                                                + "_" + messageparts[2] 
                                                + "_" + messageparts[3] 
                                                + "_" + messageparts[4] 
                                                + "_" + messageparts[5];
                                        CB_Commande.addItem(PartieMessage);
                                    }
                                }
                                break;
        } 
        CB_Commande.setSelectedIndex(-1);
        ready = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Centrale = new javax.swing.JLabel();
        CB_MessageEntrant = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        ButtonLire = new javax.swing.JButton();
        CB_Commande = new javax.swing.JComboBox();
        TF_CommandeRecue = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DetailCommande = new javax.swing.JTable();
        ButtonVerificationDisponibilite = new javax.swing.JButton();
        NonDisponible = new javax.swing.JRadioButton();
        Disponible = new javax.swing.JRadioButton();
        ButtonEnvoyerReponse = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Centrale achat - ");

        Centrale.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        CB_MessageEntrant.setText("Message entrant");

        jLabel1.setText("Commande en cours :");

        ButtonLire.setText("Lire");
        ButtonLire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLireActionPerformed(evt);
            }
        });

        CB_Commande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_CommandeActionPerformed(evt);
            }
        });

        TF_CommandeRecue.setEditable(false);

        jLabel2.setText("Détails de la commande :");

        DetailCommande.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Date :", null},
                {"Délai :", null},
                {"Libellé :", null},
                {"Type :", null},
                {"Quantité :", null}
            },
            new String [] {
                "Caractéristique", "Valeur"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DetailCommande.setFocusable(false);
        DetailCommande.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(DetailCommande);
        if (DetailCommande.getColumnModel().getColumnCount() > 0) {
            DetailCommande.getColumnModel().getColumn(0).setMinWidth(100);
            DetailCommande.getColumnModel().getColumn(0).setPreferredWidth(100);
            DetailCommande.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        ButtonVerificationDisponibilite.setText("Vérification disponibilité");

        NonDisponible.setText("Non disponible");
        NonDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NonDisponibleActionPerformed(evt);
            }
        });

        Disponible.setText("Disponible");
        Disponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisponibleActionPerformed(evt);
            }
        });

        ButtonEnvoyerReponse.setText("Envoyer réponse");
        ButtonEnvoyerReponse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEnvoyerReponseActionPerformed(evt);
            }
        });

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonVerificationDisponibilite)
                        .addGap(75, 75, 75)
                        .addComponent(Disponible, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(NonDisponible)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TF_CommandeRecue, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(ButtonLire, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CB_Commande, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(CB_MessageEntrant, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Centrale, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(327, 327, 327)
                                .addComponent(jButton1)))
                        .addGap(20, 20, 20))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonEnvoyerReponse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Centrale, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_MessageEntrant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonLire, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CB_Commande, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(TF_CommandeRecue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonVerificationDisponibilite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Disponible, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NonDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(ButtonEnvoyerReponse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisponibleActionPerformed
        NonDisponible.setSelected(false);
        disponibilite="Disponible";
        JOptionPane.showMessageDialog(null, 
                "Commande disponible", 
                "SearchBean",
                JOptionPane.INFORMATION_MESSAGE);
        
        int max = 10;
        int min = 1;
        int range = max - min + 1;
        int nbrJour = (int)(Math.random() * range) + min;
        JOptionPane.showMessageDialog(null, 
                "Livraions dans " 
                + nbrJour 
                + " jours", 
                "PrepareOrderBean",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_DisponibleActionPerformed

    private void NonDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NonDisponibleActionPerformed
        Disponible.setSelected(false);
        disponibilite="NonDisponible";
        JOptionPane.showMessageDialog(null, 
                "Commande pas disponible", 
                "SearchBean",
                JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_NonDisponibleActionPerformed

    private void ButtonLireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLireActionPerformed
        message = server.getMessage();
        if(!message.equals("RIEN"))
        {
            if(connecte == false)
            {
                client = new NetworkBasicClient(configproperties.get("ip_garage").toString(), 
                        Integer.valueOf(configproperties.get("port_garagecommande").toString()));
                connecte = true;
            }
            server.sendMessage("Commande bien reçue !");
            
            PersistanceTexte.Write(message + "__\n", chemin_commandeencours);
            
            String[] messageparts = message.split("_");
            String PartieMessage = messageparts[0] 
                    + "_" + messageparts[2] 
                    + "_" + messageparts[3] 
                    + "_" + messageparts[4] 
                    + "_" + messageparts[5];
            TF_CommandeRecue.setText(">> " + PartieMessage);
            CB_Commande.addItem(PartieMessage);
        }
    }//GEN-LAST:event_ButtonLireActionPerformed

    private void CB_CommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_CommandeActionPerformed
        if(ready == true )
        {
            Table_commande = (DefaultTableModel)DetailCommande.getModel();
            String[] messageparts = CB_Commande.getSelectedItem().toString().split("_");
            Table_commande.setValueAt(messageparts[0], 0, 1);
            Table_commande.setValueAt(messageparts[1], 1, 1);
            Table_commande.setValueAt(messageparts[2], 2, 1);
            Table_commande.setValueAt(messageparts[3], 3, 1);
            Table_commande.setValueAt(messageparts[4], 4, 1);
            
            emetteurC.NotifyPropertyChangeEvent(CB_Commande.getSelectedItem().toString());
        }
    }//GEN-LAST:event_CB_CommandeActionPerformed

   
    
    private void ButtonEnvoyerReponseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEnvoyerReponseActionPerformed
        if((NonDisponible.isSelected() || Disponible.isSelected())&& CB_Commande.getSelectedIndex() != -1)
        {
            PersistanceTexte.createWithOutDelete(chemin_commandeencours);
            String allcommande = PersistanceTexte.Read(chemin_commandeencours);
            String[] commandeparts = allcommande.split("__");
            String Commande="";

            switch(ChoixCentrale)
            {
                case "Pièces":      for (String item : commandeparts)
                                    {
                                        if(item.contains("pièces"))
                                        {
                                            String[] messageparts = item.split("_");
                                            String PartieMessage = messageparts[0] 
                                                    + "_" + messageparts[2] 
                                                    + "_" + messageparts[3] 
                                                    + "_" + messageparts[4] 
                                                    + "_" + messageparts[5];
                                            if(!CB_Commande.getSelectedItem().equals(PartieMessage))
                                            {
                                                Commande = Commande + item + "__\n";
                                            }
                                            else
                                            {
                                                client2.sendStringWithoutWaiting(disponibilite + "_" + item);
                                                JOptionPane.showMessageDialog(null, 
                                                        "Commande envoyée", 
                                                        "ReceivingBean",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        }
                                        else
                                        {
                                            Commande = Commande + item + "__\n";
                                        }
                                    }
                                    break;
                case "Pneus":       for (String item : commandeparts)
                                    {
                                        if(item.contains("pneu"))
                                        {
                                            String[] messageparts = item.split("_");
                                            String PartieMessage = messageparts[0] 
                                                    + "_" + messageparts[2] 
                                                    + "_" + messageparts[3] 
                                                    + "_" + messageparts[4] 
                                                    + "_" + messageparts[5];
                                            if(!CB_Commande.getSelectedItem().equals(PartieMessage))
                                            {
                                                Commande = Commande + item + "__\n";
                                            }
                                            else
                                            {
                                                client2.sendStringWithoutWaiting(disponibilite + "_" + item);
                                                JOptionPane.showMessageDialog(null, 
                                                        "Commande envoyée", 
                                                        "ReceivingBean",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        }
                                        else
                                        {
                                            Commande = Commande + item + "__\n";
                                        }
                                    }
                                    break;
                case "Lubrifiants": for (String item : commandeparts)
                                    {
                                        if(item.contains("lubrifiant"))
                                        {
                                            String[] messageparts = item.split("_");
                                            String PartieMessage = messageparts[0] 
                                                    + "_" + messageparts[2] 
                                                    + "_" + messageparts[3] 
                                                    + "_" + messageparts[4] 
                                                    + "_" + messageparts[5];
                                            if(!CB_Commande.getSelectedItem().equals(PartieMessage))
                                            {
                                                Commande = Commande + item + "__\n";
                                            }
                                            else
                                            {
                                                client2.sendStringWithoutWaiting(disponibilite + "_" + item);
                                                JOptionPane.showMessageDialog(null, 
                                                        "Commande envoyée", 
                                                        "ReceivingBean",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        }
                                        else
                                        {
                                            Commande = Commande + item + "__\n";
                                        }
                                    }
                                    break;
            }
            PersistanceTexte.create(chemin_commandeencours);
            PersistanceTexte.Write(Commande,chemin_commandeencours);
            ChargerCommmandes();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Formulaire incomplet", "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ButtonEnvoyerReponseActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        switch(ChoixCentrale)
        {
            case "Pièces":      connecte1=false;
                                break;
            case "Pneus":       connecte2=false;
                                break;
            case "Lubrifiants": connecte3=false;
                                break;
        } 
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


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
            java.util.logging.Logger.getLogger(CentraleAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CentraleAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CentraleAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CentraleAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> 
        {
            new CentraleAchat("").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonEnvoyerReponse;
    private javax.swing.JButton ButtonLire;
    private javax.swing.JButton ButtonVerificationDisponibilite;
    private javax.swing.JComboBox CB_Commande;
    private javax.swing.JCheckBox CB_MessageEntrant;
    private javax.swing.JLabel Centrale;
    private javax.swing.JTable DetailCommande;
    private javax.swing.JRadioButton Disponible;
    private javax.swing.JRadioButton NonDisponible;
    private javax.swing.JTextField TF_CommandeRecue;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
