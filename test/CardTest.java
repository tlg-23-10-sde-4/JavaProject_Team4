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
        newDeckList.add(new Card(1, "Devil", 2, 1));
        newDeckList.add(new Card(2, "Fairy", 2, 1));
        newDeckList.add(new Card(3, "Goblin", 2, 1));
        newDeckList.add(new Card(4, "Skeleton", 2, 1));
        newDeckList.add(new Card(5, "Vampire", 2, 1));
        newDeckList.add(new Card(6, "Zombie", 2, 1));
    }

    @Test
    public void showHandTest() {
        workingPrintHand(newDeckList);
    }
}