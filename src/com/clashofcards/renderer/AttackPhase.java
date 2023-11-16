package com.clashofcards.renderer;

import com.clashofcards.Ai;
import com.clashofcards.Card;
import com.clashofcards.Player;
import com.apps.util.Prompter;
import com.clashofcards.utils.BattleFieldDisplay;
import com.clashofcards.utils.Helper;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class AttackPhase {
    BattleFieldDisplay displayer = new BattleFieldDisplay();

    public void playerAttackPhase(Player player, Ai enemy, List<Card> playerBattleField, List<Card> enemyBattleField) {
        displayer.updateBattleField(enemyBattleField,playerBattleField,player);

        // ATTACK METHOD IF THEY HAVE CARD,

        playCard(player.getDeck(), playerBattleField); // player plays a card

        displayer.updateBattleField(enemyBattleField,playerBattleField,player);

        boolean valid = false;
        while (!valid) {
            System.out.println("Console clear test");
            Prompter prompter = new Prompter(new Scanner(System.in));
            String name = prompter.prompt("Please enter your name: ");
            if (name.length() < 20) {
                valid = true;
            }
        }
    }

    public void playCard(List<Card> playerCards, List<Card> playerBattlefield) {
        if (!playerCards.isEmpty()) {
            boolean valid = false;
            while (!valid) {
                System.out.println();
                Prompter prompter = new Prompter(new Scanner(System.in));
                String cardToPlay = prompter.prompt("Pick a card to play from your hand or pass (Enter the ID): ");

                // Use an iterator to avoid ConcurrentModificationException
                Iterator<Card> iterator = playerCards.iterator();
                while (iterator.hasNext()) {
                    Card card = iterator.next();
                    if (card.getIndex().equals(Integer.valueOf(cardToPlay))) {
                        iterator.remove(); // Remove the card from playerCards
                        playerBattlefield.add(card);
                        System.out.println("You played: " + card.getName());
                        Helper.delayGame(2);
                        valid = true;
                    }
                }

                if (!valid) {
                    System.out.println("Invalid card ID");
                }
            }
        }
    }
}