package level;

import enums.Levels;
import interfaces.IDraw;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Level implements IDraw<String> {
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

    @Override
    public List<String> draw(List<String> list) {
        if (list == null && list.size() < numberWords)
            return null;

        Collections.shuffle(list);
        List<String> randomSeries = list.subList(0, this.numberWords);//randomizing words, number depending on selected level

        return randomSeries;
    }
}
