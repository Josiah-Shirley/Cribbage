import java.util.ArrayList;

public class Player {
    String name;
    ArrayList<Card> cards = new ArrayList<>();
    boolean hasCrib;
    PlayBehavior playbehavior;
    int score;

    public Player(String n) {
        name = n;
    }



}