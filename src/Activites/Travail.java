package Activites;

import People.Mecanicien;
import Vehicules.Voiture;
import java.io.Serializable; //pour inscrire fichier data

public abstract class Travail implements Serializable{
    //Variables Membres
    private Mecanicien mecanicientravail;
    private Voiture voituretravail;

    //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Travail() {
        setMecanicien(new Mecanicien());
        setVoiture(new Voiture());
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Travail(Mecanicien NewMecanicientravail,Voiture NewVoituretravail) 
    {
        setMecanicien(NewMecanicientravail);
        setVoiture(NewVoituretravail);
    }

    //Setter
    public void setMecanicien(Mecanicien NewMecanicientravail) 
    {
        if(NewMecanicientravail != null)
            mecanicientravail = NewMecanicientravail;
    }
    public void setVoiture(Voiture NewVoituretravail) 
    {
        if(NewVoituretravail != null)
            voituretravail = NewVoituretravail;
    }

    //Getter
    public Mecanicien getMecanicien() {return mecanicientravail;}
    public Voiture getVoiture() {return voituretravail;}
}
