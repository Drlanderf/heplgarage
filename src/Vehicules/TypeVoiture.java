package Vehicules;

import Exceptions.MissingTradeMarkException;
import java.io.Serializable;

public class TypeVoiture implements Serializable {
    private String typevoiture;
    private String marque;
    private String modele;
    private int annee;
    private int nombreporte;
    
    //Constructeurs
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TypeVoiture() {
        setTypevoiture("");
        setMarque("");
        setModele("");
        setAnnee(1);
        setNombreporte(1);
        
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TypeVoiture(String newTypevoiture,String newMarque, String newModele, int newAnnee, int newNombreporte) throws MissingTradeMarkException {
        setTypevoiture(newTypevoiture);
        if (newMarque == null)
        {
            throw new MissingTradeMarkException("Pas de marque de voiture");
        }
        else
        {
            setMarque(newMarque);
        }
        setModele(newModele);
        setAnnee(newAnnee);
        setNombreporte(newNombreporte);
    }

    //Setter
    public void setTypevoiture(String newTypevoiture) {
        if(newTypevoiture != null)
            typevoiture = newTypevoiture;
    }
    public void setMarque(String newMarque) {
        if(newMarque != null)
            marque = newMarque;
    }
    public void setModele(String newModele) {
        if(newModele != null)
            modele = newModele;
    }
    public void setAnnee(int newAnnee) {
        if(newAnnee != 0)
            annee = newAnnee;
    }
    public void setNombreporte(int newNombreporte) {
        if(newNombreporte != 0)
            nombreporte = newNombreporte;
    }

    //Getter
    public String getTypevoiture() {
        return typevoiture;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }
    public int getAnnee() {
        return annee;
    }
    public int getNombreporte() {
        return nombreporte;
    }
}
