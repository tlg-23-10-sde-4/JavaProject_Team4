package com.clashofcards.models;

import com.apps.util.Prompter;
import com.clashofcards.utils.Game;

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
     *
     * These will include all necessary actions from the player and AI
     */
    public void resetPlayer() {
        setHealth(20);
        initializeDeckAndHand();
    }

    private void initializeDeckAndHand() {
        List<Card> cards = initializeCards();  // Assuming 40 cards in total
        Collections.shuffle(cards);  // Shuffle the cards

        hand = new ArrayList<>(cards.subList(0, 7));  // Assign the first 7 cards to hand

        deck = new ArrayList<>(cards.subList(7, 40)); // Assign the next 33 cards to deck
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
    public Card enemyBlock(List<Card> enemyBattleField, Card playerAttackingCard) {
        return null;
    }

    // Check and handle our notifications for the Ai's chosen card, this should only be used by those classes
    public void handleAiCardToPlay(List<Card> enemyBattleField, Card chosenCard) {
        if (chosenCard != null) {
            getHand().remove(chosenCard);
            enemyBattleField.add(chosenCard);
            Game.delayGame(2);
            System.out.println(" " + getName() + " played " + chosenCard.getName());
            Game.delayGame(2);
        } else {
            Game.delayGame(2);
            System.out.println(" " + getName() + "has no cards to play!");
            Game.delayGame(2);
        }
    }

    // Check and handle our Ai's attack phase, this should only be used by those classes
    public void handleAiAttack(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField, Player enemy, Prompter prompter, Card chosenCard) {
        boolean valid = false;
        if (chosenCard != null) {
            System.out.println(" Enemy is attacking with " + chosenCard.getName() + "\n");
            Game.delayGame(2);

            if (!playerBattlefield.isEmpty()) {
                while (!valid) {
                    String blockChoice = prompter.prompt(" Would you like to block (y/n)?: ").trim().toLowerCase();
                    System.out.println();
                    valid = Game.handleBlockChoice(playerBattlefield, p, enemyBattleField, enemy, prompter, chosenCard, valid, blockChoice);
                }
            } else {
                System.out.println(" " + p.getName() + " has no cards to block with");
                Game.handleDirectDamage(p, chosenCard);
            }
        } else {
            System.out.println(" Enemy has no cards to attack with or chose not to attack");
            Game.delayGame(2);
        }
    }


    // Getter and Setters
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