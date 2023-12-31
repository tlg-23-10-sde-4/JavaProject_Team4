package com.clashofcards.models;

import com.apps.util.Prompter;
import com.clashofcards.utils.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User extends Player{

    public User() {super();}

    @Override
    public void drawCard() {
        System.out.println(" You draw a card...");
        System.out.println();
        Game.delayGame(3);
        Card drawnCard = Game.handleCardDraw(getHand(), getDeck());
        System.out.println(" You drew a " + drawnCard.getName());
        Game.delayGame(3);
    }


    @Override
    public void playCard(Prompter prompter, List<Card> playerBattlefield) {
        boolean valid = false;
        while (!valid) {

            String cardToPlay = prompter.prompt(" Pick a card to play from your hand (Enter the ID): ");

            Iterator<Card> iterator = getHand().iterator();
            while (iterator.hasNext()) {
                Card card = iterator.next();
                if (card.getIndex().equals(Integer.valueOf(cardToPlay))) {
                    iterator.remove(); // Remove the card from playerCards
                    playerBattlefield.add(card);
                    System.out.println();
                    System.out.println(" You played: " + card.getName());
                    Game.delayGame(2);
                    valid = true;
                }
            }

            if (!valid) {
                System.out.println(" Invalid card ID, please pick a card from Your Hand");
            }
        }
    }

    @Override
    public void attackWithCard(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField, Player enemy, Prompter prompter) {
        boolean valid = false;
        while (!valid) {
            String cardIndexStr = prompter.prompt(" Enter the ID of the card you want to attack with from YOUR BATTLEFIELD: ").trim();
            try {
                int cardIndex = Integer.parseInt(cardIndexStr);
                boolean cardFound = false; // Flag to check if the selected card is found

                List<Card> cardsToRemove = new ArrayList<>(); // Collect cards to be removed

                for (Card selectedCard : playerBattlefield) {
                    if (selectedCard.getIndex().equals(cardIndex)) {
                        System.out.println();
                        System.out.println(" " + p.getName() + " chose to attack with: " + selectedCard.getName());
                        System.out.println();
                        Game.delayGame(2);

                        if (!enemyBattleField.isEmpty()) {
                            Card enemyBlockingCard = enemy.enemyBlock(enemyBattleField, selectedCard);
                            if (enemyBlockingCard != null) {
                                System.out.println(" " + enemy.getName() + " chose to block with " + enemyBlockingCard.getName());
                                Game.delayGame(2);
                                List<Card> removedCards = Game.calculateBattleResults(enemyBlockingCard, selectedCard, p, enemy, playerBattlefield, enemyBattleField, false);
                                cardsToRemove.addAll(removedCards); // Collect cards to be removed
                                valid = true;
                            } else {
                                System.out.println(" " + enemy.getName() + " chose not block or has no cards to block with");
                                valid = Game.handleDirectDamage(enemy, selectedCard);
                            }
                        } else {
                            System.out.println(" " + enemy.getName() + " has no cards to block with");
                            valid = Game.handleDirectDamage(enemy, selectedCard);
                        }

                        cardFound = true;
                    }
                }

                if (!cardFound) {
                    System.out.println(" Invalid card ID. Please enter a valid ID.");
                }

                // Remove the cards after the iteration
                for (Card card : cardsToRemove) {
                    enemyBattleField.remove(card);
                    Game.delayGame(1);
                    playerBattlefield.remove(card);
                }
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a valid number.");
            }
        }
    }
}
