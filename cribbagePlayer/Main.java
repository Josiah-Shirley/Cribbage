public class Main {
    public static void main(String[] args) {
        
        Game newGame = new Game();
        newGame.setupGame();
        System.out.println(newGame.dealCards());
        
        /*      
        // Prints out both player's names for testing purposes
        for (int i=0; i<newGame.players.length; i++) {
            System.out.println(newGame.players[i].name);
        }
         * 
         */


        /*     
        // Prints out the deck for testing purposes
        for (int i=0; i<newGame.deck.length; i++) {
            System.out.println(newGame.deck[i].suit);
            System.out.println(newGame.deck[i].value);
        }
         */


    }
    
}

