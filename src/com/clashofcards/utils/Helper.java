package com.clashofcards.utils;

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