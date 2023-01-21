package Beans;

public class PropertyChangeEvent {
    private String commanderecue;

    public PropertyChangeEvent(String c){
        commanderecue = c;
    }
    
    public String getCommanderecue() {
        return commanderecue;
    }

    public void setCommanderecue(String CommandeRecue) {
        this.commanderecue = CommandeRecue;
    }
}
