package AppGestion;

import WindowMenu.AddMecanicien;
import WindowMenu.ListeMecaniciens;
import WindowMenu.ListeVoitures;
import WindowMenu.ListeEmployes;
import WindowMenu.AddTechnicien;
import WindowMenu.AddEmploye;
import WindowMenu.ListeClients;
import WindowMenu.ListeTechniciens;
import WindowMenu.AddClient;
import WindowMenu.AddVoiture;
import Activites.*;

import People.Client;
import Persistancedata.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

public class Accueil extends javax.swing.JFrame {
    
    public static Properties configproperties;
    public static String chemin_users;
    public static String chemin_userstype;
    public static String chemin_mecanicien;
    public static String chemin_employe;
    public static String chemin_technicien;
    public static String chemin_client;
    public static String chemin_voiture;
    public static String chemin_typevoiture;
    public static String chemin_entretien;
    public static String chemin_reparation;
    public static String chemin_entretienencours;
    public static String chemin_reparationencours;
    public static String chemin_entretienfini;
    public static String chemin_reparationfini;
    public static String chemin_emplacementatelier;
    public static String chemin_clientbureau;
    public static String chemin_travailaeffectuer;
    public static String chemin_travailaeffectuer2;
    public static String chemin_log;
    public static String fonctionpersonnel = "";
    public static String Utilisateurpersonnel = "";
    public static String chemin_commande;
    public static String chemin_facture;
    
    ArrayList<Entretien> ListeEntretienencours = new ArrayList<>();
    ArrayList<Reparation> ListeReparationencours = new ArrayList<>();
    ArrayList<Entretien> ListeEntretienfini = new ArrayList<>();
    ArrayList<Reparation> ListeReparationfini = new ArrayList<>();
    ArrayList<String> EmplacementAtelier = new ArrayList<>();
    ArrayList<Client> ListeClient = new ArrayList<>();
    
    int selectedemplacement=0;
    boolean emplacementvide1=true, emplacementvide2=true, emplacementvide3=true, emplacementvide4=true;
    boolean patrondispo=true;
    
    Thread ThreadDate;
    String dateLocal = "France";
    String dateFormat = "Format court";
    String tempsFormat = "Format court";
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Accueil(String fonction, String Utilisateur) {
        fonctionpersonnel = fonction;
        Utilisateurpersonnel = Utilisateur;
        initComponents();
        initComponents2();
        
        configproperties = PersistanceUsersProperties.ChargerUsersProperties("data/config.properties");
        chemin_users = configproperties.get("chemin_users").toString();
        chemin_userstype = configproperties.get("chemin_userstype").toString();
        chemin_mecanicien = configproperties.get("chemin_mecanicien").toString();
        chemin_employe = configproperties.get("chemin_employe").toString();
        chemin_technicien = configproperties.get("chemin_technicien").toString();
        chemin_client = configproperties.get("chemin_client").toString();
        chemin_voiture = configproperties.get("chemin_voiture").toString();
        chemin_typevoiture = configproperties.get("chemin_typevoiture").toString();
        chemin_entretien = configproperties.get("chemin_entretien").toString();
        chemin_reparation = configproperties.get("chemin_reparation").toString();
        chemin_entretienencours = configproperties.get("chemin_entretienencours").toString();
        chemin_reparationencours = configproperties.get("chemin_reparationencours").toString();
        chemin_entretienfini = configproperties.get("chemin_entretienfini").toString();
        chemin_reparationfini = configproperties.get("chemin_reparationfini").toString();
        chemin_emplacementatelier = configproperties.get("chemin_emplacementatelier").toString();
        chemin_clientbureau = configproperties.get("chemin_clientbureau").toString();
        chemin_travailaeffectuer = configproperties.get("chemin_travailaeffectuer").toString();
        chemin_travailaeffectuer2 = configproperties.get("chemin_travailaeffectuer2").toString();
        chemin_log = configproperties.get("chemin_log").toString();
        chemin_commande = configproperties.get("chemin_commande").toString();
        chemin_facture = configproperties.get("chemin_facture").toString();
        setTitle(getTitle() + " : " + fonction + " " + Utilisateurpersonnel);

        SetPontEmplacement();
        personnelpresent();
        SetClientBureau(); 
        AfficherDate();
    }
    
