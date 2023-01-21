package Exceptions;
//exept par défaut
import MyVariousUtils.Color;

public class MissingTradeMarkException extends Exception {
    String message;

    public MissingTradeMarkException(String newMessage) {
        message = newMessage;
    }

    @Override
    public String toString() {
        return Color.ANSI_RED + "[GarageHEPL.MissingTradeMarkException]" + "Erreur: " + message + Color.ANSI_RESET;
    }
}
