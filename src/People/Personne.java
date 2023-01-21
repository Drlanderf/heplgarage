package People;
import java.io.Serializable;

public abstract class Personne implements Serializable {
     //Variables Membres
    private String nom;
    private String prenom;
    private String adresse;
    private String numtel;
    
     //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Personne() {
        setNom("");
        setPrenom("");
        setAdresse("");
        setNumtel("");
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Personne(String newNom,String newPrenom, String newAdresse, String newNumtel) {
        setNom(newNom);
        setPrenom(newPrenom);
        setAdresse(newAdresse);
        setNumtel(newNumtel);
    }
    
    //Setter
    public void setNom(String newNom) {
        if(newNom != null)
            nom = newNom;
    }
    public void setPrenom(String newPrenom) {
        if(newPrenom != null)
            prenom = newPrenom;
    }
    public void setAdresse(String newAdresse) {
        if(newAdresse != null)
            adresse = newAdresse;
    }
    public void setNumtel(String newNumtel) {
        if(newNumtel != null)
            numtel = newNumtel;
    }

    //Getter
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }
    public String getNumtel() {
        return numtel;
    }
}
