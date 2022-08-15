package game;

import enums.Levels;
import level.Level;

import java.util.*;

import static game.Board.CODE_A;
import static game.Board.NUM_COL;

public class MemoryGame {
    private List<String> words;
    private Map<Levels, Level> difficulty = new HashMap<>();
    private List<Pair> pairs = new ArrayList<>();
    private Levels currentLevel = Levels.NONE;
    private Pair firstChoice = null;
    private int counterChances;
    private int counterFoundedPair;
    private boolean fail = false;

    public MemoryGame(List<String> words) {
        this.words = words;
        difficulty.put(Levels.EASY, new Level(4, 10, Levels.EASY));
        difficulty.put(Levels.HARD, new Level(8, 15, Levels.HARD));
    }

    private void createPair() {
        Level current = this.difficulty.get(this.currentLevel);
        List<String> randomWords = current.draw(this.words);

        if (randomWords != null) {
            List<Position> positions = Board.initBoard(current);
            for (String word : randomWords) {
                Position pos1 = positions.get(0);
                positions.remove(0);
                Position pos2 = positions.get(0);
                positions.remove(0);
                Card card1 = new Card(pos1);
                Card card2 = new Card(pos2);
                Pair pair = new Pair(word, card1, card2);
                this.pairs.add(pair);
            }

        }

    }

    private void game() {

        System.out.println("Welcome!");
        this.difficultyChoice();
        Level level = this.difficulty.get(this.currentLevel);
        int chances = level.getChances();
        this.counterChances = chances;
        this.counterFoundedPair = 0;
        this.createPair();

        do {
            Board.displayBoard(this.pairs, level.getType(), this.counterChances);
            if (this.firstChoice == null) {
                this.revealFirstWord();
            } else {
                this.revealSecondWord();
            }
        } while (this.counterChances != 0 && this.counterFoundedPair != this.pairs.size());

        if (this.counterChances == 0) {
            System.out.println("Sorry, you loose!");
        } else {
            Board.displayBoard(this.pairs, level.getType(), this.counterChances);
            System.out.println("You win!");
        }
    }

    public void initGame() {
        this.currentLevel = Levels.NONE;
        this.firstChoice = null;
        this.pairs.clear();   //cleaning all pairs, when game is restarted
        this.game();
    }

    private void difficultyChoice() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Choose game difficulty (0 - EASY, 1 - HARD): ");
            try {
                Integer input = Integer.valueOf(scanner.nextLine());

                if (input == 0 || input == 1) {
                    this.currentLevel = Levels.values()[input];
                } else {
                    System.out.println("You tap incorrect value!");
                }

            } catch (NumberFormatException | NoSuchElementException | IllegalStateException ex) {
                System.err.println("Something goes wrong, try again");

            }

        } while (this.currentLevel.equals(Levels.NONE));

    }

    private Position userChoices() {
        Position pos = null;
        Scanner scanner = new Scanner(System.in);
        int numRow = (2*pairs.size()) / NUM_COL;
        char max = (char) (CODE_A + numRow);       // max value of rows in ASCII code
        System.out.println("Select and tap the coordination of card to reveal");

        do {
            try {
                String cords = scanner.nextLine().toUpperCase(Locale.ROOT);
                if (cords.length() == 2) {
                    int col = Integer.parseInt(cords.charAt(1) + "") - 1; // col starting  number from 0
                    int row = (cords.charAt(0) - Board.CODE_A);
                    if (0 <= col && col < (NUM_COL) && 0 <= row && row < numRow)  {

                    boolean isReveal = posIsAlreadyReveal(col, row);
                    if (!isReveal) {
                        pos = new Position(col, row);
                    } else {
                        System.out.println("Sorry, this word is already reveal!");
                    }
                    }else{
                        System.out.println("Wrong coordinates, choose between A-" + ((char) (CODE_A + numRow - 1))  + " and between 1-" + NUM_COL);
                    }
                }else{
                    System.out.println("Wrong coordinates, use correct ones ex. B2");

                }
            } catch (NumberFormatException | NoSuchElementException | IllegalStateException ex) {
                System.err.println("Something goes wrong, try again");
            }
        } while (pos == null);

        return pos;
    }

    private boolean posIsAlreadyReveal(int col, int row) {
        boolean isReveal = false;
        for (Pair pair : this.pairs) {

            if (pair.getCard1().getPosition().compare(col, row)) {
                if (pair.getCard1().isReveal()) {
                    isReveal = true;
                }
            } else if (pair.getCard2().getPosition().compare(col, row)) {
                if (pair.getCard2().isReveal()) {
                    isReveal = true;
                }

            }
        }
        return isReveal;
    }

    //player enter coordinate for first time word which will be revealed
    private void revealFirstWord() {
        Position pos = userChoices();
        if (pos != null) {
            int col = pos.getCol();
            int row = pos.getRow();
            for (Pair pair : this.pairs) {
                boolean isChoice = false;
                if (pair.getCard1().getPosition().compare(col, row)) {
                    if (!pair.getCard1().isReveal()) {
                        isChoice = true;
                        pair.getCard1().setReveal(true);
                    }
                } else if (pair.getCard2().getPosition().compare(col, row)) {
                    if (!pair.getCard2().isReveal()) {
                        isChoice = true;
                        pair.getCard2().setReveal(true);

                    }
                }
                if (isChoice) {
                    this.firstChoice = pair;
                }
            }
        }
    }

    private void revealSecondWord() {
        Position pos = userChoices() ;
            if (pos != null){
                int col = pos.getCol();
                int row = pos.getRow();
                Card card = null;
                if (!this.firstChoice.getCard1().isReveal()) {
                    card = this.firstChoice.getCard1();
                } else if (!this.firstChoice.getCard2().isReveal()) {
                    card = this.firstChoice.getCard2();
                }
                if (card.getPosition().compare(col, row)) {
                    card.setReveal(true);
                    this.counterFoundedPair++;
                    this.firstChoice = null;    //reset
                } else {
                    System.out.println("You missed");
                    this.counterChances--;
                    hideFirstWord();
                    this.firstChoice = null;
                }
            }
    }
    //TEST
    private void hideFirstWord(){
        boolean card1 = this.firstChoice.getCard1().isReveal();
        boolean card2 = this.firstChoice.getCard2().isReveal();
        if(card1 == true) this.firstChoice.getCard1().setReveal(false);
        else if(card2 == true)this.firstChoice.getCard2().setReveal(false);


    }
}