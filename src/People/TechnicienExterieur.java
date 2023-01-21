package People;

import Authenticate.*;
import java.io.Serializable;

public class TechnicienExterieur extends Personne implements Identifiable, AValider, Serializable {
    //Variables Membres
    private String idtechnicien;
    private String mdp;
    private String typeemploye;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TechnicienExterieur() {
        setNom("");
        setPrenom("");
        setAdresse("");
        setNumtel("");
        setMdp("");
        setTypeemploye("");
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TechnicienExterieur(String newNom,String newPrenom, String newAdresse, String newNumtel, String newMdp) {
        setNom(newNom);
        setPrenom(newPrenom);
        setAdresse(newAdresse);
        setNumtel(newNumtel);
        setMdp(newMdp);
        setTypeemploye("Technicien");
    }
    
    //Setter
    public void setMdp(String newMdp) {
        if(newMdp != null)
            mdp = newMdp;
    }
    public void setTypeemploye(String newTypeemploye) {
        if(newTypeemploye != null)
            typeemploye = newTypeemploye;
    }
    
    //Getter
    public String getMdp() {
        return mdp;
    }
    public String getTypeemploye() {
        return typeemploye;
    }
    
    //Méthodes
    @Override
    public String setId(String id)
    {
        idtechnicien = id;
        return idtechnicien;
    }
    
    @Override
    public String getId() 
    {
        return idtechnicien;
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
