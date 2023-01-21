package AppGestion;

import People.Client;
import People.Mecanicien;
import Activites.*;
import static AppGestion.Accueil.*;
import Persistancedata.*;
import Vehicules.Voiture;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;

public class APrevoir extends javax.swing.JFrame {

    ArrayList<Mecanicien> ListeMecanicien = new ArrayList<>();
    ArrayList<Client> ListeClient = new ArrayList<>();
    ArrayList<Voiture> ListeVoiture = new ArrayList<>();
    ArrayList<Entretien> ListeEntretien = new ArrayList<>();
    ArrayList<Reparation> ListeReparation = new ArrayList<>();
    ArrayList<String> ListeTravailAEffectuer = new ArrayList<>();
    ArrayList<String> ListeTravailAEffectuer2 = new ArrayList<>();
    ArrayList<Entretien> ListeEntretienencours = new ArrayList<>();
    ArrayList<Entretien> ListeEntretienfini = new ArrayList<>();
    ArrayList<Reparation> ListeReparationencours = new ArrayList<>();
    ArrayList<Reparation> ListeReparationfini = new ArrayList<>();
    
    Mecanicien mecanicienactuel;
    String typetravail="";
    int id1=0;
    int id2=0;
    boolean ready = false;
    boolean button1 = false;
    boolean button2 = false;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public APrevoir() {
        initComponents();
        
        try 
        {
            ListeClient = PersistanceBinaire.ChargerBinaire(chemin_client);  
            ListeVoiture = PersistanceBinaire.ChargerBinaire(chemin_voiture);
            ListeEntretien = PersistanceBinaire.ChargerBinaire(chemin_entretien);
            ListeReparation = PersistanceBinaire.ChargerBinaire(chemin_reparation);
            ListeMecanicien = PersistanceBinaire.ChargerBinaire(chemin_mecanicien);
            PersistanceTexte.createWithOutDelete(chemin_travailaeffectuer);
            String travailaeffectuer = PersistanceTexte.Read(chemin_travailaeffectuer);
            PersistanceTexte.createWithOutDelete(chemin_travailaeffectuer2);
            String travailaeffectuer2 = PersistanceTexte.Read(chemin_travailaeffectuer2);
            ListeEntretienencours = PersistanceBinaire.ChargerBinaire(chemin_entretienencours);
            ListeEntretienfini = PersistanceBinaire.ChargerBinaire(chemin_entretienfini);
            ListeReparationencours = PersistanceBinaire.ChargerBinaire(chemin_reparationencours);
            ListeReparationfini = PersistanceBinaire.ChargerBinaire(chemin_reparationfini);
            
            if(travailaeffectuer.isEmpty())
            {
                AddListeTravail();
            }
            else
            {
                
                String[] parts = travailaeffectuer.split("_");
                ListeTravailAEffectuer.addAll(Arrays.asList(parts));
            }
            if(travailaeffectuer2.isEmpty())
            {
                AddListeTravail2();
            }
            else
            {
                String[] parts2 = travailaeffectuer2.split("_");
                ListeTravailAEffectuer2.addAll(Arrays.asList(parts2));
            }
        }
        catch(IOException | ClassNotFoundException e)
        {
                System.err.println(e.toString());
        }
        
        DefaultComboBoxModel CB_Voiture = (DefaultComboBoxModel) this.Voiture.getModel();
	ListeVoiture.forEach((item) -> 
        {
            CB_Voiture.addElement(item.getTypevoiturevehicule().getMarque() + " " + item.getTypevoiturevehicule().getModele() + " " + item.getTypevoiturevehicule().getAnnee());
        });
        
        DefaultComboBoxModel CB_Client = (DefaultComboBoxModel) this.Proprietaire.getModel();
	ListeClient.forEach((item) -> 
        {
            CB_Client.addElement(item.getNom() + " " + item.getPrenom());
        });
        
        Voiture.setSelectedIndex(-1);     
        Proprietaire.setSelectedIndex(-1);
        TypeTravailAEffectuer.setSelectedIndex(-1);
            
        for (Mecanicien ListeMecanicien1 : ListeMecanicien) 
        {
            String Utilisateur2 = ListeMecanicien1.getNom() + ListeMecanicien1.getPrenom();
            if (Utilisateurpersonnel.equals(Utilisateur2)) 
            {
                mecanicienactuel = ListeMecanicien1;
            }
        }
        
        setTitle(getTitle() + " : " + Utilisateurpersonnel);
        
        ready = true;
    }
    void addtravail(int choix)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        Voiture voiture;
        Mecanicien mecanicientravail;
        
