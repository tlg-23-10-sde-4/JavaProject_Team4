package com.clashofcards.renderer;

import com.clashofcards.models.Ai;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;
import com.apps.util.Prompter;
import com.clashofcards.utils.BattleFieldDisplay;
import com.clashofcards.utils.Helper;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;


public class AttackPhase {
    BattleFieldDisplay displayer = new BattleFieldDisplay();
    Prompter prompter = new Prompter(new Scanner(System.in));

    public void playerAttackPhase(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        displayer.updateBattleField(enemyBattleField, playerBattleField, player);

        if (!playerBattleField.isEmpty()) {
            boolean wantsToAttack = promptAttack(playerBattleField, player, enemyBattleField);
            displayer.updateBattleField(enemyBattleField, playerBattleField, player);
            if(wantsToAttack) {
                attackWithCard(playerBattleField,player,enemyBattleField, enemy);
            }
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player);

        playCard(player.getDeck(), playerBattleField); // player plays a card

        displayer.updateBattleField(enemyBattleField, playerBattleField, player);
    }

    private void playCard(List<Card> playerCards, List<Card> playerBattlefield) {
        if (!playerCards.isEmpty()) {
            boolean valid = false;
            while (!valid) {
                System.out.println();

                String cardToPlay = prompter.prompt("Pick a card to play from your hand or pass (Enter the ID): ");

                // Use an iterator to avoid ConcurrentModificationException
                Iterator<Card> iterator = playerCards.iterator();
                while (iterator.hasNext()) {
                    Card card = iterator.next();
                    if (card.getIndex().equals(Integer.valueOf(cardToPlay))) {
                        iterator.remove(); // Remove the card from playerCards
                        playerBattlefield.add(card);
                        System.out.println("You played: " + card.getName());
                        Helper.delayGame(2);
                        valid = true;
                    }
                }

                if (!valid) {
                    System.out.println("Invalid card ID");
                }
            }
        }
    }

    private void attackWithCard(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField, Ai enemy) {
        System.out.println("Select a card to attack with:");
        boolean valid = false;
        while (!valid) {
            String cardIndexStr = prompter.prompt("Enter the ID of the card you want to attack with from your battlefield!(13): ");
            try {
                int cardIndex = Integer.parseInt(cardIndexStr);
                for(Card selectedCard : playerBattlefield) {
                    if (selectedCard.getIndex().equals(cardIndex)) {
                        System.out.println(p.getName() + " chose to attack with: " + selectedCard.getName());
                        Helper.delayGame(1);

                        if(!enemyBattleField.isEmpty()) {
                            Card enemyBlockingCard = enemyBlock(enemyBattleField, selectedCard);
                            if(enemyBlockingCard != null) {
                                calculateBattleResults(enemyBlockingCard, selectedCard, p, enemy, playerBattlefield, enemyBattleField);
                            }
                        }

                        System.out.println(enemy.getName() + " has no cards to block with");
                        Helper.delayGame(2);
                        System.out.println(enemy.getName() + " took " + selectedCard.getToughness() + " damage!");
                        enemy.setHealth(enemy.getHealth() - selectedCard.getToughness());
                        Helper.delayGame(2);

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

    private boolean promptAttack(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField) {
        boolean valid = false;
        boolean wantsToAttack = false;
        while (!valid) {
            String attack = prompter.prompt("Would you like to attack (y/n)?");
            if (attack.equals("y") || attack.equals("n")) {
                valid = true;
                if (attack.equals("y")) {
                    System.out.println(p.getName() + " chose to attack!");
                    Helper.delayGame(2);
                    wantsToAttack = true;
                } else {
                    Helper.delayGame(2);
                    System.out.println(p.getName() + " chose not to attack");
                }
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
        return wantsToAttack;
    }

    private Card enemyBlock(List<Card> enemyBattleField, Card playerAttackingCard) {
        Card chosenCard = null;
        List<Card> eligibleCards = enemyBattleField.stream()
                .filter(card -> card.getToughness() < playerAttackingCard.getStrength())
                .collect(Collectors.toList());
        for(Card card : enemyBattleField) {
            if(card.getToughness() >= playerAttackingCard.getStrength()) {
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

    private void calculateBattleResults(Card enemyCard, Card playerCard, Player p, Ai e, List<Card> playerBattleField, List<Card> enemyBattleField) {
        if(enemyCard.getToughness() > playerCard.getStrength()) {
            System.out.println(e.getName() + "s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
            System.out.println(p.getName() + "s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
            System.out.println(e.getName() + " blocked all damage from " + p.getName() + " with " + enemyCard.getName());
        } else if(enemyCard.getToughness().equals(playerCard.getStrength())) {
            blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField);
        } else if(enemyCard.getToughness() < playerCard.getStrength()) {
            blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField);
            e.setHealth(e.getHealth() - (playerCard.getStrength() - enemyCard.getToughness()));
            Helper.delayGame(1);
            System.out.println(p.getName() + " hit " + e.getName() + " for " + (playerCard.getStrength()-enemyCard.getToughness()) + " points");
        }
    }

    private static void blockAndDestroy(Card enemyCard, Card playerCard, Player p, Ai e, List<Card> enemyBattleField) {
        System.out.println(p.getName() + "s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
        Helper.delayGame(1);

        System.out.println(e.getName() + "s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
        Helper.delayGame(1);

        System.out.println(e.getName() + " blocked " + p.getName() + " with " + enemyCard.getName());
        Helper.delayGame(1);

        System.out.println(e.getName() + "s " + enemyCard.getName() + " was destroyed by " + p.getName() + "s " + playerCard.getName());
        enemyBattleField.remove(enemyCard);
    }
}