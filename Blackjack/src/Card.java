public abstract class Card {
    public static final String[] FACES = {"ZERO", "ACE", "TWO", "THREE", "FOUR",
            "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};

    private String suit;
    private int face;

    //constructors
    public Card(int fc, String st) {
        face = fc;
        suit = st;
    }

    public Card() {

    }

    //accessors
    public String getSuit() {
        return suit;
    }

    // modifiers
    public void setSuit(String newSuit) {
        suit = newSuit;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int newFace) {
        face = newFace;
    }

    public abstract int getValue();

    public boolean equals(Object obj) {
        Card c = (Card) obj;
        return c.getFace() == face && c.getSuit() == suit;
    }

    //toString
    public String toString() {
        return FACES[face] + " of " + getSuit();
    }

}