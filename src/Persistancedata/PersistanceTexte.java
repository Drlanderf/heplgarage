package Persistancedata;

import java.io.*;

public class PersistanceTexte 
{
    public static void Write(String tmp,String cheminfichier)
    {
        try (FileWriter fw = new FileWriter(cheminfichier,true)) 
        {
            fw.write(tmp);
        } 
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static String Read(String cheminfichier)
    {
        FileReader flux= null;
        BufferedReader input= null;
        String line;
        String tmp = new String();
        
        try
        { 
            flux= new FileReader (cheminfichier);
            input= new BufferedReader(flux);
            
            while((line = input.readLine())!=null)
                tmp = tmp + line;
            
            flux.close();
        }
        catch (IOException e)
        {
             System.out.println(e.getMessage());
        }  
        return tmp;
    }
    public static void create(String cheminfichier)
    {
        try
        { 
            File yourFile = new File(cheminfichier);
            yourFile.delete();
            yourFile.createNewFile();  
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }  
    }
    public static void createWithOutDelete(String cheminfichier)
    {
        try
        { 
            File yourFile = new File(cheminfichier);
            yourFile.createNewFile();  
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }  
    }

    public static void Write(String[] parts, String dataemplacementateliertxt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
