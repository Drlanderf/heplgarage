package AppGestion;

import People.TechnicienExterieur;
import People.Employe;
import People.Mecanicien;
import Exceptions.ExceptionGarage;
import Persistancedata.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Authentification extends javax.swing.JFrame {
    
    public Properties usersproperties; 
    public Properties userstypeproperties; 
    public Properties configproperties; 
    public String chemin_users;
    public String chemin_userstype;
    public String chemin_mecanicien;
    public String chemin_employe;
    public String chemin_technicien;
    public String chemin_log;
    
    String selectionfonction ="";
    String fonction1 = "Technicien";
    String fonction2 = "Mecanicien";
    String fonction3 = "Employe";
    char [] fonction;
    
    ArrayList<Employe> ListeEmploye = new ArrayList<>();
    ArrayList<Mecanicien> ListeMecanicien = new ArrayList<>();
    ArrayList<TechnicienExterieur> ListeTechnicienExterieur = new ArrayList<>();
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Authentification()
    {
        initComponents();
        
        File config = new File("data/config.properties");
        if(!(config.exists()))
        {
            addconfig();
        }
        configproperties = PersistanceUsersProperties.ChargerUsersProperties("data/config.properties");
        chemin_users = configproperties.get("chemin_users").toString();
        chemin_userstype = configproperties.get("chemin_userstype").toString();
        chemin_mecanicien = configproperties.get("chemin_mecanicien").toString();
        chemin_employe = configproperties.get("chemin_employe").toString();
        chemin_technicien = configproperties.get("chemin_technicien").toString();
        chemin_log = configproperties.get("chemin_log").toString();
        
        File file = new File(chemin_users);
        if(!(file.exists()))
        {
            addpersonnel();
        }
                
        usersproperties = PersistanceUsersProperties.ChargerUsersProperties(chemin_users);
        userstypeproperties = PersistanceUsersProperties.ChargerUsersProperties(chemin_userstype);  
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MembrePersonnel = new javax.swing.JRadioButton();
        TechnicienExterieur = new javax.swing.JRadioButton();
        ButtonOk = new javax.swing.JButton();
        ButtonCancel = new javax.swing.JButton();
        Login = new javax.swing.JTextField();
        Password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage HEPL - Authentification d'un utilisateur");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setText("Utilisateur :");

        jLabel4.setText("Mot de passe :");

        MembrePersonnel.setText("Membre du personnel");
        MembrePersonnel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MembrePersonnelActionPerformed(evt);
            }
        });

        TechnicienExterieur.setText("Extérieur habilité");
        TechnicienExterieur.setToolTipText("");
        TechnicienExterieur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TechnicienExterieurActionPerformed(evt);
            }
        });

        ButtonOk.setText("Ok");
        ButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOkActionPerformed(evt);
            }
        });

        ButtonCancel.setText("Annuler");
        ButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelActionPerformed(evt);
            }
        });

        Password.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonOk, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(Password)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(MembrePersonnel)
                        .addGap(45, 45, 45)
                        .addComponent(TechnicienExterieur)
                        .addGap(44, 44, 44)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TechnicienExterieur)
                    .addComponent(MembrePersonnel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOkActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        try
        {
            if(usersproperties.get(Login.getText()) != null)
            {
                if (Arrays.equals(Password.getPassword(),usersproperties.get(Login.getText()).toString().toCharArray()))
                {
                        if(Arrays.equals((selectionfonction.toCharArray()),userstypeproperties.get(Login.getText()).toString().toCharArray()))
                        {
                            fenetreaccueuil(fonction1, Login.getText());
                        }
                        else
                        {
                            if(Arrays.equals(fonction1.toCharArray(),userstypeproperties.get(Login.getText()).toString().toCharArray()))
                            {
                                fenetrewarning();
                                fenetreaccueuil(fonction1, Login.getText());
                            }
                            else
                            {
                                if(selectionfonction.equals("Personnel"))
                                {
                                    if(Arrays.equals(fonction2.toCharArray(),userstypeproperties.get(Login.getText()).toString().toCharArray()))
                                    {
                                        fenetreaccueuil(fonction2, Login.getText());
                                    }
                                    else
                                    {
                                        fenetreaccueuil(fonction3, Login.getText());
                                    }
                                }
                                else
                                {
                                    if(Arrays.equals(fonction2.toCharArray(),userstypeproperties.get(Login.getText()).toString().toCharArray()))
                                    {
                                        fenetrewarning();
                                        fenetreaccueuil(fonction2, Login.getText());
                                    }
                                    else
                                    {
                                        fenetrewarning();
                                        fenetreaccueuil(fonction3, Login.getText());
                                    }
                                }
                            }
                        }
                }
                else
                {
                    throw new ExceptionGarage("Mauvais mot de passe");
                } 
            }
            else
            {
                this.dispose();
            }
        }
        catch(ExceptionGarage e)
        {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_ButtonOkActionPerformed

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        this.dispose();
    }//GEN-LAST:event_ButtonCancelActionPerformed

    private void TechnicienExterieurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TechnicienExterieurActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        MembrePersonnel.setSelected(false);
        selectionfonction = "Technicien";
    }//GEN-LAST:event_TechnicienExterieurActionPerformed

    private void MembrePersonnelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MembrePersonnelActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        TechnicienExterieur.setSelected(false);
        selectionfonction = "Personnel";
    }//GEN-LAST:event_MembrePersonnelActionPerformed

    public static void main(String args[]){
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new Authentification().setVisible(true);
        });
    }
    
    void fenetreaccueuil(String fonction, String Utilisateur)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Login.getText() + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        Accueil fenetreAccueil = new Accueil(fonction,Utilisateur);
        fenetreAccueil.setVisible(true);
        this.dispose();
    }
    
    void fenetrewarning()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Login.getText() + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        WrongSelectionLogin wrongselectionlogin = new WrongSelectionLogin(null, true);
        wrongselectionlogin.setVisible(true);
    }
    
    void addpersonnel()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        try 
        {
            ListeEmploye = PersistanceBinaire.ChargerBinaire(chemin_employe);
            ListeMecanicien = PersistanceBinaire.ChargerBinaire(chemin_mecanicien);
            ListeTechnicienExterieur = PersistanceBinaire.ChargerBinaire(chemin_technicien);
            Mecanicien mecanicien;
            mecanicien = new Mecanicien("Neyens", "Thomas",  "Rue Bierset,1", "0410101010",  1, "Neyens", "mecano");
            ListeMecanicien.add(mecanicien);
            
            Employe employe;
            employe = new Employe("Vandenryt", "Matthew",  "Rue de seraing, 1",  "0410101010",  2, "Vandenryt");
            ListeEmploye.add(employe);

            TechnicienExterieur Technicienexterieur;
            Technicienexterieur = new TechnicienExterieur("Caprasse", "François",  "Rue de seraing, 2",  "0410101010", "Caprasse");
            ListeTechnicienExterieur.add(Technicienexterieur);
            
            PersistanceBinaire.SauvegarderBinaire(ListeTechnicienExterieur,chemin_technicien);
            PersistanceBinaire.SauvegarderBinaire(ListeMecanicien, chemin_mecanicien);
            PersistanceBinaire.SauvegarderBinaire(ListeEmploye,chemin_employe);

            usersproperties = PersistanceUsersProperties.ChargerUsersProperties(chemin_users);
            userstypeproperties = PersistanceUsersProperties.ChargerUsersProperties(chemin_userstype);

            usersproperties.put(mecanicien.getNom()+mecanicien.getPrenom(), mecanicien.getMdp());
            userstypeproperties.put(mecanicien.getNom()+mecanicien.getPrenom(), mecanicien.getTypeemploye());
            
            usersproperties.put(employe.getNom()+employe.getPrenom(), employe.getMdp());
            userstypeproperties.put(employe.getNom()+employe.getPrenom(), employe.getTypeemploye());
            
            usersproperties.put(Technicienexterieur.getNom()+Technicienexterieur.getPrenom(), Technicienexterieur.getMdp());
            userstypeproperties.put(Technicienexterieur.getNom()+Technicienexterieur.getPrenom(), Technicienexterieur.getTypeemploye());

            PersistanceUsersProperties.SauvegarderUsersProperties(usersproperties,chemin_users);
            PersistanceUsersProperties.SauvegarderUsersProperties(userstypeproperties,chemin_userstype);
        }
        catch(IOException | ClassNotFoundException e)
        {
                System.err.println(e.toString());
        }
    }
    
    void addconfig()
    {
            configproperties = PersistanceUsersProperties.ChargerUsersProperties("data/config.properties");
            
            configproperties.put("ip_garage","127.0.0.1");
            configproperties.put("ip_centrale1","127.0.0.1");
            configproperties.put("ip_centrale2","127.0.0.1");
            configproperties.put("ip_centrale3","127.0.0.1");
            configproperties.put("port_centrale1","5010");
            configproperties.put("port_centrale2","5020");
            configproperties.put("port_centrale3","5030");
            configproperties.put("port_garagecommande","5000");
            configproperties.put("port_garagereception1","5001");
            configproperties.put("port_garagereception2","5002");
            configproperties.put("port_garagereception3","5003");
            
            configproperties.put("chemin_users","data/users.properties");
            configproperties.put("chemin_userstype","data/userstype.properties");
            
            configproperties.put("chemin_mecanicien","data/mecanicien.data");
            configproperties.put("chemin_employe","data/employe.data");
            configproperties.put("chemin_technicien","data/technicien.data");
            
            configproperties.put("chemin_client","data/client.data");
            configproperties.put("chemin_voiture","data/voiture.data");
            configproperties.put("chemin_typevoiture","data/typevoiture.data");
            
            configproperties.put("chemin_entretien","data/entretien.data");
            configproperties.put("chemin_reparation","data/reparation.datan");
            configproperties.put("chemin_entretienencours","data/entretienencours.data");
            configproperties.put("chemin_reparationencours","data/reparationencours.data");
            configproperties.put("chemin_entretienfini","data/entretienfini");
            configproperties.put("chemin_reparationfini","data/reparationfini.data");
            
            configproperties.put("chemin_emplacementatelier","data/emplacementatelier.txt");
            configproperties.put("chemin_clientbureau","data/clientbureau.txt");
            
            configproperties.put("chemin_travailaeffectuer","data/travailaeffectuer.txt");
            configproperties.put("chemin_travailaeffectuer2","data/travailaeffectuer2.txt");
            
            configproperties.put("chemin_commande","data/commande.txt");
            configproperties.put("chemin_commandeencours","data/commandeencours.txt");
            
            configproperties.put("chemin_facture","data/facture.txt");
            
            configproperties.put("chemin_log","log/log.log");
           
            PersistanceUsersProperties.SauvegarderUsersProperties(configproperties,"data/config.properties");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JButton ButtonOk;
    private javax.swing.JTextField Login;
    private javax.swing.JRadioButton MembrePersonnel;
    private javax.swing.JPasswordField Password;
    private javax.swing.JRadioButton TechnicienExterieur;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
