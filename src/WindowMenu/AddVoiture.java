package WindowMenu;

import Vehicules.TypeVoiture;
import Vehicules.Voiture;
import Exceptions.MissingTradeMarkException;
import People.Client;
import Persistancedata.*;
import java.awt.event.*;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import static AppGestion.Accueil.*;

public class AddVoiture extends javax.swing.JFrame {
    
    boolean plaquebelge = false;
    ArrayList<TypeVoiture> ListeTypeVoiture = new ArrayList<>();
    ArrayList<Voiture> ListeVoiture = new ArrayList<>();
    ArrayList<Client> ListeClient = new ArrayList<>();
    
    public AddVoiture() 
    {
        initComponents();
        
        try 
        {
            ListeTypeVoiture = PersistanceBinaire.ChargerBinaire(chemin_typevoiture);
            ListeVoiture = PersistanceBinaire.ChargerBinaire(chemin_voiture);
            ListeClient = PersistanceBinaire.ChargerBinaire(chemin_client);  
        }
        catch(IOException | ClassNotFoundException e){
                System.err.println(e.toString());
        }
        
        DefaultComboBoxModel CB_Client = (DefaultComboBoxModel) this.Clients.getModel();
        ListeClient.forEach((item) -> 
        {
            CB_Client.addElement(item.getNom() + " " + item.getPrenom());
        });
        
        FieldAnnee.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyTyped(KeyEvent e) 
            {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) 
                {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        
        FieldNbPortes.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyTyped(KeyEvent e) 
            {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) 
                {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        Clients.setSelectedIndex(-1);
        setTitle(getTitle() + " : " + Utilisateurpersonnel);
    }
    
    void add() throws MissingTradeMarkException
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        Voiture voiture;
        TypeVoiture typevoiture;
        Client client;      

        typevoiture = new TypeVoiture(FieldTypeVoiture.getText(), FieldMarque.getText(), FieldModele.getText(), parseInt(FieldAnnee.getText()), parseInt(FieldNbPortes.getText()));
        int selectedIndex = Clients.getSelectedIndex();
        client = ListeClient.get(selectedIndex);
        voiture = new Voiture(typevoiture, client, FieldImmatriculation.getText().toUpperCase());
        ListeVoiture.add(voiture);
        ListeTypeVoiture.add(typevoiture);
        PersistanceBinaire.SauvegarderBinaire(ListeVoiture,chemin_voiture);
        PersistanceBinaire.SauvegarderBinaire(ListeTypeVoiture,chemin_typevoiture);
        
        JOptionPane.showMessageDialog(null, "Voiture ajouté", "Message",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Clients = new javax.swing.JComboBox();
        ButtonCancel = new javax.swing.JButton();
        ButtonAdd = new javax.swing.JButton();
        FieldTypeVoiture = new javax.swing.JTextField();
        FieldMarque = new javax.swing.JTextField();
        FieldModele = new javax.swing.JTextField();
        FieldAnnee = new javax.swing.JTextField();
        FieldNbPortes = new javax.swing.JTextField();
        PlaqueBelge = new javax.swing.JRadioButton();
        FieldImmatriculation = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ajouter une voiture");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Type de voiture :");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Marque");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Modèle");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Année :");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nombre de portes :");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Immatriculation :");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setPreferredSize(new java.awt.Dimension(25, 14));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Client :");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setPreferredSize(new java.awt.Dimension(25, 14));

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

        PlaqueBelge.setText("Plaque belge");
        PlaqueBelge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaqueBelgeActionPerformed(evt);
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
                .addComponent(ButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PlaqueBelge)
                    .addComponent(FieldMarque)
                    .addComponent(FieldModele)
                    .addComponent(FieldAnnee)
                    .addComponent(FieldNbPortes)
                    .addComponent(Clients, 0, 200, Short.MAX_VALUE)
                    .addComponent(FieldTypeVoiture, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FieldImmatriculation))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FieldTypeVoiture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FieldMarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(FieldModele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(FieldAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldNbPortes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldImmatriculation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PlaqueBelge)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Clients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        this.dispose();
    }//GEN-LAST:event_ButtonCancelActionPerformed

    private void ButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(!FieldTypeVoiture.getText().equals("") && !FieldMarque.getText().equals("") && !FieldModele.getText().equals("") && !FieldAnnee.getText().equals("") && !FieldNbPortes.getText().equals("") && !FieldImmatriculation.getText().equals("") && Clients.getSelectedIndex() != -1)
        {
            if(plaquebelge == false)
            {
                boolean ok = true;
                String immatriculation, immatriculation2;

                immatriculation = FieldImmatriculation.getText();

                for (Voiture ListeVoiture1 : ListeVoiture) {
                    immatriculation2 = ListeVoiture1.getImmatriculation();
                    if(immatriculation.equals(immatriculation2))
                    {    
                        JOptionPane.showMessageDialog(null, "Immatriculation déja existante", "Warning",JOptionPane.WARNING_MESSAGE);
                        ok = false;
                    }
                }
                if(ok == true)
                {
                    try {
                        add();
                    } catch (MissingTradeMarkException ex) {
                        Logger.getLogger(AddVoiture.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else
            {
                String immatriculation;
                immatriculation = FieldImmatriculation.getText().toUpperCase();
                if(verifierimmatriculation(immatriculation) == true)
                {
                    boolean ok = true;
                    String immatriculation2;

                    for (Voiture ListeVoiture1 : ListeVoiture) {
                        immatriculation2 = ListeVoiture1.getImmatriculation();
                        if(immatriculation.equals(immatriculation2))
                        {    
                            JOptionPane.showMessageDialog(null, "Immatriculation déja existante", "Warning",JOptionPane.WARNING_MESSAGE);
                            ok = false;
                        }
                    }
                    if(ok == true)
                    {
                        try {
                            add();
                        } catch (MissingTradeMarkException ex) {
                            Logger.getLogger(AddVoiture.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Format de plaque belge non respecté", "Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Formulaire incomplet", "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ButtonAddActionPerformed

    private void PlaqueBelgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlaqueBelgeActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       plaquebelge = !plaquebelge;
    }//GEN-LAST:event_PlaqueBelgeActionPerformed
   
    boolean verifierimmatriculation(String immatriculation)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        char [] immat = immatriculation.toCharArray();
        char [] lettres = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        if(immat.length != 9)
            return false;
        
        for(int i=0;i<immat.length;i++)
        {    
            char c = immat[i];
            switch(i)
            {
                case 0: 
                        if(c != '1' && c != '2')
                            return false;
                        break;
                    
                case 1: case 5:
                        if(c != '-')
                            return false;
                        break;
                    
                case 2: case 3: case 4:
                        for(int j = 0, dif = 0; j<lettres.length;j++)
                        {
                            if(c != lettres[j])
                            {
                                dif++;
                                if(dif == lettres.length)
                                    return false;
                            }
                        }
                        break;
                    
                case 6: case 7: case 8:
                        if(c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9')
                            return false;
                        break;
            }
        } 
        return true;
    }
    
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
            java.util.logging.Logger.getLogger(AddVoiture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddVoiture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddVoiture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddVoiture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new AddVoiture().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JComboBox Clients;
    private javax.swing.JTextField FieldAnnee;
    private javax.swing.JTextField FieldImmatriculation;
    private javax.swing.JTextField FieldMarque;
    private javax.swing.JTextField FieldModele;
    private javax.swing.JTextField FieldNbPortes;
    private javax.swing.JTextField FieldTypeVoiture;
    private javax.swing.JRadioButton PlaqueBelge;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
