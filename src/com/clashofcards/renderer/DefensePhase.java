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
    public void playerDefensePhase(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {

        System.out.println(" " + enemy.getName() + "s attack phase begins!");
        Game.delayGame(2);

        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);

        if (!enemyBattleField.isEmpty()) {
            enemy.attackWithCard(playerBattleField, player, enemyBattleField, enemy, prompter);
        } else {
            System.out.println(" " + enemy.getName() + " has no cards to play!");
            Game.delayGame(2);
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);

        if (!enemy.getHand().isEmpty()) {
            Game.delayGame(1);
            enemy.playCard(prompter, enemyBattleField); // player plays a card
            Game.delayGame(2);
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player, enemy);
    }
}