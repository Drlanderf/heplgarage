package Beans;

public class InStockEvent {
    private int nbrJour;
    
    public InStockEvent(int n){
        nbrJour = n;
    }
    
    public int getnbrJour() {
        return nbrJour;
    }

    public void setnbrJour(int NmbrJour) {
        this.nbrJour = NmbrJour;
    }
}
