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

    public static void calculateBattleResults(Card enemyCard, Card playerCard, Player p, Ai e, List<Card> playerBattleField, List<Card> enemyBattleField) {
        if(enemyCard.getToughness() > playerCard.getStrength()) {
            System.out.println(e.getName() + "s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
            System.out.println(p.getName() + "s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
            System.out.println(e.getName() + " blocked all damage from " + p.getName() + " with " + enemyCard.getName());
        } else if(enemyCard.getToughness().equals(playerCard.getStrength())) {
            blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField);
        } else if(enemyCard.getToughness() < playerCard.getStrength()) {
            blockAndDestroy(enemyCard, playerCard, p, e, enemyBattleField);
            e.setHealth(e.getHealth() - (playerCard.getStrength() - enemyCard.getToughness()));
            Game.delayGame(1);
            System.out.println(p.getName() + " hit " + e.getName() + " for " + (playerCard.getStrength()-enemyCard.getToughness()) + " points");
        }
    }

    private static void blockAndDestroy(Card enemyCard, Card playerCard, Player p, Ai e, List<Card> enemyBattleField) {
        System.out.println(p.getName() + "s " + playerCard.getName() + " has an attack of " + playerCard.getStrength());
        Game.delayGame(1);

        System.out.println(e.getName() + "s " + enemyCard.getName() + " has a defense of " + enemyCard.getToughness());
        Game.delayGame(1);

        System.out.println(e.getName() + " blocked " + p.getName() + " with " + enemyCard.getName());
        Game.delayGame(1);

        System.out.println(e.getName() + "s " + enemyCard.getName() + " was destroyed by " + p.getName() + "s " + playerCard.getName());
        enemyBattleField.remove(enemyCard);
    }
}