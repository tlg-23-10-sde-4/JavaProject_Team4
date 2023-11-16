package com.clashofcards.app;

import com.apps.util.Prompter;
import com.clashofcards.Ai;
import com.clashofcards.Card;
import com.clashofcards.Player;
import com.clashofcards.renderer.AttackPhase;
import com.clashofcards.renderer.DefensePhase;
import com.clashofcards.renderer.Welcome;

import com.apps.util.Console;
import com.clashofcards.utils.Helper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CardGame {
    private Player player = new Player();
    private Ai enemy = new Ai();
    private List<Card> playerBattleField = new ArrayList<>();
    private List<Card> enemyBattleField = new ArrayList<>();
    private final Card card = new Card();
    private final AttackPhase attackPhase = new AttackPhase();
    private final DefensePhase defensePhase = new DefensePhase();

    // CTOR's
    public CardGame() throws IOException {
    }

    public CardGame(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) throws IOException {
        this.player = player;
        this.enemy = enemy;
        this.playerBattleField = playerBattleField;
        this.enemyBattleField = enemyBattleField;
    }


    // TODO: ADD GAME LOGIC IN ORDER HERE
    public void startGame() {
        intializeGame();
        Console.clear();

//        while (player.getHealth() > 0 || enemy.getHealth() > 0) {
        attackPhase.playerAttackPhase(player, enemy, playerBattleField, enemyBattleField);
//        defensePhase.playerDefensePhase(player, (Ai) enemy, playerBattleField, enemyBattleField);
            // Defense phase here
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

        player.setDeck(card.getDeck());
        player.setHealth(20);
        enemy.setDeck(card.getDeck());
        enemy.setHealth(20);
        Integer h = enemy.getHealth();

        Console.clear();

        Helper.delayGame(1);

        System.out.println("Everyone has drawn 10 cards");
    }



    private void displayUpdatedStats() {
        // TODO: Logic to display the udpated stats
    }

    private void updatePlayersStats(int damage) {
        // TODO: LOGIC TO UPDATE THE PLAYER AND AI STATS
        displayUpdatedStats();
    }

    // Getters Setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Ai enemy) {
        this.enemy = enemy;
    }

    public List<Card> getPlayerBattleField() {
        return playerBattleField;
    }

    public void setPlayerBattleField(List<Card> playerBattleField) {
        this.playerBattleField = playerBattleField;
    }

    public List<Card> getEnemyBattleField() {
        return enemyBattleField;
    }

    public void setEnemyBattleField(List<Card> enemyBattleField) {
        this.enemyBattleField = enemyBattleField;
    }
}