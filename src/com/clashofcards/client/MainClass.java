package com.clashofcards.client;

import com.clashofcards.CardGame;

public class MainClass {
    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        while(cardGame.getPlayer().getHeatlh() > 0 || cardGame.getEnemy().getHealth() > 0) {
            // TODO: game Logic and methods here
        }
    }
}