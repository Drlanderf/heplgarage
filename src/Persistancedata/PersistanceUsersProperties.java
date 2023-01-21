package Persistancedata;

import java.io.*; 
import java.util.*; 

public class PersistanceUsersProperties 
{
        //Sauvegarder les .properties
        public static void SauvegarderUsersProperties(Properties props,String cheminfichier)
        {
            try
            {
                OutputStream out = new FileOutputStream(cheminfichier);
                props.store(out,cheminfichier); 
                out.flush(); 
            }
            catch(NullPointerException | IOException e)          
            {
                e.getMessage();
            }
        }
        
        //Charger les .properties
        public static Properties ChargerUsersProperties(String cheminfichier)
        {
            Properties props = new Properties(); 
            try
            {
                props.load(new FileInputStream(cheminfichier)); 
            }
            catch(NullPointerException | IOException e)          
            {
                e.getMessage();
            }
            return props;
        }
}
