package com.clashofcards.app;

import com.clashofcards.Ai;
import com.clashofcards.Card;
import com.clashofcards.Player;
import com.clashofcards.renderer.Welcome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

public class CardGame {
    private final Scanner scanner = new Scanner(System.in);
    private Player player = new Player();
    private Player enemy = new Ai();
    private List<Card> playerBattleField = new ArrayList<>();
    private List<Card> enemyBattleField = new ArrayList<>();

    // CTOR's
    public CardGame() {
    }

    public CardGame(Player player, Player enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        this.player = player;
        this.enemy = enemy;
        this.playerBattleField = playerBattleField;
        this.enemyBattleField = enemyBattleField;
    }

    public void startGame() {
        // TODO: ADD GAME LOGIC IN ORDER HERE
        intializeGame();
    }


    private void intializeGame() {
        System.out.println(Welcome.welcomeBanner());
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your name (No more than 10 Characters)");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() <= 10) {
                validInput = true;
                player.setName(input);
            }
        }
    }

    // Business Methods/Game Logic
    private static void playerAttackPhase(Player player, Ai enemy) {
        // TODO: add game logic for player attack phase
    }

    private static void playerDefensePhase(Player player, Ai enemy) {
        // TODO: add logic for player defense phase
    }

    private List<Card> initalizeCards() {
        // TODO: initalize player decks if we cannot make this work in the card class
        return new ArrayList<>(); // Placeholder
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

    public void setEnemy(Player enemy) {
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