package com.clashofcards.utils;/*
 * Class to test Card and it's methods
 */

import com.clashofcards.app.CardGame;
import org.junit.Test;

import java.util.List;

public class PrintArtTest extends CardGame {
    String dName = "Doofenshmirtz";
    String jName = "Jimbo;";


    @Test
    public void showArtD() {
        printArt(dName);
    }

    @Test
    public void showArtJ() {
        printArt(jName);
    }
}