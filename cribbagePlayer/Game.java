import java.util.Scanner;
import java.util.Random;


public class Game {
    Player[] players;
    Player currentCribHolder;
    Player currentLeader;
    Card[] deck;

    public Game() {
        players = null;
        currentCribHolder = null;
        currentLeader = null;
        deck = null;
    }

    public void setupGame() {

        // This adds the players to the game
        boolean addingPlayers = true;
        players = new Player[2];
        int currentPos = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            while (addingPlayers) {
                System.out.println("Adding player" + (currentPos+1) + "...");
                //String playerName = scanner.nextLine();       // This was giving me some problems so I'll come back to it later.
                Player newPlayer = new Player("player" + (currentPos+1));
                players[currentPos] = newPlayer;
                currentPos++;
                if (currentPos >= 2) {
                    addingPlayers = false;
                }
            } // end of while loop which helps initialize the players
        } // end of try statement which helps initialize the players

            // This creates the cards for the game (All 52 cards)
        deck = new Card[52];
        String[] suits = new String[]{"hearts", "spades", "daimonds", "clubs"};
        String[] values = new String[]{"ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
        currentPos = 0;
        for (int i=0; i<suits.length; i++) {
            for (int j=0; j<values.length; j++) {
                Card newCard = new Card(values[j], suits[i]);
                deck[currentPos] = newCard;
                currentPos++;
            } // end of for loop iterating through values
        } // end of for loop iterating through suits

    } // end of setupGame() method

    public void dealCard() {

        Random rand = new Random();
        int random = rand.nextInt(52);
        

    }
}
