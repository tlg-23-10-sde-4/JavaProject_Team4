package com.clashofcards.app;

import com.apps.util.Prompter;
import com.clashofcards.models.*;
import com.clashofcards.renderer.AttackPhase;
import com.clashofcards.renderer.DefensePhase;
import com.clashofcards.renderer.Welcome;

import com.apps.util.Console;
import com.clashofcards.utils.Game;
import com.clashofcards.utils.Art;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {
    private boolean gameIsOngoing = false;
    private Player player = new User();
    private Player enemy;
    private List<Card> playerBattleField = new ArrayList<>();
    private List<Card> enemyBattleField = new ArrayList<>();
    private final AttackPhase attackPhase = new AttackPhase();
    private final DefensePhase defensePhase = new DefensePhase();
    private final Prompter prompter = new Prompter(new Scanner(System.in));


    // CTOR's
    public CardGame() {
    }

    public void startGame() {
        while (true) {
            Console.clear();
            Welcome.welcomeBanner();

            String play = prompter.prompt(" Would you like to play the game? (y/n): ").trim().toLowerCase();
            if (!play.equals("n") && !play.equals("y")) {
                System.out.println(" Invalid input, please enter 'y' or 'n'");
            } else if (play.equals("n")) {
                break;
            } else {
                askForInstructions();
                initializeGame(); // Initialize the game

                // Run the game loop until conditions are met
                while (!gameIsOngoing) {
                    boolean gameOver = checkGameEndingConditions();
                    if (gameOver) {
                        break;
                    }
                    defensePhase.playerDefensePhase(player, enemy, playerBattleField, enemyBattleField);
                    boolean gameOver2 = checkGameEndingConditions();
                    if (gameOver2) {
                        break;
                    }
                    attackPhase.playerAttackPhase(player, enemy, playerBattleField, enemyBattleField);
                }

                Console.clear();

                endGame(); // End the game
            }
        }
    }

    private boolean checkGameEndingConditions() {
        return player.getHealth() <= 0 || enemy.getHealth() <= 0;
    }


    // This is a good commit
    private void initializeGame() {
        Console.clear();
        System.out.println();

        boolean validInput = false;
        while (!validInput) {
            String input = prompter.prompt(" Enter your name when you're ready to begin (in between 1-10 characters): ");
            if (input.length() <= 10 && input.length() > 0) {
                player.setName(input);
                validInput = true;
            } else {
                System.out.println(" That name is to long, please enter a name less than 10 characters");
            }
        }

        //  now we will ask for "difficulty"
        validInput = false;
        while (!validInput) {
            System.out.println();
            String aiChoice = prompter.prompt(" Would you like to play Jimbo or Doofenshmirtz (j/d)?: ").trim().toLowerCase();
            if (aiChoice.equals("j")) {
                enemy = new Ai();
                enemy.setName("Jimbo");
                printArt("Jimbo");
                validInput = true;
            } else if (aiChoice.equals("d")) {
                enemy = new SmarterAi();
                enemy.setName("Doofenshmirtz");
                printArt("Doofenshmirtz");
                validInput = true;
            } else {
                System.out.println(" Invalid input, choose again!");
            }
        }

        Game.initializeGameNotification();

        System.out.println();
        prompter.prompt(" Press enter and the game will begin...");
    }

    private void askForInstructions() {
        System.out.println();
        List<String> instructions = null;
        try {
            instructions = Files.readAllLines(Path.of("Data/instructions.txt"));
        } catch (Exception e) {
            System.out.println("File was not found");
            e.printStackTrace();
        }

        boolean validInput  = false;
        while (!validInput) {
            System.out.println();
            String input = prompter.prompt(" Would you like to see instructions (y/n)?: ").toLowerCase().trim();
            if(input.equals("y")) {
                for(String line : instructions) {
                    System.out.println(line);
                }
                System.out.println();
                prompter.prompt(" Press enter and to continue...");
            } else if (input.equals("n")) {
                validInput = true;
            } else {
                System.out.println(" invalid input, please enter 'y' or 'n'");
            }
        }
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
            Console.clear();

            playerBattleField.clear();
            enemyBattleField.clear();
            player.resetPlayer();
            enemy.resetPlayer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //  method to print avatars of AI
    public void printArt(String artName){
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Path.of("images/" + artName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Console.clear();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            System.out.println();
        }
        System.out.println();
        prompter.prompt("   Press Enter if you ready...");
        Console.clear();
    }

    // GETTERS AND SETTERS FOR TESTING ONLY


    public boolean isGameIsOngoing() {
        return gameIsOngoing;
    }

    public void setGameIsOngoing(boolean gameIsOngoing) {
        this.gameIsOngoing = gameIsOngoing;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public Prompter getPrompter() {
        return prompter;
    }
}