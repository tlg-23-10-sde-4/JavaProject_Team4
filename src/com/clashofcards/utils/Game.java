package com.clashofcards.utils;

import com.clashofcards.models.Ai;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game {
    // DelayGame for given amount of seconds
    public static void delayGame(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);  // Sleep for determined amount of seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void calculateBattleResults(Card enemyCard, Card playerCard, Player p, Player e, List<Card> playerBattleField, List<Card> enemyBattleField, boolean enemyIsAttacking) {
        if (enemyIsAttacking) {
            if (playerCard.getToughness() > enemyCard.getStrength()) { // Case: toughness greater than attack
                System.out.println(" " + p.getName() + "'s " + playerCard.getName() + " has a defense of " + playerCard.getToughness());
                delayGame(2);
                System.out.println(" " + e.getName() + "'s " + enemyCard.getName() + " has an attack of " + enemyCard.getStrength());
                delayGame(2);
                System.out.println(" " + p.getName() + " blocked all damage from " + e.getName() + " with " + playerCard.getName());
                delayGame(2);
            } else if (playerCard.getToughness().equals(enemyCard.getStrength())) { // Case: toughness same as attack
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, true);
            } else if (playerCard.getToughness() < enemyCard.getStrength()) { // Case: toughness less than attack
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, true);
                p.setHealth(p.getHealth() - (enemyCard.getStrength() - playerCard.getToughness()));
                delayGame(2);
                System.out.println(" " + e.getName() + " hit " + p.getName() + " for " + (enemyCard.getStrength() - playerCard.getToughness()) + " points");
                Game.delayGame(2);
            }
        } else {
            if (enemyCard.getToughness() > playerCard.getStrength()) { // Case: toughness greater than attack
                Game.delayGame(2);
                System.out.println(" " + e.getName() + "'s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
                Game.delayGame(2);
                System.out.println(" " + p.getName() + "'s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
                Game.delayGame(2);
                System.out.println(" " + e.getName() + " blocked all damage from " + p.getName() + " with " + enemyCard.getName());
                Game.delayGame(2);
            } else if (enemyCard.getToughness().equals(playerCard.getStrength())) { // Case: toughness same as attack
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, false);
            } else if (enemyCard.getToughness() < playerCard.getStrength()) { // Case: toughness less than attack
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, false);
                e.setHealth(e.getHealth() - (playerCard.getStrength() - enemyCard.getToughness()));
                System.out.println(" " + p.getName() + " hit " + e.getName() + " for " + (playerCard.getStrength() - enemyCard.getToughness()) + " points");
                delayGame(2);
            }
        }
    }

    private static void blockAndDestroy(Card enemyCard, Card playerCard, Player p, Player e, List<Card> enemyBattleField, List<Card> playerBattleField, boolean enemyIsAttacking) {
        if (enemyIsAttacking) {
            System.out.println(" " + e.getName() + "'s " + enemyCard.getName() + " has an attack of " + enemyCard.getStrength());
            delayGame(2);

            System.out.println(" " + p.getName() + "'s " + playerCard.getName() + " has a defense of " + playerCard.getToughness());
            delayGame(2);

            System.out.println(" " + p.getName() + " blocked " + e.getName() + " with " + playerCard.getName());
            delayGame(2);

            System.out.println(" " + p.getName() + "'s " + playerCard.getName() + " was destroyed by " + e.getName() + "s " + enemyCard.getName());
            playerBattleField.remove(playerCard);
            delayGame(2);
        } else {
            System.out.println(" " + p.getName() + "'s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
            delayGame(2);

            System.out.println(" " + e.getName() + "'s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
            delayGame(2);

            System.out.println(" " + e.getName() + " blocked " + p.getName() + " with " + enemyCard.getName());
            delayGame(2);

            System.out.println(" " + e.getName() + "'s " + enemyCard.getName() + " was destroyed by " + p.getName() + "'s " + playerCard.getName());
            enemyBattleField.remove(enemyCard);
            delayGame(2);
        }
    }

    public static Card handleCardDraw(List<Card> hand, List<Card> deck) {
        hand.add(deck.get(0));
        deck.remove(0);
        return hand.get(6);
    }

    public static void playerDrawNotification(Player p, Player e, boolean isUserTurn) {
        String user;
        if(isUserTurn) {
            user = p.getName();
        } else {
            user = e.getName();
        }

        System.out.println(" " + user + " cannot draw a card");
        Game.delayGame(2);
        System.out.println(" " + user + "'s hand is either full or your deck is empty");
        Game.delayGame(2);
    }

    public static void playerNonCardPlayNotification(Player p, Player e, boolean isUserTurn) {
        String user;
        if(isUserTurn) {
            user = p.getName();
        } else {
            user = e.getName();
        }

        System.out.println(" " + user + " cannot play a card");
        Game.delayGame(2);
        System.out.println(" "+ user + " has no cards to play or you have the max amount of cards on the battlefield(7)");
        Game.delayGame(2);
    }

    public static void initializeGameNotification() {
        Game.delayGame(1);
        System.out.println();

        System.out.println(" The decks have been shuffled...");
        Game.delayGame(2);
        System.out.println();

        System.out.println(" Everyone has drawn 7 cards...");
        Game.delayGame(2);
        System.out.println();

        System.out.println(" The game is about to begin!");
        Game.delayGame(2);
    }

    public static boolean handleDirectDamage(Player enemy, Card selectedCard) {
        Game.delayGame(2);
        System.out.println(" " + enemy.getName() + " took " + selectedCard.getStrength() + " damage!");
        enemy.setHealth(enemy.getHealth() - selectedCard.getStrength());
        Game.delayGame(2);
        return true;
    }
}