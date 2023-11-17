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

public class Player {
    private static final String dataFilePath = "Data/Cards.csv";
    private String name;
    private int health = 20;
    private List<Card> deck = initializeDeck();
    private List<Card> hand = initializeDeck();
    private List<Card> graveyard;


    public Player() {}

    // Business Methods
    private List<Card> initializeDeck() {
        List<Card> newDeck = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(dataFilePath));

            Collections.shuffle(lines);

            for (int i = 0; i < 10; i++) {
                String[] tokens = lines.get(i).split(",");
                String id = tokens[0].trim();
                String name = tokens[1].trim();
                int attack = Integer.parseInt(tokens[2].trim());
                int defense = Integer.parseInt(tokens[3].trim());

                newDeck.add(new Card(id, name, attack, defense));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newDeck;
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

    public void playCard(Prompter prompter, List<Card> playerBattlefield) {
            boolean valid = false;
            while (!valid) {
                System.out.println();

                String cardToPlay = prompter.prompt("Pick a card to play from your hand or pass (Enter the ID): ");

                // Use an iterator to avoid ConcurrentModificationException
                Iterator<Card> iterator = getHand().iterator();
                while (iterator.hasNext()) {
                    Card card = iterator.next();
                    if (card.getIndex().equals(Integer.valueOf(cardToPlay))) {
                        iterator.remove(); // Remove the card from playerCards
                        playerBattlefield.add(card);
                        System.out.println("You played: " + card.getName());
                        Game.delayGame(2);
                        valid = true;
                    }
                }

                if (!valid) {
                    System.out.println("Invalid card ID");
                }
            }
    }

    public void attackWithCard(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField, Ai enemy, Prompter prompter) {
        System.out.println("Select a card to attack with:");
        boolean valid = false;
        while (!valid) {
            String cardIndexStr = prompter.prompt("Enter the ID of the card you want to attack with from your battlefield!(13): ");
            try {
                int cardIndex = Integer.parseInt(cardIndexStr);
                for(Card selectedCard : playerBattlefield) {
                    if (selectedCard.getIndex().equals(cardIndex)) {
                        System.out.println(p.getName() + " chose to attack with: " + selectedCard.getName());
                        Game.delayGame(1);

                        if(!enemyBattleField.isEmpty()) {
                            Card enemyBlockingCard = enemy.enemyBlock(enemyBattleField, selectedCard);
                            if(enemyBlockingCard != null) {
                                Game.calculateBattleResults(enemyBlockingCard, selectedCard, p, enemy, playerBattlefield, enemyBattleField, false);
                            }
                        }

                        System.out.println(enemy.getName() + " has no cards to block with");
                        Game.delayGame(2);
                        System.out.println(enemy.getName() + " took " + selectedCard.getToughness() + " damage!");
                        enemy.setHealth(enemy.getHealth() - selectedCard.getToughness());
                        Game.delayGame(2);

                        valid = true;
                    } else {
                        System.out.println("Invalid card ID. Please enter a valid ID.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public void startTurn() {
        drawCard();
    }

    // Getter/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public Integer getHealth() {
        return health;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getGraveyard() {
        return graveyard;
    }

    public void setGraveyard(List<Card> graveyard) {
        this.graveyard = graveyard;
    }
}