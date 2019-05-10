/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scramble_generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author family
 */
public class letterScheme {
    public String[] scheme;
    
    public letterScheme() {
       //String[] scheme;
       scheme = new String[21]; 
       
       // The name of the file to open.
        String fileName = "letterScheme.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            int count = 0;
            while((line = bufferedReader.readLine()) != null) {
                if (!line.contains("//")&&!line.equals("")&&!line.equals("\n")&&!line.equals(" ")) {
                    scheme[count] = line;
                    count++;
                }
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
    }
}
