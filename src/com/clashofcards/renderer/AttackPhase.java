package com.clashofcards.renderer;

import com.clashofcards.Ai;
import com.clashofcards.Card;
import com.clashofcards.Player;
import com.apps.util.Prompter;
import java.util.List;
import java.util.Scanner;


public class AttackPhase {

    public void playerAttackPhase(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        displayEnemyBattleField(enemyBattleField);
        showPlayerBattlefield(playerBattleField);
        showPlayerHand(player.getDeck());
        playCard(player.getDeck(), playerBattleField);

        boolean valid = false;
        while (!valid) {
            System.out.println("Console clear test");
            Prompter prompter = new Prompter(new Scanner(System.in));
            String name = prompter.prompt("Please enter your name: ");
            if (name.length() < 20) {
                valid = true;
            }
        }
    }

    public void playCard(List<Card> playerCards, List<Card> playerBattlefield) {
        if (!playerCards.isEmpty()) {
            boolean valid = false;
            while (!valid) {
                System.out.println();
                Prompter prompter = new Prompter(new Scanner(System.in));
                String cardToPlay = prompter.prompt("Pick a card to play from your hand or pass (Enter the ID): ");
                for (Card card : playerCards) {
                    if (card.getIndex().equals(Integer.valueOf(cardToPlay))) {
                        playerCards.remove(card);
                        playerBattlefield.add(card);
                        valid = true;
                    } else {
                        System.out.println("Invalid card ID");
                    }
                }
            }
        }
    }

    private void displayEnemyBattleField(List<Card> enemyBattleField) {
        System.out.println();
        if (enemyBattleField.isEmpty()) {
            PrintBattlefieldText.printEnemyBattlefield();
            System.out.println("----Enemy Has not played any cards yet----");
        } else {
            PrintBattlefieldText.printEnemyBattlefield();
            for (Card card : enemyBattleField) {
                System.out.println(card + " ");
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
                System.out.print(card + "   ");
            }
            PrintBattlefieldText.printYourBattleField();
            System.out.println("\n"+"\n"+"\n"+"\n");
        }
    }

    private void showPlayerHand(List<Card> playerHand) {
        for (Card card : playerHand) {
            System.out.print(card + "   ");
        }
        PrintBattlefieldText.printYourHand();
    }
}