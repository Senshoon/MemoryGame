package level;

import enums.Levels;

public class Level {
    private int numberWords;
    private int chances;
    private Levels type;

    public int getChances() {
        return chances;
    }

    public int getNumberWords() {
        return numberWords;
    }

    public Levels getType() {
        return type;
    }

    public Level(int numberWords, int chances, Levels type) {
        this.numberWords = numberWords;
        this.chances = chances;
        this.type = type;

    }
}
