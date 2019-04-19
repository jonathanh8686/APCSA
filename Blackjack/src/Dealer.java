//define Dealer class here
class Dealer extends AbstractPlayer {
    private Deck deckOfCards;

    //constructors
    public Dealer() {
        deckOfCards = new Deck();
    }


    //method to shuffle
    public void shuffle() {
        deckOfCards.shuffle();
    }


    //method to deal a card
    public Card deal() {
        return deckOfCards.nextCard();
    }


    //hit method goes here
    @Override
    public boolean hit() {
        if (this.getHandValue() > 21) return false;
        return this.getHandValue() < 17;
    }
}
	

