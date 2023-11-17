package com.clashofcards.utils;

import com.clashofcards.app.CardGame;
import com.clashofcards.models.*;
import com.clashofcards.models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CalculateBattleResultsTest {
    private CardGame game;

    @Before
    public void init() {
        game = new CardGame();
        game.getPlayer().setName("Player");
        game.getEnemy().setName("Enemy");
        game.getPlayerBattleField().add(new Card(11, "zombie", 0, 0));
        game.getEnemyBattleField().add(new Card(12, "zombie", 5, 5));
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

        Game.calculateBattleResults(eCard,pCard,game.getPlayer(),game.getEnemy(),game.getPlayerBattleField(),game.getEnemyBattleField(), true);

        int playerHealth = game.getPlayer().getHealth();

        assertEquals(15, playerHealth);
    }
}
