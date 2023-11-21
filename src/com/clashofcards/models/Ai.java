package com.clashofcards.models;

import com.apps.util.Prompter;
import com.clashofcards.utils.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Ai extends Player {
    public Ai() {super();}

    // Custom AI blocking method, should return card from the AI's battlefield
    @Override
    public Card enemyBlock(List<Card> enemyBattleField, Card playerAttackingCard) {
        Card chosenCard = null;
        List<Card> eligibleCards = enemyBattleField.stream()
                .filter(card -> card.getToughness() < playerAttackingCard.getStrength())
                .collect(Collectors.toList());
        for (Card card : enemyBattleField) {
            if (card.getToughness() >= playerAttackingCard.getStrength()) {
                chosenCard = card;
            } else {
                if (!eligibleCards.isEmpty()) {
                    // If there are eligible cards, choose a random one
                    Random random = new Random();
                    int randomIndex = random.nextInt(eligibleCards.size());
                    chosenCard = eligibleCards.get(randomIndex);
                }
            }
        }
        return chosenCard;
    }

    @Override
    public void playCard(Prompter prompter, List<Card> enemyBattleField) {
        System.out.println(" " + getName() + "'s turn to play a card");
        Game.delayGame(2);
        List<Card> eligibleCards = new ArrayList<>(getHand());
        Card chosenCard = null;

        // Get a random card from the Ai's hand
        Random random = new Random();
        int randomIndex = random.nextInt(eligibleCards.size());
        chosenCard = eligibleCards.get(randomIndex);

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
        Card chosenCard = null;

        // Ai picks random card
        Random random = new Random();
        int randomIndex = random.nextInt(enemyBattleField.size());
        chosenCard = enemyBattleField.get(randomIndex);

        // We do not need to call super here, but we will do it to remind us this is coming from the parent class
        super.handleAiAttack(playerBattlefield, p, enemyBattleField, enemy, prompter, chosenCard);
    }

    // Draw card
    @Override
    public void drawCard() {
        Game.handleCardDraw(getHand(), getDeck());
        System.out.println(" " + getName() + " drew a card");
        Game.delayGame(3);
    }
}