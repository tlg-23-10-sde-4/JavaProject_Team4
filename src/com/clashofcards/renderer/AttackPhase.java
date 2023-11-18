package com.clashofcards.renderer;

import com.clashofcards.models.Ai;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;
import com.apps.util.Prompter;
import com.clashofcards.utils.BattleFieldDisplay;
import com.clashofcards.utils.Game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;


public class AttackPhase {
    BattleFieldDisplay displayer = new BattleFieldDisplay();
    Prompter prompter = new Prompter(new Scanner(System.in));

    public void playerAttackPhase(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        System.out.println(" " + player.getName() + "s attack phase Begins!");
        Game.delayGame(2);

        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);

        if (!playerBattleField.isEmpty()) {
            boolean wantsToAttack = promptAttack(player);
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
            if(wantsToAttack) {
                player.attackWithCard(playerBattleField,player,enemyBattleField, enemy, prompter);
                displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
            }
        }

        if (!player.getHand().isEmpty()) {
            player.playCard(prompter, playerBattleField); // player plays a card
        } else {
            System.out.println(player.getName() + " has no cards to play");
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
    }


    private boolean promptAttack(Player p) {
        boolean valid = false;
        boolean wantsToAttack = false;
        while (!valid) {
            String attack = prompter.prompt(" Would you like to attack (y/n)?").trim().toLowerCase();
            if (attack.equals("y") || attack.equals("n")) {
                if (attack.equals("y")) {
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