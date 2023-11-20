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
    public void playCard(Prompter prompter, List<Card> playerBattlefield) {
        boolean valid = false;
        while (!valid) {

            String cardToPlay = prompter.prompt(" Pick a card to play from your hand (Enter the ID): ");

            Iterator<Card> iterator = getHand().iterator();
            while (iterator.hasNext()) {
                Card card = iterator.next();
                if (card.getIndex().equals(Integer.valueOf(cardToPlay))) {
                    iterator.remove(); // Remove the card from playerCards
                    playerBattlefield.add(card);
                    System.out.println(" You played: " + card.getName());
                    Game.delayGame(2);
                    valid = true;
                }
            }

            if (!valid) {
                System.out.println(" Invalid card ID, please pick a card from Your Hand");
            }
        }
    }

    // Attack with card
    public void attackWithCard(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField, Ai enemy, Prompter prompter) {
        boolean valid = false;
        while (!valid) {
            String cardIndexStr = prompter.prompt(" Enter the ID of the card you want to attack with from your battlefield: ").trim();
            try {
                int cardIndex = Integer.parseInt(cardIndexStr);
                boolean cardFound = false; // Flag to check if the selected card is found

                for (Card selectedCard : playerBattlefield) {
                    if (selectedCard.getIndex().equals(cardIndex)) {
                        System.out.println(" " + p.getName() + " chose to attack with: " + selectedCard.getName());
                        Game.delayGame(2);

                        if (!enemyBattleField.isEmpty()) {
                            Card enemyBlockingCard = enemy.enemyBlock(enemyBattleField, selectedCard);
                            if (enemyBlockingCard != null) {
                                System.out.println(" " + enemy.getName() + " chose to block with " + enemyBlockingCard.getName());
                                Game.delayGame(2);
                                Game.calculateBattleResults(enemyBlockingCard, selectedCard, p, enemy, playerBattlefield, enemyBattleField, false);
                                valid = true;
                            }
                        } else {
                            System.out.println(" " + enemy.getName() + " has no cards to block with");
                            Game.delayGame(2);
                            System.out.println(" " + enemy.getName() + " took " + selectedCard.getToughness() + " damage!");
                            enemy.setHealth(enemy.getHealth() - selectedCard.getToughness());
                            Game.delayGame(2);
                            valid = true;
                        }

                        cardFound = true;
                        break; // Exit the loop once a matching card is found, otherwise the Invalid card will be triggered
                    }
                }

                if (!cardFound) {
                    System.out.println(" Invalid card ID. Please enter a valid ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a valid number.");
            }
        }
    }

    // Draw a card
    public void drawCard() {
        System.out.println(" You draw a card...");
        Game.delayGame(2);
        Card drawnCard = Game.handleCardDraw(getHand(), getDeck());
        System.out.println(" You drew a " + drawnCard.getName());
        Game.delayGame(3);
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

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getDeck() {
        return deck;
    }
}