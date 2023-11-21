package com.clashofcards.models;

import com.apps.util.Prompter;
import com.clashofcards.utils.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class SmarterAi extends Player {

    //  ctor
    public SmarterAi() {
        super();
    }


    //  Business methods:
    // Custom AI blocking method
    @Override
    public Card enemyBlock(List<Card> enemyBattleField, Card playerAttackingCard) {
        Card chosenCard = this.bestByToughness(enemyBattleField);

        if (chosenCard.getToughness() > playerAttackingCard.getStrength()) {
            return chosenCard;
        } else if (Objects.equals(chosenCard.getToughness(), playerAttackingCard.getStrength())) {
            if (getHealth() <= playerAttackingCard.getStrength()) {
                return chosenCard;
            }
        }
        return chosenCard;
    }

    @Override
    public void playCard(Prompter prompter, List<Card> enemyBattleField) {
        System.out.println(" " + getName() + "'s turn to play a card" + "\n");
        Game.delayGame(2);
        //  TODO: if low health, take best by toughness
        Card chosenCard = bestByStrength(getHand());

        // We do not need to call super here, but we will do it to remind us this is coming from the parent class
        super.handleAiCardToPlay(enemyBattleField, chosenCard);
    }


    @Override
    public void attackWithCard(List<Card> playerBattlefield,
                               Player p,
                               List<Card> enemyBattleField,
                               Player enemy,
                               Prompter prompter)
    {
        Card chosenCard = this.bestByStrength(enemyBattleField);

        // We do not need to call super here, but we will do it to remind us this is coming from the parent class
        super.handleAiAttack(playerBattlefield, p, enemyBattleField, enemy, prompter, chosenCard);
    }


    // Choose best card by strength
    private Card bestByStrength(List<Card> list) {
        Card bestCard = list.get(0);
        for (Card card : list) {
            if (card.getStrength() > bestCard.getStrength()) {
                bestCard = card;
            }
        }
        return bestCard;
    }

    // Choose best card by toughness
    private Card bestByToughness(List<Card> list) {
        Card bestCard = list.get(0);
        for (Card card : list) {
            if (card.getToughness() > bestCard.getToughness()) {
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