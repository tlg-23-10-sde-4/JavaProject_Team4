package com.clashofcards.renderer;

import com.apps.util.Prompter;
import com.clashofcards.Ai;
import com.clashofcards.Card;
import com.clashofcards.Player;
import com.clashofcards.utils.BattleFieldDisplay;
import com.clashofcards.utils.Helper;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DefensePhase{

        BattleFieldDisplay displayer = new BattleFieldDisplay();
        Prompter prompter = new Prompter(new Scanner(System.in));
    private Object Player;

    public void aiBlockPhase (Player Ai, Player enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
            displayer.updateBattleField(enemyBattleField, playerBattleField, Ai);

            if (!playerBattleField.isEmpty()) {
                boolean wantsToAttack = promptBlock(playerBattleField, Ai, enemyBattleField);
                displayer.updateBattleField(enemyBattleField, playerBattleField, Ai);
                if (wantsToAttack) {
                    blockWithCard(playerBattleField, Ai, enemyBattleField, Player, enemy);
                }
            }

            displayer.updateBattleField(enemyBattleField, playerBattleField, Ai);
            playCard(Ai.getDeck(), playerBattleField);
            displayer.updateBattleField(enemyBattleField, playerBattleField, Ai);
        }

    private void blockWithCard(List<Card> playerBattleField, com.clashofcards.Player ai, List<Card> enemyBattleField, Object player, com.clashofcards.Player enemy) {
    }

    private void playCard(List<Card> playerCards, List<Card> playerBattleField) {
            if (!playerCards.isEmpty()) {
                boolean valid = false;

                while (!valid) {
                    System.out.println();
                    String cardToPlay = prompter.prompt("Pick a card to play from your hand or pass (Enter the ID): ");

                    // Use an iterator to avoid ConcurrentModificationException
                    for (Card card : playerCards) {
                        if (card.getIndex().equals(Integer.valueOf(cardToPlay))) {
                            playerCards.remove(card); // Remove the card from playerCards
                            playerBattleField.add(card);
                            System.out.println("You played: " + card.getName());
                            Helper.delayGame(2);
                            valid = true;
                            break;
                        }
                    }

                    if (!valid) {
                        System.out.println("Invalid card ID");
                    }
                }
            }
        }

        private void blockWithCard(List<Card> playerBattleField, Player player, List<Card> enemyBattleField, Ai enemy) {
            System.out.println("Select a card to block with:");

            boolean valid = false;

            while (!valid) {
                String cardIndexStr = prompter.prompt("Enter the ID of the card you want to attack with from your battlefield: ");

                try {
                    int cardIndex = Integer.parseInt(cardIndexStr);
                    for (Card selectedCard : playerBattleField) {
                        if (selectedCard.getIndex().equals(cardIndex)) {
                            System.out.println(player.getName() + " chose to attack with: " + selectedCard.getName());
                            Helper.delayGame(1);

                            if (!enemyBattleField.isEmpty()) {
                                Card enemyBlockingCard = enemyBlock(enemyBattleField, selectedCard);
                                if (enemyBlockingCard != null) {
                                    calculateBattleResults(enemyBlockingCard, selectedCard, player, enemy, playerBattleField, enemyBattleField);
                                }

                                System.out.println(enemy.getName() + " has no cards to block with");
                                Helper.delayGame(2);
                            }

                            valid = true;
                        }
                    }

                    if (!valid) {
                        System.out.println("Invalid card ID. Please enter a valid ID.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
        }

        private boolean promptBlock(List<Card> playerBattleField, Player Ai, List<Card> enemyBattleField) {
            boolean valid = false;
            boolean wantsToblock = false;

            while (!valid) {
                String block = prompter.prompt("Would you like to block (y/n)?");

                if (block.equals("y") || block.equals("n")) {
                    valid = true;

                    if (block.equals("y")) {
                        System.out.println(Ai.getName() + " chose to block!");
                        Helper.delayGame(2);
                        wantsToblock = true;
                    } else {
                        Helper.delayGame(2);
                        System.out.println(Ai.getName() + " chose not to block");
                    }
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }

            return wantsToblock;
        }

        private Card enemyBlock(List<Card> enemyBattleField, Card playerAttackingCard) {
            Card chosenCard = null;

            List<Card> eligibleCards = enemyBattleField.stream()
                    .filter(card -> card.getToughness() >= playerAttackingCard.getStrength())
                    .collect(Collectors.toList());

            for (Card card : enemyBattleField) {
                if (card.getToughness() >= playerAttackingCard.getStrength()) {
                    chosenCard = card;
                    break;
                }
            }

            if (!eligibleCards.isEmpty()) {
                // If there are eligible cards, choose a random one
                Random random = new Random();
                int randomIndex = random.nextInt(eligibleCards.size());
                chosenCard = eligibleCards.get(randomIndex);
            }

            return chosenCard;
        }

        private void calculateBattleResults(Card enemyCard, Card playerCard, Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
            if (enemyCard.getToughness() > playerCard.getStrength()) {
                System.out.println(enemy.getName() + "'s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
                System.out.println(player.getName() + "'s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
                System.out.println(enemy.getName() + " blocked all damage from " + player.getName() + "'s " + enemyCard.getName());
            } else if (enemyCard.getToughness().equals(playerCard.getStrength())) {
                blockAndDestroy(enemyCard, playerCard, player, enemy, enemyBattleField);
            } else if (enemyCard.getToughness() < playerCard.getStrength()) {
                blockAndDestroy(enemyCard, playerCard, player, enemy, enemyBattleField);
                enemy.setHealth(enemy.getHealth() - (playerCard.getStrength() - enemyCard.getToughness()));
                Helper.delayGame(1);
                System.out.println(player.getName() + " hit " + enemy.getName() + " for " + (playerCard.getStrength() - enemyCard.getToughness()) + " points");
            }
        }

        private static void blockAndDestroy(Card enemyCard, Card playerCard, Player player, Ai enemy, List<Card> enemyBattleField) {
            System.out.println(player.getName() + "'s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
            Helper.delayGame(1);
            System.out.println(enemy.getName() + "'s " + enemyCard.getName() + " has defense of " + enemyCard.getToughness());
            Helper.delayGame(1);
            System.out.println(enemy.getName() + " blocked " + player.getName() + " with " + enemyCard.getName());
            Helper.delayGame(1);
            System.out.println(enemy.getName() + "'s " + enemyCard.getName() + " was destroyed by " + player.getName() + "'s " + playerCard.getName());
            enemyBattleField.remove(enemyCard);
        }
        /*
        public static void main(String[] args) {
            // Implementation of the main method can be added if needed

            class BattleFieldDisplay {
                // Implementation of BattleFieldDisplay can be added if needed
            }

            class Prompter {
                // Implementation of Prompter can be added if needed
            }

            class Helper {
                void delayGame(int seconds) {
                    // Implementation of delayGame can be added if needed
                }
            }

            class Card {
                // Implementation of Card class can be added if needed
            }

            class Player {
                // Implementation of Player class can be added if needed
            }

            class AI {
                // Implementation of AI class can be added if needed
            }
        }

         */
