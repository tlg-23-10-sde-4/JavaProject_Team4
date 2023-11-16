package com.clashofcards.renderer;

import java.util.List;
import java.util.Scanner;

public class DefensePhase {

    /**
     * Conducts the player's defense phase in the card game.
     *
     * @param player           The player participating in the defense phase.
     * @param enemy            The AI opponent.
     * @param playerBattlefield The current state of the player's battlefield.
     * @param enemyBattlefield  The current state of the enemy's battlefield.
     */
    public void playerDefensePhase(Player player, AI enemy, List<Card> playerBattlefield, List<Card> enemyBattlefield) {
        displayEnemyBattlefield(enemyBattlefield);
        showPlayerBattlefield(playerBattlefield);
        showPlayerHand(player.getDeck());

        boolean valid = false;
        while (!valid) {
            clearConsole();
            Prompter prompter = new Prompter(new Scanner(System.in));
            String name = prompter.prompt("Please enter your name: ");

            if (name.length() < 20) {
                valid = true;
            }
        }

        // TODO: Add logic for the interactive defense phase
        // You can add code to allow the player to choose cards to defend with or take other defensive actions.
    }

    /**
     * Displays the enemy's battlefield.
     *
     * @param enemyBattlefield The current state of the enemy's battlefield.
     */
    private void displayEnemyBattlefield(List<Card> enemyBattlefield) {
        // TODO: Implement logic to display the enemy's battlefield
        for (Card card : enemyBattlefield) {
            System.out.println(card + "\n");
        }
        System.out.println();
    }

    /**
     * Displays the player's battlefield.
     *
     * @param playerBattlefield The current state of the player's battlefield.
     */
    private void showPlayerBattlefield(List<Card> playerBattlefield) {
        // TODO: Implement logic to display the player's battlefield
    }

    /*
     * Displays the player's hand.
     *
     * @param playerHand The current state of the player's hand.
     */
    private void showPlayerHand(List<card> playerHand) {
        System.out.println("Player's Hand:");
        int cardCount = 0;
        for (Card card : playerHand) {
            System.out.print(card + " ");
            cardCount++;

            // Change the number 5 to the desired number of cards per line
            if (cardCount % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    /*
     * Clears the console screen.
     * Note: This method may not work in all environments. as jay said
     */
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
