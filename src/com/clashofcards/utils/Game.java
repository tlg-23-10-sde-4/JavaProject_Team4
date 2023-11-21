package com.clashofcards.utils;

import com.apps.util.Prompter;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;

import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * All static helper methods for game functionality
 *
 * example:
 * Game.playerDrawNotification(player, enemy, true);
 * This would notify the player they cannot draw a card
 *
 */
public class Game {
    // DelayGame for given amount of seconds
    public static void delayGame(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);  // Sleep for determined amount of seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Calculates the results of two cards battling
    public static void calculateBattleResults(Card enemyCard, Card playerCard, Player p, Player e, List<Card> playerBattleField, List<Card> enemyBattleField, boolean enemyIsAttacking) {
        int damage = Math.max(0, playerCard.getStrength() - enemyCard.getToughness());

        switch (compareStrengthAndToughness(playerCard, enemyCard, enemyIsAttacking)) {
            case 1: // playerCard strength > enemyCard toughness
                handleVictory(playerCard, enemyCard, p, e, enemyBattleField, playerBattleField, enemyIsAttacking);
                break;
            case 0: // playerCard strength = enemyCard toughness
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, enemyIsAttacking);
                break;
            case -1: // playerCard strength < enemyCard toughness
                handleDefeat(playerCard, enemyCard, p, e, enemyBattleField, playerBattleField, enemyIsAttacking, damage);
                break;
            case -2: // Both cards are destroyed
                handleDraw(playerCard, enemyCard, p, e, enemyBattleField, playerBattleField, enemyIsAttacking);
                break;
        }
    }

    // Compare strength and toughness and return the proper case
    private static int compareStrengthAndToughness(Card playerCard, Card enemyCard, boolean enemyIsAttacking) {
        int strength1 = playerCard.getStrength();
        int toughness1 = playerCard.getToughness();
        int strength2 = enemyCard.getStrength();
        int toughness2 = enemyCard.getToughness();

        if(enemyIsAttacking) {
            if (strength2 >= toughness1 && strength1 >= toughness2) {
                return -2; // card1 wins
            } else if (strength2 > toughness1) {
                return -1;
            } else if(strength2 == toughness1) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (strength1 >= toughness2 && strength2 >= toughness1) {
                return -2; // card1 wins
            } else if (strength1 > toughness2) {
                return -1;
            } else if(strength1 == toughness2) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    // Victory condition with damage being dealt
    private static void handleVictory(Card playerCard, Card enemyCard, Player p, Player e, List<Card> enemyBattlefield, List<Card> playerBattlefield, boolean enemyIsAttacking) {
        if (enemyIsAttacking) {
            notifyCardStats(playerCard, enemyCard, p, e);
            System.out.println(" " + p.getName() + " blocked all damage from " + e.getName() + " with " + playerCard.getName() + "\n");
            Game.delayGame(2);
            if(playerCard.getStrength() > enemyCard.getToughness()) {
                System.out.println(" Enemy card was destroyed by " + p.getName() + "'s " + playerCard.getName());
                enemyBattlefield.remove(enemyCard);
                delayGame(2);
            }
        } else {
            notifyCardStats(enemyCard, playerCard, e, p);
            System.out.println(" " + e.getName() + " blocked all damage from " + p.getName() + " with " + enemyCard.getName() + "\n");
            delayGame(2);
            if(enemyCard.getStrength() > playerCard.getToughness()) {
                System.out.println(" Your card was destroyed by " + e.getName() + "'s " + enemyCard.getName());
                playerBattlefield.remove(playerCard);
                delayGame(2);
            }
        }
    }

    // Defeat condition with card being destroyed and damage dealt
    // This should only happen when the other card's stats are all lower than the attacking card stats
    private static void handleDefeat(Card playerCard, Card enemyCard, Player p, Player e, List<Card> enemyBattleField, List<Card> playerBattleField, boolean enemyIsAttacking, int damage) {
        blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, enemyIsAttacking);
        if (enemyIsAttacking) {
            p.setHealth(p.getHealth() - damage);
            System.out.println(" " + e.getName() + " hit " + p.getName() + " for " + damage + " points");
        } else {
            e.setHealth(e.getHealth() - damage);
            System.out.println(" " + p.getName() + " hit " + e.getName() + " for " + damage + " points");
            delayGame(2);
        }
    }

    // Handle the case where both card's attacks are greater or equal to the opposing cards defense
    private static void handleDraw(Card playerCard, Card enemyCard, Player p, Player e, List<Card> enemyBattleField, List<Card> playerBattleField, boolean enemyIsAttacking) {
        notifyCardStats(playerCard, enemyCard, p, e);
        delayGame(2);
        System.out.println(" Both cards are destroyed." + "\n");
        delayGame(2);
        if (enemyIsAttacking) {
            if(enemyCard.getStrength() > playerCard.getToughness()) {
                int damage = enemyCard.getStrength() - playerCard.getToughness();
                p.setHealth(p.getHealth() - damage);
                System.out.println(" " + p.getName() + " was hit for " + damage + " damage!");
                Game.delayGame(2);
            }
        } else {
            if(playerCard.getStrength() > enemyCard.getToughness()) {
                int damage = playerCard.getStrength() - enemyCard.getToughness();
                e.setHealth(e.getHealth() - damage);
                System.out.println(" " + e.getName() + " was hit for " + damage + " damage!");
                Game.delayGame(2);
            }
        }
        playerBattleField.remove(playerCard);
        enemyBattleField.remove(enemyCard);
    }

    // Print a message where the card was blocked and destroyed
    private static void blockAndDestroy(Card enemyCard, Card playerCard, Player p, Player e, List<Card> enemyBattleField, List<Card> playerBattleField, boolean enemyIsAttacking) {
        if (enemyIsAttacking) {
            notifyCardStats(playerCard, enemyCard, p, e);

            System.out.println(" " + p.getName() + "'s " + playerCard.getName() + " was destroyed by " + e.getName() + "s " + enemyCard.getName());
            delayGame(2);
            playerBattleField.remove(playerCard);
        } else {
            System.out.println(" " + e.getName() + " blocked " + p.getName() + " with " + enemyCard.getName());
            delayGame(2);

            notifyCardStats(playerCard, enemyCard, p, e);

            System.out.println(" " + e.getName() + "'s " + enemyCard.getName() + " was destroyed by " + p.getName() + "'s " + playerCard.getName());
            enemyBattleField.remove(enemyCard);
            delayGame(2);
        }
    }

    // Notify the stats of each card
    private static void notifyCardStats(Card playerCard, Card enemyCard, Player p, Player e) {
        System.out.println(" " + p.getName() + "'s " + playerCard.getName() + " has a strength of " + playerCard.getStrength());
        delayGame(2);
        System.out.println(" " + p.getName() + "'s " + playerCard.getName() + " has a defense of " + playerCard.getToughness());
        delayGame(2);
        System.out.println(" " + e.getName() + "'s " + enemyCard.getName() + " has an strength of " + enemyCard.getStrength());
        delayGame(2);
        System.out.println(" " + e.getName() + "'s " + enemyCard.getName() + " has an defense of " + enemyCard.getToughness());
        delayGame(2);
    }

    // Handle players drawing a card from their deck
    public static Card handleCardDraw(List<Card> hand, List<Card> deck) {
        hand.add(deck.get(0));
        deck.remove(0);
        return hand.get(6);
    }

    // Handle direct damage in case of no blocking cards or choice not to block
    public static boolean handleDirectDamage(Player enemy, Card selectedCard) {
        Game.delayGame(2);
        System.out.println(" " + enemy.getName() + " took " + selectedCard.getStrength() + " damage!");
        enemy.setHealth(enemy.getHealth() - selectedCard.getStrength());
        Game.delayGame(2);
        return true;
    }

    // Below are static notifications we can print across our application
    public static void playerDrawNotification(Player p, Player e, boolean isUserTurn) {
        String user;
        if(isUserTurn) {
            user = p.getName();
        } else {
            user = e.getName();
        }

        System.out.println(" " + user + " cannot draw a card" + "\n");
        System.out.println();
        Game.delayGame(2);
        System.out.println(" " + user + "'s hand is either full or the deck is empty");
        Game.delayGame(2);
    }

    public static void playerNonCardPlayNotification(Player p, Player e, boolean isUserTurn) {
        String user;
        if(isUserTurn) {
            user = p.getName();
        } else {
            user = e.getName();
        }

        System.out.println(" " + user + "'s play a card phase skipped" + "\n");
        Game.delayGame(2);
        System.out.println(" " + user + " has no cards to play or you have the max amount of cards on the battlefield(7)" + "\n");
        Game.delayGame(2);
    }

    public static void playerEmptyBattlefieldNotification(Player player, Player enemy, List<Card> playerBattleField, List<Card> enemyBattleField, BattleFieldDisplay displayer, boolean isPlayerTurn) {
        String name;
        if(isPlayerTurn) {
            name = player.getName();
        } else {
            name = enemy.getName();
        }
        System.out.println(" " + name + "'s battlefield is empty, no cards to attack with");
        Game.delayGame(3);
        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
    }

    public static void initializeGameNotification() {
        Game.delayGame(1);
        System.out.println();

        System.out.println(" The decks have been shuffled...");
        Game.delayGame(1);
        System.out.println();

        System.out.println(" Everyone has drawn 7 cards...");
        Game.delayGame(1);
        System.out.println();

        System.out.println(" The game is about to begin!");
        Game.delayGame(1);
    }
}