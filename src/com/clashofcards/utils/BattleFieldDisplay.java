package com.clashofcards.utils;

import com.apps.util.Console;
import com.clashofcards.models.Ai;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;

import java.util.List;

public class BattleFieldDisplay {
    Card card = new Card();

    private void displayEnemyBattleField(List<Card> enemyBattleField) {
        System.out.println();
        if (enemyBattleField.isEmpty()) {
            PrintBattlefieldText.printEnemyBattlefield();
            System.out.println("----Enemy Has not played any cards yet----");
        } else {
            PrintBattlefieldText.printEnemyBattlefield();
            card.printCards(enemyBattleField);
        }
    }

    private void showPlayerBattlefield(List<Card> playerBattlefield) {
        if (playerBattlefield.isEmpty()) {
            System.out.println("---Your battlefield is empty---");
            printPlayerBattleFieldText();
        } else {
            card.printCards(playerBattlefield);
            printPlayerBattleFieldText();
        }
    }

    private static void printPlayerBattleFieldText() {
        PrintBattlefieldText.printYourBattleField();
        System.out.println();
    }

    private void showPlayerHand(List<Card> playerHand) {
        card.printCards(playerHand);
        PrintBattlefieldText.printYourHand();
        System.out.println();
    }

    private void displayGameStats(Player p, Player e) {
        System.out.println("                                                                                          " +
                p.getName() + "s Health: " + p.getHealth() + " " +  e.getName() + "s  Health: " + e.getHealth());
    }

    public void updateBattleField(List<Card> enemyBattleField, List<Card> playerBattleField, Player player, Player enemy) {
        Console.clear();
        displayEnemyBattleField(enemyBattleField);
        displayGameStats(player, enemy);
        showPlayerBattlefield(playerBattleField);
        showPlayerHand(player.getHand());
    }
}
