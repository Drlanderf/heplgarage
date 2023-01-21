package AppGestion;

import Activites.*;
import static AppGestion.Accueil.*;
import Persistancedata.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class ListeTravaux extends javax.swing.JFrame {
    
    DefaultTableModel travaux;
    ArrayList<Entretien> ListeEntretienefini = new ArrayList<>();
    ArrayList<Reparation> ListeReparationenfini= new ArrayList<>();
    
    public ListeTravaux() {
        initComponents();
        
        try 
        {
            ListeEntretienefini = PersistanceBinaire.ChargerBinaire(chemin_entretienfini);
            ListeReparationenfini = PersistanceBinaire.ChargerBinaire(chemin_reparationfini);
        }
        catch(IOException | ClassNotFoundException e)
        {
                System.err.println(e.toString());
        }
        
        travaux = (DefaultTableModel)TableTravaux.getModel(); 

        ListeEntretienefini.forEach((item) -> 
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
        
        ListeReparationenfini.forEach((item) -> 
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTravaux = new javax.swing.JTable();
        ButtonOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage HEPL - Travaux terminés");

        jLabel1.setText("Travaux terminés :");

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
            TableTravaux.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        ButtonOk.setText("Ok");
        ButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(ButtonOk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(ButtonOk)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOkActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        this.dispose();
    }//GEN-LAST:event_ButtonOkActionPerformed

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
            java.util.logging.Logger.getLogger(ListeTravaux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListeTravaux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListeTravaux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListeTravaux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new ListeTravaux().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonOk;
    private javax.swing.JTable TableTravaux;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
