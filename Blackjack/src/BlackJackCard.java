public class BlackJackCard extends Card {
    private String suit;
    private int face;

    public BlackJackCard() {

    }

    public BlackJackCard(int fc, String st) {
        super(fc, st);
    }


    public int getValue() {
        //enables you to build the value for the game into the card
        //this makes writing the whole program a little easier
        if (getFace() == 1) return 11;
        if(getFace() >= 10) return 10;
        else return getFace();
    }

}