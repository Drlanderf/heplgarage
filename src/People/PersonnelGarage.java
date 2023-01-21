package People;
import Authenticate.*;
import java.util.Hashtable;
import java.io.Serializable;

 public abstract class PersonnelGarage extends Personne implements Serializable{
    //Variables Membres
    private int matricule;
    private String mdp;
    private String typeemploye;
    
    //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public PersonnelGarage() {
        setNom("");
        setPrenom("");
        setAdresse("");
        setNumtel("");
        setMatricule(1);
        setMdp("");
        setTypeemploye("");
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public PersonnelGarage(String newNom,String newPrenom, String newAdresse, String newNumtel, int newMatricule,String newMdp, String newTypeemploye) {
        setNom(newNom);
        setPrenom(newPrenom);
        setAdresse(newAdresse);
        setNumtel(newNumtel);
        setMatricule(newMatricule);
        setMdp(newMdp);
        setTypeemploye(newTypeemploye);
    }
    
    //Setter
    public void setMatricule(int newMatricule) {
        if(newMatricule != 0)
            matricule = newMatricule;
    }
    public void setMdp(String newMdp) {
        if(newMdp != null)
            mdp = newMdp;
    }
    public void setTypeemploye(String newTypeemploye) {
        if(newTypeemploye != null)
            typeemploye = newTypeemploye;
    }
    
    //Getter
    public int getMatricule() {
        return matricule;
    }
    public String getMdp() {
        return mdp;
    }
    public String getTypeemploye() {
        return typeemploye;
    }
}