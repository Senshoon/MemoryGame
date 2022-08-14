package game;

import enums.Levels;
import enums.StateGame;
import level.Level;

import java.util.*;

public class MemoryGame {
    private List<String> words;
    private  Map<Levels, Level> difficulty = new HashMap<>();
    private  List<Pair> pairs = new ArrayList<>();
    private Levels currentLevel = Levels.NONE;
    private Pair firstChoice = null;
    private int counter = 0;

    public MemoryGame(List<String> words) {
        this.words = words;
        difficulty.put(Levels.EASY,new Level( 4, 10,Levels.EASY));
        difficulty.put(Levels.HARD, new Level(8,15,Levels.HARD));
    }

    private void createPair(){
        Level current = this.difficulty.get(this.currentLevel);
        List<String> randomWords = current.draw(this.words);

        if(randomWords != null){
            List<Position> positions = Board.initBoard(current);
            for(String word: randomWords){
                Position pos1 = positions.get(0);
                positions.remove(0);
                Position pos2 = positions.get(0);
                positions.remove(0);
                Card card1 = new Card(pos1);
                Card card2 = new Card(pos2);
                Pair pair = new Pair(word, card1, card2,false);
                this.pairs.add(pair);
            }

        }

    }
    private void game(){

        System.out.println("Welcome!");
        this.difficultyChoice();
        int chances = this.difficulty.get(this.currentLevel).getChances();
        this.createPair();

        do{
            Board.displayBoard(this.pairs);
            if(this.firstChoice == null){
                this.userChoices();
            }

        }while(this.counter != chances);
        Board.displayBoard(this.pairs);
    }
    public void initGame(){
        this.currentLevel = Levels.NONE;
        this.pairs.clear();
        this.game();
    }

    private void difficultyChoice(){
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Choose game difficulty (0 - EASY, 1 - HARD): ");
            try{
                Integer input = Integer.valueOf(scanner.nextLine());

                if(input == 0 || input == 1){
                    this.currentLevel = Levels.values()[input];
                }else{
                    System.out.println("You tap incorrect value!");
                }

            }catch (NumberFormatException | NoSuchElementException | IllegalStateException ex){
                System.err.println("Something goes wrong, try again");

            }

        }while(this.currentLevel.equals(Levels.NONE));

    }

    private void userChoices(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select and tap he coordination of card to reveal");

        do{
            try{
                String cords = scanner.nextLine().toUpperCase(Locale.ROOT);
                if(cords.length() == 2){
                    int col = Integer.parseInt(cords.charAt(1) + "") - 1; // col starting  number from 0
                    int row = (cords.charAt(0)-65);
                    for (Pair pair : this.pairs) {
                        if(!pair.isFound()){
                            boolean isChoice = false;
                            if(pair.getCard1().getPosition().getCol() == col && pair.getCard1().getPosition().getRow() == col){
                                if(!pair.getCard1().isReveal()){
                                    isChoice = true;
                                    pair.getCard1().setReveal(true);
                                }else{
                                    System.out.println("Sorry, this word is already reveal!");
                                }

                            }else if(pair.getCard2().getPosition().getCol() == row && pair.getCard2().getPosition().getRow() == row){
                                if(!pair.getCard2().isReveal()){
                                    isChoice = true;
                                    pair.getCard2().setReveal(true);
                                }else{
                                    System.out.println("Sorry, this word is already reveal!");
                                }
                            }

                            if(isChoice){
                                this.firstChoice = pair;
                            }
                        }else{
                            System.out.println("Sorry, this word is already reveal!");
                        }
                    }

                }
            }catch (NumberFormatException | NoSuchElementException | IllegalStateException ex){
                System.err.println("Something goes wrong, try again");

            }
        }while(this.firstChoice == null);

    }

}
