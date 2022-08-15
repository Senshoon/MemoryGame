import interfaces.IFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
         //this class if for reading list of words from TXT file.
public class FileReader implements IFileReader {
    public static final String PATH_NAME = "Words.txt";
    public List<String> read() {
        try{
            List<String> words = new ArrayList<>();
            File file = new File(PATH_NAME);
            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                String word = in.nextLine();
                words.add(word);
            }
            in.close();   //close for reading file, when loading is done
            return words;
        }catch (FileNotFoundException ex){
            return null;
        }

    }

}
