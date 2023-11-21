package com.clashofcards.models;/*
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
        newDeckList.add(new Card(11, "Beast", 2, 1));
        newDeckList.add(new Card(12, "Reptile", 2, 1));
        newDeckList.add(new Card(13, "Spider", 2, 1));
        newDeckList.add(new Card(14, "Rodent", 2, 1));
        newDeckList.add(new Card(15, "Minotaur", 2, 1));
        newDeckList.add(new Card(16, "Phoenix", 2, 1));
    }

    @Test
    public void showHandTest() {
        printCards(newDeckList);
    }
}