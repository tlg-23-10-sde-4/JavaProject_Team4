package com.clashofcards.models;

import com.apps.util.Prompter;
import com.clashofcards.utils.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Ai extends Player {
    public Ai() {
        super();
    }

    // Custom AI blocking method, should return card from the AI's battlefield
    @Override
    public Card enemyBlock(List<Card> enemyBattleField, Card playerAttackingCard) {
        Card chosenCard = null;
        List<Card> eligibleCards = enemyBattleField.stream()
                .filter(card -> card.getToughness() < playerAttackingCard.getStrength())
                .collect(Collectors.toList());
        for (Card card : enemyBattleField) {
            if (card.getToughness() >= playerAttackingCard.getStrength()) {
                chosenCard = card;
            } else {
                if (!eligibleCards.isEmpty()) {
                    // If there are eligible cards, choose a random one
                    Random random = new Random();
                    int randomIndex = random.nextInt(eligibleCards.size());
                    chosenCard = eligibleCards.get(randomIndex);
                }
            }
        }
        return chosenCard;
    }

    @Override
    public void playCard(Prompter prompter, List<Card> enemyBattleField) {
        System.out.println(" " + getName() + "'s turn to play a card");
        Game.delayGame(2);
        List<Card> eligibleCards = new ArrayList<>(getHand());
        Card chosenCard = null;

        // Get a random card from the Ai's hand
        Random random = new Random();
        int randomIndex = random.nextInt(eligibleCards.size());
        chosenCard = eligibleCards.get(randomIndex);

        // Ensure its not null
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

    @Override
    public void attackWithCard(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField, Player enemy, Prompter prompter) {
        Card chosenCard = null;

        // Ai picks random card
        Random random = new Random();
        int randomIndex = random.nextInt(enemyBattleField.size());
        chosenCard = enemyBattleField.get(randomIndex);

        boolean valid = false;
        if (chosenCard != null) {
            System.out.println(" Enemy is attacking with " + chosenCard.getName());
            Game.delayGame(2);

            if (!playerBattlefield.isEmpty()) {
                while (!valid) {
                    String blockChoice = prompter.prompt(" Would you like to block (y/n)").trim().toLowerCase();
                    if (blockChoice.equals("y") || blockChoice.equals("n")) {
                        if (blockChoice.equals("y")) {
                            String cardIndexStr = prompter.prompt(" Enter the ID of the card you'd like to block with from your battlefield: ").trim();
                            boolean cardFound = false;

                            for (Card selectedCard : playerBattlefield) {
                                if (selectedCard.getIndex().equals(Integer.valueOf(cardIndexStr))) {
                                    System.out.println(" " + p.getName() + " chose to block with: " + selectedCard.getName());
                                    Game.delayGame(2);

                                    Game.calculateBattleResults(chosenCard, selectedCard, p, enemy, playerBattlefield, enemyBattleField, true);
                                    Game.delayGame(2);
                                    valid = true;
                                    cardFound = true;
                                    break; // Exit the loop once a matching card is found
                                }
                            }

                            if (!cardFound) {
                                System.out.println(" Invalid input, please select the ID of a card from your battlefield (e.g., 13)");
                            }
                        } else {
                            System.out.println(" " + p.getName() + " chose not to block");
                            valid = Game.handleDirectDamage(p, chosenCard);
                        }
                    } else {
                        System.out.println(" Invalid input: please enter 'y' or 'n'");
                    }
                }
            } else {
                System.out.println(" " + p.getName() + " has no cards to block with");
                Game.handleDirectDamage(p, chosenCard);
            }
        }
    }

    // Draw card
    @Override
    public void drawCard() {
        Game.handleCardDraw(getHand(), getDeck());
        System.out.println(" " + getName() + " drew a card");
        Game.delayGame(3);
    }
}