import enums.Levels;
import enums.StateGame;
import game.MemoryGame;
import interfaces.IFileReader;
import level.Level;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        IFileReader reader = new FileReader();
        StateGame state = StateGame.START;

        List<String> words = reader.read();
        if(words == null) {
            System.out.println("No file found");
            state = StateGame.ERROR;
        }

        MemoryGame memoryGame = new MemoryGame(words);

        //Main loop of MemoryGame
        do{
            if(state.equals(StateGame.START)){
                memoryGame.initGame();
                state = StateGame.STOP;
            }
            if(state.equals(StateGame.STOP)){
                System.out.println("Do you wanna play again? tap 1");
                System.out.println("If you wanna exit the game tap 0");

                try{
                    Integer input = Integer.valueOf(scanner.nextLine());

                    if(input == 0 || input == 1){
                        state = StateGame.values()[input];
                    }else{
                        System.out.println("You tap incorrect value!");
                    }

                }catch (NumberFormatException | NoSuchElementException | IllegalStateException ex){
                    System.err.println("Something goes wrong, try again");

                }
            }


        }while(!state.equals(StateGame.EXIT) && !state.equals(StateGame.ERROR));

//        Map<Levels, Level> difficuty = new HashMap<>();
//        difficuty.put(Levels.EASY,new Level( 4, 10,Levels.EASY));
//        difficuty.put(Levels.HARD, new Level(8,15,Levels.HARD));
//
//        IFileReader reader = new FileReader();
//        List<String> words = reader.read();
//        if(words == null){
//            System.out.println("No file found");
//        }else{
//            System.out.println(words.size());
//            for (String word : words) {
//                System.out.println(word);
//            }
//         }





    }
}