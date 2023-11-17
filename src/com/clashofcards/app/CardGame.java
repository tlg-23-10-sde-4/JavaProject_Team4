package com.clashofcards.app;

import com.apps.util.Prompter;
import com.clashofcards.models.Ai;
import com.clashofcards.models.Card;
import com.clashofcards.models.Player;
import com.clashofcards.renderer.AttackPhase;
import com.clashofcards.renderer.DefensePhase;
import com.clashofcards.renderer.Welcome;

import com.apps.util.Console;
import com.clashofcards.utils.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {
    private Player player = new Player();
    private Ai enemy = new Ai();
    private List<Card> playerBattleField = new ArrayList<>();
    private List<Card> enemyBattleField = new ArrayList<>();
    private final AttackPhase attackPhase = new AttackPhase();
    private final DefensePhase defensePhase = new DefensePhase();

    // CTOR's
    public CardGame() {}

    public CardGame(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        this.player = player;
        this.enemy = enemy;
        this.playerBattleField = playerBattleField;
        this.enemyBattleField = enemyBattleField;
    }


    // TODO: ADD GAME LOGIC IN ORDER HERE
    public void startGame() {
//        weclcome();
        intializeGame();
        Console.clear();

        // The methods below are for testing
        playerBattleField.add(player.getDeck().get(0));
        player.getDeck().remove(0);
        enemyBattleField.add(enemy.getDeck().get(0));
        enemyBattleField.add(enemy.getDeck().get(1));
        enemy.getDeck().remove(0);
        enemy.getDeck().remove(1);


//        while (player.getHealth() > 0 || enemy.getHealth() > 0) {
        attackPhase.playerAttackPhase(player, enemy, playerBattleField, enemyBattleField);
//        defensePhase.playerDefensePhase(player, (Ai) enemy, playerBattleField, enemyBattleField);
//        }
    }


    private void intializeGame() {
        System.out.println(Welcome.welcomeBanner());
        boolean validInput = false;
        while (!validInput) {
            Prompter prompter = new Prompter(new Scanner(System.in));
            String input = prompter.prompt("Enter your name (No more than 10 Characters)");
            if (input.length() <= 10) {
                player.setName(input);
                validInput = true;
            }
        }

        enemy.setName("Jimbo");

        Console.clear();

        Game.delayGame(1);

        System.out.println();
        System.out.println("Everyone has drawn 10 cards");
        System.out.println();

        Game.delayGame(2);
    }

    private void weclcome() {
        System.out.println(Welcome.welcomeBanner());
        System.out.println();
        // TODO: Add logic to display instructions for the game or start the game
    }
}