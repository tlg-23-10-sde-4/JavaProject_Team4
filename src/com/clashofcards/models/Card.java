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

    //  Deck / Hand / Graveyard vars
    private static final String dataFilePath = "Data/Cards.csv";
    public List<Card> deck;
    public List<Card> hand;
    public List<Card> graveyard;


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




    //  newInstance of deck
    public List<Card> getDeck() {
        List<Card> newDeck = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(dataFilePath));

            Collections.shuffle(lines);

            for (int i = 0; i < 10; i++) {
                String[] tokens = lines.get(i).split(",");
                int id = Integer.parseInt(tokens[0].trim());
                String name = tokens[1].trim();
                int attack = Integer.parseInt(tokens[2].trim());
                int defense = Integer.parseInt(tokens[3].trim());

                newDeck.add(new Card(id, name, attack, defense));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newDeck;
    }


    //  business method to show full deck to end user
    public void show() {
        System.out.println(deck);
    }

    public void print(){
        for(String line : getImageLines()){
            System.out.println(line);
        }
        System.out.printf("#%s %s   ✊ %s ❤ %s\n", getIndex(), getName(), getStrength(), getToughness());
    }
    // ❤ ✊
    public void printHand(List <Card> list){
        for(Card card : list){
            for(String line : card.getImageLines()){
                System.out.println(line);
            }
        }
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

    //    @Override
//    public String toString(){
//        return String.format("\n#: %s - %s   %s/%s",getIndex(),getName(),getStrength(),getToughness());
//    }


    //    @Override
//    public String toString() {
//        return String.format("| #: %s %s S: %s T: %s |", getIndex(), getName(), getStrength(), getToughness());
//    }
    @Override
    public String toString() {
        return String.format(
                         "---------------------" +
                        "| #: %s     %s   |" +
                        "|                   |" +
                        "|                   |" +
                        "|                   |" +
                        "|                   |" +
                        "|                   |" +
                        "| S: %s    |    T: %s |" +
                        "---------------------\n",
                getIndex(), getName(), getStrength(), getToughness());
    }
}