/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scramble_generator;
import java.util.*;
import java.io.*;

/**
 *
 * @author family
 */
public class Scramble_Generator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        System.out.println("This program generates scrambles that contain certain letter pairs");
        /*for (int i = 0; i<21; i++) {
            System.out.println(ls.scheme[i]);
        }*/
        
        //String[] letterPairs = { "BS", "SB", "LP", "PL", "BL", "LB", "RQ", "QR",
        //    "PH", "HP", "TP", "PT", "BE", "EB" };
        String[] letterPairs = getLP().split(" ");
        
        System.out.print("Drilling these letter pairs: ");
        for(String pair: letterPairs) System.out.print(pair+" ");
        System.out.println();
        
        System.out.print("Enter freq (recommended less than 3): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int freq = Integer.parseInt(input);
        //int freq = 3;

        boolean done1 = false;
        while (true) {
            while (!done1) {
                Cube cube = new Cube();
                cube.genScramAndMemo();
                
                //System.out.println("scramble: " + cube.scram);
                //System.out.println("memo: " + cube.memo);
                
                String[] lp2 = letterPairs;
                String[] mArr = cube.memo.split(" ");
                //String[] mArr = { "FG", "DB", "GH", "HO", "45" };

                Set<String> s1 = new HashSet<>(Arrays.asList(mArr));
                Set<String> s2 = new HashSet<>(Arrays.asList(lp2));
                s1.retainAll(s2);

                String[] result = s1.toArray(new String[s1.size()]);

                if (result.length >= freq) {
                    System.out.print("\nScramble: " + cube.scram);
                    
                    Scanner sc2 = new Scanner(System.in);
                    String input2 = sc2.nextLine();
                    //System.out.println("memo: " + cube.memo);
                    System.out.print("Letter pairs: ");
                    for (String item : result) {
                        System.out.print(item + " ");
                    }
                    done1 = true;
                }

            } //end while
        System.out.print("\nHit \"Enter\" to gen another scramble");
        Scanner sc2 = new Scanner(System.in);
        String input2 = sc2.nextLine();
        done1 = false;
        }
    }
    
    static String getLP() {
        String lp = "";
        // The name of the file to open.
        String fileName = "lpList.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if (!line.contains("//")&&!line.equals("")&&!line.equals("\n")&&!line.equals(" ")) {
                    lp += line;
                    lp += " ";
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
        return lp;
    }
}
