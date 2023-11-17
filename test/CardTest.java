/*
 * Class to test Card and it's methods
 */

import com.clashofcards.models.Card;
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


    @Test
    public void showCardsSeparately() {
        // System.out.println(deck);
        card1.print();
        System.out.println();
        card2.print();
        System.out.println();
        card3.print();
        System.out.println();
        card4.print();
        System.out.println();
        card5.print();
        System.out.println();
        card6.print();
        System.out.println();
    }

    @Test
    public void showHandTest(){
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