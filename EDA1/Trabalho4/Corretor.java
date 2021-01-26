package Trabalho4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Corretor {

    private static Hashtable<String> hashtable;
    private static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'á', 'à', 'ã', 'â', 'Á', 'À', 'Ã',
            'Â', 'é', 'ê', 'É', 'Ê', 'í', 'î', 'Í', 'Î', 'ó', 'õ', 'ô', 'Ó', 'Õ', 'Ô', 'ú', 'Ú', '-' };

    public static void main(String[] args) {

        String fileName = "Trabalho4/wordlist-ao-2020.txt";

        File file = new File(fileName);

        createDictionary(file);


        checkTextForErrors("Olá o meu nme  Arminda e eu ador longos passeis pela praia enquanto me penetam por tras sou uma besta peluda");

    }

    private static String[] checkTextForErrors(String text) {

        String[] splitText = text.split("\\s+");
        String[] incorrectWords = new String[splitText.length];
        int incorrectWordsIndex = 0;

        for(int i = 0; i < splitText.length; i++) {
            if(hashtable.get(splitText[i]) == null) {
                incorrectWords[incorrectWordsIndex] = splitText[i];
                incorrectWordsIndex++;
            }
        }

        for(int i = 0; i < incorrectWords.length; i++) {
            if(incorrectWords[i] != null) {
                System.out.println("Palavra incorreta: " + incorrectWords[i]);
                System.out.println("Sugestões: " + giveWordSugestion(incorrectWords[i]) );
            }
        }

        return incorrectWords;
       
    }

    private static String giveWordSugestion(String word) {
        String sugestions = "";

        String[] wordsWithAddedLetters = addLetters(word);
        String[] wordsWithRemovedLetters = removeLetters(word);
        String[] wordsWithSwappedLetters = swapLetters(word);

        for(int i = 0; i < wordsWithAddedLetters.length; i++) {

            if(wordsWithAddedLetters[i] == null) {
                break;
            }

            sugestions += wordsWithAddedLetters[i] + ",";
        }

        for(int i = 0; i < wordsWithRemovedLetters.length; i++) {

            if(wordsWithRemovedLetters[i] == null) {
                break;
            }

            sugestions += wordsWithRemovedLetters[i] + ",";
        }

        for(int i = 0; i < wordsWithSwappedLetters.length; i++) {

            if(wordsWithSwappedLetters[i] == null) {
                break;
            }

            sugestions += wordsWithSwappedLetters[i] + ",";
        }

        return sugestions;
    }

    private static String[] addLetters(String word) {
        int index = 0;
        int correctWordIndex = 0;
        String newWord;

        //Array com espaço suficiente para todas as sugestões
        String[] correctedWords = new String[word.length() * 2];

        for(int i = 0; i < alphabet.length; i++) {

            //Reset da palvra e do index onde vamos adicionar uma letra nova
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

        return correctedWords;
    }

    private static String[] removeLetters(String word) {

        int index = 0;
        int correctWordIndex = 0;
        String newWord = word;

        //Array com espaço suficiente para todas as sugestões
        String[] correctedWords = new String[word.length()];

        while(hashtable.get(newWord) == null && index < word.length()) {
            newWord = deleteChar(word, index);
            if(hashtable.get(newWord) != null) {
                correctedWords[correctWordIndex] = newWord;
                correctWordIndex++;
            }
                
            index++;
        }

        return correctedWords;
        
    }

    private static String[] swapLetters(String word) {

        int index = 0;
        int correctWordIndex = 0;
        String newWord = word;

        //Array com espaço suficiente para todas as sugestões
        String[] correctedWords = new String[word.length()];

        while(index < word.length() - 1) {
            newWord = swapChars(word, index);

            if(hashtable.get(newWord) != null) {
                correctedWords[correctWordIndex] = newWord;
                correctWordIndex++;
            }
                
            index++;
        }

        return correctedWords;
        
    }

    private static String addChar(String word, char letter, int index) {

        StringBuilder newWord = new StringBuilder(word);
        newWord.insert(index, letter);
        return newWord.toString();
    }

    private static String deleteChar(String word, int index) {

        StringBuilder newWord = new StringBuilder(word);
        newWord.deleteCharAt(index);
        return newWord.toString();
    }

    private static String swapChars(String word, int index) {

        StringBuilder newWord = new StringBuilder(word);
        char firstLetter =  newWord.charAt(index);
        char secondLetter = newWord.charAt(index + 1);
        
        newWord.setCharAt(index, secondLetter);
        newWord.setCharAt(index + 1, firstLetter);

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

