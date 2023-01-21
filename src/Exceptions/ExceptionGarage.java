package Exceptions;
//exept pour ce projet
import MyVariousUtils.Color;

public class ExceptionGarage extends Exception {
    
    String message;

    public ExceptionGarage(String newMessage) {
        message = newMessage;
    }

    @Override
    public String toString() {
        return Color.ANSI_RED + "[GarageHEPL.ExceptionGarage]" + "Erreur: " + message + Color.ANSI_RESET;
    }
}
