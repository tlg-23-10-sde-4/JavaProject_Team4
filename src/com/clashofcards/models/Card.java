package com.clashofcards.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {
    //  Card vars
    private Integer index;
    private String name;
    private Integer strength;
    private Integer toughness;
    private List<String> imageLines;

    //  constructors for Card
    public Card() {
    }

    public Card(Integer index, String name, Integer strength, Integer toughness) {
        setIndex(index);
        setName(name);
        setStrength(strength);
        setToughness(toughness);
        try {
            this.imageLines = Files.readAllLines(Path.of("Data/" + getName() + ".txt"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void printCards(List<Card> list){
        //  loop repeats i times, where "i" is rows in each image
        String heartSymbol = "H";
        String fistSymbol = "A";
        int imageRowsNum = 8;
        for (int i = 0; i < list.get(0).getImageLines().size(); i++){

            //  loop repeats number of card times
            for(Card card : list){
                System.out.print(card.getImageLines().get(i)+"\t");
            }

            System.out.println();
        }
        for (Card card : list){
            System.out.printf("id:%s       %s %s %s %s \t", card.getIndex(),fistSymbol,card.getStrength(),heartSymbol,card.getToughness());
        }
        System.out.println();
    }

    //  accessor method
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getToughness() {
        return this.toughness;
    }

    public void setToughness(Integer toughness) {
        this.toughness = toughness;
    }

    public List<String> getImageLines() {
        return imageLines;
    }

    public void setImageLines(List<String> imageLines) {
        this.imageLines = imageLines;
    }
}