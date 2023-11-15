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
        playCard(player.getDeck(),playerBattleField);

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
        // TODO: Add logic to display enemy battle field
        for (Card card : enemyBattleField) {
            System.out.println(card + " ");
        }
        System.out.println();
    }

    private void showPlayerBattlefield(List<Card> playerBattlefield) {
        // TODO: Add Logic to show player battlefield
    }

    private void showPlayerHand(List<Card> playerHand) {
        // TODO: Add logic to display players hand
        int cardsPrinted = 0;
        for (Card card : playerHand) {
            System.out.print(card + " ");
            cardsPrinted++;
            if (cardsPrinted % 5 == 0) {  // Change 5 to the desired number of cards per line
                System.out.println();  // Move to the next line after every 5 cards
            }
        }
        if (cardsPrinted % 5 != 0) {
            System.out.println();  // Move to the next line if the last line is not complete
        }
    }
}
