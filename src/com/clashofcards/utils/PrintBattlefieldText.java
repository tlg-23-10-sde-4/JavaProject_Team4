package com.clashofcards.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class PrintBattlefieldText {
    private static final List<String> enemyBattleFieldText;
    private static final List<String> yourBattleFieldText;
    private static final List<String> yourHandText;

    static {
        try {
            enemyBattleFieldText = readAllLines(Path.of("images/EnemyBattlefield.txt"));
            yourBattleFieldText = readAllLines(Path.of("images/YourBattlefield.txt"));
            yourHandText = readAllLines(Path.of("images/YourHand.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printEnemyBattlefield() {
        for (String line : enemyBattleFieldText) {
            System.out.println(line);
        }
    }

    public static void printYourBattleField() {
        for (String line : yourBattleFieldText) {
            System.out.println(line);
        }
    }

    public static void printYourHand() {
        for (String line : yourHandText) {
            System.out.println(line);
        }
    }


}
