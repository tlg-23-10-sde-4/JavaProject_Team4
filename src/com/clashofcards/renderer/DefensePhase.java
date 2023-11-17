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

        displayer.updateBattleField(enemyBattleField, playerBattleField, player);

        if (!enemyBattleField.isEmpty()) {
            enemy.attackWithCard(playerBattleField, player, enemyBattleField, enemy, prompter);
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player);

        if (!enemy.getHand().isEmpty()) {
            Game.delayGame(2);
            enemy.playCard(prompter, enemyBattleField); // player plays a card
            Game.delayGame(2);
        }

        displayer.updateBattleField(enemyBattleField, playerBattleField, player);
    }
}