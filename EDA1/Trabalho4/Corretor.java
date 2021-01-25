package Trabalho4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Corretor {

    private char[] alphabet = 
    {
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',  
        'á','à','ã','â','Á','À','Ã','Â',
        'é','ê','É','Ê',
        'í','ì','î','Í','Ì','Î',
        'ó','ò','õ','ô','Ó','Ò','Õ','Ô',
        'ú','ù','û','Ú','Ù','Û'
    };
    
    

    public static void main(String[] args) {

        File file = new File("C:/Users/AKATi/Desktop/University/University101/EDA1/wordlist-ao-2020.text");

        System.out.println(file.exists());


       /*readFile("dicionario.txt"); */

        LinHashtable<String> hashtable = new LinHashtable<String>(20);
    }

    private void addLetter(String word) {

    }

    private void removeLetter(String word) {

    }

    private void swapLetters(String word) {

    }

    private static void readFile(String fileName) {
        
        try{
            File file = new File("wordlist-ao-2020.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                System.out.println(line);
                /* line = reader.readLine(); */
            }
            reader.close();
        } catch(IOException ex){
            System.out.println("File not found");
        }
    }

}
