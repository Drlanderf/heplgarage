package People;

import Authenticate.*;
import java.io.Serializable;

public class Mecanicien extends PersonnelGarage implements Identifiable, AValider, Serializable{
    //Variables Membres
    private String specialite;
    private String idmecanicien;
    
    //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Mecanicien() {
        setNom("");
        setPrenom("");
        setAdresse("");
        setNumtel("");
        setMatricule(1);
        setMdp("");
        setTypeemploye("");
        setSpecialite("");
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Mecanicien(String newNom,String newPrenom, String newAdresse, String newNumtel, int newMatricule,String newMdp, String newSpecialite) {
        setNom(newNom);
        setPrenom(newPrenom);
        setAdresse(newAdresse);
        setNumtel(newNumtel);
        setMatricule(newMatricule);
        setMdp(newMdp);
        setTypeemploye("Mecanicien");
        setSpecialite(newSpecialite);
    }
    
    //Setter
    public void setSpecialite(String newSpecialite) {
        if(newSpecialite != null)
            specialite = newSpecialite;
    }

    //Getter
    public String getSpecialite() {
        return specialite;
    }
    
    //Méthodes
    @Override
    public String setId(String id)
    {
        idmecanicien = id;
        return idmecanicien;
    }
    
    @Override
    public String getId() 
    {
        return idmecanicien;
    }
    
    @Override
    public boolean isValid()
    {
        return true;
    }
    
    @Override
    public boolean validate()
    {
        return true;
    }
}
