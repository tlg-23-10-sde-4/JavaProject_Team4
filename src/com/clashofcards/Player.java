package com.clashofcards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> graveyard;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.health = 20;
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.graveyard = new ArrayList<>();
        initializeDeck();
        drawInitialCards(7);
    }

    private void initializeDeck() {
        // Add cards to the deck, assuming you have a method to create cards
        // Card card1 = new Card("Card1", 3, 2);
        // deck.add(card1);
        // Add more cards to reach a deck size of 10
    }

    private void drawInitialCards(int numCards) {
        for (int i = 0; i < numCards; i++) {
            drawCard();
        }
    }

    public void drawCard() {
        if (!deck.isEmpty()) {
            Card drawnCard = deck.remove(0);
            hand.add(drawnCard);
        } else {
            // Handle case when the deck is empty, possibly reshuffle graveyard to deck
            // Or any specific game logic you might have for this situation
        }
    }

    public void playCard(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            graveyard.add(card);
            // Perform actions related to playing the card
            // For example, apply effects, modify game state, etc.
        } else {
            // Handle case when the card isn't in the player's hand
        }
    }

    public void startTurn() {
        drawCard();
    }

    // Other methods for game mechanics, health modification, etc.

    public int getHealth() {
        return health;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getGraveyard() {
        return graveyard;
    }
}
