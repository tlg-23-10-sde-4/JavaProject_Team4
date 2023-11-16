package com.clashofcards.utils;

import com.clashofcards.Card;
import com.clashofcards.Player;
import com.clashofcards.renderer.AttackPhase;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {
    // DelayGame for given amount of seconds
    public static void delayGame(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);  // Sleep for 2 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}