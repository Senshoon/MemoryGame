import enums.Levels;
import interfaces.IFileReader;
import level.Level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<Levels, Level> difficuty = new HashMap<>();
        difficuty.put(Levels.EASY,new Level( 4, 10,Levels.EASY));
        difficuty.put(Levels.HARD, new Level(8,15,Levels.HARD));

        IFileReader reader = new FileReader();
        List<String> words = reader.Read();
        if(words == null){
            System.out.println("No file found");
        }else{
            System.out.println(words.size());
            for (String word : words) {
                System.out.println(word);
            }
        }





    }
}