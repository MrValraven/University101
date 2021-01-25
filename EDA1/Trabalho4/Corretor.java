package Trabalho4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Corretor {

    private static Hashtable<String> hashtable;
    private static String[] incorrectWords;
    private static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'á', 'à', 'ã', 'â', 'Á', 'À', 'Ã',
            'Â', 'é', 'ê', 'É', 'Ê', 'í', 'ì', 'î', 'Í', 'Ì', 'Î', 'ó', 'ò', 'õ', 'ô', 'Ó', 'Ò', 'Õ', 'Ô', 'ú', 'ù',
            'û', 'Ú', 'Ù', 'Û', '-' };

    public static void main(String[] args) {

        String fileName = "Trabalho4/wordlist-ao-2020.txt";

        File file = new File(fileName);

        createDictionary(file);

        String text = "";
        incorrectWords = new String[text.length()];

        addLetters("ador");
    }

    private static String[] addLetters(String word) {
        int index = 0;
        int correctWordIndex = 0;
        String newWord;

        //Array com espaço suficiente para todas as sugestões
        String[] correctedWords = new String[word.length() * 2];

        for(int i = 0; i < alphabet.length; i++) {

            //Reset da palvra e do index
            index = 0;
            newWord = word + " ";

            // word length + 1 porque estamos a adiconar uma letra nova (potencialmente)
            while(hashtable.get(newWord) == null && index < word.length() + 1) {
                newWord = addChar(word, alphabet[i], index);
                if(hashtable.get(newWord) != null) {
                    correctedWords[correctWordIndex] = newWord;
                    correctWordIndex++;
                }
                
                index++;
            } 
        }
        
        for(int i = 0; i < correctedWords.length; i++) {
            if(correctedWords[i] != null) {
                System.out.println(correctedWords[i]);
            }
        }

        return correctedWords;
    }

    private void removeLetter(String word) {

    }

    private void swapLetters(String word) {

    }

    private static String addChar(String word, char letter, int index) {

        StringBuilder newWord = new StringBuilder(word);
        newWord.insert(index, letter);
        return newWord.toString();
    }

    private static void createDictionary(File file) {
        
        try{

            int numberOfLines = getNumberOfLines(file);
            hashtable = new Hashtable<String>(numberOfLines);

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                hashtable.put(line, line);
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException ex){
            System.out.println("File not found");
        }
    }

    private static int getNumberOfLines(File file) {
        int numberOfLines = 0;


        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                numberOfLines++;
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException ex){
            System.out.println("File not found");
        }
        return numberOfLines;
    }
}

