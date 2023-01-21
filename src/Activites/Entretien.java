package Activites;

import Authenticate.Identifiable;
import People.Mecanicien;
import Vehicules.Voiture;
import java.io.Serializable; //pour inscrire fichier data

public class Entretien extends Travail implements Identifiable, Serializable{
    //Variables Membres
    private String Typetravail;
    private String idtravail;
    private String instructionssup;
    private String travailaeffectuer;
    
    //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Entretien() 
    {
        setMecanicien(new Mecanicien());
        setVoiture(new Voiture());
        setTypetravail("Entretien");
        setInstructionssup("");
        setTravailaeffectuer("");
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Entretien(Mecanicien NewMecanicientravail,
            Voiture NewVoituretravail, 
            String Newinstructions, 
            String Newtravailaeffectuer) 
    {
        setMecanicien(NewMecanicientravail);
        setVoiture(NewVoituretravail);
        setTypetravail("Entretien");
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
    public String setId(String id) {
        idtravail = id;
        return idtravail;
    }
    
    @Override
    public String getId() {return idtravail;}
}