    void AfficherDate()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        ThreadDate = new Thread()
        {
            @Override
            @SuppressWarnings("SleepWhileInLoop")
            public void run()
            {
                while(true)
                {
                    switch(dateLocal) 
                    {
			case "France":
                            if(dateFormat.equals("Format court"))
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, Locale.FRANCE).format(new Date()));
                                }
                            }
                            else
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.FRANCE).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.FRANCE).format(new Date()));
                                }
                            }
                            break;
			case "UK":
                            if(dateFormat.equals("Format court"))
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.UK).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, Locale.UK).format(new Date()));
                                }
                            }
                            else
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.UK).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.UK).format(new Date()));
                                }
                            }
                            break;
			case "Allemagne":
                            if(dateFormat.equals("Format court"))
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.GERMANY).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, Locale.GERMANY).format(new Date()));
                                }
                            }
                            else
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.GERMANY).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.GERMANY).format(new Date()));
                                }
                            }
                            break;
			case "Italie":
                            if(dateFormat.equals("Format court"))
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, Locale.ITALY).format(new Date()));
                                }
                            }
                            else
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.ITALY).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.ITALY).format(new Date()));
                                }
                            }
                            break;
			case "US":
                            if(dateFormat.equals("Format court"))
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, Locale.US).format(new Date()));
                                }
                            }
                            else
                            {
                                if(tempsFormat.equals("Format court"))
                                {

                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.US).format(new Date()) + " h");
                                }
                                else
                                {
                                    jLabel11.setText(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.US).format(new Date()));
                                }
                            }
                            break;
                    }
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
    
    void SetPontEmplacement()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        PersistanceTexte.createWithOutDelete(chemin_emplacementatelier); 
        String emplacementatelier = PersistanceTexte.Read(chemin_emplacementatelier);
        String id="";
        String pont="";
        if(emplacementatelier.isEmpty())
        {
            EmplacementAtelier.add("1");
            EmplacementAtelier.add("0");
            EmplacementAtelier.add("0");
            EmplacementAtelier.add("2");
            EmplacementAtelier.add("0");
            EmplacementAtelier.add("0");
            EmplacementAtelier.add("3");
            EmplacementAtelier.add("0");
            EmplacementAtelier.add("0");
            EmplacementAtelier.add("4");
            EmplacementAtelier.add("0");
            EmplacementAtelier.add("0");
            String valeuremplacement = "";
            PersistanceTexte.create(chemin_emplacementatelier);
            for (String item : EmplacementAtelier)
            {
                valeuremplacement = valeuremplacement + item + "_";
            }
            PersistanceTexte.Write(valeuremplacement, chemin_emplacementatelier);
            EmplacementPont1.setText("--Libre--");
            EmplacementPont2.setText("--Libre--");
            EmplacementPont3.setText("--Libre--");
            EmplacementSol.setText("--Libre--");
        }
        else
        {
            String[] parts = emplacementatelier.split("_");
            if(parts[1].equals("0"))
            {
                EmplacementPont1.setText("--Libre--");
                emplacementvide1=true;
            }
            else
            {
                id = parts[1];
                if(parts[2].equals("1"))
                {
                    pont = ChercherEntretien(id,1);
                }
                else
                {
                    pont = ChercherReparation(id,1);
                }
                EmplacementPont1.setText(pont);
                emplacementvide1=false;
            }
            if(parts[4].equals("0"))
            {
                EmplacementPont2.setText("--Libre--");
                emplacementvide2=true;
            }
            else
            {
                id = parts[4];
                if(parts[5].equals("1"))
                {
                    pont = ChercherEntretien(id,1);
                }
                else
                {
                    pont = ChercherReparation(id,1);
                }
                EmplacementPont2.setText(pont);
                emplacementvide2=false;
            }
            if(parts[7].equals("0"))
            {
                EmplacementPont3.setText("--Libre--");
                emplacementvide3=true;
            }
            else
            {
                id = parts[7];
                if(parts[8].equals("1"))
                {
                    pont = ChercherEntretien(id,1);
                }
                else
                {
                    pont = ChercherReparation(id,1);
                }
                EmplacementPont3.setText(pont);
                emplacementvide3=false;
            }
            if(parts[10].equals("0"))
            {
                EmplacementSol.setText("--Libre--");
                emplacementvide4=true;
            }
            else
            {
                id = parts[10];
                if(parts[11].equals("1"))
                {
                    pont = ChercherEntretien(id,1);
                }
                else
                {
                    pont = ChercherReparation(id,1);
                }
                EmplacementSol.setText(pont);
                emplacementvide4=false;
            }
        }
    }
    
    String ChercherEntretien(String id, int choix)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        String id2="";
        
        try 
        {
            ListeEntretienencours = PersistanceBinaire.ChargerBinaire(chemin_entretienencours);
        }
        catch(IOException | ClassNotFoundException e)
        {
                System.err.println(e.toString());
        }
        
        if(choix == 1)
        {
            String textemplacement="";
            String immat="";
            String marque="";
            String modele="";
            int annee=0;
            String travailaeffectuer="";
            
            for (Entretien ListeEntretienencours1 : ListeEntretienencours) 
            {
                id2 = ListeEntretienencours1.getId();
                if(id.equals(id2))
                {   
                    immat = ListeEntretienencours1.getVoiture().getImmatriculation();
                    marque = ListeEntretienencours1.getVoiture().getTypevoiturevehicule().getMarque();
                    modele = ListeEntretienencours1.getVoiture().getTypevoiturevehicule().getModele();
                    annee = ListeEntretienencours1.getVoiture().getTypevoiturevehicule().getAnnee();
                    travailaeffectuer = ListeEntretienencours1.getTravailaeffectuer();
                }
            }
            textemplacement = marque + " " + modele + " " + annee + " (" + immat + ")  ->" + travailaeffectuer;
            return textemplacement;
        }
        else
        {
            String info="";
            for (Entretien ListeEntretienencours1 : ListeEntretienencours) 
            {
                id2 = ListeEntretienencours1.getId();
                if(id.equals(id2))
                {   
                    info = ListeEntretienencours1.getInstructionssup();
                }
            }
            return info;
        }
    }
    String ChercherReparation(String id,int choix)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        String id2="";
        try 
        {
            ListeReparationencours = PersistanceBinaire.ChargerBinaire(chemin_reparationencours);
        }
        catch(IOException | ClassNotFoundException e)
        {
                System.err.println(e.toString());
        }
        
        if(choix == 1)
        {
            String textemplacement="";
            String immat="";
            String marque="";
            String modele="";
            int annee=0;
            String travailaeffectuer="";
            
            for (Reparation ListeReparationencours1 : ListeReparationencours) 
            {
                id2 = ListeReparationencours1.getId();
                if(id.equals(id2))
                {   
                    immat = ListeReparationencours1.getVoiture().getImmatriculation();
                    marque = ListeReparationencours1.getVoiture().getTypevoiturevehicule().getMarque();
                    modele = ListeReparationencours1.getVoiture().getTypevoiturevehicule().getModele();
                    annee = ListeReparationencours1.getVoiture().getTypevoiturevehicule().getAnnee();
                    travailaeffectuer = ListeReparationencours1.getTravailaeffectuer();
                }
            }
            textemplacement = marque + " " + modele + " " + annee + " (" + immat + ")  ->" + travailaeffectuer;
            return textemplacement;
        }
        else
        {
            String info="";
            for (Reparation ListeReparationencours1 : ListeReparationencours) 
            {
                id2 = ListeReparationencours1.getId();
                if(id.equals(id2))
                {   
                    info = ListeReparationencours1.getInstructionssup();
                }
            }
            return info;
        }
    }
    
    void RemplirInfoEmplacement(int emplacement)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        String idinfo="";
        String info="";
        String typetravail="";
        
        PersistanceTexte.createWithOutDelete(chemin_emplacementatelier); 
        String emplacementatelier = PersistanceTexte.Read(chemin_emplacementatelier);
        String[] parts = emplacementatelier.split("_");
        
        try 
        {
            ListeEntretienencours = PersistanceBinaire.ChargerBinaire(chemin_entretienencours);
            ListeReparationencours = PersistanceBinaire.ChargerBinaire(chemin_reparationencours);
        }
        catch(IOException | ClassNotFoundException e)
        {
                System.err.println(e.toString());
        }
        
        int emplacementtab = emplacement*3-2;
        idinfo = parts[emplacementtab];
        typetravail = parts[emplacementtab+1];
        
        switch(emplacement)
        {
            case 1: case 2: case 3:
                    if(typetravail.equals("1"))
                    {
                        info = ChercherEntretien(idinfo,2);
                    }
                    else
                    {
                        if(typetravail.equals("2"))
                            info = ChercherReparation(idinfo,2);
                    }
                    EmplacementInformations.setText("Pont " + valueOf(emplacement) + " : " + info);
                    break;
            case 4:
                    if(typetravail.equals("1"))
                    {
                        info = ChercherEntretien(idinfo,2);
                    }
                    else
                    {
                        if(typetravail.equals("2"))
                            info = ChercherReparation(idinfo,2);
                    }
                    EmplacementInformations.setText("Sol : " + info);
                    break;
        }
    }
    
    void SetClientBureau()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        PersistanceTexte.createWithOutDelete(chemin_clientbureau); 
        String clientbureau = PersistanceTexte.Read(chemin_clientbureau);
        if(clientbureau.isEmpty())
        {
            clientbureau="0";
            PersistanceTexte.create(chemin_clientbureau);
            PersistanceTexte.Write(clientbureau, chemin_clientbureau);
            BureauClient.setText("--Libre--");
        }
        else
        {
            if(clientbureau.equals("0"))
            {
                BureauClient.setText("--Libre--");
                PatronDispo.setSelected(true);
                patrondispo=true;
            }
            else
            {
                
                String client = ChercherClient(clientbureau);
                BureauClient.setText(client);
                PatronDispo.setSelected(false);
                patrondispo=false;
            }
        }
    }
    String ChercherClient(String numclient)
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        String client="";
        String numclient2="";
        try 
        {
            ListeClient = PersistanceBinaire.ChargerBinaire(chemin_client);
        }
        catch(IOException | ClassNotFoundException e)
        {
                System.err.println(e.toString());
        }
        
        for (Client ListeClient1 : ListeClient) 
            {
                numclient2 = valueOf(ListeClient1.getNumclient());
                if(numclient.equals(numclient2))
                {   
                    client = ListeClient1.getNom() + " " + ListeClient1.getPrenom();
                }
            }
        
        return client;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        PatronDispo = new javax.swing.JCheckBox();
        PauseMidi = new javax.swing.JCheckBox();
        PersonnelPresent = new javax.swing.JRadioButton();
        PersonnelAbsent = new javax.swing.JRadioButton();
        EmplacementPont1 = new javax.swing.JTextField();
        EmplacementPont2 = new javax.swing.JTextField();
        EmplacementPont3 = new javax.swing.JTextField();
        EmplacementSol = new javax.swing.JTextField();
        EmplacementInformations = new javax.swing.JTextField();
        BureauClient = new javax.swing.JTextField();
        BureauCompta = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Atelier = new javax.swing.JMenu();
        APrevoir = new javax.swing.JMenuItem();
        PriseEnCharge = new javax.swing.JMenuItem();
        TerminerTravail = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        ListeTravaux = new javax.swing.JMenuItem();
        Matériel = new javax.swing.JMenu();
        Commander = new javax.swing.JMenu();
        CommanderPiece = new javax.swing.JMenuItem();
        CommanderPneu = new javax.swing.JMenuItem();
        CommanderLubrifiant = new javax.swing.JMenuItem();
        ReceiveCommande = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        ListeCommandes = new javax.swing.JMenuItem();
        Facture = new javax.swing.JMenu();
        AddFacture = new javax.swing.JMenuItem();
        ListeFactures = new javax.swing.JMenuItem();
        Add = new javax.swing.JMenu();
        AddMecanicien = new javax.swing.JMenuItem();
        AddEmploye = new javax.swing.JMenuItem();
        AddTechnicien = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        AddClient = new javax.swing.JMenuItem();
        AddVoiture = new javax.swing.JMenuItem();
        Liste = new javax.swing.JMenu();
        ListeMecanicien = new javax.swing.JMenuItem();
        ListeEmploye = new javax.swing.JMenuItem();
        ListeTechnicien = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        ListeClients = new javax.swing.JMenuItem();
        ListeVoiture = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Garage HEPL - La Policlinique de vos voitures");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Atelier");
        jLabel1.setPreferredSize(new java.awt.Dimension(44, 18));

        jLabel2.setText("Pont n°1 :");

        jLabel3.setText("Pont n°2 :");

        jLabel4.setText("Pont n°3 :");

        jLabel5.setText("Sol :");

        jLabel6.setText("Informations :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Bureau");

        jLabel8.setText("Bureau client :");

        jLabel9.setText("Bureau comptabilité :");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Data/Images/ImageAccueil.png"))); // NOI18N

        PatronDispo.setText("Patron disponible");
        PatronDispo.setOpaque(false);
        PatronDispo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatronDispoActionPerformed(evt);
            }
        });

        PauseMidi.setText("Pause-Midi");

        PersonnelPresent.setText("Tout le monde présent");
        PersonnelPresent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PersonnelPresentActionPerformed(evt);
            }
        });

        PersonnelAbsent.setText("Certains absents");
        PersonnelAbsent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PersonnelAbsentActionPerformed(evt);
            }
        });

        EmplacementPont1.setEditable(false);
        EmplacementPont1.setBackground(new java.awt.Color(255, 255, 255));
        EmplacementPont1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        EmplacementPont1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EmplacementPont1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmplacementPont1FocusLost(evt);
            }
        });

        EmplacementPont2.setEditable(false);
        EmplacementPont2.setBackground(new java.awt.Color(255, 255, 255));
        EmplacementPont2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EmplacementPont2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmplacementPont2FocusLost(evt);
            }
        });

        EmplacementPont3.setEditable(false);
        EmplacementPont3.setBackground(new java.awt.Color(255, 255, 255));
        EmplacementPont3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EmplacementPont3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmplacementPont3FocusLost(evt);
            }
        });

        EmplacementSol.setEditable(false);
        EmplacementSol.setBackground(new java.awt.Color(255, 255, 255));
        EmplacementSol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EmplacementSolFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmplacementSolFocusLost(evt);
            }
        });

        EmplacementInformations.setEditable(false);
        EmplacementInformations.setBackground(new java.awt.Color(255, 255, 255));
        EmplacementInformations.setFocusable(false);

        BureauClient.setEditable(false);
        BureauClient.setBackground(new java.awt.Color(255, 255, 255));
        BureauClient.setFocusable(false);

        BureauCompta.setEditable(false);
        BureauCompta.setBackground(new java.awt.Color(255, 255, 255));
        BureauCompta.setFocusable(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setFocusable(false);
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Atelier.setText("Atelier");

        APrevoir.setText("A prévoir");
        APrevoir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APrevoirActionPerformed(evt);
            }
        });
        Atelier.add(APrevoir);

        PriseEnCharge.setText("Prise en charge");
        PriseEnCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriseEnChargeActionPerformed(evt);
            }
        });
        Atelier.add(PriseEnCharge);

        TerminerTravail.setText("Terminé");
        TerminerTravail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TerminerTravailActionPerformed(evt);
            }
        });
        Atelier.add(TerminerTravail);
        Atelier.add(jSeparator1);

        ListeTravaux.setText("Listes");
        ListeTravaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeTravauxActionPerformed(evt);
            }
        });
        Atelier.add(ListeTravaux);

        jMenuBar1.add(Atelier);

        Matériel.setText("Matériel");

        Commander.setText("Commander");

        CommanderPiece.setText("Pièce");
        CommanderPiece.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CommanderPieceActionPerformed(evt);
            }
        });
        Commander.add(CommanderPiece);

        CommanderPneu.setText("Pneu");
        CommanderPneu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CommanderPneuActionPerformed(evt);
            }
        });
        Commander.add(CommanderPneu);

        CommanderLubrifiant.setText("Lubrifiant");
        CommanderLubrifiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CommanderLubrifiantActionPerformed(evt);
            }
        });
        Commander.add(CommanderLubrifiant);

        Matériel.add(Commander);

        ReceiveCommande.setText("Recepetionner");
        ReceiveCommande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReceiveCommandeActionPerformed(evt);
            }
        });
        Matériel.add(ReceiveCommande);
        Matériel.add(jSeparator4);

        ListeCommandes.setText("Liste commandes");
        ListeCommandes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeCommandesActionPerformed(evt);
            }
        });
        Matériel.add(ListeCommandes);

        jMenuBar1.add(Matériel);

        Facture.setText("Factures");

        AddFacture.setText("Nouvelle facture");
        AddFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddFactureActionPerformed(evt);
            }
        });
        Facture.add(AddFacture);

        ListeFactures.setText("Liste factures");
        ListeFactures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeFacturesActionPerformed(evt);
            }
        });
        Facture.add(ListeFactures);

        jMenuBar1.add(Facture);

        Add.setText("Ajouter");

        AddMecanicien.setText("Mecanicien");
        AddMecanicien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMecanicienActionPerformed(evt);
            }
        });
        Add.add(AddMecanicien);

        AddEmploye.setText("Employé");
        AddEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEmployeActionPerformed(evt);
            }
        });
        Add.add(AddEmploye);

        AddTechnicien.setText("Technicien");
        AddTechnicien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTechnicienActionPerformed(evt);
            }
        });
        Add.add(AddTechnicien);
        Add.add(jSeparator2);

        AddClient.setText("Client");
        AddClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddClientActionPerformed(evt);
            }
        });
        Add.add(AddClient);

        AddVoiture.setText("Voiture");
        AddVoiture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddVoitureActionPerformed(evt);
            }
        });
        Add.add(AddVoiture);

        jMenuBar1.add(Add);

        Liste.setText("Listes");

        ListeMecanicien.setText("Mecaniciens");
        ListeMecanicien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeMecanicienActionPerformed(evt);
            }
        });
        Liste.add(ListeMecanicien);

        ListeEmploye.setText("Employés");
        ListeEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeEmployeActionPerformed(evt);
            }
        });
        Liste.add(ListeEmploye);

        ListeTechnicien.setText("Techniciens");
        ListeTechnicien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeTechnicienActionPerformed(evt);
            }
        });
        Liste.add(ListeTechnicien);
        Liste.add(jSeparator3);

        ListeClients.setText("Clients");
        ListeClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeClientsActionPerformed(evt);
            }
        });
        Liste.add(ListeClients);

        ListeVoiture.setText("Voitures");
        ListeVoiture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeVoitureActionPerformed(evt);
            }
        });
        Liste.add(ListeVoiture);

        jMenuBar1.add(Liste);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BureauCompta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BureauClient, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PauseMidi)
                            .addComponent(PatronDispo))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PersonnelAbsent)
                            .addComponent(PersonnelPresent))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4))
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(EmplacementPont3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                                        .addComponent(EmplacementSol)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(EmplacementPont2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                                        .addComponent(EmplacementPont1)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(EmplacementInformations, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(EmplacementPont1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(EmplacementPont2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(EmplacementPont3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(EmplacementSol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(EmplacementInformations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(PatronDispo)
                    .addComponent(PersonnelPresent)
                    .addComponent(BureauClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(PauseMidi)
                    .addComponent(PersonnelAbsent)
                    .addComponent(BureauCompta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void APrevoirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_APrevoirActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien"))
       {
           APrevoir fenetreAPrevoir = new APrevoir();
           fenetreAPrevoir.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_APrevoirActionPerformed

    private void PriseEnChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriseEnChargeActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien"))
       {
           PriseEnCharge fenetrePriseEnCharge = new PriseEnCharge();
           fenetrePriseEnCharge.setVisible(true);
           fenetrePriseEnCharge.addWindowListener(new WindowAdapter() 
           {
                @Override
                public void windowClosed(WindowEvent e) 
                {
                    SetPontEmplacement();
                }
           });
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_PriseEnChargeActionPerformed

    private void TerminerTravailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminerTravailActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien"))
       {
            if(selectedemplacement != 0)
            {
                boolean emplacementvide=true;
                switch(selectedemplacement)
                {
                    case 1: emplacementvide = emplacementvide1; break;
                    case 2: emplacementvide = emplacementvide2; break;
                    case 3: emplacementvide = emplacementvide3; break;
                    case 4: emplacementvide = emplacementvide4; break;
                }
                if(emplacementvide==false)
                {
                    try 
                    {
                        ListeEntretienfini = PersistanceBinaire.ChargerBinaire(chemin_entretienfini);
                        ListeReparationfini = PersistanceBinaire.ChargerBinaire(chemin_reparationfini);
                        ListeEntretienencours = PersistanceBinaire.ChargerBinaire(chemin_entretienencours);
                        ListeReparationencours = PersistanceBinaire.ChargerBinaire(chemin_reparationencours);
                    } 
                    catch (IOException | ClassNotFoundException ex) 
                    {
                        Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    PersistanceTexte.createWithOutDelete(chemin_emplacementatelier); 
                    String emplacementatelier = PersistanceTexte.Read(chemin_emplacementatelier);
                    String[] parts = emplacementatelier.split("_");
                    ArrayList<String> atelier = new ArrayList<>();
                    int emplacementtab = selectedemplacement*3-2;
                    String idt = parts[emplacementtab];

                    if(parts[emplacementtab+1].equals("1"))
                    {
                        Entretien entretien = new Entretien();
                        for (Entretien ListeEntretienencours1 : ListeEntretienencours) {
                            String id2 = ListeEntretienencours1.getId();
                            if (id2.equals(idt)) 
                            {
                                entretien = ListeEntretienencours1;
                            }
                        }
                        ListeEntretienencours.remove(entretien);
                        PersistanceBinaire.SauvegarderBinaire(ListeEntretienencours,chemin_entretienencours);
                        ListeEntretienfini.add(entretien);
                        PersistanceBinaire.SauvegarderBinaire(ListeEntretienfini,chemin_entretienfini);
                    }
                    else
                    {
                        Reparation reparation = new Reparation();
                        for (Reparation ListeReparationencours1 : ListeReparationencours) {
                            String id2 = ListeReparationencours1.getId();
                            if (id2.equals(idt)) 
                            {
                                reparation = ListeReparationencours1;
                            }
                        }
                        ListeReparationencours.remove(reparation);
                        PersistanceBinaire.SauvegarderBinaire(ListeReparationencours,chemin_reparationencours);
                        ListeReparationfini.add(reparation);
                        PersistanceBinaire.SauvegarderBinaire(ListeReparationfini,chemin_reparationfini);
                    }

                    parts[emplacementtab] = "0";
                    parts[emplacementtab+1] = "0";
                    atelier.addAll(Arrays.asList(parts));

                    String emplacement="";
                    for (String item : atelier)
                    {
                        emplacement = emplacement + item + "_";
                    }
                    PersistanceTexte.create(chemin_emplacementatelier);
                    PersistanceTexte.Write(emplacement, chemin_emplacementatelier);

                    JOptionPane.showMessageDialog(null, "Travail terminé", "Message",JOptionPane.INFORMATION_MESSAGE);
                    SetPontEmplacement();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "L'emplacement est vide", "Warning",JOptionPane.WARNING_MESSAGE); 
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Aucun emplacement selectionné", "Warning",JOptionPane.WARNING_MESSAGE); 
            }
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_TerminerTravailActionPerformed

    private void ListeTravauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeTravauxActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien"))
       { 
            ListeTravaux fenetreListeTravaux = new ListeTravaux();
            fenetreListeTravaux.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }   
    }//GEN-LAST:event_ListeTravauxActionPerformed

    private void AddMecanicienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMecanicienActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            AddMecanicien fenetreMecanicien = new AddMecanicien();
            fenetreMecanicien.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_AddMecanicienActionPerformed

    private void AddEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEmployeActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            AddEmploye fenetreEmploye = new AddEmploye();
            fenetreEmploye.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_AddEmployeActionPerformed

    private void AddTechnicienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTechnicienActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            AddTechnicien fenetreTechnicien = new AddTechnicien();
            fenetreTechnicien.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_AddTechnicienActionPerformed

    private void AddClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddClientActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            AddClient fenetreClient = new AddClient();
            fenetreClient.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_AddClientActionPerformed

    private void AddVoitureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddVoitureActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            AddVoiture fenetreVoiture = new AddVoiture();
            fenetreVoiture.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_AddVoitureActionPerformed

    private void EmplacementPont1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmplacementPont1FocusGained
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        RemplirInfoEmplacement(1);
        selectedemplacement=1;
        EmplacementPont1.setBackground(Color.orange);
    }//GEN-LAST:event_EmplacementPont1FocusGained

    private void EmplacementPont2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmplacementPont2FocusGained
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        RemplirInfoEmplacement(2);
        selectedemplacement=2;
        EmplacementPont2.setBackground(Color.orange);
    }//GEN-LAST:event_EmplacementPont2FocusGained

    private void EmplacementPont3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmplacementPont3FocusGained
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        RemplirInfoEmplacement(3);
        selectedemplacement=3;
        EmplacementPont3.setBackground(Color.orange);
    }//GEN-LAST:event_EmplacementPont3FocusGained

    private void EmplacementSolFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmplacementSolFocusGained
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        RemplirInfoEmplacement(4);
        selectedemplacement=4;
        EmplacementSol.setBackground(Color.orange);
    }//GEN-LAST:event_EmplacementSolFocusGained

    private void EmplacementPont1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmplacementPont1FocusLost
        EmplacementPont1.setBackground(Color.white);
    }//GEN-LAST:event_EmplacementPont1FocusLost

    private void EmplacementPont2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmplacementPont2FocusLost
        EmplacementPont2.setBackground(Color.white);
    }//GEN-LAST:event_EmplacementPont2FocusLost

    private void EmplacementPont3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmplacementPont3FocusLost
        EmplacementPont3.setBackground(Color.white);
    }//GEN-LAST:event_EmplacementPont3FocusLost

    private void EmplacementSolFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmplacementSolFocusLost
        EmplacementSol.setBackground(Color.white);
    }//GEN-LAST:event_EmplacementSolFocusLost

    private void PersonnelPresentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PersonnelPresentActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        personnelpresent();
    }//GEN-LAST:event_PersonnelPresentActionPerformed

    private void PersonnelAbsentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PersonnelAbsentActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        personnelpaspresent();
    }//GEN-LAST:event_PersonnelAbsentActionPerformed

    private void ListeMecanicienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeMecanicienActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            ListeMecaniciens fenetreListeM = new ListeMecaniciens();
            fenetreListeM.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_ListeMecanicienActionPerformed

    private void ListeEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeEmployeActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            ListeEmployes fenetreListeE = new ListeEmployes();
            fenetreListeE.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_ListeEmployeActionPerformed

    private void ListeTechnicienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeTechnicienActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);       
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            ListeTechniciens fenetreListeT = new ListeTechniciens();
            fenetreListeT.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_ListeTechnicienActionPerformed

    private void ListeClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeClientsActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            ListeClients fenetreListeC = new ListeClients();
            fenetreListeC.setVisible(true);
            fenetreListeC.addWindowListener(new WindowAdapter() 
            {
                @Override
                public void windowClosed(WindowEvent e) 
                {
                    SetClientBureau();
                }
            });
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_ListeClientsActionPerformed

    private void ListeVoitureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeVoitureActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       {
            ListeVoitures fenetreListeV = new ListeVoitures();
            fenetreListeV.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_ListeVoitureActionPerformed

    private void PatronDispoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatronDispoActionPerformed
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(patrondispo==true)
        {
            PatronDispo.setSelected(true);
        }
        else
        {
            String clientbureau="0";
            PersistanceTexte.create(chemin_clientbureau);
            PersistanceTexte.Write(clientbureau, chemin_clientbureau);
            SetClientBureau();
        }
    }//GEN-LAST:event_PatronDispoActionPerformed

    private void AddFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddFactureActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       { 
            AddFacture fenetreFacture = new AddFacture();
            fenetreFacture.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       } 
    }//GEN-LAST:event_AddFactureActionPerformed

    private void ListeFacturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeFacturesActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       { 
            PersistanceTexte.createWithOutDelete(chemin_facture); 
            String facture = PersistanceTexte.Read(chemin_facture);
            String[] factureparts = facture.split("_");
            JOptionPane.showInputDialog(null, "Voici les factures\n", "Factures", JOptionPane.INFORMATION_MESSAGE, null, factureparts, factureparts[0]);
       }
       else
       {
           AccessNotAuthorized();
       } 
    }//GEN-LAST:event_ListeFacturesActionPerformed

    private void CommanderPieceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CommanderPieceActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       { 
            Commande CommandePiece = new Commande("pièces");
            CommandePiece.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_CommanderPieceActionPerformed

    private void CommanderPneuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CommanderPneuActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       { 
            Commande CommandePneu = new Commande("pneu");
            CommandePneu.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_CommanderPneuActionPerformed

    private void CommanderLubrifiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CommanderLubrifiantActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       { 
            Commande CommandeLubrifiant = new Commande("lubrifiant");
            CommandeLubrifiant.setVisible(true);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_CommanderLubrifiantActionPerformed

    private void ListeCommandesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeCommandesActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       { 
            PersistanceTexte.createWithOutDelete(chemin_commande); 
            String commande = PersistanceTexte.Read(chemin_commande);
            String[] commandeparts = commande.split("__");
            JOptionPane.showInputDialog(null, "Voici les commandes\n", "Commandes", JOptionPane.INFORMATION_MESSAGE, null, commandeparts, commandeparts[0]);
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_ListeCommandesActionPerformed

    private void ReceiveCommandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReceiveCommandeActionPerformed
       PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
       if(fonctionpersonnel.equals("Mecanicien") || fonctionpersonnel.equals("Employe"))
       { 
            String choix="";
            int ChoixReceive=0;
            String[] TypeReceive = {"Pièces","Pneus","Lubrifiants"};

            ChoixReceive = JOptionPane.showOptionDialog(null, "Sélectionnez le type de réception", "Choix réception", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, TypeReceive, TypeReceive[0]);

            if(ChoixReceive != -1)
            {
                choix=TypeReceive[ChoixReceive];
                ReceiveCommande fenetrereceive = new ReceiveCommande(choix);
                fenetrereceive.setVisible(true);
            }
       }
       else
       {
           AccessNotAuthorized();
       }
    }//GEN-LAST:event_ReceiveCommandeActionPerformed
    
    void personnelpresent()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        PersonnelPresent.setSelected(true);
        PersonnelAbsent.setSelected(false);
        BureauCompta.setText("Le comptable est présent");
    }
    void personnelpaspresent()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        PersonnelPresent.setSelected(false);
        PersonnelAbsent.setSelected(true);
        BureauCompta.setText("Le comptable n'est pas présent");
    }
    
    private void InfoSystemeActionPerformed(java.awt.event.ActionEvent evt) {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        JOptionPane.showMessageDialog(null, "Informations systèmes : \n" + "OS : " + System.getProperty("os.name") + "\n" + "Active directory : " + System.getProperty("user.dir"), "Informations systèmes",JOptionPane.INFORMATION_MESSAGE);     
    }
    
    private void DebuterActionPerformed(java.awt.event.ActionEvent evt) {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        JOptionPane.showMessageDialog(null, "Lancez ApplicationCentrale et ensuite connectez vous sur ApplicationGestion afin de gérer le garage","Pour débuter",JOptionPane.INFORMATION_MESSAGE);     
    } 
   
    private void AProposActionPerformed(java.awt.event.ActionEvent evt) { 
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        APropos fenetreAPropos = new APropos();
        fenetreAPropos.setVisible(true);
    } 
    
    private void ParametreDateActionPerformed(java.awt.event.ActionEvent evt) {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
	int Localisation=0,formatdate=0,Timeformat=0;
        String[] Pays = {"France","UK","Allemagne","Italie","US"};
        Localisation = JOptionPane.showOptionDialog(null, "Sélectionnez la localisation", "Format pays", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, Pays, Pays[0]);
        if(Localisation != -1)
            dateLocal=Pays[Localisation];
        
	String[] Formats = {"Format court","Format long"};
	formatdate = JOptionPane.showOptionDialog(null,"Sélectionnez le format de la date","Format date",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,Formats,Formats[0]);
        if(formatdate!= -1)
            dateFormat=Formats[formatdate];
            
	String[] Temps = {"Format court","Format long"};
	Timeformat = JOptionPane.showOptionDialog(null,"Sélectionnez le format du temps","Format temps",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,Temps,Temps[0]);
        if(Timeformat!= -1)
            tempsFormat=Temps[Timeformat];
    } 
    
    private void ListeLogActionPerformed(java.awt.event.ActionEvent evt) { 
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        if(fonctionpersonnel.equals("Mecanicien"))
        { 
            String log = PersistanceTexte.Read(chemin_log);
            String[] logparts = log.split("_");
            JOptionPane.showInputDialog(null, "Voici les log\n", "Log", JOptionPane.INFORMATION_MESSAGE, null, logparts, logparts[0]);
        }
        else
        {
            AccessNotAuthorized();
        }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new Accueil("", "").setVisible(true);
        });
    }
    
    void AccessNotAuthorized()
    {
        PersistanceTexte.Write(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()) + ">[" + getClass().getName() + "] " + Utilisateurpersonnel + " -> " + Thread.currentThread().getStackTrace()[1].getMethodName() + "_\n" , chemin_log);
        Noaccess accespersonnel = new Noaccess(null, true);
        accespersonnel.setVisible(true);
    }
    
    private void initComponents2() 
    {
        JMenu Parametre, Aide;
        JMenuItem InfoSysteme, Debuter, APropos,ParametreDate, ListeLog;
        JSeparator Separator2;
        
        jMenuBar1.add(Box.createHorizontalGlue());
        
        Parametre = new JMenu ("Paramètres");
        InfoSysteme = new JMenuItem("Infos système");
        Parametre.add(InfoSysteme);
        ParametreDate = new JMenuItem("Date");
        Parametre.add(ParametreDate);
        ListeLog = new JMenuItem("Liste log");
        Parametre.add(ListeLog);
        
        Aide = new JMenu ("Aide");
        Debuter = new JMenuItem("Pour débuter");
        Aide.add(Debuter);
        Separator2 = new JSeparator();
        Aide.add(Separator2);
        APropos = new JMenuItem("A propos de ...");
        Aide.add(APropos);
        
        InfoSysteme.addActionListener(this::InfoSystemeActionPerformed);
        Debuter.addActionListener(this::DebuterActionPerformed);
        APropos.addActionListener(this::AProposActionPerformed);
        ParametreDate.addActionListener(this::ParametreDateActionPerformed);
        ListeLog.addActionListener(this::ListeLogActionPerformed);
        
        jMenuBar1.add(Parametre);
        jMenuBar1.add(Aide);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem APrevoir;
    private javax.swing.JMenu Add;
    private javax.swing.JMenuItem AddClient;
    private javax.swing.JMenuItem AddEmploye;
    private javax.swing.JMenuItem AddFacture;
    private javax.swing.JMenuItem AddMecanicien;
    private javax.swing.JMenuItem AddTechnicien;
    private javax.swing.JMenuItem AddVoiture;
    private javax.swing.JMenu Atelier;
    private javax.swing.JTextField BureauClient;
    private javax.swing.JTextField BureauCompta;
    private javax.swing.JMenu Commander;
    private javax.swing.JMenuItem CommanderLubrifiant;
    private javax.swing.JMenuItem CommanderPiece;
    private javax.swing.JMenuItem CommanderPneu;
    private javax.swing.JTextField EmplacementInformations;
    private javax.swing.JTextField EmplacementPont1;
    private javax.swing.JTextField EmplacementPont2;
    private javax.swing.JTextField EmplacementPont3;
    private javax.swing.JTextField EmplacementSol;
    private javax.swing.JMenu Facture;
    private javax.swing.JMenu Liste;
    private javax.swing.JMenuItem ListeClients;
    private javax.swing.JMenuItem ListeCommandes;
    private javax.swing.JMenuItem ListeEmploye;
    private javax.swing.JMenuItem ListeFactures;
    private javax.swing.JMenuItem ListeMecanicien;
    private javax.swing.JMenuItem ListeTechnicien;
    private javax.swing.JMenuItem ListeTravaux;
    private javax.swing.JMenuItem ListeVoiture;
    private javax.swing.JMenu Matériel;
    private javax.swing.JCheckBox PatronDispo;
    private javax.swing.JCheckBox PauseMidi;
    private javax.swing.JRadioButton PersonnelAbsent;
    private javax.swing.JRadioButton PersonnelPresent;
    private javax.swing.JMenuItem PriseEnCharge;
    private javax.swing.JMenuItem ReceiveCommande;
    private javax.swing.JMenuItem TerminerTravail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
