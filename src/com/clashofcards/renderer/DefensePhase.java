package com.clashofcards.renderer;

import com.apps.util.Prompter;
import com.clashofcards.models.Ai;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;
import com.clashofcards.utils.BattleFieldDisplay;
import com.clashofcards.utils.Game;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class DefensePhase {
    BattleFieldDisplay displayer = new BattleFieldDisplay();
    Prompter prompter = new Prompter(new Scanner(System.in));

    public void playerDefensePhase(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy); // We will use this to clear and update the battlefield

        System.out.println(" " + enemy.getName() + "'s attack phase begins!");
        Game.delayGame(2);

        // Enemies actual attack phase
        if (!enemyBattleField.isEmpty()) {
            enemy.attackWithCard(playerBattleField, player, enemyBattleField, enemy, prompter);
        } else {
            System.out.println(" " + enemy.getName() + " has no cards to attack with!");
            Game.delayGame(2);
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);

        // Enemy plays a card
        if (!enemy.getHand().isEmpty() && enemyBattleField.size() < 7) {
            enemy.playCard(prompter, enemyBattleField); // player plays a card
            Game.delayGame(2);
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
        } else {
            Game.playerNonCardPlayNotification(player, enemy, false);
        }

        // Enemy draw a card
        if (enemy.getHand().size() < 7 && !enemy.getDeck().isEmpty()) {
            System.out.println(" " + enemy.getName() + " is drawing a card");
            Game.delayGame(2);
            enemy.drawCard();
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
        } else {
            Game.playerDrawNotification(player, enemy, false); // Notify why they cannot draw
        }
    }
}