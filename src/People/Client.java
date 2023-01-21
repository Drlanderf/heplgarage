package People;

import Authenticate.Identifiable;
import java.io.Serializable;

public class Client extends Personne implements Identifiable, Serializable{
    //Variables Membres
    private int numclient;
    private String idclient;
    
    //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Client() {
        setNom("");
        setPrenom("");
        setAdresse("");
        setNumtel("");
        setNumclient(1);
    }
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Client(String newNom,String newPrenom, String newAdresse, String newNumtel, int newNumclient) {
        setNom(newNom);
        setPrenom(newPrenom);
        setAdresse(newAdresse);
        setNumtel(newNumtel);
        setNumclient(newNumclient);
    }

    //Setter
    public void setNumclient(int newNumclient) {
        if(newNumclient != 0)
            numclient = newNumclient;
    }

    //Getter
    public int getNumclient() {
        return numclient;
    }
    
    
    //Méthodes
    @Override
    public String setId(String id)
    {
        idclient = id;
        return idclient;
    }
    
    @Override
    public String getId() 
    {
        return idclient;
    }
}
