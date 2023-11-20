package com.clashofcards.renderer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Welcome {
    private static List<String> welcome = null;

    public static void welcomeBanner() {
        try {
            welcome = Files.readAllLines(Path.of("images/Welcome.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String line : welcome) {
            System.out.println(line);
        }
        System.out.println();
        System.out.println(" It is highly recommended you play in full screen (and zoom out slightly)," +
                " if you haven't done so, you should do that now!" + "\n");
    }
}
