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
        newDeckList.add(new Card(11, "Demon", 2, 1));
        newDeckList.add(new Card(12, "Fairy", 2, 1));
        newDeckList.add(new Card(13, "Goblin", 2, 1));
        newDeckList.add(new Card(14, "Skeleton", 2, 1));
        newDeckList.add(new Card(15, "Vampire", 2, 1));
        newDeckList.add(new Card(16, "Zombie", 2, 1));
    }

    @Test
    public void showHandTest() {
        printCards(newDeckList);
    }
}