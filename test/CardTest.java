/*
 * Class to test Card and it's methods
 */

import com.clashofcards.Card;
import org.junit.Test;

public class CardTest extends Card {
    Card card = new Card(3, "Goblin", 2, 1);

    @Test
    public void showCard(){
        System.out.println(card);
    }
    @Test
    public void showGobo(){
        System.out.println(gobo());
    }

}