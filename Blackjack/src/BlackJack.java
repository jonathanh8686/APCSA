import java.util.Scanner;

public class BlackJack {
    Player player;
    Dealer dealer;

    public BlackJack() {
        player = new Player();
        dealer = new Dealer();

    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.playGame();
    }

    public void playGame() {

        dealer.shuffle();

        Scanner keyboard = new Scanner(System.in);
        char choice = 0;

        do {

            System.out.println("Play? (Y/N)");
            if(keyboard.next().toLowerCase().charAt(0) != 'y')
                break;

            System.out.println("\n\n\n\n\n\n");
            System.out.println("Starting game...");
            player.addCardToHand(dealer.deal());
            player.addCardToHand(dealer.deal());
            dealer.addCardToHand(dealer.deal());
            dealer.addCardToHand(dealer.deal());
            System.out.println("Dealt two cards to Player and Dealer...");

            System.out.println("PLAYER HAND");
            System.out.println(player);
            System.out.println(player.getHandValue());

            System.out.println("DEALER HAND");
            System.out.println(dealer);
            System.out.println(dealer.getHandValue());

            while (player.getHandValue() < 21) {
                System.out.print("Do you want to hit? (Y/N):\t");
                choice = keyboard.next().charAt(0);

                if (choice == 'Y')
                    player.addCardToHand(dealer.deal());
                else
                    break;
                System.out.println(player);
                System.out.println(player.getHandValue());
            }

            if (player.getHandValue() > 21) {
                System.out.println("Player Busted! - Dealer Wins.");
                dealer.setWinCount(dealer.getWinCount() + 1);
                continue;
            }

            System.out.println(dealer);
            System.out.println(dealer.getHandValue());
            while (dealer.hit())
                dealer.addCardToHand(dealer.deal());

            System.out.println("After dealing... the dealer has:");
            System.out.println(dealer);
            System.out.println(dealer.getHandValue());

            if (dealer.getHandValue() > 21) {
                System.out.println("Dealer Busted! - Player Wins.");
                player.setWinCount(player.getWinCount() + 1);
                continue;
            }

            if (player.getHandValue() > 21 || dealer.getHandValue() > player.getHandValue()) {
                System.out.println("Dealer Won.");
                dealer.setWinCount(dealer.getWinCount() + 1);

            } else if (player.getHandValue() > dealer.getHandValue() || dealer.getHandValue() > 21) {
                System.out.println("Player won!");
                player.setWinCount(player.getWinCount() + 1);

            } else
                System.out.println("Tie.");

            dealer.shuffle();
            dealer.resetHand();
            player.resetHand();


        } while (true);


    }
}