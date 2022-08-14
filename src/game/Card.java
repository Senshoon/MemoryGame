package game;

public class Card {
    private Position position;
    private boolean isReveal = false;
    public Card(Position position) {
        this.position = position;
    }

    public boolean isReveal() {
        return isReveal;
    }

    public void setReveal(boolean reveal) {
        isReveal = reveal;
    }

    public Position getPosition() {
        return position;
    }
}
