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

    //  business method to show full deck to end user
    public void print(){
        for(String line : getImageLines()){
            System.out.println(line);
        }
        System.out.printf("#%s %s   ✊ %s ❤ %s\n", getIndex(), getName(), getStrength(), getToughness());
    }

    // ❤ ✊
    public void printHand(List<Card> list){
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

    @Override
    public String toString() {
        return String.format("| ID:%s  %s  S:%s  T:%s |", getIndex(), getName(), getStrength(), getToughness());
    }
}