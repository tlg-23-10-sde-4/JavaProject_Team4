/*
 * Class to test Card and it's methods
 */

import com.clashofcards.models.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CardTest extends Card {
    private final List<Card> newDeckList = new ArrayList<>();

    @Before
    public void init() {
        newDeckList.add(new Card("01", "Demon", 2, 1));
        newDeckList.add(new Card("02", "Fairy", 2, 1));
        newDeckList.add(new Card("03", "Goblin", 2, 1));
        newDeckList.add(new Card("04", "Skeleton", 2, 1));
        newDeckList.add(new Card("05", "Vampire", 2, 1));
        newDeckList.add(new Card("06", "Zombie", 2, 1));
    }

    @Test
    public void showHandTest() {
        workingPrintHand(newDeckList);
    }

    @Test
    public void showCardsInCustomHand() {
        newDeckList.add(card1);
     /*   newDeckList.add(card2);
        newDeckList.add(card3);
        newDeckList.add(card4);
        newDeckList.add(card5);
        newDeckList.add(card6);*/

        for (Card card : newDeckList) {
            card.print();
        }
    }

    @Test
    public void showWorkingHandMethod(){
            newDeckList.add(card1);
            newDeckList.add(card2);
            newDeckList.add(card3);
            newDeckList.add(card4);
            newDeckList.add(card5);
            newDeckList.add(card6);

        workingPrintHand(newDeckList);
    }


}