        int selectedIndex = Voiture.getSelectedIndex();
        int selectedIndex2 = Proprietaire.getSelectedIndex();
        
        voiture = ListeVoiture.get(selectedIndex);
        mecanicientravail =  mecanicienactuel;
        String TravailAEffectuer = TypeTravailAEffectuer.getSelectedItem().toString();
        if(choix == 1)
        {
            Entretien entretien;
            entretien = new Entretien(mecanicientravail, voiture, Instructions.getText(), TravailAEffectuer);
            
            if(!ListeEntretien.isEmpty() || !ListeEntretienencours.isEmpty() || !ListeEntretienfini.isEmpty())
            {
                int ide1 = 0,ide2 = 0,ide3 = 0;
                
                if(!ListeEntretien.isEmpty())
                {
                    ide1 = Integer.parseInt((ListeEntretien.get(ListeEntretien.size()-1).getId()))+1;
                }
                
                if(!ListeEntretienencours.isEmpty())
                {
                    for (Entretien ListeEntretienencours1 : ListeEntretienencours) 
                    {
                        int idtmp = Integer.parseInt(ListeEntretienencours1.getId());
                        if (idtmp > ide2) 
                        {
                            ide2 = idtmp;
                        }
                    }
                    ide2++;
                }
                
                if(!ListeEntretienfini.isEmpty())
                {
                    for (Entretien ListeEntretienfini1 : ListeEntretienfini) 
                    {
                        int idtmp = Integer.parseInt(ListeEntretienfini1.getId());
                        if (idtmp > ide3) 
                        {
                            ide3 = idtmp;
                        }
                    }
                    ide3++;
                }
                id1 = PlusGrand(ide1, ide2, ide3); 
            }
            else
            {
                id1=1;
            }
            String identretien = String.valueOf(id1);
            entretien.setId(identretien);
            ListeEntretien.add(entretien);
            PersistanceBinaire.SauvegarderBinaire(ListeEntretien,chemin_entretien);
        }
        else
        {
            Reparation reparation;
            reparation = new Reparation(mecanicientravail, voiture, Instructions.getText(), TravailAEffectuer);

            if(!ListeReparation.isEmpty() || !ListeReparationencours.isEmpty() || !ListeReparationfini.isEmpty())
            {
                int ide1 = 0,ide2 = 0,ide3 = 0;
                
                if(!ListeReparation.isEmpty())
                {
                   ide1 = Integer.parseInt((ListeReparation.get(ListeReparation.size()-1).getId()))+1;
                }
                
                if(!ListeReparationencours.isEmpty())
                {
                   for (Reparation ListeReparationencours1 : ListeReparationencours) 
                    {
                        int idtmp = Integer.parseInt(ListeReparationencours1.getId());
                        if (idtmp > ide2) 
                        {
                            ide2 = idtmp;
                        }
                    }
                    ide2++;
                }
                
                if(!ListeReparationfini.isEmpty())
                {
                   for (Reparation ListeReparationfini1 : ListeReparationfini) 
                    {
                        int idtmp = Integer.parseInt(ListeReparationfini1.getId());
                        if (idtmp > ide3) 
                        {
                            ide3 = idtmp;
                        }
                    }
                    ide3++;
                }
                id2 = PlusGrand(ide1, ide2, ide3); 
            }
            else
            {
                id2=1;
            }
            String idreparation = String.valueOf(id2);
            reparation.setId(idreparation);
            
            ListeReparation.add(reparation);
            PersistanceBinaire.SauvegarderBinaire(ListeReparation,chemin_reparation);
        }
        JOptionPane.showMessageDialog(null, "Travail ajouté", "Message",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
    
    int PlusGrand(int ide1, int ide2, int ide3)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(ide1 > ide2 && ide1 > ide3)
        {
            return ide1;
        }
        if(ide2 > ide1 && ide2 > ide3)
        {
            return ide2;
        }
        if(ide3 > ide1 && ide3 > ide2)
        {
            return ide3;
        }
        return -1;
    }
    
