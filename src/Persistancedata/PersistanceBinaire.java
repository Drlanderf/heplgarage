package Persistancedata;

import java.io.*;
import java.util.ArrayList;

public class PersistanceBinaire 
{
    //Sauvegarder en binaire
    public static void SauvegarderBinaire(ArrayList obj,String cheminfichier)
    {
        try(FileOutputStream fileOut = new FileOutputStream(cheminfichier);ObjectOutputStream out = new ObjectOutputStream(fileOut);)
        {
            out.writeObject(obj);
        }
        catch(IOException e)
        {
            System.out.println("SauvegarderBinaire : " + e.getMessage());
        }
    }
    
    //Charger binaire
    public static ArrayList ChargerBinaire(String cheminfichier) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList tmp = new ArrayList();

        try
        {
            File file = new File(cheminfichier);
            if(!(file.exists()))
            {
                file.createNewFile();
            }
            else
            {
            
                FileInputStream fileIn = new FileInputStream(cheminfichier);
                ObjectInputStream ois = new ObjectInputStream(fileIn);
                tmp = (ArrayList) ois.readObject();  
            }
        }
        catch(IOException e)
        {
            if(e.getMessage() != null)
                    System.out.println("ChargerBinaire : " + e.getMessage());
        }
        return tmp;
    } 
}
