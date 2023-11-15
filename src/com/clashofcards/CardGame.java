package com.clashofcards;

import java.util.ArrayList;
import java.util.List;

public class CardGame {
    Player player = new Player();
    Player enemy = new Ai();
    List<Card> playerBattleField = new ArrayList<>();
    List<Card> enemyBattleField = new ArrayList<>();

    // CTOR's
    public CardGame() {}

    public CardGame(Player player, Player enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        this.player = player;
        this.enemy = enemy;
        this.playerBattleField = playerBattleField;
        this.enemyBattleField = enemyBattleField;
    }

    // Business Methods/Game Logic
    private static void playerAttackPhase(Player player, Ai enemy) {
        // TODO: add game logic for player attack phase
    }

    private static void playerDefensePhase(Player player, Ai enemy) {
        // TODO: add logic for player defense phase
    }

    private static void initalizeCards() {
        // TODO: initalize player decks if we cannot make this work in the card class
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