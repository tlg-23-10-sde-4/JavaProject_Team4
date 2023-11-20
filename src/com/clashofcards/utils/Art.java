package com.clashofcards.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Art {


    public void printArt(String artName){
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Path.of("images/" + artName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            System.out.println();
        }
        Game.delayGame(3);
        System.out.println();
    }

}