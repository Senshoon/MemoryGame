package game;

public class Pair {
    private String word;
    private Card card1;
    private Card card2;


    public Pair(String word, Card card1, Card card2) {
        this.word = word;
        this.card1 = card1;
        this.card2 = card2;
    }

    public String getWord() {
        return word;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }
}
