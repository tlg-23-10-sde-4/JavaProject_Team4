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
        displayer.updateBattleField(enemyBattleField, playerBattleField, player);

        if (!playerBattleField.isEmpty()) {
            boolean wantsToAttack = promptAttack(player);
            displayer.updateBattleField(enemyBattleField, playerBattleField, player);
            if(wantsToAttack) {
                player.attackWithCard(playerBattleField,player,enemyBattleField, enemy, prompter);
                displayer.updateBattleField(enemyBattleField, playerBattleField, player);
            }
        }

        if (!player.getDeck().isEmpty()) {
            player.playCard(prompter, playerBattleField); // player plays a card
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player);
    }


    private boolean promptAttack(Player p) {
        boolean valid = false;
        boolean wantsToAttack = false;
        while (!valid) {
            String attack = prompter.prompt("Would you like to attack (y/n)?");
            if (attack.equals("y") || attack.equals("n")) {
                if (attack.equals("y")) {
                    System.out.println(p.getName() + " chose to attack!");
                    Game.delayGame(2);
                    wantsToAttack = true;
                    valid = true;
                } else {
                    Game.delayGame(2);
                    System.out.println(p.getName() + " chose not to attack");
                    valid = true;
                }
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
        return wantsToAttack;
    }
}