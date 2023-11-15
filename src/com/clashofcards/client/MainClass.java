package com.clashofcards.client;

import com.clashofcards.app.CardGame;

public class MainClass {
    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        cardGame.startGame();
//        while(cardGame.getPlayer().getHealth() > 0 || cardGame.getEnemy().getHealth() > 0) {
//            // TODO: game Logic and methods here
//        }
    }
}