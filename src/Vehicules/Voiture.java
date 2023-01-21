package Vehicules;

import Authenticate.*;
import People.Client;
import java.io.Serializable;

public class Voiture implements Identifiable, AValider, Serializable
{
    private TypeVoiture typevoiturevehicule;
    private Client clientvehicule;
    private String immatriculation;
    private String idvoiture;

    //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Voiture() {
        setTypevoiturevehicule(new TypeVoiture());
        setClientvehicule(new Client());
        setImmatriculation("");
        
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Voiture(TypeVoiture NewTypevoiturevehicule,Client NewClientvehicule, String Immatriculation) {
        setTypevoiturevehicule(NewTypevoiturevehicule);
        setClientvehicule(NewClientvehicule);
        setImmatriculation(Immatriculation);
    }

    //Setter
    public void setTypevoiturevehicule(TypeVoiture NewTypevoiturevehicule) {
        if(NewTypevoiturevehicule != null)
            typevoiturevehicule = NewTypevoiturevehicule;
    }
    public void setClientvehicule(Client NewClientvehicule) {
        if(NewClientvehicule != null)
            clientvehicule = NewClientvehicule;
    }
    public void setImmatriculation(String Immatriculation) {
        if(Immatriculation != null)
            immatriculation = Immatriculation;
    }

    //Getter
    public TypeVoiture getTypevoiturevehicule() {
        return typevoiturevehicule;
    }
    public Client getClientvehicule() {
        return clientvehicule;
    }
    public String getImmatriculation() {
        return immatriculation;
    }
    
    //Méthodes
    @Override
    public String setId(String id)
    {
        idvoiture = id;
        return idvoiture;
    }
    
    @Override
    public String getId() 
    {
        return idvoiture;
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
