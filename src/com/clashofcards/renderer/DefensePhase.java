package com.clashofcards.renderer;

import com.apps.util.Prompter;
import com.clashofcards.models.Ai;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;
import com.clashofcards.utils.BattleFieldDisplay;
import com.clashofcards.utils.Game;

import java.util.List;
import java.util.Scanner;

public class DefensePhase {
    BattleFieldDisplay displayer = new BattleFieldDisplay();
    Prompter prompter = new Prompter(new Scanner(System.in));

    public void playerDefensePhase(Player player, Player enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {

        // Notify of the enemies turn, update the battlefield
        notifyAndUpdate(player, enemy, playerBattleField, enemyBattleField);

        // Enemies actual attack phase
        if (!enemyBattleField.isEmpty()) {
            enemy.attackWithCard(playerBattleField, player, enemyBattleField, enemy, prompter);
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
        } else {
            Game.playerEmptyBattlefieldNotification(player, enemy, playerBattleField, enemyBattleField, displayer, false);
        }

        // Enemy plays a card
        if (!enemy.getHand().isEmpty() && enemyBattleField.size() < 7) {
            enemy.playCard(prompter, enemyBattleField); // player plays a card
            Game.delayGame(2);
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
        } else {
            Game.playerNonCardPlayNotification(player, enemy, false); // Notify when they cant play a card
        }

        // Enemy draw a card
        if (enemy.getHand().size() < 7 && !enemy.getDeck().isEmpty()) {
            System.out.println(" " + enemy.getName() + " is drawing a card" + "\n");
            Game.delayGame(2);
            enemy.drawCard();
            displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
        } else {
            Game.playerDrawNotification(player, enemy, false); // Notify why they cannot draw
        }
    }

    // Notify of the enemies turn, update the battlefield
    private void notifyAndUpdate(Player player, Player enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy); // We will use this to clear and update the battlefield

        System.out.println(" It is " + enemy.getName() + "'s turn! " + "\n");
        Game.delayGame(2);
    }
}