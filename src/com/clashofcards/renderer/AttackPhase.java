package com.clashofcards.renderer;

import com.clashofcards.models.Card;
import com.clashofcards.models.Player;
import com.apps.util.Prompter;
import com.clashofcards.utils.BattleFieldDisplay;
import com.clashofcards.utils.Game;

import java.util.List;
import java.util.Scanner;

public class AttackPhase {
   private final BattleFieldDisplay displayer = new BattleFieldDisplay();
   private final Prompter prompter = new Prompter(new Scanner(System.in));

    public void playerAttackPhase(Player player, Player enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        // Notify the player it's their turn, update the battlefield
        notifyAndUpdate(player, enemy, playerBattleField, enemyBattleField);

        // Actual attack phase
        if (!playerBattleField.isEmpty()) {
            boolean wantsToAttack = promptAttack(player); // Prompt the player to attack
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy); // Update and clear the screen
            if (wantsToAttack) {
                player.attackWithCard(playerBattleField, player, enemyBattleField, enemy, prompter); // Actual attack method
                displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy); // Update and clear the screen
            }
        } else {
            Game.playerEmptyBattlefieldNotification(player, enemy, playerBattleField, enemyBattleField, displayer, true);
        }

        // Play a card
        if (!player.getHand().isEmpty() && playerBattleField.size() < 7) {
            System.out.println(" Your turn to play a card" + "\n");
            Game.delayGame(2);
            player.playCard(prompter, playerBattleField); // player plays a card
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
        } else {
            Game.playerNonCardPlayNotification(player, enemy, true); // Notify why then cannot play a card
        }

        // Draw a card
        if(player.getHand().size() < 7 && !player.getDeck().isEmpty()) {
            player.drawCard();
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
        } else {
            Game.playerDrawNotification(player, enemy, true); // Notify why they cannot draw
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
    }

    // Notify the player it's their turn, update the battlefield
    private void notifyAndUpdate(Player player, Player enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        System.out.println(" " + player.getName() + "'s turn!");
        Game.delayGame(2);

        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy); // We will use this to clear and update the battlefield

        System.out.println(" Start of your attack phase" + "\n");
        Game.delayGame(2);
    }


    // Ask the user if the wish to attack, they might not want to every turn
    private boolean promptAttack(Player p) {
        boolean valid = false;
        boolean wantsToAttack = false;
        while (!valid) {
            String attack = prompter.prompt(" Would you like to attack (y/n)?: ").trim().toLowerCase();
            if (attack.equals("y") || attack.equals("n")) {
                if (attack.equals("y")) {
                    System.out.println();
                    System.out.println(" " + p.getName() + " chose to attack!");
                    Game.delayGame(2);
                    wantsToAttack = true;
                    valid = true;
                } else {
                    Game.delayGame(2);
                    System.out.println(" " + p.getName() + " chose not to attack");
                    Game.delayGame(2);
                    valid = true;
                }
            } else {
                System.out.println(" Invalid input. Please enter 'y' or 'n'.");
            }
        }
        return wantsToAttack;
    }
}