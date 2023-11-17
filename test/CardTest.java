/*
 * Class to test Card and it's methods
 */

import com.clashofcards.models.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CardTest extends Card {
    Card card1 = new Card(1, "Devil", 2, 1);
    Card card2 = new Card(2, "Fairy", 2, 1);
    Card card3 = new Card(3, "Goblin", 2, 1);
    Card card4 = new Card(4, "Skeleton", 2, 1);
    Card card5 = new Card(5, "Vampire", 2, 1);
    Card card6 = new Card(6, "Zombie", 2, 1);
    private final List<Card> newDeckList = new ArrayList<>();


    @Before
    public void init() {
        newDeckList.add(card1);
        newDeckList.add(card2);
        newDeckList.add(card3);
        newDeckList.add(card4);
        newDeckList.add(card5);
        newDeckList.add(card6);
    }

    @Test
    public void showCardsInCustomHand() {
        for (int i = 0; i < 9; i++) {
            for (Card card : newDeckList) {
                System.out.print(card.getImageLines().get(i) + "\t\t");
            }
            System.out.println(); // Move to the next line after printing each horizontal line
        }
    }
}