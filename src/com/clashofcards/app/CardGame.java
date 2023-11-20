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
import java.nio.file.Files;
import java.nio.file.Path;
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
    Prompter prompter = new Prompter(new Scanner(System.in));

    // CTOR's
    public CardGame() {
    }

    public CardGame(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        this.player = player;
        this.enemy = enemy;
        this.playerBattleField = playerBattleField;
        this.enemyBattleField = enemyBattleField;
    }


    public void startGame() {
        weclcome(); // Welcome the player
        intializeGame(); // Initialize the game

        // Run the game loop until conditions are met
        while (isGameOngoing()) {
            defensePhase.playerDefensePhase(player, enemy, playerBattleField, enemyBattleField);
            attackPhase.playerAttackPhase(player, enemy, playerBattleField, enemyBattleField);
        }

        Console.clear();

        endGame(); // End the game
    }


    private void intializeGame() {
        Console.clear();
        Welcome.welcomeBanner();
        System.out.println();
        boolean validInput = false;
        while (!validInput) {
            String input = prompter.prompt(" Enter your name when you're ready to begin(No more than 10 Characters): ");
            if (input.length() <= 10) {
                player.setName(input);
                validInput = true;
            } else {
                System.out.println(" That name is to long, please enter a name less than 10 Characters");
            }
        }

        enemy.setName("Jimbo");

        Game.delayGame(1);
        System.out.println();

        System.out.println(" The decks have been shuffled...");
        Game.delayGame(2);
        System.out.println();

        System.out.println(" Everyone has drawn 7 cards...");
        Game.delayGame(2);
        System.out.println();

        System.out.println(" The game is about to begin!");
        Game.delayGame(2);
    }

    private void weclcome() {
        Welcome.welcomeBanner();

        List<String> instructions = null;
        try {
            instructions = Files.readAllLines(Path.of("Data/instructions.txt"));
        } catch (Exception e) {
            System.out.println("File was not found");
            e.printStackTrace();
        }


        boolean userWantsInstructions = askForInstructions();

        if (userWantsInstructions) {
            if (instructions != null) {
                for (String line : instructions) {
                    System.out.println(line);
                }
            }

            // Ask the user to press a key or input something to continue
            prompter.prompt(" Press Enter to continue...");
        }
    }

    private boolean askForInstructions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" Would you like to see instructions? (Y/N): ");
        String input = scanner.next().toUpperCase();
        return input.equals("Y");
    }

    private boolean isGameOngoing() {
        return player.getHealth() > 0 && enemy.getHealth() > 0;
    }

    private void endGame() {
        try {
            List<String> gameOverText = Files.readAllLines(Path.of("images/GameOver.txt"));
            List<String> loserText = Files.readAllLines(Path.of("images/Loser.txt"));
            List<String> winnerText = Files.readAllLines(Path.of("images/Winner.txt"));

            for (String line : gameOverText) {
                System.out.println(line);
            }
            Game.delayGame(2);

            Console.clear();

            if (player.getHealth() <= 0) {
                for (String line : loserText) {
                    System.out.println(line);
                }
            } else {
                for (String line : winnerText) {
                    System.out.println(line);
                }
            }

            Game.delayGame(6);

            System.out.println(" Would you like to play again?");
            // TODO: Implement Logic to restart the game
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // GETTERS AND SETTERS FOR TESTING ONLY
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Ai getEnemy() {
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

    public AttackPhase getAttackPhase() {
        return attackPhase;
    }

    public DefensePhase getDefensePhase() {
        return defensePhase;
    }
}