package com.clashofcards.utils;

import com.apps.util.Console;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;

import java.util.List;

public class BattleFieldDisplay {
    private void displayEnemyBattleField(List<Card> enemyBattleField) {
        System.out.println();
        if (enemyBattleField.isEmpty()) {
            PrintBattlefieldText.printEnemyBattlefield();
            System.out.println("----Enemy Has not played any cards yet----");
        } else {
            PrintBattlefieldText.printEnemyBattlefield();
            for (Card card : enemyBattleField) {
                card.print();
            }
        }
        System.out.println("\n" + "\n" + "\n");
    }

    private void showPlayerBattlefield(List<Card> playerBattlefield) {
        if (playerBattlefield.isEmpty()) {
            System.out.println("---Your battlefield is empty---");
            PrintBattlefieldText.printYourBattleField();
        } else {
            for (Card card : playerBattlefield) {
                card.print();
            }
            System.out.println();
            PrintBattlefieldText.printYourBattleField();
            System.out.println("\n"+"\n"+"\n");
        }
    }

    private void showPlayerHand(List<Card> playerHand) {
        for (Card card : playerHand) {
            card.print();
        }
        PrintBattlefieldText.printYourHand();
    }

    public void updateBattleField(List<Card> enemyBattleField, List<Card> playerBattleField, Player player) {
        Console.clear();
        displayEnemyBattleField(enemyBattleField);
        showPlayerBattlefield(playerBattleField);
        showPlayerHand(player.getDeck());
    }
}
