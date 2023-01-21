package AppGestion;

import static AppGestion.Accueil.*;
import static AppCentrale.CentraleAchat.connecte;
import Persistancedata.PersistanceTexte;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JOptionPane;
import network.*;

public class Commande extends javax.swing.JFrame 
{
    private NetworkBasicClient client;
    private String message;
    
    Thread ThreadDate;
    String TypeCommande="";
    String delaicommande="";

    Vector<String> dataCommande = new Vector<String>();
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Commande(String Typecommande) 
    {
        initComponents();
        TypeCommande = Typecommande;
        setTitle(getTitle() + TypeCommande + " : " + Utilisateurpersonnel);
        AfficherDate();
        switch(Typecommande)
        {
            case "pièces":     client = new NetworkBasicClient(configproperties.get("ip_centrale1").toString(), Integer.valueOf(configproperties.get("port_centrale1").toString()));
                               break;
            case "pneu":       client = new NetworkBasicClient(configproperties.get("ip_centrale2").toString(), Integer.valueOf(configproperties.get("port_centrale2").toString()));
                               break;
            case "lubrifiant": client = new NetworkBasicClient(configproperties.get("ip_centrale3").toString(), Integer.valueOf(configproperties.get("port_centrale3").toString()));
                               break;
        }
    } 
    
    void AfficherDate()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "." + TypeCommande + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        
        ThreadDate = new Thread()
        {
            @Override
            @SuppressWarnings("SleepWhileInLoop")
            public void run()
            {
                while(true)
                {
                    labelDate.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()));
                    try
                    {
                        sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.getMessage();
                    }
                }     
            }
        };
        ThreadDate.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelCommande = new javax.swing.JLabel();
        Urgent = new javax.swing.JRadioButton();
        Normal = new javax.swing.JRadioButton();
        NonPrioritaire = new javax.swing.JRadioButton();
        LabelListeCommande = new javax.swing.JLabel();
        Libelle = new javax.swing.JLabel();
        Type = new javax.swing.JLabel();
        Quantite = new javax.swing.JLabel();
        FieldLibelle = new javax.swing.JTextField();
        FieldQuantite = new javax.swing.JTextField();
        FieldType = new javax.swing.JTextField();
        ButtonSend = new javax.swing.JButton();
        ButtonCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListeCommande = new javax.swing.JList();
        labelDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage HEPL : commande de ");

        LabelCommande.setText("Commande :");
        LabelCommande.setFocusable(false);

        Urgent.setText("Urgent");
        Urgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UrgentActionPerformed(evt);
            }
        });

        Normal.setText("Normal");
        Normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NormalActionPerformed(evt);
            }
        });

        NonPrioritaire.setText("Non prioritaire");
        NonPrioritaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NonPrioritaireActionPerformed(evt);
            }
        });

        LabelListeCommande.setText("Commandes :");

        Libelle.setText("Libellé :");

        Type.setText("Type :");

        Quantite.setText("Quantité :");

        ButtonSend.setText("Envoyer");
        ButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSendActionPerformed(evt);
            }
        });

        ButtonCancel.setText("Annuler");
        ButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(ListeCommande);

        labelDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDate.setFocusable(false);
        labelDate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        labelDate.setPreferredSize(new java.awt.Dimension(34, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Urgent)
                                    .addComponent(Libelle)
                                    .addComponent(Quantite)
                                    .addComponent(Type))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FieldQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FieldLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FieldType, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Normal)
                                        .addGap(40, 40, 40)
                                        .addComponent(NonPrioritaire))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(LabelCommande))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(ButtonSend)
                                .addGap(80, 80, 80)
                                .addComponent(ButtonCancel)))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelListeCommande)
                                .addGap(0, 341, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCommande)
                    .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Urgent)
                    .addComponent(Normal)
                    .addComponent(NonPrioritaire)
                    .addComponent(LabelListeCommande))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Libelle)
                            .addComponent(FieldLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Type)
                            .addComponent(FieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Quantite)
                            .addComponent(FieldQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonSend)
                            .addComponent(ButtonCancel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UrgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UrgentActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "." + TypeCommande + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        
        NonPrioritaire.setSelected(false);
        Normal.setSelected(false);
        delaicommande="Urgent";
    }//GEN-LAST:event_UrgentActionPerformed

    private void NormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NormalActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "." + TypeCommande + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        
        Urgent.setSelected(false);
        NonPrioritaire.setSelected(false);
        delaicommande="Normal";
    }//GEN-LAST:event_NormalActionPerformed

    private void NonPrioritaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NonPrioritaireActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "." + TypeCommande + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        
        Urgent.setSelected(false);
        Normal.setSelected(false);
        delaicommande="NonPrioritaire";
    }//GEN-LAST:event_NonPrioritaireActionPerformed

    private void ButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSendActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "." + TypeCommande + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(!FieldLibelle.getText().equals("") && !FieldType.getText().equals("") && !FieldQuantite.getText().equals("") && (Urgent.isSelected() || Normal.isSelected() || NonPrioritaire.isSelected()))
        {
            String commande = delaicommande + "_" + FieldLibelle.getText() + "_" + FieldType.getText() + "_" + FieldQuantite.getText();

            dataCommande.add(commande);
            ListeCommande.setListData(dataCommande);

            String Com = "Commande de " + TypeCommande + "_" + commande;
            
            FieldLibelle.setText("");
            FieldType.setText("");
            FieldQuantite.setText("");
            
            message = labelDate.getText() + "_" + Com;
            
            client.sendStringWithoutWaiting(message);
            //JOptionPane.showMessageDialog(null, reponse, "Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Formulaire incomplet", "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ButtonSendActionPerformed

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "." + TypeCommande + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        connecte = false;
        this.dispose();
    }//GEN-LAST:event_ButtonCancelActionPerformed

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
            java.util.logging.Logger.getLogger(Commande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Commande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Commande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Commande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> 
        {
            new Commande("").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JButton ButtonSend;
    private javax.swing.JTextField FieldLibelle;
    private javax.swing.JTextField FieldQuantite;
    private javax.swing.JTextField FieldType;
    private javax.swing.JLabel LabelCommande;
    private javax.swing.JLabel LabelListeCommande;
    private javax.swing.JLabel Libelle;
    private javax.swing.JList ListeCommande;
    private javax.swing.JRadioButton NonPrioritaire;
    private javax.swing.JRadioButton Normal;
    private javax.swing.JLabel Quantite;
    private javax.swing.JLabel Type;
    private javax.swing.JRadioButton Urgent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDate;
    // End of variables declaration//GEN-END:variables
}
