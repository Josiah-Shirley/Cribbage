import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;


public class Game {
    Player[] players;
    Player currentCribHolder;
    Player currentLeader;
    ArrayList<Card> deck;
    boolean playersAdded;

    public Game() {
        players = null;
        currentCribHolder = null;
        currentLeader = null;
        deck = null;
        playersAdded = false;
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
        if (players.length == 2) {
            playersAdded = true;
        }

            // This creates the cards for the game (All 52 cards)
        deck = new ArrayList<Card>();
        String[] suits = new String[]{"hearts", "spades", "daimonds", "clubs"};
        String[] values = new String[]{"ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
        for (int i=0; i<suits.length; i++) {
            for (int j=0; j<values.length; j++) {
                Card newCard = new Card(values[j], suits[i]);
                deck.add(newCard);
            } // end of for loop iterating through values
        } // end of for loop iterating through suits

    } // end of setupGame() method

    public boolean dealCards() {
        boolean handsDealtCorrectly = false;
        boolean deckInitializedCorrectly = false;
        boolean playersInitializedCorrectly = false;
        boolean cardsDealtAreUnique = false;
        boolean isSuccess = false;
        ArrayList<Card> toBeDealt = new ArrayList<>();
        if (playersAdded) {     // This only works if both players have been initialized (i.e. if setupGame() has been executed.)
            Random rand = new Random();
            Integer random;
            boolean stillDealing = true;
            while (stillDealing) {
                random = rand.nextInt(52);
                Card newCard = deck.get(random);
                while (toBeDealt.contains(newCard)) {
                    random = rand.nextInt(52);
                    newCard = deck.get(random);
                }
                toBeDealt.add(newCard);
                if (toBeDealt.size() >= 12) {
                    stillDealing = false;
                }
            }   // end of while loop that executes as long as cards are still being selected to be dealt
            // This checks that each card in the toBeDealt collection is only there once.
            // This is accomplished by focusing on a card, removing it from the collection, 
            // and then checking to see if the card is still in the collection
            // which would mean that it is in the collection at least twice.
            // After this it puts the card back exactly where it got it from.
            int correctlyDealt = 0;
            for (int i=0; i<toBeDealt.size(); i++) {
                Card currentCard = toBeDealt.get(i);
                int indexOfCurrentCard = toBeDealt.indexOf(currentCard);
                toBeDealt.remove(indexOfCurrentCard);
                if (!toBeDealt.contains(currentCard)) {
                    correctlyDealt++;
                }
                toBeDealt.add(indexOfCurrentCard, currentCard);
            }
            if (correctlyDealt == 12) {
                cardsDealtAreUnique = true;
            } else if (correctlyDealt > 12) {
                System.out.println("ERROR: Too many cards were dealt.");
            } else {
                System.out.println("ERROR: Cards dealt incorrectly.");
                System.out.println(correctlyDealt + " cards have been dealt correctly.");
            }
            // This part deals the cards to each player's hands.
            for (int i=0; i<players.length; i++) {
                for (int j=0; j<6; j++) {
                    players[i].cards.add(toBeDealt.get(j));
                }
                for (int j=0; j<6; j++) {
                    toBeDealt.remove(0);
                }
            }
            // This section does a few checks to make sure everything has been done correctly so far.
            // Certain errors would make it past this check, but major errors such as a deck with more or less
            // than 52 cards, more or less than 2 players playing, or players having more or less than 6 cards
            // each would be caught here.
            if (players[0].cards.size() == 6 && players[1].cards.size() == 6) {
                handsDealtCorrectly = true;
            } else {
                System.out.println("ERROR: One or more player's hands do not contain 6 cards exactly.");
            }
            if (deck.size() == 52) {
                deckInitializedCorrectly = true;
            } else {
                System.out.println("ERROR: Deck size is not 52.");
            }
            if (players.length == 2) {
                playersInitializedCorrectly = true;
            } else {
                System.out.println("ERROR: Number of players is not 2.");
            }
            if (handsDealtCorrectly && deckInitializedCorrectly && playersInitializedCorrectly && cardsDealtAreUnique) {
                isSuccess = true;
            }
        } // end of block executed if players have been added already
        return (isSuccess);
    } // end of dealCards() method

    public void printPlayersAndHands() {
        for (int i=0; i<players.length; i++) {
            System.out.println(players[i].name);
            for (int j=0; j<players[i].cards.size(); j++) {
                System.out.println(players[i].cards.get(j).value + " of " + players[i].cards.get(j).suit);
            }
        }
    }

}
