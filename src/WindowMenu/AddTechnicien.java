package WindowMenu;

import People.TechnicienExterieur;
import Persistancedata.PersistanceBinaire;
import Persistancedata.PersistanceTexte;
import Persistancedata.PersistanceUsersProperties;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;
import static AppGestion.Accueil.*;

public class AddTechnicien extends javax.swing.JFrame {
    
    ArrayList<TechnicienExterieur> ListeTechnicienExterieur = new ArrayList<>();
    
    public AddTechnicien() {
        initComponents();
        
        try 
        {
            ListeTechnicienExterieur = PersistanceBinaire.ChargerBinaire(chemin_technicien);                   
        }
        catch(IOException | ClassNotFoundException e){
                System.err.println(e.toString());
        }
        setTitle(getTitle() + " : " + Utilisateurpersonnel);
    }
    
    void add()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        TechnicienExterieur Technicienexterieur;
        Technicienexterieur = new TechnicienExterieur(FieldNom.getText(), FieldPrenom.getText(), FieldAdresse.getText(), FieldNumTel.getText(), FieldMDP.getText());
        ListeTechnicienExterieur.add(Technicienexterieur);

        PersistanceBinaire.SauvegarderBinaire(ListeTechnicienExterieur,chemin_technicien);
        
        Properties usersproperties; 
        Properties userstypeproperties; 

        usersproperties = PersistanceUsersProperties.ChargerUsersProperties(chemin_users);
        userstypeproperties = PersistanceUsersProperties.ChargerUsersProperties(chemin_userstype);
        
        usersproperties.put(Technicienexterieur.getNom()+Technicienexterieur.getPrenom(), Technicienexterieur.getMdp());
        userstypeproperties.put(Technicienexterieur.getNom()+Technicienexterieur.getPrenom(), Technicienexterieur.getTypeemploye());

        PersistanceUsersProperties.SauvegarderUsersProperties(usersproperties,chemin_users);
        PersistanceUsersProperties.SauvegarderUsersProperties(userstypeproperties,chemin_userstype);
        
        JOptionPane.showMessageDialog(null, "Technicien ajouté", "Message",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ButtonCancel = new javax.swing.JButton();
        ButtonAdd = new javax.swing.JButton();
        FieldNom = new javax.swing.JTextField();
        FieldPrenom = new javax.swing.JTextField();
        FieldAdresse = new javax.swing.JTextField();
        FieldNumTel = new javax.swing.JTextField();
        FieldMDP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ajouter un technicien");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nom :");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Prenom :");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Adresse :");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("NumTel :");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Mot de passe :");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setPreferredSize(new java.awt.Dimension(25, 14));

        ButtonCancel.setText("Annuler");
        ButtonCancel.setToolTipText("");
        ButtonCancel.setOpaque(false);
        ButtonCancel.setPreferredSize(new java.awt.Dimension(50, 25));
        ButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelActionPerformed(evt);
            }
        });

        ButtonAdd.setText("Ajouter");
        ButtonAdd.setToolTipText("");
        ButtonAdd.setOpaque(false);
        ButtonAdd.setPreferredSize(new java.awt.Dimension(50, 25));
        ButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(ButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FieldMDP, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FieldNom, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(FieldPrenom, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(FieldAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(FieldNumTel, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))))
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldNumTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldMDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        this.dispose();
    }//GEN-LAST:event_ButtonCancelActionPerformed

    private void ButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(!FieldNom.getText().equals("") && !FieldPrenom.getText().equals("") && !FieldAdresse.getText().equals("") && !FieldNumTel.getText().equals("") && !FieldMDP.getText().equals(""))
        {
            boolean ok = true;
            String nom, nom2;
            String prenom, prenom2;
            
            nom = FieldNom.getText();
            prenom = FieldPrenom.getText();
            for (TechnicienExterieur ListeTechnicienExterieur1 : ListeTechnicienExterieur) {
                nom2 = ListeTechnicienExterieur1.getNom();
                prenom2 = ListeTechnicienExterieur1.getPrenom();
                if(nom.equals(nom2) && prenom.equals(prenom2))
                {    
                    JOptionPane.showMessageDialog(null, "Techicien déja existant", "Warning",JOptionPane.WARNING_MESSAGE);
                    ok = false;
                }
            }
            if(ok == true)
            {
                add();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Formulaire incomplet", "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ButtonAddActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(AddTechnicien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTechnicien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTechnicien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTechnicien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new AddTechnicien().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JTextField FieldAdresse;
    private javax.swing.JTextField FieldMDP;
    private javax.swing.JTextField FieldNom;
    private javax.swing.JTextField FieldNumTel;
    private javax.swing.JTextField FieldPrenom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
