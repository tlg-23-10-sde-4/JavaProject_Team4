package com.clashofcards.models;

import com.apps.util.Prompter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    private static final String dataFilePath = "Data/Cards.csv";
    private String name;
    private int health = 20;
    private List<Card> hand;
    private List<Card> deck;

    public Player() {
        initializeDeckAndHand();
    }

    /*
     * Business Methods
     * These will include all necessary actions from the player and AI
     */

    public void resetPlayer() {
        setHealth(20);
        initializeDeckAndHand();
    }

    private void initializeDeckAndHand() {
        List<Card> cards = initializeCards();  // Assuming 40 cards in total
        Collections.shuffle(cards);  // Shuffle the cards

        // Assign the first 7 cards to hand
        hand = new ArrayList<>(cards.subList(0, 7));

        // Assign the next 33 cards to deck
        deck = new ArrayList<>(cards.subList(7, 40));
    }

    // initialize the deck and hand at random
    private List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(dataFilePath));

            for (int i = 0; i < 40; i++) {
                String[] tokens = lines.get(i).split(",");
                Integer id = Integer.parseInt(tokens[0].trim());
                String name = tokens[1].trim();
                int attack = Integer.parseInt(tokens[2].trim());
                int defense = Integer.parseInt(tokens[3].trim());

                cards.add(new Card(id, name, attack, defense));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cards;
    }

    // Play a card
    public abstract void playCard(Prompter prompter, List<Card> playerBattlefield);

    // Attack with card
    public abstract void attackWithCard(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField, Player enemy, Prompter prompter);

    // Draw a card
    public abstract void drawCard();

    // Blocking card for enemy
    public abstract Card enemyBlock(List<Card> enemyBattleField, Card playerAttackingCard);


    // Getter/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }
}