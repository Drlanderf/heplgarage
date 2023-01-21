package People;

import Authenticate.*;
import java.io.Serializable;

public class Employe extends PersonnelGarage implements Identifiable, AValider, Serializable{
    //Variables Membres
    private String idemploye;
    
    public Employe() {
        setNom("");
        setPrenom("");
        setAdresse("");
        setNumtel("");
        setMatricule(1);
        setMdp("");
        setTypeemploye("");
    }
    public Employe(String newNom,String newPrenom, String newAdresse, String newNumtel, int newMatricule,String newMdp) {
        setNom(newNom);
        setPrenom(newPrenom);
        setAdresse(newAdresse);
        setNumtel(newNumtel);
        setMatricule(newMatricule);
        setMdp(newMdp);
        setTypeemploye("Employe");
    }
    
    //Méthodes
    @Override
    public String setId(String id)
    {
        idemploye = id;
        return idemploye;
    }
    
    @Override
    public String getId() 
    {
        return idemploye;
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
