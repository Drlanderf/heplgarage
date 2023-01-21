package Activites;

import Authenticate.*;
import People.Mecanicien;
import Vehicules.Voiture;
import java.io.Serializable; //pour inscrire fichier data

public class Reparation extends Travail implements Identifiable, Serializable{
    //Variables Membres
    private String Typetravail;
    private String idtravail;
    private String instructionssup;
    private String travailaeffectuer;

    //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Reparation() 
    {
        setMecanicien(new Mecanicien());
        setVoiture(new Voiture());
        setTypetravail("Réparation");
        setInstructionssup("");
        setTravailaeffectuer("");
        
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Reparation(Mecanicien NewMecanicientravail,
            Voiture NewVoituretravail, 
            String Newinstructions, 
            String Newtravailaeffectuer) 
    {
        setMecanicien(NewMecanicientravail);
        setVoiture(NewVoituretravail);
        setTypetravail("Réparation");
        setInstructionssup(Newinstructions);
        setTravailaeffectuer(Newtravailaeffectuer);
    }

    //Setter
    public void setTypetravail(String NewTypetravail) 
    {
        if(!"".equals(NewTypetravail))
            Typetravail = NewTypetravail;
    }
    public void setInstructionssup(String Newinstructions) 
    {
        if(!"".equals(Newinstructions))
            instructionssup = Newinstructions;
    }
    public void setTravailaeffectuer(String Newtravailaeffectuer) 
    {
        if(!"".equals(Newtravailaeffectuer))
            travailaeffectuer = Newtravailaeffectuer;
    }

    //Getter
    public String getTypetravail() {return Typetravail;}
    public String getInstructionssup() {return instructionssup;}
    public String getTravailaeffectuer() {return travailaeffectuer;}
    
     //Méthodes
    @Override
    public String setId(String id)
    {
        idtravail = id;
        return idtravail;
    }
    
    @Override
    public String getId() 
    {
        return idtravail;
    }
}