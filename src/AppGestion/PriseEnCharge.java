package AppGestion;

import Activites.*;
import static AppGestion.Accueil.*;
import Persistancedata.*;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PriseEnCharge extends javax.swing.JFrame {
    
    ArrayList<Entretien> ListeEntretien = new ArrayList<>();
    ArrayList<Reparation> ListeReparation = new ArrayList<>();
    ArrayList<Entretien> ListeEntretienencours = new ArrayList<>();
    ArrayList<Reparation> ListeReparationencours = new ArrayList<>();
    
    int id=0;
    int selectionpont = 0;
    boolean pontselection = false;
    boolean solselection = false;
    
    DefaultTableModel travaux;
    
    public PriseEnCharge() {
        initComponents();
        
        try 
        {
            ListeEntretien = PersistanceBinaire.ChargerBinaire(chemin_entretien);
            ListeReparation = PersistanceBinaire.ChargerBinaire(chemin_reparation);
            ListeEntretienencours = PersistanceBinaire.ChargerBinaire(chemin_entretienencours);
            ListeReparationencours = PersistanceBinaire.ChargerBinaire(chemin_reparationencours);
        }
        catch(IOException | ClassNotFoundException e)
        {
                System.err.println(e.toString());
        }
        
        travaux = (DefaultTableModel)TableTravaux.getModel(); 

        ListeEntretien.forEach((item) -> 
        {
            String voiture = item.getVoiture().getTypevoiturevehicule().getMarque() + " " + item.getVoiture().getTypevoiturevehicule().getModele()+ " " + item.getVoiture().getTypevoiturevehicule().getAnnee();
            String immatriculation = item.getVoiture().getImmatriculation();
            String proprietaire = item.getVoiture().getClientvehicule().getNom() + " " + item.getVoiture().getClientvehicule().getPrenom();
            String travail = item.getTypetravail();
            String travailaeffectuer = item.getTravailaeffectuer();
            String remarques = item.getInstructionssup();
            Entretien entretien = item;
            String idtmp = entretien.getId();
            Object[] data = {voiture, immatriculation, proprietaire, travail, travailaeffectuer, remarques, idtmp};
            travaux.addRow(data);
        });
        
        ListeReparation.forEach((item) -> 
        {
            String voiture = item.getVoiture().getTypevoiturevehicule().getMarque() + " " + item.getVoiture().getTypevoiturevehicule().getModele()+ " " + item.getVoiture().getTypevoiturevehicule().getAnnee();
            String immatriculation = item.getVoiture().getImmatriculation();
            String proprietaire = item.getVoiture().getClientvehicule().getNom() + " " + item.getVoiture().getClientvehicule().getPrenom();
            String travail = item.getTypetravail();
            String travailaeffectuer = item.getTravailaeffectuer();
            String remarques = item.getInstructionssup();
            Reparation reparation = item;
            String idtmp = reparation.getId();
            Object[] data = {voiture, immatriculation, proprietaire, travail, travailaeffectuer, remarques, idtmp};
            travaux.addRow(data);
        }); 
        setTitle(getTitle() + " : " + Utilisateurpersonnel);
    }
    
    boolean VerifierEmplacement(int pont)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        PersistanceTexte.createWithOutDelete(chemin_emplacementatelier); 
        String emplacementatelier = PersistanceTexte.Read(chemin_emplacementatelier);
        String[] parts = emplacementatelier.split("_");
        if(pont == 1)
        {
            if(parts[1].equals("0"))
            {
                return true;
            }
            return false;
        }
        if(pont == 2)
        {
            if(parts[4].equals("0"))
            {
                return true;
            }
            return false;
        }
        if(pont == 3)
        {
            if(parts[7].equals("0"))
            {
                return true;
            }
            return false;
        }
        if(pont == 4)
        {
            if(parts[10].equals("0"))
            {
                return true;
            }
            return false;
        }
        return false;
    }
    
    void Modifieremplacement(int choix, int choixemplacement, int id)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        PersistanceTexte.createWithOutDelete(chemin_emplacementatelier); 
        String emplacementatelier = PersistanceTexte.Read(chemin_emplacementatelier);
        String[] parts = emplacementatelier.split("_");
        String idt = valueOf(id);
        int emplacementtab = choixemplacement*3-2;
        if(choix == 1)
        {
            parts[emplacementtab] = idt;
            parts[emplacementtab+1] = "1";
        }
        else
        {
            parts[emplacementtab] = idt;
            parts[emplacementtab+1] = "2";
        }
        ArrayList<String> EmplacementAtelier = new ArrayList<>();
        
        EmplacementAtelier.addAll(Arrays.asList(parts));
        String emplacement="";
        for (String item : EmplacementAtelier)
        {
            emplacement = emplacement + item + "_";
        }
        PersistanceTexte.create(chemin_emplacementatelier);
        PersistanceTexte.Write(emplacement, chemin_emplacementatelier);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Pont = new javax.swing.JRadioButton();
        SelectionPont = new javax.swing.JComboBox();
        Sol = new javax.swing.JRadioButton();
        ButtonOk = new javax.swing.JButton();
        ButtonCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTravaux = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage HEPL - Prise en charge d'un travail");

        jLabel1.setText("Travaux en attente :");

        Pont.setText("Pont");
        Pont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PontActionPerformed(evt);
            }
        });

        SelectionPont.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        SelectionPont.setSelectedIndex(-1);
        SelectionPont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectionPontActionPerformed(evt);
            }
        });

        Sol.setText("Sol");
        Sol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SolActionPerformed(evt);
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

        TableTravaux.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Voiture", "Immatriculation", "Propriétaire", "Type travail", "Travail", "Remarques", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableTravaux.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TableTravaux);
        if (TableTravaux.getColumnModel().getColumnCount() > 0) {
            TableTravaux.getColumnModel().getColumn(6).setPreferredWidth(30);
            TableTravaux.getColumnModel().getColumn(6).setMaxWidth(30);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Pont))
                        .addGap(50, 50, 50)
                        .addComponent(SelectionPont, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Sol)
                            .addComponent(ButtonCancel)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addGap(30, 30, 30)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pont)
                    .addComponent(SelectionPont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sol))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonCancel)
                    .addComponent(ButtonOk))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelectionPontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectionPontActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        selectionpont = SelectionPont.getSelectedIndex()+1;
    }//GEN-LAST:event_SelectionPontActionPerformed

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);        
        this.dispose();
    }//GEN-LAST:event_ButtonCancelActionPerformed

    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOkActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(TableTravaux.getSelectedRow() != -1 && solselection != false || ( pontselection != false && selectionpont !=0))
        {
            if(VerifierEmplacement(selectionpont) == true)
            {
                if((TableTravaux.getValueAt(TableTravaux.getSelectedRow(), 3).toString()).equals("Entretien"))
                {   
                    Entretien entretien = new Entretien();
                    int idattente = parseInt(TableTravaux.getValueAt(TableTravaux.getSelectedRow(), 6).toString());
                    for (Entretien ListeEntretien1 : ListeEntretien) {
                        id = parseInt(ListeEntretien1.getId());
                        if (id == idattente) 
                        {
                            entretien = ListeEntretien1;
                        }
                    }
                    ListeEntretien.remove(entretien);
                    PersistanceBinaire.SauvegarderBinaire(ListeEntretien,chemin_entretien);
                    ListeEntretienencours.add(entretien);
                    PersistanceBinaire.SauvegarderBinaire(ListeEntretienencours,chemin_entretienencours);
                    Modifieremplacement(1, selectionpont, parseInt(entretien.getId()));
                }
                else
                {
                    Reparation reparation = new Reparation();
                    int idattente = parseInt(TableTravaux.getValueAt(TableTravaux.getSelectedRow(), 6).toString());
                    for (Reparation ListeReparation1 : ListeReparation) {
                        id = parseInt(ListeReparation1.getId());
                        if (id == idattente) 
                        {
                            reparation = ListeReparation1;
                        }
                    }
                    ListeReparation.remove(reparation);
                    PersistanceBinaire.SauvegarderBinaire(ListeReparation,chemin_reparation);
                    ListeReparationencours.add(reparation);
                    PersistanceBinaire.SauvegarderBinaire(ListeReparationencours,chemin_reparationencours);
                    Modifieremplacement(2, selectionpont, parseInt(reparation.getId()));
                }
                JOptionPane.showMessageDialog(null, "Travail ajouté à l'atelier", "Message",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "L'emplacement n'est pas disponible", "Warning",JOptionPane.WARNING_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Formulaire incomplet", "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ButtonOkActionPerformed

    private void PontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PontActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        Sol.setSelected(false);
        pontselection = true;
        solselection = false;
    }//GEN-LAST:event_PontActionPerformed

    private void SolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SolActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        Pont.setSelected(false);
        SelectionPont.setSelectedIndex(-1);
        selectionpont = 4;
        solselection = true;
        pontselection = false;
    }//GEN-LAST:event_SolActionPerformed

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
            java.util.logging.Logger.getLogger(PriseEnCharge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PriseEnCharge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PriseEnCharge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PriseEnCharge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new PriseEnCharge().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JButton ButtonOk;
    private javax.swing.JRadioButton Pont;
    private javax.swing.JComboBox SelectionPont;
    private javax.swing.JRadioButton Sol;
    private javax.swing.JTable TableTravaux;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
