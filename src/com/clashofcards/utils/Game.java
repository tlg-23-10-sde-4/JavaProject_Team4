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
            TimeUnit.SECONDS.sleep(seconds);  // Sleep for 2 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void calculateBattleResults(Card enemyCard, Card playerCard, Player p, Ai e, List<Card> playerBattleField, List<Card> enemyBattleField, boolean enemyIsAttacking) {
        if (enemyIsAttacking) {
            if (playerCard.getToughness() > enemyCard.getStrength()) {
                System.out.println(p.getName() + "s " + playerCard.getName() + " has a defense of " + playerCard.getToughness());
                System.out.println(e.getName() + "s " + enemyCard.getName() + " has an attack of " + enemyCard.getStrength());
                System.out.println(p.getName() + " blocked all damage from " + e.getName() + " with " + playerCard.getName());
            } else if (playerCard.getToughness().equals(enemyCard.getStrength())) {
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, true);
            } else if (playerCard.getToughness() < enemyCard.getStrength()) {
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, true);
                p.setHealth(p.getHealth() - (enemyCard.getStrength() - playerCard.getToughness()));
                delayGame(2);
                System.out.println(e.getName() + " hit " + p.getName() + " for " + (enemyCard.getStrength() - playerCard.getToughness()) + " points");
            }
        } else {
            if (enemyCard.getToughness() > playerCard.getStrength()) {
                System.out.println(e.getName() + "s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
                System.out.println(p.getName() + "s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
                System.out.println(e.getName() + " blocked all damage from " + p.getName() + " with " + enemyCard.getName());
            } else if (enemyCard.getToughness().equals(playerCard.getStrength())) {
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, false);
            } else if (enemyCard.getToughness() < playerCard.getStrength()) {
                blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField, playerBattleField, false);
                e.setHealth(e.getHealth() - (playerCard.getStrength() - enemyCard.getToughness()));
                delayGame(1);
                System.out.println(p.getName() + " hit " + e.getName() + " for " + (playerCard.getStrength() - enemyCard.getToughness()) + " points");
            }
        }
    }

    private static void blockAndDestroy(Card enemyCard, Card playerCard, Player p, Ai e, List<Card> enemyBattleField, List<Card> playerBattleField, boolean enemyIsAttacking) {
        if (enemyIsAttacking) {
            System.out.println(e.getName() + "s " + enemyCard.getName() + " has an attack of " + enemyCard.getStrength());
            delayGame(1);

            System.out.println(p.getName() + "s " + playerCard.getName() + " has a defense of " + playerCard.getToughness());
            delayGame(1);

            System.out.println(p.getName() + " blocked " + e.getName() + " with " + playerCard.getName());
            delayGame(1);

            System.out.println(p.getName() + "s " + playerCard.getName() + " was destroyed by " + e.getName() + "s " + enemyCard.getName());
            playerBattleField.remove(enemyCard);
        } else {
            System.out.println(p.getName() + "s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
            delayGame(1);

            System.out.println(e.getName() + "s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
            delayGame(1);

            System.out.println(e.getName() + " blocked " + p.getName() + " with " + enemyCard.getName());
            delayGame(1);

            System.out.println(e.getName() + "s " + enemyCard.getName() + " was destroyed by " + p.getName() + "s " + playerCard.getName());
            enemyBattleField.remove(enemyCard);
        }
    }
}