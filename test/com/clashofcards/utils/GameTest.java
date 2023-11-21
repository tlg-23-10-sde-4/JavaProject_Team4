package com.clashofcards.utils;

import com.clashofcards.app.CardGame;
import com.clashofcards.models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private CardGame game;
    private Player player;
    private Player enemy;

    @Before
    public void init() {
        game = new CardGame();
        game.getPlayer().setName("Player");
        game.getPlayerBattleField().add(new Card(11, "zombie", 0, 0));
        game.getEnemyBattleField().add(new Card(12, "zombie", 5, 5));

        player = new User();
        enemy = new Ai();

        game.setEnemy(enemy);
        game.setPlayer(player);
    }

    @Test
    public void playerHealthShouldInitializeWith_twentyHealth() {
        int playerHealth = game.getPlayer().getHealth();
        assertEquals(20, playerHealth);
    }

    @Test
    public void playerHealthShouldReflect_specifiedDamageValue() {
        Card eCard = new Card(11, "zombie", 5, 5);
        Card pCard = new Card(12, "zombie", 0, 0);

        Game.calculateBattleResults(eCard, pCard, player, enemy, game.getPlayerBattleField(), game.getEnemyBattleField(), true);

        int playerHealth = game.getPlayer().getHealth();

        assertEquals(15, playerHealth);
    }

    @Test
    public void playerHealthShouldEqualFifteen_afterGameMethodCalled() {
        Card enemyCard = new Card(11, "zombie", 5, 5);

        Game.handleDirectDamage(player, enemyCard);

        assertEquals(15, player.getHealth());
    }

    @Test
    public void firstCardInPlayerDeck_shouldNotBeEqualAfterGameMethodCalled() {
        Integer firstCardIdBefore = player.getDeck().get(0).getIndex();

        Game.handleCardDraw(player.getHand(), player.getDeck());

        Integer firstCardIdAfter = player.getDeck().get(0).getIndex();

        assertNotEquals(firstCardIdBefore, firstCardIdAfter);
    }

}