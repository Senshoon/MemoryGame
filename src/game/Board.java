package game;

import enums.Levels;
import level.Level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    public static final int NUM_COL = 4;
    public static final int CODE_A = 65;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static List<Position> initBoard(Level level){
        int numWords = level.getNumberWords() * 2;
        int numRow = numWords / NUM_COL;

        List<Position> positions = new ArrayList<>();

        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < NUM_COL; j++){

                positions.add(new Position(j,i));

            }
        }


    Collections.shuffle(positions);
    return positions;
    }

    public static void displayBoard(List<Pair> pairs, Levels level, int chances) {
        int numRow = (2*pairs.size()) / NUM_COL;
//        System.out.print("\033[H\033[2J");  cleaning command console
//        System.out.flush();
        System.out.println("Level " + level.toString());
        System.out.println("Guess chance " + chances);
        System.out.print(" ");
        for(int i = 1; i <= NUM_COL; i++ ){
            System.out.print(i+" ");
        }
        System.out.println("");
        for(int i = 0; i < numRow; i++){
            char id = (char) (CODE_A + i);
            System.out.print(id+ " ");
            for(int j = 0; j < NUM_COL; j++){
                for (Pair pair: pairs) {
                    boolean display = false;
                    boolean isFirstChoice = false;
                    if(pair.getCard1().getPosition().compare(j, i)){
                        isFirstChoice = pair.getCard1().isReveal();
                        display = true;
                    }else if (pair.getCard2().getPosition().compare(j, i)){
                        isFirstChoice = pair.getCard2().isReveal();
                        display = true;
                    }
                    if(isFirstChoice){
                        System.out.print(ANSI_GREEN + pair.getWord()+ " " + ANSI_RESET);
                    }else if(display){
                        System.out.print("X" + " ");
                    }
                }
            }
            System.out.println("");
        }
    }

}
