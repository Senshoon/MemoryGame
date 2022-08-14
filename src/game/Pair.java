package game;

public class Pair {
    private String word;
    private Card card1;
    private Card card2;
    private boolean isFound = false;

    public Pair(String word, Card card1, Card card2, boolean isFound) {
        this.word = word;
        this.card1 = card1;
        this.card2 = card2;
        this.isFound = isFound;
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

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }
}
