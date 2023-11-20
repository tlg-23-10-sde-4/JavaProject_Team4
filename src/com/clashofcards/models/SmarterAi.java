package com.clashofcards.models;

import com.apps.util.Prompter;
import com.clashofcards.utils.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class SmarterAi extends Player{

    //  ctor
    public SmarterAi() {
        super();
    }


    //  Business methods:
    // Custom AI blocking method
    @Override
    public Card enemyBlock(List<Card> enemyBattleField, Card playerAttackingCard) {
        Card chosenCard = bestByToughness(enemyBattleField);

        if (chosenCard.getToughness() > playerAttackingCard.getStrength()){
            return chosenCard;
        }   else if (Objects.equals(chosenCard.getToughness(), playerAttackingCard.getStrength())){
            if(getHealth() <= playerAttackingCard.getStrength()){
                return chosenCard;
            }
        }
        return chosenCard;
    }

    @Override
    public void playCard(Prompter prompter, List<Card> enemyBattleField) {
        System.out.println(" " + getName() + "'s turn to play a card");
        Game.delayGame(2);
        //  TODO: if low health, take best by toughness
        Card chosenCard = bestByStrength(getHand());

        // Ensure its not null
        if (chosenCard != null) {
            getHand().remove(chosenCard);
            enemyBattleField.add(chosenCard);
            Game.delayGame(2);
            System.out.println(" " + getName() + " played " + chosenCard.getName());
            Game.delayGame(2);
        } else {
            Game.delayGame(2);
            System.out.println(" " + getName() + "has no cards to play!");
            Game.delayGame(2);
        }
    }

    @Override
    public void attackWithCard(List<Card> playerBattlefield, Player p, List<Card> enemyBattleField, Player enemy, Prompter prompter) {
        Card chosenCard = bestByStrength(enemyBattleField);

        boolean valid = false;
        if (chosenCard != null) {
            System.out.println(" Enemy is attacking with " + chosenCard.getName());
            Game.delayGame(2);

            if (!playerBattlefield.isEmpty()) {
                while (!valid) {
                    String blockChoice = prompter.prompt(" Would you like to block (y/n)").trim().toLowerCase();
                    if (blockChoice.equals("y") || blockChoice.equals("n")) {
                        if (blockChoice.equals("y")) {
                            String cardIndexStr = prompter.prompt(" Enter the ID of the card you'd like to block with from your battlefield: ").trim();
                            boolean cardFound = false;

                            for (Card selectedCard : playerBattlefield) {
                                if (selectedCard.getIndex().equals(Integer.valueOf(cardIndexStr))) {
                                    System.out.println(" " + p.getName() + " chose to block with: " + selectedCard.getName());
                                    Game.delayGame(2);

                                    Game.calculateBattleResults(chosenCard, selectedCard, p, enemy, playerBattlefield, enemyBattleField, true);
                                    Game.delayGame(2);
                                    valid = true;
                                    cardFound = true;
                                    break; // Exit the loop once a matching card is found
                                }
                            }

                            if (!cardFound) {
                                System.out.println(" Invalid input, please select the ID of a card from your battlefield (e.g., 13)");
                            }
                        } else {
                            System.out.println(" " + p.getName() + " chose not to block");
                            valid = Game.handleDirectDamage(p, chosenCard);
                        }
                    } else {
                        System.out.println(" Invalid input: please enter 'y' or 'n'");
                    }
                }
            } else {
                System.out.println(" " + p.getName() + " has no cards to block with");
                Game.handleDirectDamage(p, chosenCard);
            }
        }
    }

    // Choose best card by strength
    private Card bestByStrength(List<Card> list){
        Card bestCard = list.get(0);
        for (Card card : list){
            if (card.getStrength() > bestCard.getStrength()){
                bestCard = card;
            }
        }
        return bestCard;
    }

    // Choose best card by toughness
    private Card bestByToughness(List<Card> list){
        Card bestCard = list.get(0);
        for (Card card : list){
            if (card.getToughness() > bestCard.getToughness()){
                bestCard = card;
            }
        }
        return bestCard;
    }

    // Draw a card
    public void drawCard() {
        Game.handleCardDraw(getHand(), getDeck());
        System.out.println(" " + getName() + " drew a card");
        Game.delayGame(3);
    }
}