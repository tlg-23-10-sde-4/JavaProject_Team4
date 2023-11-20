package com.clashofcards.models;

import com.apps.util.Prompter;
import com.clashofcards.utils.Game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class Player {
    private static final String dataFilePath = "Data/Cards.csv";
    private String name;
    private int health = 20;
    private final List<Card> hand = initializeCards(7);
    private final List<Card> deck = initializeCards(33);

    public Player() {
    }


    /*
     * Business Methods
     * These will include all necessary actions from the player and AI
     */

    // initialize the deck and hand at random
    private List<Card> initializeCards(int numberOfCards) {
        List<Card> cards = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(dataFilePath));

            Collections.shuffle(lines);

            for (int i = 0; i < numberOfCards; i++) {
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
}