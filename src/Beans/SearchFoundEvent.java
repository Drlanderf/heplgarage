package Beans;

public class SearchFoundEvent {
    private String libelle;

    public SearchFoundEvent(String c){
        libelle = c;
    }
    
    public String getLibelle() {
        return libelle;
    }

    public void setCommanderecue(String Libelle) {
        this.libelle = Libelle;
    }
}
