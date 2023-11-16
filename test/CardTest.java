/*
 * Class to test Card and it's methods
 */

import com.clashofcards.Card;
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
    List<Card> newDeckList = new ArrayList<>();

    @Test
    public void showCardsSeparately(){
       // System.out.println(deck);
        card1.print(card1);
        System.out.println();
        card2.print(card2);
        System.out.println();
        card3.print(card3);
        System.out.println();
        card4.print(card4);
        System.out.println();
        card5.print(card5);
        System.out.println();
        card6.print(card6);
        System.out.println();
    }

    @Test
    public void showCardsInCustomHand(){
            for(Card card : newDeckList){
                for(String line : card.getImageLines()){
                    System.out.println(line);
                }
            }
    }

}