    void changeindex(int index, int choix)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(choix == 1)
        {
            Proprietaire.setSelectedIndex(index);
        }
        else
        {
            Voiture.setSelectedIndex(index);
        }
    }
    
    void AddListeTravail()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        ListeTravailAEffectuer.add("Petit entretien");
        ListeTravailAEffectuer.add("Gros entretien");
        ListeTravailAEffectuer.add("Vidange");
        ListeTravailAEffectuer.add("Essuie-glaces");
        ListeTravailAEffectuer.add("Ampoule");
        ListeTravailAEffectuer.add("Nettoyage");
        ListeTravailAEffectuer.add("Batterie");
            
        String Travailaeffectuer = "";
        PersistanceTexte.create(chemin_travailaeffectuer);
        for (String item : ListeTravailAEffectuer)
        {
            Travailaeffectuer = Travailaeffectuer + item + "_";
        }
        PersistanceTexte.Write(Travailaeffectuer, chemin_travailaeffectuer);
    }
    void AddListeTravail2()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        ListeTravailAEffectuer2.add("Aile");
        ListeTravailAEffectuer2.add("Bas de caisse");
        ListeTravailAEffectuer2.add("Capot");
        ListeTravailAEffectuer2.add("Hayon/Coffre");
        ListeTravailAEffectuer2.add("Jante");
        ListeTravailAEffectuer2.add("Pare-chocs");
        ListeTravailAEffectuer2.add("Phare");
        ListeTravailAEffectuer2.add("Rétroviseur");
        ListeTravailAEffectuer2.add("Portière");
        ListeTravailAEffectuer2.add("Intérieur");
            
        String Travailaeffectuer2 = "";
        PersistanceTexte.create(chemin_travailaeffectuer2);
        for (String item : ListeTravailAEffectuer2)
        {
            Travailaeffectuer2 = Travailaeffectuer2 + item + "_";
        }
        PersistanceTexte.Write(Travailaeffectuer2, chemin_travailaeffectuer2);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Voiture = new javax.swing.JComboBox();
        Proprietaire = new javax.swing.JComboBox();
        TypeTravailAEffectuer = new javax.swing.JComboBox();
        Immatriculation = new javax.swing.JTextField();
        Instructions = new javax.swing.JTextField();
        Entretien = new javax.swing.JRadioButton();
        Reparation = new javax.swing.JRadioButton();
        ButtonOk = new javax.swing.JButton();
        ButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage HEPL - Nouveau travail pour l'atelier");

        jLabel1.setText("Voiture :");

        jLabel2.setText("Immatriculation :");

        jLabel3.setText("Propriétaire :");

        jLabel4.setText("Type de travail :");

        jLabel5.setText("Instructions particulières :");

        Voiture.setToolTipText("");
        Voiture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoitureActionPerformed(evt);
            }
        });

        Proprietaire.setToolTipText("");
        Proprietaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProprietaireActionPerformed(evt);
            }
        });

        Immatriculation.setEditable(false);

        Entretien.setText("Entretien");
        Entretien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntretienActionPerformed(evt);
            }
        });

        Reparation.setText("Réparation");
        Reparation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReparationActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonOk)
                .addGap(51, 51, 51)
                .addComponent(ButtonCancel)
                .addGap(167, 167, 167))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(Entretien)
                        .addGap(100, 100, 100)
                        .addComponent(Reparation))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(Instructions, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Immatriculation)
                                    .addComponent(Proprietaire, 0, 295, Short.MAX_VALUE)
                                    .addComponent(TypeTravailAEffectuer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Voiture, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Voiture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Immatriculation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Proprietaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Entretien)
                    .addComponent(Reparation))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TypeTravailAEffectuer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(Instructions, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonCancel)
                    .addComponent(ButtonOk))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VoitureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoitureActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(ready == true)
        {
            int selectedIndex = Voiture.getSelectedIndex();
            int numclient, numclient2;

            Voiture voiture;
            voiture = ListeVoiture.get(selectedIndex);
            Immatriculation.setText(voiture.getImmatriculation());

            numclient = voiture.getClientvehicule().getNumclient();
            int indexclient=0;
            for(int i = 0; i < ListeClient.size() ; i++)
            {
                numclient2 = ListeClient.get(i).getNumclient();
                if(numclient == numclient2)
                {    
                    indexclient = i;
                    changeindex(indexclient,1);
                }
            }
        }
    }//GEN-LAST:event_VoitureActionPerformed

    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOkActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(Voiture.getSelectedIndex() != -1 && !Immatriculation.getText().equals("") && Proprietaire.getSelectedIndex() != -1 && TypeTravailAEffectuer.getSelectedIndex() != -1 && !Instructions.getText().equals("") && !typetravail.equals(""))
        {
            if(typetravail.equals("Entretien"))
            {
                addtravail(1);
            }
            else
            {
                addtravail(2);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Formulaire incomplet", "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ButtonOkActionPerformed

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        this.dispose();
    }//GEN-LAST:event_ButtonCancelActionPerformed

    private void ReparationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReparationActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        button2 = !button2;
        if(typetravail.equals("Entretien"))
        {
            Entretien.setSelected(false);
            button1 = !button1;
        }
        viderCB();
        Addelement(2,button2);
    }//GEN-LAST:event_ReparationActionPerformed

    private void EntretienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntretienActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        button1 = !button1;
        if(typetravail.equals("Reparation"))
        {
            Reparation.setSelected(false);
            button2 = !button2;
        }
        viderCB();
        Addelement(1,button1);
    }//GEN-LAST:event_EntretienActionPerformed

    private void ProprietaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProprietaireActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(ready == true)
        {
            int selectedIndex = Proprietaire.getSelectedIndex();
            int numclient, numclient2;

            Client client;
            client = ListeClient.get(selectedIndex);

            numclient = client.getNumclient();
            int indexvoiture=0;
            for(int i = 0; i < ListeVoiture.size() ; i++)
            {
                numclient2 = ListeVoiture.get(i).getClientvehicule().getNumclient();
                if(numclient == numclient2)
                {    
                    indexvoiture = i;
                    changeindex(indexvoiture,2);
                }
            }
        }
    }//GEN-LAST:event_ProprietaireActionPerformed

    void Addelement(int choix, boolean button)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(choix == 1 && button == true)
        {
            typetravail = "Entretien";
            DefaultComboBoxModel CB_TypeTravailAEffectuer = (DefaultComboBoxModel) this.TypeTravailAEffectuer.getModel();
            ListeTravailAEffectuer.forEach((item) -> 
            {
                CB_TypeTravailAEffectuer.addElement(item);
            });
        }
        else
        {
            if(choix == 2 && button == true)
            {
                typetravail = "Reparation";
                DefaultComboBoxModel CB_TypeTravailAEffectuer = (DefaultComboBoxModel) this.TypeTravailAEffectuer.getModel();
                ListeTravailAEffectuer2.forEach((item) -> 
                {
                    CB_TypeTravailAEffectuer.addElement(item);
                });
            }
        }
    }
    
    void viderCB()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        DefaultComboBoxModel CB_TypeTravailAEffectuer = (DefaultComboBoxModel) this.TypeTravailAEffectuer.getModel();
        CB_TypeTravailAEffectuer.removeAllElements();
        typetravail = "";
    }
    
    
    public void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new APrevoir().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JButton ButtonOk;
    private javax.swing.JRadioButton Entretien;
    private javax.swing.JTextField Immatriculation;
    private javax.swing.JTextField Instructions;
    private javax.swing.JComboBox Proprietaire;
    private javax.swing.JRadioButton Reparation;
    private javax.swing.JComboBox TypeTravailAEffectuer;
    private javax.swing.JComboBox Voiture;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
