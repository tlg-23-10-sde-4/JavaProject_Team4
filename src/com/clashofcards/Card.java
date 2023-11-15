package com.clashofcards;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

    //  Deck / Hand / Graveyard vars
    private static final String dataFilePath = "Data/Cards.csv";
    public ArrayList<Card> deck;
    public ArrayList<Card> hand;
    public ArrayList<Card> graveyard;


    //  constructors for Card
    public Card(){
    }

    public Card(Integer index, String name, Integer strength, Integer toughness){
        setIndex(index);
        setName(name);
        setStrength(strength);
        setToughness(toughness);
    }

    //  newInstance of deck
    public ArrayList<Card> getDeck() {
        ArrayList<Card> newDeck = new ArrayList<>();

        try {
            List <String> lines = Files.readAllLines(Path.of(dataFilePath));
            for (String line : lines){
                String[] tokens = line.split(",");
                newDeck.add(new Card(Integer.valueOf(tokens[0]), tokens[1], Integer.valueOf(tokens[2]), Integer.valueOf(tokens[3])));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return newDeck;
    }

    //  business method to show full deck to end user
    public void show(){
        System.out.println(deck);
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
        return toughness;
    }

    public void setToughness(Integer toughness) {
        this.toughness = toughness;
    }

    @Override
    public String toString(){
        return String.format(
                "---------------------\n" +
                "| #: %s       %s |\n" +
                "|                   |\n" +
                "|                   |\n" +
                "|                   |\n" +
                "|                   |\n" +
                "|                   |\n" +
                "| S: %s    |    T: %s |\n" +
                "---------------------"
        ,getIndex(),getName(),getStrength(),getToughness());
    }
}